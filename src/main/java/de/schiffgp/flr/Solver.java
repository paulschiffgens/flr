package de.schiffgp.flr;

public class Solver {

    private ColorBoard colorBoard;
    private int[][] nodeId;
    private int[] rootNode;
    private int[] areaSize;

    private int maxRegionSize;

    public Solver(ColorBoard colorBoard) {
        allocateMemory(colorBoard);
        initialize();
    }

    private void allocateMemory(ColorBoard colorBoard) {
        this.colorBoard = colorBoard;
        int k = colorBoard.getColums() * colorBoard.getRows();
        nodeId = new int[colorBoard.getColums()][colorBoard.getRows()];
        rootNode = new int[k];
        areaSize = new int[k];
        maxRegionSize = 1;
    }

    private void initialize() {
        int i = 0;
        for (int x = 0; x < colorBoard.getColums(); x++) {
            for (int y = 0; y < colorBoard.getRows(); y++) {
                nodeId[x][y] = i;
                rootNode[i] = i;
                areaSize[i] = 1;
                i++;
            }
        }
    }

    public int findLargestRegion() {

        for (int x = 0; x < colorBoard.getColums(); x++) {
            for (int y = 0; y < colorBoard.getRows(); y++) {

                int currentNodeColor = colorBoard.getColor(x, y);
                int currentNode = nodeId[x][y];

                if (x == 0 && y == 0) {
                    // Situation A
                    // nothing to do
                }

                if (x == 0 && y != 0) {
                    // Situation B
                    int topNodeColor = colorBoard.getColor(x, y - 1);
                    int topNode = nodeId[x][y - 1];

                    if (currentNodeColor == topNodeColor) {
                        // link root node of currentNode to root node of topNode
                        relinkRootNode(currentNode, topNode);
                    } else {
                        // nothing to do
                    }
                }

                if (x != 0 && y == 0) {
                    // Situation C
                    int leftNodeColor = colorBoard.getColor(x - 1, y);
                    int leftNode = nodeId[x - 1][y];

                    if (currentNodeColor == leftNodeColor) {
                        // link root node of currentNode to root node of leftNode
                        relinkRootNode(currentNode, leftNode);
                    } else {
                        // nothing to do
                    }
                }

                if (x != 0 && y != 0) {
                    // Situation D
                    int topNodeColor = colorBoard.getColor(x, y - 1);
                    int topNode = nodeId[x][y - 1];
                    int leftNodeColor = colorBoard.getColor(x - 1, y);
                    int leftNode = nodeId[x - 1][y];

                    if (currentNodeColor != leftNodeColor && currentNodeColor != topNodeColor) {
                        // Fall 1
                        // nothing to do
                    }

                    if (currentNodeColor != leftNodeColor && currentNodeColor == topNodeColor) {
                        // Fall 2
                        relinkRootNode(currentNode, topNode);
                    }

                    if (currentNodeColor == leftNodeColor && currentNodeColor != topNodeColor) {
                        // Fall 3
                        relinkRootNode(currentNode, leftNode);
                    }

                    if (currentNodeColor == leftNodeColor && currentNodeColor == topNodeColor) {
                        // Fall 4

                        int rootNodeOfLeftNode = getRootNode(leftNode);
                        int rootNodeOfTopNode = getRootNode(topNode);

                        if (rootNodeOfLeftNode == rootNodeOfTopNode) {
                            relinkRootNode(currentNode, leftNode);
                        } else {
                            relinkRootNode(leftNode, currentNode);
                            relinkRootNode(topNode, currentNode);
                        }
                    }
                }
            }
        }

        return maxRegionSize;
    }

    private void relinkRootNode(int nodeToRelink, int sourceNode) {
        int rootNodeOfNodeToRelink = getRootNode(nodeToRelink);
        int rootNodeOfSourceNode = getRootNode(sourceNode);

        rootNode[rootNodeOfNodeToRelink] = rootNodeOfSourceNode;
        areaSize[rootNodeOfSourceNode] += areaSize[rootNodeOfNodeToRelink];

        if (areaSize[rootNodeOfSourceNode] > maxRegionSize) {
            maxRegionSize = areaSize[rootNodeOfSourceNode];
        }

    }

    private int getRootNode(int node) {
        if (rootNode[node] == node) {
            return node;
        } else {
            return getRootNode(rootNode[node]);
        }
    }
}
