package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.HexCordinate;
import com.teksystems.bootcamp.capstone2.hex.HexEdge;

public class MatchingMouseClickToHexEdge {
    public HexEdge isClickCordinateMatchedWithEdge(double x, double y, GameBoard gameBoard) {
        HexEdge edgeFound = null;
        boolean isThisX = false;
        boolean isThisY = false;
        for (HexEdge edge : gameBoard.getEdges()) {
            isThisX = (x >= edge.getStartX()-5 && x <= edge.getEndX()+5) || x <= edge.getStartX()+5 && x >= edge.getEndX()-5;
            isThisY = (y >= edge.getStartY()-5 && y <= edge.getEndY()+5) || (y <= edge.getStartY()+5 && y >= edge.getEndY()-5);
           if(isThisX && isThisY){
               return edge;
           }
        }
        return edgeFound;

    }
}
