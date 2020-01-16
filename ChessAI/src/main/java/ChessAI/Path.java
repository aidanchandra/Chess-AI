/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessAI;

import java.util.ArrayList;

/**
 *
 * @author aidanchandra
 */
public class Path {
 
    private ArrayList<Coordinate> coordinates;
    
    public Path(){
        coordinates = new ArrayList();
    }
    public Path(Coordinate c){
        coordinates = new ArrayList();
        coordinates.add(c);
    }
    public ArrayList<Coordinate> getCoordinates(){
        return coordinates;
    }
    public void addCoordinate(Coordinate e){
        coordinates.add(e);
    }
    
}
