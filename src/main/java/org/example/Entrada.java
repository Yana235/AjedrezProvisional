package org.example;


import java.util.Scanner;


public class Entrada {


    public static String giveCoordinate() {
        Scanner sc = new Scanner(System.in);
        String coordinatePiece;

        coordinatePiece = sc.next();

        return coordinatePiece;
    }
}