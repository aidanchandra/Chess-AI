/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessAI;

/**
 *
 * @author aidanchandra
 */
public class Move {
    private Piece piece;
    private Coordinate pos;
    private boolean take;
    private long evaluation;
    
    public Move(Piece piece, Coordinate pos, boolean take){
        this.piece = piece;
        this.pos = pos;
        this.take = take;
    }
    public Move(Piece piece, int x, int y, boolean take) throws Exception{
        this.piece = piece;
        this.pos = new Coordinate(x,y);
        this.take = take;
    }
    public Coordinate getCord(){
        return pos;
    }
    public boolean isEvaluated(){
        return this.evaluation != 0;
    }
    public void setEvaluation(long eval){
        evaluation = eval;
    }
    public long getEvaluation(){
        return evaluation;
    }
    public Piece getPiece(){
        return piece;
    }
    public boolean getTake(){
        return take;
    }
    @Override
    public String toString(){
        String pieceIDd = String.valueOf(this.piece.toString());
        if(this.piece.pieceID < 10)
            pieceIDd = "0" + pieceIDd;
        
        if (take) return pieceIDd + " to " + this.pos.getCoordinateString() + " with take";
        return pieceIDd + " to " + this.pos.getCoordinateString();
    }
}
