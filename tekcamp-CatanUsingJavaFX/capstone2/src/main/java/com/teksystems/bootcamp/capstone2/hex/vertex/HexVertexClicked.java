package com.teksystems.bootcamp.capstone2.hex.vertex;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;

public class HexVertexClicked {
    GameBoard gameBoard;
   public HexVertexClicked(GameBoard gameBoard){
        this.gameBoard= gameBoard;
    }
    public HexCordinate isClickCordinateMatchedWithVertices(double x, double y) {
        HexCordinate vertexFound= null;
       for(HexCordinate vertex: gameBoard.getVertices().values()){
            if(vertex.getCenterX()>=x-5 && vertex.getCenterX()<=x+5 && vertex.getCenterY()>=y-5 && vertex.getCenterY()<=y+5) {
                vertexFound = vertex;
                return vertexFound;
            }
        }
       return vertexFound;
    }
}
