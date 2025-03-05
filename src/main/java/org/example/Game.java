package org.example;

import org.example.piezas.King;
import org.example.piezas.Piece;

import java.util.Collections;
import java.util.HashSet;
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
        giveName();
    }

    public void giveName() {
        Scanner sc = new Scanner(System.in);


        Screen.showBoard(board);


        System.out.println("Give me a name,you start with the white pieces ");
        player1 = sc.nextLine();

        System.out.println("Give me a name,you be the black pieces ");
        player2 = sc.nextLine();
    }

    public Coordinate giveCoordinate() {

        String coordinatePiece;
        boolean condicion1 = false;
        Coordinate c = null;
        int number;


        do {

            coordinatePiece = Entrada.giveCoordinate();
            System.out.println();
            if (coordinatePiece.length() == 2) {

                if (coordinatePiece.charAt(0) >= 'a' && coordinatePiece.charAt(0) <= 'h' || coordinatePiece.charAt(0) >= 'A' && coordinatePiece.charAt(0) <= 'H') {
                    if (coordinatePiece.charAt(1) >= 49 && coordinatePiece.charAt(1) <= 56) {

                        condicion1 = true;

                        number = Integer.parseInt(String.valueOf(coordinatePiece.charAt(1)));
                        c = new Coordinate(coordinatePiece.charAt(0), number);

                    } else {
                        System.out.println("the second digit must be a number between 1 and 8\n");

                    }

                } else {
                    System.out.println("the first digit must be between A and H  or a and j\n");
                }

            } else {
                System.out.println("you must enter a two-digit coordinate\n");

            }
            //debo llamar al metodo para mostrar donde mover las piezas

            if (!condicion1) {
                if (!turno) {
                    Screen.showBoard(board);

                } else {
                    Screen.showBoardRevert(board);

                }

                System.out.println("Enter a valid coordinate");
            }

        } while (!condicion1);

        return c;
    }

    public Coordinate givePiece() {

        boolean condicion2 = false;
        Coordinate c;
        Piece p;

        Set<Coordinate> movimientos;
        do {


            System.out.println("Which piece do you want to move ");
            c = giveCoordinate();


            p = board.getCellAt(c).getPiece();


            if (!board.getCellAt(c).isEmpty()) {
                movimientos = new HashSet<>(p.getNextMovements());

                if (!movimientos.isEmpty()) {

                    if (!turno) {
                        if (board.getCellAt(c).getPiece().getColor().equals(Piece.Color.WHITE)) {
                            condicion2 = true;
                        } else {
                            System.out.println("you must move a white piece\n");
                        }
                    } else {
                        if (board.getCellAt(c).getPiece().getColor().equals(Piece.Color.BLACK)) {
                            condicion2 = true;
                        } else {
                            System.out.println("you must move a black piece\n");
                        }
                    }

                } else {
                    System.out.println("you can't move this piece");

                }
            } else {

                System.out.println("There aren't piece in this position");
            }


            if (!condicion2) {
                if (!turno) {
                    Screen.showBoard(board);

                } else {
                    Screen.showBoardRevert(board);

                }

            }

        } while (!condicion2);

        return c;
    }

    public Coordinate giveMovement(Coordinate position) {
        Coordinate c;
        boolean condicion;

        board.highLight(board.getCellAt(position).getPiece().getNextMovements());

        do {


            System.out.println("Where do you want put the piece?");
            c = giveCoordinate();

            condicion = board.getCellAt(position).getPiece().canMoveTo(c);


            if (!condicion) {
                System.out.println("the movement must be one of those shown");


                if (!turno) {

                    Screen.showBoard(board);
                } else {
                    Screen.showBoardRevert(board);

                }

            }
            // board.removeHighLight();


        } while (!condicion);

        return c;

    }


    public Coordinate pieceSelect() {

        Coordinate c;

        if (!turno) {
            System.out.println("Player " + player1 + " :(White pieces) ");
            Screen.showBoard(board);
        } else {
            System.out.println("Player " + player2 + " :(Black pieces) ");
            Screen.showBoardRevert(board);
            //   turno=false;
        }

        c = givePiece();


        return c;

    }


    public void movePiece(Coordinate positionPiece) {
        Coordinate coordinateMovePiece;

        Piece piece_Select = board.getCellAt(positionPiece).getPiece();

        board.highLight(piece_Select.getNextMovements());

        if (!turno) {
            Screen.showBoard(board);
        } else {
            Screen.showBoardRevert(board);
        }

        // board.removeHighLight();

        coordinateMovePiece = giveMovement(piece_Select.getCell().getCoordinate());


        piece_Select.moveTo(coordinateMovePiece);

    }


    public void play() {
        Coordinate coordinatePiece;
        // do {
        while (check()) {
            coordinatePiece = pieceSelect();

            movePiece(coordinatePiece);

            board.removeHighLight();
            // }while(checkKing(coordinatePiece));

            if (!turno) {
                turno = true;
            } else {
                turno = false;
            }
        }
    }

    public boolean check() {
        return true;
    }


/*
    public boolean checkKing(Coordinate c) {
        // King kingWhite =new King(board,c,King.Type.WHITE);
        // King kingBlack=new King(board,c,King.Type.BLACK);

        if(board.getNextMovements(board.getCellAt(c).getPiece().getColor()).contains(kingWhite.getNextMovements())
        ||board.getNextMovements(board.getCellAt(c).getPiece().getColor()).contains(kingBlack.getNextMovements())) {
            return false;
        }
        return true;
    }


 */
}
