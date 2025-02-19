package org.example;

import org.example.piezas.Piece;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Entrada {


    public static Coordinate giveCoordinate(Board board, boolean turno) {
        Scanner sc = new Scanner(System.in);
        String coordinatePiece;
        boolean condicion1 = false;
        Coordinate c = null;
        int number;


        do {

            coordinatePiece = sc.next();
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

    public static Coordinate givePiece(Board board, boolean turno) {

        boolean condicion2 = false;
        Coordinate c;
        Piece p;

        Set<Coordinate> movimientos;
        do {


            System.out.println("Which piece do you want to move ");
            c = giveCoordinate(board, turno);


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


    public static Coordinate giveMovement(Board board, Coordinate position, boolean turno) {

        Coordinate c;
        boolean condicion, condicion2;
        do {


            System.out.println("Where do you want put the piece?");
            c = giveCoordinate(board, turno);

            condicion = board.getCellAt(position).getPiece().canMoveTo(c);


            if (!condicion) {
                System.out.println("the movement must be one of those shown");
                board.highLight(board.getCellAt(position).getPiece().getNextMovements());


                if (!turno) {
                    Screen.showBoardRevert(board);

                } else {
                    Screen.showBoard(board);

                }

            }
            board.removeHighLight();


        } while (!condicion);

        return c;

    }


}
