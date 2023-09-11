package com.teksystems.bootcamp.capstone2.hex.edge;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdge;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class HexEdges {
    private final GameBoard gameBoard;

    public HexEdges(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }
    public List<HexEdge> getAllEdges(int x, int y, int widthOrHeight) {
        List<HexEdge> hexEdges= new ArrayList<>();
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight/2,y,x+widthOrHeight,y+widthOrHeight/4, Color.RED,120,10,-17));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x,y+widthOrHeight/4,x,y+widthOrHeight*3/4,Color.BLUE,0, -10, -10));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight,y+widthOrHeight*3/4,x+widthOrHeight/2,y+widthOrHeight,Color.YELLOW,60,-28,-23));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight,y+widthOrHeight/4,x+widthOrHeight,y+widthOrHeight*3/4,Color.GREEN,0,-10,-10));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x,y+widthOrHeight*3/4,x+widthOrHeight/2,y+widthOrHeight,Color.BEIGE,120,10,-17));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight/2,y,x, y+widthOrHeight/4, Color.BLACK,60,-30,-23));
        return hexEdges;
    }

    public  HexEdge getExistingEdgeIFAlreadyAddedElseCreateNewEdge(double startX, double startY, double endX, double endY, Color color, double angle, int xOffset,int yOffset){
        String edgeCode = String.valueOf((int)startX)+String.valueOf((int)startY)+"-"+String.valueOf((int)endX)+String.valueOf((int)endY);
        String edgeCode2 = String.valueOf((int)endX)+String.valueOf((int)endY) +"-"+String.valueOf((int)startX)+String.valueOf((int)startY);
        String edgeCode3 = String.valueOf((int)startX)+String.valueOf((int)startY+5)+"-"+String.valueOf((int)endX)+String.valueOf((int)endY);
        String edgeCode4 = String.valueOf((int)endX)+String.valueOf((int)endY+5) +"-"+String.valueOf((int)startX)+String.valueOf((int)startY);
        String edgeCode5 = String.valueOf((int)startX)+String.valueOf((int)startY)+"-"+String.valueOf((int)endX)+String.valueOf((int)endY+5);
        String edgeCode6 = String.valueOf((int)endX)+String.valueOf((int)endY) +"-"+String.valueOf((int)startX)+String.valueOf((int)startY+5);
        String edgeCode7 = String.valueOf((int)startX)+String.valueOf((int)startY+5)+"-"+String.valueOf((int)endX)+String.valueOf((int)endY+5);
        String edgeCode8 = String.valueOf((int)endX)+String.valueOf((int)endY+5) +"-"+String.valueOf((int)startX)+String.valueOf((int)startY+5);
        HexEdge edge = gameBoard.getEdges().get(edgeCode);
        HexEdge edge2 = gameBoard.getEdges().get(edgeCode2);
        HexEdge edge3 = gameBoard.getEdges().get(edgeCode3);
        HexEdge edge4 = gameBoard.getEdges().get(edgeCode4);
        HexEdge edge5 = gameBoard.getEdges().get(edgeCode5);
        HexEdge edge6 = gameBoard.getEdges().get(edgeCode6);
        HexEdge edge7 = gameBoard.getEdges().get(edgeCode7);
        HexEdge edge8 = gameBoard.getEdges().get(edgeCode8);
        if(edge!= null){
            return edge;
        }
        if(edge2!=null){
            return edge2;
        }
        if(edge3!= null){
            return edge3;
        }
        if(edge4!=null){
            return edge4;
        }
        if(edge5!= null){
            return edge5;
        }
        if(edge6!=null){
            return edge6;
        }
        if(edge7!= null){
            return edge7;
        }
        if(edge8!=null){
            return edge8;
        }
      HexEdge newEdge = new HexEdge(startX,startY,endX,endY, xOffset, yOffset,color,angle,edgeCode, gameBoard);
        gameBoard.getEdges().put(edgeCode, newEdge);
        gameBoard.getChildren().add(newEdge);
        return newEdge;
    }



}
