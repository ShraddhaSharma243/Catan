package com.teksystems.bootcamp.capstone2.hex;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Settlement;
import com.teksystems.bootcamp.capstone2.scene.Road;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;
public class HexEdges {
    public List<HexEdge> getAllEdges(int x, int y, int widthOrHeight, GameBoard gameBoard) {
        List<HexEdge> hexEdges= new ArrayList<>();
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight/2,y,x+widthOrHeight,y+widthOrHeight/4, Color.RED,120,10,-17, gameBoard));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x,y+widthOrHeight/4,x,y+widthOrHeight*3/4,Color.BLUE,0, -10, -10,gameBoard));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight,y+widthOrHeight*3/4,x+widthOrHeight/2,y+widthOrHeight,Color.YELLOW,60,-28,-23,gameBoard));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight,y+widthOrHeight/4,x+widthOrHeight,y+widthOrHeight*3/4,Color.GREEN,0,-10,-10,gameBoard));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x,y+widthOrHeight*3/4,x+widthOrHeight/2,y+widthOrHeight,Color.BEIGE,120,10,-17,gameBoard));
        hexEdges.add(getExistingEdgeIFAlreadyAddedElseCreateNewEdge(x+widthOrHeight/2,y,x, y+widthOrHeight/4, Color.BLACK,60,-30,-23,gameBoard));
        return hexEdges;
    }

    public  HexEdge getExistingEdgeIFAlreadyAddedElseCreateNewEdge(double startX, double startY, double endX, double endY, Color color, double angle, int xOffset,int yOffset, GameBoard gameBoard){

        for(HexEdge edge: gameBoard.getEdges()){
            if(edge.getStartX()==startX
                    && edge.getStartY()==startY
                    && edge.getEndX()==endX
                    && edge.getEndY()==endY){
                        return edge;
            }
        }
        HexEdge newEdge = new HexEdge(startX,startY,endX,endY, xOffset, yOffset,color,angle, gameBoard);
         // addClickEventToRoad(newEdge,xOffset,yOffset,gameBoard);

        gameBoard.getEdges().add(newEdge);
        gameBoard.getChildren().add(newEdge);
        return newEdge;
    }

    private void addClickEventToRoad(HexEdge newEdge, int xOffset, int yOffset, GameBoard gameBoard){
        newEdge.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double angle = newEdge.getAngle();
                String currentRoadImgURL;
                Road rd = new Road(newEdge, IMGURL_ROAD_WHITE);
                rd.setRotate(angle);
                rd.setX(newEdge.getStartX()+xOffset);
                rd.setY(newEdge.getStartY()+yOffset);
                gameBoard.getChildren().add(rd);


            }
        });
    }

}
