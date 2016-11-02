package com.minesweeper.board;

import com.minesweeper.board.layer.BombLayer;
import com.minesweeper.board.layer.HintLayer;
import com.minesweeper.board.layer.TileLayer;

public class BoardGame {

    int width = 0;
    int height = 0;
    int bombCount = 0;

    BombLayer bombLayer;
    HintLayer hintLayer;
    TileLayer tileLayer;

    GameStatus gameStatus;

    public BoardGame(int width, int height, int bombCount) {
        this.width = width;
        this.height = height;
        this.bombCount = bombCount;
        this.gameStatus = GameStatus.PLAYING;
        this.tileLayer = new TileLayer(width, height);
        this.bombLayer =  new BombLayer(width, height, bombCount);
        this.hintLayer = new HintLayer(width, height, bombLayer);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setBombLayer(BombLayer bombLayer) {
        this.bombLayer = bombLayer;
    }

    public BombLayer getBombLayer() {
        return bombLayer;
    }

    public void setHintLayer(HintLayer hintLayer) {
        this.hintLayer = hintLayer;
    }

    public void setTileLayer(TileLayer tileLayer) {
        this.tileLayer = tileLayer;
    }

    public GameStatus uncover(int x, int y) {
        if (bombLayer.isThereABombHere(x, y)) {
            setGameStatus(GameStatus.GAME_OVER);
        } else {
            uncoverWithPropagation(x, y);
            if (isGameWon()) {
                setGameStatus(GameStatus.WON);
            }
        }

        return gameStatus;
    }

    private boolean isGameWon() {
        return tileLayer.getUncoveredTileCount() == bombCount;
    }

    private void uncoverWithPropagation(int x, int y) {
        if (hintLayer.getHint(x, y) == 0) {
            tileLayer.uncover(x, y);
            uncoverAround(x, y);
        } else {
            tileLayer.uncover(x, y);
        }
    }

    private void uncoverAround(int x, int y) {
        for (int j = -1; j <= 1 ; j++) {
            for (int i = -1; i <= 1 ; i++) {
                int xAround = x+i;
                int yAround = y+j;
                boolean isNotCenter = i != 0 || j != 0;
                boolean isCoordinateInsideGrid = xAround >= 0 && xAround < width && yAround >= 0 && yAround < height;
                if (isNotCenter && isCoordinateInsideGrid && tileLayer.isCovered(xAround, yAround)) {
                    uncoverWithPropagation(xAround, yAround);
                }
            }
        }
    }

    public String drawBoard(boolean withSolution) {
        StringBuffer board = new StringBuffer();

        board.append("\\x ");
        for (int x = 0 ; x < width ; x++) {
            board.append(x);
            board.append(" ");
        }
        board.append("\ny\n");

        for (int y = 0 ; y < width ; y++) {
            board.append(y);
            board.append("  ");
            for (int x = 0 ; x < width ; x++) {
                if (!withSolution && tileLayer.isCovered(x, y)) {
                    board.append("? ");
                } else if (withSolution && bombLayer.isThereABombHere(x, y)) {
                    board.append("X ");
                } else {
                    if (hintLayer.getHint(x, y) == 0) {
                        board.append(". ");
                    } else {
                        board.append(hintLayer.getHint(x, y));
                        board.append(" ");
                    }
                }
            }
            board.append("\n");
        }

        return board.toString();
    }

}
