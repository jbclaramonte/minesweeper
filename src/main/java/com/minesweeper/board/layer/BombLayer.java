package com.minesweeper.board.layer;

public class BombLayer extends AbstractLayer {

    private int bombCount = 0;

    public BombLayer(int width, int height, int bombCount) {
        this.width = width;
        this.height = height;
        this.bombCount = bombCount;
        shuffleBomb();
    }

    public BombLayer(int[][] gridWithBombs) {
        this.width = gridWithBombs[1].length;
        this.height = gridWithBombs[0].length;
        this.grid = gridWithBombs;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.bombCount++;
            }
        }
    }

    public boolean isThereABombHere(int x, int y) {
        return getValue(x, y) == 1;
    }


    void setBomb(int x, int y) {
        setValue(x, y, 1);
    }

    void shuffleBomb() {
        initNewGrid(0);

        int remainingBomb = bombCount;
        for (int y = 0; y < height && remainingBomb > 0; y++) {
            for (int x = 0; x < width && remainingBomb > 0; x++) {
                double remainingSquares = (width * height) - ((y + 1)* width - (width - (x + 1)));
                if (Math.random() <= remainingBomb / (remainingSquares + 0.00000000001)) {
                    setBomb(x, y);
                    remainingBomb--;
                }
            }
        }

    }
}
