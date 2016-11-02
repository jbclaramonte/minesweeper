package com.minesweeper.board.layer

import spock.lang.Specification

class BombLayerSpec extends Specification {

    def "when shuffling bomb on the board then all the bomb should have been set"() {
        when:
            BombLayer bombLayer = new BombLayer(10, 10, 5);

        then:
            bombLayer.grid.flatten().sum() == 5

    }

}
