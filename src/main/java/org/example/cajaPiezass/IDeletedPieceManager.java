package org.example.cajaPiezass;

import org.example.piezas.Piece;

public interface IDeletedPieceManager {
    void addPiece(Piece piece);
    //Piece removePiece(Piece piece);
    void removePiece(Piece piece);

    int count(Piece.Type pieceType);
}
