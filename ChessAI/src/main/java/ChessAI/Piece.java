/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * CURRENT POSITION BEING A LEGAL MOVE??
 */
public class Piece {

    boolean debug = true;
    boolean white;

    public static String[] pieces = {"Pawn", "Knight", "Bishop", "Rook", "Queen", "King"};

    int pieceID;
    String pieceString;
    Coordinate currentPosition;

    public static void main(String[] args) throws Exception {
        Piece piece = new Piece("King", 1, true, new Coordinate("E5"));
        int[][] board = new int[8][8];

        for (Path p : piece.getLegalMoves()) {
            for (Coordinate a : p.getCoordinates()) {
                System.out.println(a.getCoordinateString());

                board[a.getX()][a.getY()] = 1;
            }

        }
        board[piece.currentPosition.getX()][piece.currentPosition.getY()] = 9;
        print2D(board);

    }

    public Piece(String piece, int pieceID, boolean color, Coordinate position) {
        this.pieceID = pieceID;
        white = color;
        currentPosition = position;
        int i = 0;
        for (String a : pieces) {
            if (a.equals(piece)) {
                pieceID = i;
                pieceString = piece;
            } else {
                i++;
            }
        }
    }

    public ArrayList<Path> getLegalMoves() throws Exception {
        ArrayList<Path> paths = new ArrayList();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        Path potentialPathOne = new Path();
        Path potentialPathTwo = new Path();
        Path potentialPathThree = new Path();
        Path potentialPathFour = new Path();
        Path potentialPathFive = new Path();
        Path potentialPathSix = new Path();
        Path potentialPathSeven = new Path();
        Path potentialPathEight = new Path();
        switch (pieceString) {
            case "Pawn":
                if (white) {
                    if (y < 7) {
                        paths.add(new Path(new Coordinate(x, y + 1)));
                        if(y < 6)
                            paths.add(new Path(new Coordinate(x, y + 2)));
                    }
                    if(x>0){
                        paths.add(new Path(new Coordinate(x-1, y + 1)));
                    }
                    if(x<7){
                        paths.add(new Path(new Coordinate(x+1, y + 1)));
                    }
                } 
                else {
                    if (y > 1) {
                        paths.add(new Path(new Coordinate(x, y - 1)));
                        if(y > 2)
                            paths.add(new Path(new Coordinate(x, y - 2)));
                    }
                    if(x>0){
                        paths.add(new Path(new Coordinate(x-1, y - 1)));
                    }
                    if(x<7){
                        paths.add(new Path(new Coordinate(x+1, y - 1)));
                    }
                }
                break;
            case "Knight":
                int[] xDeltas = {-2, -1, 1, 2, 2, 1, -1, -2};
                int[] yDeltas = {-1, -2, -2, -1, 1, 2, 2, 1};

                for (int i = 0; i < xDeltas.length; i++) {
                    Coordinate cord;
                    try {
                        cord = new Coordinate(x + xDeltas[i], y + yDeltas[i]);
                    } catch (Exception e) {
                        continue;
                    }
                    paths.add(new Path(cord));
                }
                break;
            case "Rook":
                potentialPathOne = new Path();
                potentialPathTwo = new Path();
                potentialPathThree = new Path();
                potentialPathFour = new Path();
                for (int i = 0; i < 8; i++) {
                    try {
                        potentialPathOne.addCoordinate(new Coordinate(x, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathTwo.addCoordinate(new Coordinate(x, y + i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathThree.addCoordinate(new Coordinate(x + i, y));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathFour.addCoordinate(new Coordinate(x - i, y));
                    } catch (Exception e) {
                    }
                }
                paths.add(potentialPathOne);
                paths.add(potentialPathTwo);
                paths.add(potentialPathThree);
                paths.add(potentialPathFour);
                break;
            case "Bishop":
                potentialPathOne = new Path();
                potentialPathTwo = new Path();
                potentialPathThree = new Path();
                potentialPathFour = new Path();
                for (int i = 0; i < 8; i++) {
                    try {
                        potentialPathOne.addCoordinate(new Coordinate(x - i, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathTwo.addCoordinate(new Coordinate(x - i, y + i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathThree.addCoordinate(new Coordinate(x + i, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathFour.addCoordinate(new Coordinate(x + i, y + i));
                    } catch (Exception e) {
                    }
                }
                paths.add(potentialPathOne);
                paths.add(potentialPathTwo);
                paths.add(potentialPathThree);
                paths.add(potentialPathFour);
                break;
            case "Queen":
                potentialPathOne = new Path();
                potentialPathTwo = new Path();
                potentialPathThree = new Path();
                potentialPathFour = new Path();
                for (int i = 0; i < 8; i++) {
                    try {
                        potentialPathOne.addCoordinate(new Coordinate(x, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathTwo.addCoordinate(new Coordinate(x, y + i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathThree.addCoordinate(new Coordinate(x + i, y));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathFour.addCoordinate(new Coordinate(x - i, y));
                    } catch (Exception e) {
                    }
                }
                paths.add(potentialPathOne);
                paths.add(potentialPathTwo);
                paths.add(potentialPathThree);
                paths.add(potentialPathFour);

                potentialPathFive = new Path();
                potentialPathSix = new Path();
                potentialPathSeven = new Path();
                potentialPathEight = new Path();
                for (int i = 0; i < 8; i++) {
                    try {
                        potentialPathFive.addCoordinate(new Coordinate(x - i, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathSix.addCoordinate(new Coordinate(x - i, y + i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathSeven.addCoordinate(new Coordinate(x + i, y - i));
                    } catch (Exception e) {
                    }
                    try {
                        potentialPathEight.addCoordinate(new Coordinate(x + i, y + i));
                    } catch (Exception e) {
                    }
                }
                paths.add(potentialPathFive);
                paths.add(potentialPathSix);
                paths.add(potentialPathSeven);
                paths.add(potentialPathEight);
                break;
            case "King":
                try {
                paths.add(new Path(new Coordinate(x - 1, y - 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x + 1, y - 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x - 1, y + 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x + 1, y + 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x, y - 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x, y + 1)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x - 1, y)));
                } catch (Exception e) {
                }
                try {
                    paths.add(new Path(new Coordinate(x + 1, y)));
                } catch (Exception e) {
                }
            break;
        }
        return paths;
    }

    public String getColor() {
        if (this.white == true) {
            return "White";
        }
        return "Black";
    }

    @Override
    public String toString() {
        String color = "W";
        if (!white) {
            color = "B";
        }
        if (this.pieceString.equals("King")) {
            return color + "*" + String.valueOf(pieceID);
        }
        String number = String.valueOf(pieceID);
        if (pieceID < 10) {
            number = "0" + number;
        }
        return color + pieceString.substring(0, 1) + number;
        //return this.currentPosition.getCoordinateString();
    }

    private static void print2D(int mat[][]) {
        // Loop through all rows 
        for (int[] row : mat) // converting each row as string 
        // and then printing in a separate line 
        {
            System.out.println(Arrays.toString(row));
        }
    }

}
