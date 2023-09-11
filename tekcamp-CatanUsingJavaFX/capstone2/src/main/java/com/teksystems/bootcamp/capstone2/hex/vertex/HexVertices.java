package com.teksystems.bootcamp.capstone2.hex.vertex;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.Hex;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;

import java.util.ArrayList;
import java.util.List;

public class HexVertices {
    GameBoard gameBoard;
    public HexVertices(GameBoard gameBoard) {
        this.gameBoard=gameBoard;
    }

    public List<HexCordinate> getAllVertices(int x, int y, int widthOrHeight, Hex hex){
        List<HexCordinate> hexVertices= new ArrayList<>();
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight/2,y,hex));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight/2,y+widthOrHeight,hex));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight,y+widthOrHeight*3/4,hex));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x+widthOrHeight,y+widthOrHeight/4,hex));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x,y+widthOrHeight*3/4,hex));
        hexVertices.add(getExistingVertxIFAlreadyAddedElseCreateNewVertex(x, y+widthOrHeight/4,hex));
        return hexVertices;
    }
    public HexCordinate getExistingVertxIFAlreadyAddedElseCreateNewVertex(double x, double y, Hex hex ){
       for(int i=0;i<=6;i++){
            HexCordinate vertex;
           String vertexUniqueCode = (int) x +String.valueOf((int)y+i);
           boolean isVeretxAlreadyPresent = gameBoard.getVertices().containsKey(vertexUniqueCode);
           if(isVeretxAlreadyPresent){
                vertex =gameBoard.getVertices().get(vertexUniqueCode);
                vertex.getListOfConnectedHexes().add(hex);
               return vertex;
           }
       }
        String newVertexUniqueCode = (int) x +String.valueOf((int)y);

        // String vertexUniqueCodeWithSlightOffset = String.valueOf((int)x) +String.valueOf((int)y+5);

    //    boolean isVeretxWithOffsetAlreadyPresent = gameBoard.getVertices().containsKey(vertexUniqueCodeWithSlightOffset);

      /*  for(HexCordinate vertex: gameBoard.getVertices()){
            if(vertex.getCenterX()==x && vertex.getCenterY()>=y && vertex.getCenterY()<=y+5){
                return vertex;
            }
        }*/
        HexCordinate newCordinate = new HexCordinate(x,y,newVertexUniqueCode,gameBoard, hex);
        gameBoard.getVertices().put(newVertexUniqueCode,newCordinate);
        return newCordinate;
    }
}
