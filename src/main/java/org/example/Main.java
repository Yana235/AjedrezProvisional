package org.example;

import com.diogonunes.jcolor.Attribute;
import org.example.piezas.Pawn;
import org.example.piezas.Piece;
import org.example.piezas.Rook;

import javax.swing.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {
    public static void main(String[] args) {

       // Board board = new Board();
       // board.placePieces();


       // System.out.println(board);


        Game game=new Game();

           game.play();

    }
}