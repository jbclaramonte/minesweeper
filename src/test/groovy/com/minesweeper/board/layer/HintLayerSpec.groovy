package com.minesweeper.board.layer

import spock.lang.Specification

class HintLayerSpec extends Specification {

    def "given a bomb layout then we expect a specific hint layout : case 1"() {
        given:
            int[][] bombLayerGrid = [
                    [0, 0, 0, 0],
                    [0, 1, 1, 0],
                    [0, 0, 0, 0]
            ]
            BombLayer bombLayer = new BombLayer(bombLayerGrid)
        and:
            int[][] expectedHintLayerGrid = [
                    [1, 2, 2, 1],
                    [1, 1, 1, 1],
                    [1, 2, 2, 1]
            ]

        when:
            HintLayer hintLayer = new HintLayer(4, 3, bombLayer)

        then:
            hintLayer.grid == expectedHintLayerGrid

    }

    def "given a bomb layout then we expect a specific hint layout : case 2"() {
        given:
        int[][] bombLayerGrid = [
                [0, 0, 0, 0],
                [0, 1, 0, 0],
                [0, 0, 0, 0]
        ]

        BombLayer bombLayer = new BombLayer(bombLayerGrid)

        and:
        int[][] expectedHintLayerGrid = [
                [1, 1, 1, 0],
                [1, 0, 1, 0],
                [1, 1, 1, 0]
        ]

        when:
        HintLayer hintLayer = new HintLayer(4, 3, bombLayer)

        then:
        hintLayer.grid == expectedHintLayerGrid

    }

}
