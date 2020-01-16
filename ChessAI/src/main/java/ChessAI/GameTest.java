/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessAI;

import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author aidanchandra
 */
public class GameTest {
    
    public static boolean ENABLE_AI_TIMER = true;
    public static boolean ENABLE_MOVE_ORDERING = true; 
    public static boolean ENABLE_AB_PRUNING = true;


    static Board currentBoard;
    static MiniMax ai;
    
    public static void main(String[] args) throws Exception{
        
        currentBoard = new Board();
        Board.print2D(currentBoard.getBoard());
        ai = new MiniMax();
        MiniMax.UtilMove move = ai.nextMove(currentBoard, "White", 2);
        Board.print2D(currentBoard.getBoard());
        System.out.println(move.getMove().toString());
        /*
        currentBoard = new Board();
        System.out.println("Starting Board:");
        Board.print2D(currentBoard.getBoard());
        
        Scanner myObj = new Scanner(System.in);
        String stringIn = "";
        String color = "White";
        
        while(!stringIn.contains("stop")){
            System.out.println("->Enter a move for " + color);
            stringIn = myObj.nextLine();
            System.out.println("->Your move: " + stringIn);
            
            ArrayList<Move> moves = currentBoard.getMoves(color);
            Move move = null;
            for(Move m : moves){
                if(m.toString().equals(stringIn)){
                    move = m;
                    break;
                }  
            }
            if(move == null){
                System.out.println("Invalid Move Given");
                continue;
            }
                
            Board newBoard = new Board(currentBoard,move);
            System.out.println("Board after " + move.toString());
            Board.print2D(newBoard.getBoard());

            if(color.equals("White"))
                color = "Black";
            else if(color.equals("Black"))
                color = "White";
        }
        System.out.println("->Ending");
        */

    }
    
}
