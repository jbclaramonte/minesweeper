package com.minesweeper.board;

import com.minesweeper.board.layer.BombLayer;
import com.minesweeper.board.layer.HintLayer;
import com.minesweeper.board.layer.TileLayer;

public class BoardGameBuilder {
    
    public static BoardGame build(int width, int height, int bombCount) {
        BoardGame boardGame = new BoardGame(width, height, bombCount);
        boardGame.setBombLayer(new BombLayer(width, height, bombCount));
        boardGame.setHintLayer(new HintLayer(width, height, boardGame.getBombLayer()));
        boardGame.setTileLayer(new TileLayer(width, height));
        return boardGame;
    }
    
}
