package com.minesweeper;

import com.minesweeper.board.BoardGame;
import com.minesweeper.board.BoardGameBuilder;
import com.minesweeper.board.GameStatus;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Largeur du plateau ?");
        int width = sc.nextInt();

        System.out.println("Hauteur du plateau ?");
        int height = sc.nextInt();

        System.out.println("Nombre de bombes disposées ?");
        int bombCount = sc.nextInt();

        BoardGame boardGame = BoardGameBuilder.build(width, height, bombCount);

        System.out.println(boardGame.drawBoard(false));

        Integer x;
        Integer y;

        while(boardGame.getGameStatus() == GameStatus.PLAYING) {
            System.out.println("Dans quelle case voulez vous jouer ?");
            System.out.print("x=");
            x = sc.nextInt();
            System.out.print("\ny=");
            y = sc.nextInt();

            boardGame.uncover(x, y);
            System.out.println(boardGame.drawBoard(false));
        }

        if (GameStatus.WON == boardGame.getGameStatus()) {
            System.out.println("Gagné !!");
        } else {
            System.out.println("BOOOMMM !!");
        }

        System.out.println(boardGame.drawBoard(true));


    }

}
