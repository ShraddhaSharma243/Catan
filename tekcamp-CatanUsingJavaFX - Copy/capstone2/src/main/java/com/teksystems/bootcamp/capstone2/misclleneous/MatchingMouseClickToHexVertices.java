package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.HexCordinate;

public class MatchingMouseClickToHexVertices {
    public HexCordinate isClickCordinateMatchedWithVertices(double x, double y, GameBoard gameBoard) {
        HexCordinate vertexFound= null;
        for(HexCordinate vertex: gameBoard.getVertices()){
            if(vertex.getCenterX()>=x-5 && vertex.getCenterX()<=x+5 && vertex.getCenterY()>=y-5 && vertex.getCenterY()<=y+5) {
                vertexFound = vertex;
                return vertexFound;
            }
        }
       return vertexFound;
    }
}
