package de.schiffgp.flr;

public class Solver {

    private ColorBoard colorBoard;
    private int[][] nodeId;
    private int[] rootNode;
    private int[] areaSize;

    private int maxRegionSize;

    public Solver(ColorBoard colorBoard) {
        this.colorBoard = colorBoard;
        int k = colorBoard.getColums() * colorBoard.getRows();
        nodeId = new int[colorBoard.getColums()][colorBoard.getRows()];
        rootNode = new int[k];
        areaSize = new int[k];
        maxRegionSize = 1;

        initialize();
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
                        relinkRootNodes(currentNode, topNode);
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
                        relinkRootNodes(currentNode, leftNode);
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
                        relinkRootNodes(currentNode, topNode);
                    }

                    if (currentNodeColor == leftNodeColor && currentNodeColor != topNodeColor) {
                        // Fall 3
                        relinkRootNodes(currentNode, leftNode);
                    }

                    if (currentNodeColor == leftNodeColor && currentNodeColor == topNodeColor) {
                        // Fall 4

                        int leftNodeRoot = getRootNode(leftNode);
                        int topNodeRoot = getRootNode(topNode);

                        if (leftNodeRoot == topNodeRoot) {
                            relinkRootNodes(currentNode, leftNode);
                        } else {
                            relinkRootNodes(leftNode, currentNode);
                            relinkRootNodes(topNode, currentNode);
                        }
                    }
                }
            }
        }

        return maxRegionSize;
    }

    private void relinkRootNodes(int nodeA, int nodeB) {
        int rootNodeA = getRootNode(nodeA);
        int rootNodeB = getRootNode(nodeB);

        rootNode[rootNodeA] = rootNodeB;
        areaSize[rootNodeB] += areaSize[rootNodeA];

        if (areaSize[rootNodeB] > maxRegionSize) {
            maxRegionSize = areaSize[rootNodeB];
        }
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

    private int getRootNode(int node) {
        if (rootNode[node] == node) {
            return node;
        } else {
            return getRootNode(rootNode[node]);
        }
    }
}
