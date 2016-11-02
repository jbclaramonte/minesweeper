package com.minesweeper.board.layer;

public class TileLayer extends AbstractLayer {

    int uncoveredTileCount = 0;

    public TileLayer(int width, int height) {
        this.width = width;
        this.height = height;
        uncoveredTileCount = width * height;
        initNewGrid(1);
    }

    public boolean isCovered(int x, int y) {
        return getValue(x, y) == 1;
    }

    public void uncover(int x, int y) {
        setValue(x, y, 0);
        uncoveredTileCount--;
    }

    public int getUncoveredTileCount() {
        return uncoveredTileCount;
    }
}
