package org.example.cajaPiezas;

import java.util.ArrayList;
import java.util.List;

public class DeletedPieceManagerListImp implements IDeletedPieceManager {

    List<Piece> pieceList;

    public DeletedPieceManagerListImp(){
        pieceList=new ArrayList<>();
    }

    @Override
    public void addPiece(Piece piece) {
        pieceList.add(piece);
    }

    @Override
    public Piece removeLast() {
        return pieceList.remove(pieceList.size()-1);
    }

    @Override
    public int count(Piece.Type pieceType) {

        int count=0;
        for(Piece p :pieceList){
            if(p.getType().equals(pieceType)){
                count++;
            }
        }
        return count;
    }

    //put your task here

}
