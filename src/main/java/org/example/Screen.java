package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Screen {


    public static void showBoard(Board board) {

        Map<Coordinate, Cell> cells = new HashMap<>(board.getCells());


        String aux = "    A  B  C  D  E  F  G  H\n";

        for (int row = 1; row <= 8; row++) {
            aux += " " + row + " ";
            for (char col = 'A'; col <= 'H'; col++)
                aux += cells.get(new Coordinate(col, row));

            aux += " " + row + "\n";
        }
        aux += "    A  B  C  D  E  F  G  H";

        aux += board.getBoxPieces().toString();


        System.out.println(aux);

    }

    public static void showBoardRevert(Board board) {
        Map<Coordinate, Cell> cells = new HashMap<>(board.getCells());

        String aux = "    H  G  F  E  D  C  B  A\n";

        for (int row = 8; row >= 1; row--) {
            aux += " " + row + " ";
            for (char col = 'H'; col >= 'A'; col--)
                aux += cells.get(new Coordinate(col, row));

            aux += " " + row + "\n";
        }
        aux += "    H  G  F  E  D  C  B  A";

        aux += board.getBoxPieces().toString();

        System.out.println(aux);
    }
}
