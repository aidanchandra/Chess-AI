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
public class Coordinate implements Comparable{
    private int x;
    private int y;
    
    public static String[] columnLookup = {"A","B","C","D","E","F","G","H"};
    
    public Coordinate(String coord) throws Exception{
        this(getCooridnateInt(coord.substring(0,1)),Integer.valueOf(coord.substring(1, 2))-1);        
    }
    
    public Coordinate(int x, int y) throws Exception{
        if(!(x < 8 && y < 8 && x >=0 && y >=0)){
            throw new Exception("Invalid Coordinates Provided");
        }
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean setX(int val){
        if(val < 8 && val >-1){
            x = val;
            return true;
        }
        return false;
    }
    public boolean setY(int val){
        if(val < 8 && val>-1){
            y = val;
            return true;
        }
        return false;
    }
    public String getCoordinateString(){
        return columnLookup[x]+String.valueOf(y+1);
    }
    public static int getCooridnateInt(String a){
        for(int i = 0; i < columnLookup.length; i++){
            if(columnLookup[i].equals(a))
                return i;
        }
        return -1;
    }
    
    public int compareTo(Object objIn){
        Coordinate other = (Coordinate)objIn;
        if(other.getX() == this.x && other.getY() == this.y)
            return 0;
        return this.getCoordinateString().compareTo(other.getCoordinateString());
    }
    @Override
    public boolean equals(Object o) {
        return compareTo(o) == 0;
    }
    @Override
    public String toString(){
        return this.getCoordinateString();
    }
    
}
