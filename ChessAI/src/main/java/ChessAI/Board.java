/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessAI;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author aidanchandra
 */

/*

1. getPossibleBoards


*/
public class Board {

    String playerToMove = "";
    Piece[][] board = new Piece[8][8];

    public static void main(String[] args) throws Exception {
        Board b = new Board();

        for (Move m : b.getMoves("White")) {
            System.out.println(m.toString());
        }
        print2D(b.getBoard());
    }

    /*
    Handling take??
     */
    public Board(Piece[][] boardIn) {
        playerToMove = "White";
        this.board = boardIn;
    }
    
    public Board(Board boardIn, Move nextMove) {
        playerToMove = boardIn.getNextPlayer();
        Move move = nextMove;
        Piece[][] newBoard = boardIn.getBoard();

        newBoard[move.getCord().getY()][move.getCord().getX()] = nextMove.getPiece();
        newBoard[nextMove.getPiece().currentPosition.getY()][nextMove.getPiece().currentPosition.getX()] = null;

        board = newBoard;
        
        if(board[1][1] == null || !board[1][1].toString().equals("WP09")){
            System.out.println("ADFADS");
        }
    }

    public String getCurrentPlayer() {
        return this.playerToMove;
    }

    public String getNextPlayer() {
        if (playerToMove.equals("White")) {
            return "Black";
        }
        return "White";
    }

    public static String getNextPlayer(String player) {
        if (player.equals("White")) {
            return "Black";
        }
        return "White";
    }

    public Board() throws Exception {
        playerToMove = "White";
        for (int i = 0; i < 8; i++) {
            setBoard(6, i, new Piece("Pawn", i, false, new Coordinate(i, 6)));
            setBoard(1, i, new Piece("Pawn", 8 + i, true, new Coordinate(i, 1)));
        }
        String[] backLine = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};

        for (int i = 0; i < 8; i++) {
            setBoard(7, i, new Piece(backLine[i], i + 16, false, new Coordinate(i, 7)));
            setBoard(0, i, new Piece(backLine[i], i + 24, true, new Coordinate(i, 0)));
        }
    }

    private void setBoard(int x, int y, Piece value) {
        board[x][y] = value;
    }

    private ArrayList<Piece> getPieces() {
        ArrayList<Piece> returnable = new ArrayList();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((board[i][j] != null)) {
                    returnable.add(board[i][j]);
                }
            }
        }
        return returnable;
    }

    public ArrayList<Board> getNextStates(String color) throws Exception {
        ArrayList<Board> returnable = new ArrayList();
        for (Move m : getMoves(color)) {
            returnable.add(new Board(this, m));
        }
        return returnable;
    }

    public ArrayList<Move> getMoves(String color) throws Exception {
        boolean thisIsWhite = color.equals("White");
        ArrayList<Move> moves = new ArrayList();
        ArrayList<Piece> pieces = getPieces();

        for (Piece piece : pieces) {
            if (!piece.getColor().equals(color)) {
                continue;
            }
            if (piece.pieceString.contains("Pawn")) {
                for (Path p : piece.getLegalMoves()) {
                    for (Coordinate potentialMove : p.getCoordinates()) {
                        if (potentialMove.equals(piece.currentPosition)) {
                            continue;
                        }

                        if (board[potentialMove.getY()][potentialMove.getX()] == null && potentialMove.getX() == piece.currentPosition.getX()) { //1 Forward pawn

                            if (Math.abs(potentialMove.getY() - piece.currentPosition.getY()) == 2) { //Requesting double forward pawn
                                moves.add(new Move(piece, potentialMove, false));
                            } else {
                                moves.add(new Move(piece, potentialMove, false));
                            }
                        }
                        if (board[potentialMove.getY()][potentialMove.getX()] != null && potentialMove.getX() != piece.currentPosition.getX()) { //sideway attack pawn
                            if (board[potentialMove.getY()][potentialMove.getX()].white != thisIsWhite) {
                                moves.add(new Move(piece, potentialMove, true));
                            }
                        }
                        /*
                        For pawns their legal moves are also VERY board dependent so we compute some here
                         */

                    }
                }
            }
            if (piece.pieceString.contains("Knight")) {
                ArrayList<Path> paths = piece.getLegalMoves();
                for (Path p : paths) {
                    for (Coordinate potentialMove : p.getCoordinates()) {

                        if (board[potentialMove.getY()][potentialMove.getX()] == null) { //If there are no pieces there
                            moves.add(new Move(piece, potentialMove, false));
                        }
                        if (board[potentialMove.getY()][potentialMove.getX()] != null && board[potentialMove.getY()][potentialMove.getX()].white != thisIsWhite) { //If there are no pieces there
                            moves.add(new Move(piece, potentialMove, true));
                        }

                    }
                }
            }
            if (piece.pieceString.contains("King")) {
                ArrayList<Path> paths = piece.getLegalMoves();
                for (Path p : paths) {
                    for (Coordinate potentialMove : p.getCoordinates()) {

                        if (board[potentialMove.getY()][potentialMove.getX()] == null) { //If there are no pieces there
                            moves.add(new Move(piece, potentialMove, false));
                        }
                        if (board[potentialMove.getY()][potentialMove.getX()] != null && board[potentialMove.getY()][potentialMove.getX()].white != thisIsWhite) { //If there are no pieces there
                            moves.add(new Move(piece, potentialMove, true));
                        }

                    }
                }
            } else {
                for (Path p : piece.getLegalMoves()) {
                    Coordinate start = p.getCoordinates().get(0);
                    for (Coordinate c : p.getCoordinates()) {
                        if (c.equals(start)) {
                            continue;
                        }
                        if (board[c.getY()][c.getX()] != null && board[c.getY()][c.getX()].white != thisIsWhite) {
                            moves.add(new Move(piece, c, true)); //TAKE
                            break;
                        }
                        if (board[c.getY()][c.getX()] != null && board[c.getY()][c.getX()].white == thisIsWhite) {
                            break;
                        } else {
                            moves.add(new Move(piece, c, false));
                        }
                    }
                }
            }
        }
        return moves;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public static void print2D(Piece mat[][]) {
        for (int i = 7; i >= 0; i--) {
            System.out.print(String.valueOf(i + 1) + "   ");
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] != null) {
                    if (j != 7) {
                        System.out.print(mat[i][j].toString() + ",");
                    } else {
                        System.out.print(mat[i][j].toString());
                    }
                } else if (j != 7) {
                    System.out.print("0000,");
                } else {
                    System.out.print("0000");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.print("    ");
        for (int i = 0; i < Coordinate.columnLookup.length; i++) {
            if (i != 7) {
                System.out.print(Coordinate.columnLookup[i] + "    ");
            } else {
                System.out.print(Coordinate.columnLookup[i]);
            }
        }
        System.out.println("");
    }

    public int evaluationFunction(String playerID) {

        //Quantity of pieces on the board
        int pieceCountWhite = 0;
        int pieceCountBlack = 0;
        try {
            for (Move move : this.getMoves("White")) {
                if (move.getPiece().pieceString.equals("Pawn")) {
                    pieceCountWhite += 10;
                }
                if (move.getPiece().pieceString.equals("Knight")) {
                    pieceCountWhite += 30;
                }
                if (move.getPiece().pieceString.equals("Bishop")) {
                    pieceCountWhite += 35;
                }
                if (move.getPiece().pieceString.equals("Rook")) {
                    pieceCountWhite += 50;
                }
                if (move.getPiece().pieceString.equals("Queen")) {
                    pieceCountWhite += 90;
                }
            }
        } catch (Exception e) {

        }
        try {
            for (Move move : this.getMoves("Black")) {
                if (move.getPiece().pieceString.equals("Pawn")) {
                    pieceCountWhite += 10;
                }
                if (move.getPiece().pieceString.equals("Knight")) {
                    pieceCountWhite += 30;
                }
                if (move.getPiece().pieceString.equals("Bishop")) {
                    pieceCountWhite += 35;
                }
                if (move.getPiece().pieceString.equals("Rook")) {
                    pieceCountWhite += 50;
                }
                if (move.getPiece().pieceString.equals("Queen")) {
                    pieceCountWhite += 90;
                }
            }
        } catch (Exception e) {

        }
        if (playerID.equals("White")) {
            return pieceCountWhite;
        }
        else if (playerID.equals("Black")) {
            return pieceCountBlack;
        }
        else{
            System.out.println("AAAH");
            return 0;
        }
    }

    public boolean isGoalState(String color) throws Exception {
        String otherColor = "";
        if (color.equals("White")) {
            otherColor = "Black";
        } else if (color.equals("Black")) {
            otherColor = "White";
        } else {
            System.out.println("AAHAHAHAHAHHAHAHAH");
        }
        for (Move m : this.getMoves(otherColor)) {
            if (m.getPiece().pieceString.equals("King")) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(Object other) {
        Board otherBoard = (Board) other;
        long thisEval = 0;
        long otherEval = 0;
        try {
            thisEval = evaluationFunction(this.playerToMove);
        } catch (Exception e) {
            System.out.println("AAH");
        }
        try {
            otherEval = otherBoard.evaluationFunction(otherBoard.playerToMove);
        } catch (Exception e) {
            System.out.println("AAH");
        }
        return Long.compare(thisEval, otherEval);
    }

}
