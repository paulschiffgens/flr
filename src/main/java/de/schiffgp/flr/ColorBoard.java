package de.schiffgp.flr;

public class ColorBoard {

    private int[][] data;
    private int expectedLargestRegion;
    private int rows;
    private int colums;

    public ColorBoard(int rows, int colums, int expectedLargestRegion) {
        this.data = new int[colums][rows];
        this.expectedLargestRegion = expectedLargestRegion;
        this.rows = rows;
        this.colums = colums;
    }

    public void setColor(int x, int y, int color) {
        this.data[x][y] = color;
    }

    public int getColor(int x, int y) {
        return this.data[x][y];
    }

    public int getExpectedLargestRegion() {
        return this.expectedLargestRegion;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColums() {
        return this.colums;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rows).append(" ").append(colums).append(" ").append(expectedLargestRegion).append("\n");

        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getColums(); x++) {
                sb.append(data[x][y]);
                if (x == getColums() - 1) {
                    sb.append("\n");
                } else {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }
}
