package org.example;

import org.example.cajaPiezass.DeletedPieceManagerListImp;
import org.example.piezas.*;
import java.util.stream.Collectors;
import java.util.*;


import static com.diogonunes.jcolor.Ansi.colorize;

public class Board {

    private Map<Coordinate, Cell> cells;
    private DeletedPieceManagerListImp boxPieces;

    public Board() {
        cells = new HashMap<>();

        for (int row = 1; row <= 8; row++)
            for (char col = 'A'; col <= 'H'; col++)
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));

        boxPieces = new DeletedPieceManagerListImp();
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }

    public DeletedPieceManagerListImp getBoxPieces() {
        return boxPieces;
    }

    public void placePieces() {


        boxPieces.addPiece(new King(this, new Coordinate('e', 1), King.Type.BLACK));
        boxPieces.addPiece(new Queen(this, new Coordinate('d', 1), Queen.Type.BLACK));
        boxPieces.addPiece(new Rook(this, new Coordinate('a', 1), Rook.Type.BLACK));

        boxPieces.addPiece(new Bishop(this, new Coordinate('c', 1), Bishop.Type.BLACK));
        boxPieces.addPiece(new Knight(this, new Coordinate('g', 1), Knight.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('a', 2), Pawn.Type.BLACK));

        boxPieces.addPiece(new King(this, new Coordinate('e', 8), King.Type.WHITE));
        boxPieces.addPiece(new Queen(this, new Coordinate('d', 8), Queen.Type.WHITE));

        //boxPieces.addPiece(new Rook(this, new Coordinate('a', 8), Rook.Type.WHITE));
        boxPieces.addPiece(new Rook(this, new Coordinate('a', 8), Rook.Type.WHITE));

        boxPieces.addPiece(new Bishop(this, new Coordinate('f', 8), Bishop.Type.WHITE));
        boxPieces.addPiece(new Knight(this, new Coordinate('b', 8), Knight.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('a', 7), Pawn.Type.WHITE));


        boxPieces.addPiece(new Knight(this, new Coordinate('b', 1), Knight.Type.BLACK));
        boxPieces.addPiece(new Bishop(this, new Coordinate('f', 1), Bishop.Type.BLACK));
        boxPieces.addPiece(new Rook(this, new Coordinate('h', 1), Rook.Type.BLACK));


        boxPieces.addPiece(new Pawn(this, new Coordinate('b', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('c', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('d', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('e', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('f', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('g', 2), Pawn.Type.BLACK));
        boxPieces.addPiece(new Pawn(this, new Coordinate('h', 2), Pawn.Type.BLACK));


        boxPieces.addPiece(new Bishop(this, new Coordinate('c', 8), Bishop.Type.WHITE));
        boxPieces.addPiece(new Knight(this, new Coordinate('g', 8), Knight.Type.WHITE));
        boxPieces.addPiece(new Rook(this, new Coordinate('h', 8), Rook.Type.WHITE));


        boxPieces.addPiece(new Pawn(this, new Coordinate('b', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('c', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('d', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('e', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('f', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('g', 7), Pawn.Type.WHITE));
        boxPieces.addPiece(new Pawn(this, new Coordinate('h', 7), Pawn.Type.WHITE));


    }

    public Set getNextMovements(Piece.Color pieceColor) {
        Set<Coordinate> coordenadas = cells.values().stream().filter(cell -> cell.getPiece() != null)
                .map(Cell::getPiece)
                .filter(piece -> piece.getColor() == pieceColor)
                .flatMap(piece -> piece.getNextMovements().stream())
                .collect(Collectors.toSet());

        return coordenadas;
    }


    public boolean contains(Coordinate c) {
        return cells.containsKey(c);
    }

    public Cell getCellAt(Coordinate c) {
        return cells.get(c);
    }

    public void highLight(Collection<Coordinate> coordinates) {
        for (Coordinate c : coordinates)
            getCellAt(c).highlight();
    }

    public void removeHighLight() {
        cells.values().stream().forEach(cell -> cell.removeHighLight());
    }


    @Override
    public String toString() {


        String aux = "    A  B  C  D  E  F  G  H\n";

        for (int row = 1; row <= 8; row++) {
            aux += " " + row + " ";
            for (char col = 'A'; col <= 'H'; col++)
                aux += cells.get(new Coordinate(col, row));

            aux += " " + row + "\n";
        }
        aux += "    A  B  C  D  E  F  G  H";


        aux += "\n" + boxPieces;


        return aux;
    }


}
