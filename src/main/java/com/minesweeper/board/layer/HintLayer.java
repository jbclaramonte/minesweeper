package com.minesweeper.board.layer;

public class HintLayer extends AbstractLayer {

    public HintLayer(int width, int height, BombLayer bombLayer) {
        this.width = width;
        this.height = height;
        initHintGrid(bombLayer);
    }

    public int getHint(int x, int y) {
        return getValue(x, y);
    }

    private void initHintGrid(BombLayer bombLayer) {
        initNewGrid(0);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                lookAroundAndUpdateBombHintIfNecessary(x, y, bombLayer);
            }
        }
    }

    private void lookAroundAndUpdateBombHintIfNecessary(int x, int y, BombLayer bombLayer) {
        for (int j = -1; j <= 1 ; j++) {
            for (int i = -1; i <= 1 ; i++) {
                boolean isNotCenter = i != 0 || j != 0;
                boolean isCoordinateInsideGrid = x+i >= 0 && x+i < width && y+j >= 0 && y+j < height;
                if (isNotCenter && isCoordinateInsideGrid && bombLayer.isThereABombHere(x+i, y+j)) {
                    incrementBombHint(x, y);
                }
            }
        }
    }

    private void incrementBombHint(int x, int y) {
        incrementValue(x, y);
    }
}
