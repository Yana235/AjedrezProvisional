package org.example;

import org.example.piezas.King;
import org.example.piezas.Piece;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;

public class Game {
    private Board board;
    private boolean turno;
    private String player1, player2;


    public Game() {
        board = new Board();
        turno = false;
        board.placePieces();
      //  giveName();
    }

    public void giveName() {
        Scanner sc = new Scanner(System.in);



        Screen.showBoard(board);


        System.out.println("Give me a name,you start with the white pieces ");
        player1 = sc.nextLine();

        System.out.println("Give me a name,you be the black pieces ");
        player2 = sc.nextLine();
    }



    public Coordinate pieceSelect() {

        Coordinate c;

        if (!turno) {
            System.out.println("Player 1 " + player1 + " :(White pieces) ");
            Screen.showBoard(board);
        } else {
            System.out.println("Player 2 " + player2 + " :(Black pieces) ");
            Screen.showBoardRevert(board);
            //   turno=false;
        }

        c = Entrada.givePiece(board, turno);

        if (!turno) {
            turno = true;
        } else {
            turno = false;
        }

        return c;

    }

    public void movePiece(Coordinate positionPiece) {
        Coordinate coordinateMovePiece;

        Piece piece_Select = board.getCellAt(positionPiece).getPiece();

        board.highLight(piece_Select.getNextMovements());

        if(!turno) {
            Screen.showBoardRevert(board);
        }else{
            Screen.showBoard(board);
        }

        board.removeHighLight();

        coordinateMovePiece = Entrada.giveMovement(board, piece_Select.getCell().getCoordinate(),turno);


        piece_Select.moveTo(coordinateMovePiece);

    }

    public void play() {
        Coordinate coordinatePiece;
        do {

            coordinatePiece = pieceSelect();

            movePiece(coordinatePiece);

        }while(checkKing(coordinatePiece));
    }



    public boolean checkKing(Coordinate c) {
        // King kingWhite =new King(board,c,King.Type.WHITE);
        // King kingBlack=new King(board,c,King.Type.BLACK);

        if(board.getNextMovements(board.getCellAt(c).getPiece().getColor()).contains(kingWhite.getNextMovements())
        ||board.getNextMovements(board.getCellAt(c).getPiece().getColor()).contains(kingBlack.getNextMovements())) {
            return false;
        }
        return true;
    }

}
