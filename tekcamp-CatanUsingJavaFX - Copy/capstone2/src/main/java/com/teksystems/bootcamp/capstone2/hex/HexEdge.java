package com.teksystems.bootcamp.capstone2.hex;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class HexEdge extends Line {
    private boolean hasRoad;
    private String playerColor;
    private double angle;
    private Integer xOffSet;
    private Integer yOffSet;
    private GameBoard gameBoard;
   // private String color;
    public HexEdge(double startX, double startY, double endX, double endY, int xOffset, int yOffset, Color color, double angle, GameBoard gameBoard) {
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
       // setFill(color);
        setStrokeWidth(10);
        this.angle =angle;
        setStroke(Color.TRANSPARENT);
        hasRoad=false;
        this.xOffSet=xOffset;
        this.yOffSet=yOffset;
        this.gameBoard=gameBoard;
    }
    public String getPlayerColor() {
        return playerColor;
    }
    public boolean getHasRoad() {
        return hasRoad;
    }
    public void setHasRoadAndPlayerColor(boolean hasRoad, String playerColor) {
        this.hasRoad = hasRoad;
        this.playerColor=playerColor;
    }

    public double getAngle() {
        return angle;
    }

    public Integer getxOffSet() {
        return xOffSet;
    }

    public Integer getyOffSet() {
        return yOffSet;
    }

    public void setyOffSet(Integer yOffSet) {
        this.yOffSet = yOffSet;
    }

    public boolean checkConnectedRoads(String playerColor, boolean isItFirstRound) {

        boolean isEdgeConnectedToAnExistingRoadOfTheSamePlayer;
        boolean isRoadOnThisEdgeMatchingWithNewRoad;
        boolean isItTheFirstRound = chekcIsItFirstRound(playerColor);
        if(isItTheFirstRound){
            return true;
        }
        for(HexEdge edge: gameBoard.getEdges()){
           if(edge.hasRoad){
                    isRoadOnThisEdgeMatchingWithNewRoad = edge.getPlayerColor().equals(playerColor);
                    if(isRoadOnThisEdgeMatchingWithNewRoad) {
                        isEdgeConnectedToAnExistingRoadOfTheSamePlayer = checkEdgeConnectedToAnExistingRoadOfTheSamePlayer(this, edge);
                        if(isEdgeConnectedToAnExistingRoadOfTheSamePlayer){
                            return true;
                        };
                    }
           }

       }
        return false;
    }

    private boolean chekcIsItFirstRound(String playerColor) {
        if(playerColor.equals("")){
            return true;
        }
        Integer countOfRoadsForThisPlayer = gameBoard.getRoadsOfEachPlayer().get(playerColor).size();
        return (countOfRoadsForThisPlayer==0);
    }

    private boolean checkEdgeConnectedToAnExistingRoadOfTheSamePlayer(HexEdge thisEdge, HexEdge edgeToBeChecked) {
        boolean isBothStartPointMeet = thisEdge.getStartX()==edgeToBeChecked.getStartX()
                                        && (thisEdge.getStartY()>=edgeToBeChecked.getStartY()-5
                                            && thisEdge.getStartY()<=edgeToBeChecked.getStartY()+5);
        boolean isStartOfOneEdgeMeetWithEndOf2ndEdge =thisEdge.getStartX()==edgeToBeChecked.getEndX()
                && (thisEdge.getStartY()==edgeToBeChecked.getEndY()-5
                ||thisEdge.getStartY()==edgeToBeChecked.getEndY()
                || thisEdge.getStartY()-5==edgeToBeChecked.getEndY());
        boolean isEndtOfOneEdgeMeetWithStartOf2ndEdge =thisEdge.getEndX()==edgeToBeChecked.getStartX()
                && (thisEdge.getEndY()>=edgeToBeChecked.getStartY()-5 && thisEdge.getEndY()<=edgeToBeChecked.getStartY()+5);
        boolean isBothEndPointMeet = thisEdge.getEndX()==edgeToBeChecked.getEndX()
                && (thisEdge.getEndY()>=edgeToBeChecked.getEndY()-5
                    && thisEdge.getEndY()<=edgeToBeChecked.getEndY()+5);
        return isBothStartPointMeet || isStartOfOneEdgeMeetWithEndOf2ndEdge || isEndtOfOneEdgeMeetWithStartOf2ndEdge || isBothEndPointMeet;

    }
    public void addRoadToPlayerHashMap(String playerColor){
        gameBoard.getRoadsOfEachPlayer().get(playerColor).add(this);
    }


    // this.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
}
