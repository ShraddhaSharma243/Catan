package com.teksystems.bootcamp.capstone2.hex.edge;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdge;

public class HexEdgeClicked {
    GameBoard gameBoard;
    public HexEdgeClicked(GameBoard gameBoard){
    this.gameBoard=gameBoard;
    }
    public HexEdge isClickCordinateMatchedWithEdge(double x, double y) {
        HexEdge edgeFound = null;
        boolean isThisX = false;
        boolean isThisY = false;
        for (HexEdge edge : gameBoard.getEdges().values()) {
            isThisX = (x >= edge.getStartX()-5 && x <= edge.getEndX()+5) || x <= edge.getStartX()+5 && x >= edge.getEndX()-5;
            isThisY = (y >= edge.getStartY()-5 && y <= edge.getEndY()+5) || (y <= edge.getStartY()+5 && y >= edge.getEndY()-5);
           if(isThisX && isThisY){
               return edge;
           }
        }
        return edgeFound;

    }
}
