package org.example.cajaPiezass;

import com.diogonunes.jcolor.Attribute;
import org.example.piezas.Piece;

import java.util.*;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public class DeletedPieceManagerListImp implements IDeletedPieceManager {

    List<Piece> pieceList;
    List<Piece>pieceListDeath;

    public DeletedPieceManagerListImp() {
        pieceList = new LinkedList<>();
        pieceListDeath=new LinkedList<>();

    }


    @Override
    public void addPiece(Piece piece) {
        pieceList.add(piece);
    }


    @Override
    public void removePiece(Piece piece) {

        pieceListDeath.add(piece);

        pieceList.remove(piece);

    }

    @Override
    public int count(Piece.Type pieceType) {

        int count = 0;
        for (Piece p : pieceList) {
            if (p.getType().equals(pieceType)) {
                count++;
            }
        }
        return count;
    }

    public int countPieceDeath(Piece.Type pieceType) {

        int count = 0;
        for (Piece p : pieceListDeath) {
            if (p.getType().equals(pieceType)) {
                count++;
            }
        }
        return count;
    }


    @Override
    public String toString() {

        String aux = "\n          Life Pieces\n";
        String typesPiece="";

        for (Piece.Type p : Piece.Type.values()) {
            typesPiece += colorize(" ", Attribute.BACK_COLOR(180, 180, 180));
            typesPiece += colorize(p.getShape(), p.getColor().getAttribute(), Attribute.BACK_COLOR(180, 180, 180));
            typesPiece += colorize(" ", Attribute.BACK_COLOR(180, 180, 180));

        }
        aux +=typesPiece +"\n";

        for (Piece.Type p2 : Piece.Type.values()) {

            aux += colorize(" ", Attribute.BACK_COLOR(100, 100, 100));
            aux += colorize(String.valueOf(count(p2)), Attribute.BACK_COLOR(100, 100, 100));
            aux += colorize(" ", Attribute.BACK_COLOR(100, 100, 100));

        }

        aux += "\n\n          Death Pieces\n";


        aux +=typesPiece+ "\n";


        for (Piece.Type p2 : Piece.Type.values()) {

                aux += colorize(" ", Attribute.BACK_COLOR(100, 100, 100));

                    aux += colorize(String.valueOf(countPieceDeath(p2)), Attribute.BACK_COLOR(100, 100, 100));

                aux += colorize(" ", Attribute.BACK_COLOR(100, 100, 100));
            }


        return aux;
    }



}
