package com.minesweeper.board

import com.minesweeper.board.layer.BombLayer
import com.minesweeper.board.layer.HintLayer
import com.minesweeper.board.layer.TileLayer
import spock.lang.Specification

class BoardGameSpec extends Specification {

    def "when the player uncover a bomb then the game is over"() {
        given:
            BoardGame boardGame = new BoardGame(3, 3, 1)
            boardGame.bombLayer = Mock(BombLayer)
            boardGame.bombLayer.isThereABombHere(_, _) >> true
        when:
            def gameStatus = boardGame.uncover(1, 1)

        then:
            gameStatus == GameStatus.GAME_OVER

    }


    def "case 1 : when the player uncover a square with no bomb then all the squares around with no bomb should be uncovered too"() {
        given:
            int bombCount = 1
            int[][] bombLayerGrid = [
                    [0,0,0],
                    [0,0,1],
                    [0,0,0]
            ]

        BoardGame boardGame = createBoardGameForTest(bombLayerGrid, bombCount)

        and:
            int[][] expectedTileLayerGrid = [
                    [0,0,1],
                    [0,0,1],
                    [0,0,1]
            ]

        when:
            boardGame.uncover(0,0)

        then:
            boardGame.tileLayer.grid == expectedTileLayerGrid
    }



    def "case 2 : when the player uncover a square with no bomb then all the squares around with no bomb should be uncovered too"() {
        given:
        int bombCount = 1
        int[][] bombLayerGrid = [
                [0,0,0],
                [0,0,0],
                [1,0,0]
        ]

        BoardGame boardGame = createBoardGameForTest(bombLayerGrid, bombCount)

        and:
        int[][] expectedTileLayerGrid = [
                [0,0,0],
                [0,0,0],
                [1,0,0]
        ]

        when:
        boardGame.uncover(0,0)

        then:
        boardGame.tileLayer.grid == expectedTileLayerGrid
    }




    def "case 3 : when the player uncover a square with no bomb then all the squares around with no bomb should be uncovered too"() {
        given:
        int bombCount = 3
        int[][] bombLayerGrid = [
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,1,0,1],
                [0,0,0,0,1,0],
                [0,0,0,0,0,0]
        ]

        BoardGame boardGame = createBoardGameForTest(bombLayerGrid, bombCount)

        and:
        int[][] expectedTileLayerGrid = [
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,1,1,1],
                [0,0,0,0,1,1],
                [0,0,0,0,1,1]
        ]

        when:
            boardGame.uncover(0,0)

        then:
            boardGame.tileLayer.grid == expectedTileLayerGrid
    }


    def BoardGame createBoardGameForTest(int[][] bombLayerGrid, int bombCount) {
        int width = bombLayerGrid[1].length
        int height = bombLayerGrid[0].length

        BombLayer bombLayer = new BombLayer(bombLayerGrid)

        BoardGame boardGame = new BoardGame(width, height, bombCount)
        boardGame.bombLayer = bombLayer
        boardGame.hintLayer = new HintLayer(width, height, bombLayer)
        boardGame.tileLayer = new TileLayer(width, height)

        return boardGame;
    }

}
