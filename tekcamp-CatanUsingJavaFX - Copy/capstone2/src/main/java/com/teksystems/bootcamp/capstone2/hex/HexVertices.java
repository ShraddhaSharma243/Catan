package com.teksystems.bootcamp.capstone2.hex;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class HexVertices {
    public List<HexCordinate> getAllVertices(int x, int y, int widthOrHeight, GameBoard gameBoard){
        List<HexCordinate> hexVertices= new ArrayList<>();
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight/2,y,gameBoard));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight/2,y+widthOrHeight,gameBoard));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight,y+widthOrHeight*3/4,gameBoard));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight,y+widthOrHeight/4,gameBoard));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x,y+widthOrHeight*3/4,gameBoard));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x, y+widthOrHeight/4, gameBoard));
        return hexVertices;
    }
    public static HexCordinate getExistingVertxIFAlreadyAddedElseCreateNewVertex(double x, double y , GameBoard gameBoard){

        for(HexCordinate vertex: gameBoard.getVertices()){
            if(vertex.getCenterX()==x && vertex.getCenterY()>=y && vertex.getCenterY()<=y+5){
                return vertex;
            }
        }
        HexCordinate newCordinate = new HexCordinate(x,y,gameBoard);
        gameBoard.getVertices().add(newCordinate);
        return newCordinate;
    }
}
