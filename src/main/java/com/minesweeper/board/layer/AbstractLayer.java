package com.minesweeper.board.layer;

public abstract class AbstractLayer {
    int width = 0;
    int height = 0;
    int grid[][];

    public int[][] getGrid() {
        return grid;
    }

    public int getValue(int x, int y) {
        return grid[y][x];
    }

    public void setValue(int x, int y, int value) {
        grid[y][x] = value;
    }

    public void incrementValue(int x, int y) {
        grid[y][x]++;
    }

    protected void initNewGrid(int initValue) {
        grid = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setValue(x, y, initValue);
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer layer = new StringBuffer();
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                layer.append(getValue(x, y));
            }
            layer.append("\n");
        }

        return layer.toString();
    }
}