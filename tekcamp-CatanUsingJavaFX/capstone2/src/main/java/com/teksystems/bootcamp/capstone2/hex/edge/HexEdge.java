package com.teksystems.bootcamp.capstone2.hex.edge;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class HexEdge extends Line {
    private boolean hasRoad;
    private String playerColor;
    private double angle;
    private Integer xOffSet;
    private Integer yOffSet;
    private GameBoard gameBoard;
    public List<String> listOfAdjacentEdgesToThisEdge;
    private List<String> listOfConnecedVertexToThisEdge;
    private String edgeCode;
    private Integer roadDepth;



    public List<String> getListOfAdjacentEdgesToThisEdge() {
        return listOfAdjacentEdgesToThisEdge;
    }

    public List<String> getListOfConnecedVertexToThisEdge() {
        return listOfConnecedVertexToThisEdge;
    }
    public String getEdgeCode() {
        return edgeCode;
    }

    public Integer getRoadDepth() {
        return roadDepth;
    }

    public void setRoadDepth(Integer roadDepth) {
        this.roadDepth = roadDepth;
    }

    public HexEdge(double startX, double startY, double endX, double endY, int xOffset, int yOffset, Color color, double angle, String edgeCode, GameBoard gameBoard) {
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
        this.edgeCode=edgeCode;
        listOfAdjacentEdgesToThisEdge = new ArrayList<>();
        listOfConnecedVertexToThisEdge = new ArrayList<>();
roadDepth=1;
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

    public Integer checkConnectedRoads(String currentPlayerColor) {
        return checkIfAnyOfConnectedEdgeHAsARoad(this.getListOfAdjacentEdgesToThisEdge(),currentPlayerColor);
    }

    private Integer checkIfAnyOfConnectedEdgeHAsARoad(List<String> listOfAdjacentEdgesToThisEdge, String currentPlayerColor) {
        HexEdge edge;
        for (String edgeCode : listOfAdjacentEdgesToThisEdge) {
            edge = gameBoard.getEdges().get(edgeCode);
            if (edge.hasRoad && edge.getPlayerColor().equals(currentPlayerColor)) {
                return edge.roadDepth;
            }
        }
        return -1;
    }
    public void addRoadToPlayerHashMap(String playerColor){
        gameBoard.getRoadsOfEachPlayer().get(playerColor).add(this);
    }
    /*private boolean checkIf2ContinuousEdgeHaveRoadsAndBelongToSamePlayer(List<String> connectedEdges, int depth, String currentPlayerColor, String v1, String v2){
        HexEdge edge;
       String v1OfEdgeThatHasRoad;
        String v2OfEdgeThatHasRoad;
        if(depth==2){
            return true;
        }
        for (String edgeCode : connectedEdges){
            edge = gameBoard.getEdges().get(edgeCode);
            if(edge.hasRoad) {
                if (edge.getPlayerColor().equals(currentPlayerColor)) {
                    v1OfEdgeThatHasRoad=edge.getEdgeCode().split("-")[0];
                    v2OfEdgeThatHasRoad=edge.getEdgeCode().split("-")[1];
                    List<String> nextConnectedEdges=new ArrayList<>(edge.getListOfAdjacentEdgesToThisEdge());
                    nextConnectedEdges.stream().filter(s->s.contains(v1)).toList();
                    nextConnectedEdges.stream().filter(s->s.contains((v2))).toList();
                  return checkIf2ContinuousEdgeHaveRoadsAndBelongToSamePlayer(nextConnectedEdges, depth + 1,currentPlayerColor,v1OfEdgeThatHasRoad,v2OfEdgeThatHasRoad);
                }
            }
        }
        return false;
    }
    private boolean chekcIsItFirstRound(String currentPlayerColor) {
        if(currentPlayerColor.equals("")){
            return true;
        }
        Integer countOfRoadsForThisPlayer = gameBoard.getRoadsOfEachPlayer().get(currentPlayerColor).size();
        return (countOfRoadsForThisPlayer<=1);
    }

    public boolean areEdgesConnected( HexEdge edgeToBeChecked) {
        HexEdge thisEdge = this;
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


    public void searchAndAddBothVericesToThisEdge() {
        for (HexCordinate vertex : gameBoard.getVertices().values()) {
            if (checkIfEdgeCornerCordinateIsSameAsVertex(this, vertex)){
                this.listOfConnecedVertexToThisEdge.add(vertex.getvertexCordinateCode());
            }
        }
    }*/
  /*  private boolean checkIfEdgeCornerCordinateIsSameAsVertex(HexEdge hexEdge, HexCordinate vertex) {
        boolean isVertexOnEdgeCorner= false;
        if(hexEdge.getStartX()== vertex.getCenterX() && hexEdge.getStartY()==vertex.getCenterY()){
            isVertexOnEdgeCorner= true;
        }
        if(hexEdge.getEndX()== vertex.getCenterX() && hexEdge.getEndY()==vertex.getCenterY()){
            isVertexOnEdgeCorner= true;
        }
        return isVertexOnEdgeCorner;
    }
*/

    // this.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
}
