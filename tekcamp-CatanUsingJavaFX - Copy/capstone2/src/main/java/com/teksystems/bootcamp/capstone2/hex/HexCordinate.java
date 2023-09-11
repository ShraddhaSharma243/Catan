package com.teksystems.bootcamp.capstone2.hex;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Settlement;
import javafx.scene.shape.Circle;

import java.util.List;

public class HexCordinate extends Circle {
    private boolean hasSettlement;
    private String playerColor;
    private GameBoard gameBoard;

    public HexCordinate(double x, double y, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        setCenterX(x);
        setCenterY(y);
        setRadius(10);
        this.gameBoard=gameBoard;
        hasSettlement=false;
    }
    public String getPlayerColor() {
        return playerColor;
    }
    public boolean getHasSettlement() {
        return hasSettlement;
    }
    public void setHasSettlementAndPlayerColor(boolean hasSettlement, String playerColor) {
        this.hasSettlement = hasSettlement;
        this.playerColor=playerColor;
    }

    public boolean canSettlementBePlacedHere(String playerColor, HexCordinate vertexToPlaceSettlement) {

        boolean are2SamePlayerRoadsConnectedToThisVertex;
        //boolean isRoadOnThisEdgeMatchingWithNewRoad;
        boolean isItTheFirstRound = chekcIsItFirstRound(playerColor);
        if(isItTheFirstRound){
            return true;
        }
        List<HexEdge> roadsOfCurrentPlayer = gameBoard.getRoadsOfEachPlayer().get(playerColor);
            are2SamePlayerRoadsConnectedToThisVertex = checkIf2RoadsOfTheSamePlayerConnectedToThisVertex(this, roadsOfCurrentPlayer);
            if(are2SamePlayerRoadsConnectedToThisVertex){
                return true;
            }
        return false;
    }
    private boolean chekcIsItFirstRound(String playerColor) {
        if(playerColor.equals("")){
            return true;
        }
        Integer countOfSettlementsForThisPlayer = gameBoard.getSettlementsOfEachPlayer().get(playerColor).size();
        return (countOfSettlementsForThisPlayer<=1);
    }
    private boolean checkIf2RoadsOfTheSamePlayerConnectedToThisVertex(HexCordinate vertex, List<HexEdge> roadsOfCurrentPlayer) {
       for(HexEdge edge : roadsOfCurrentPlayer){
           if((edge.getStartX()>= vertex.getCenterX()-5
                && edge.getStartX()>= vertex.getCenterX()+5)
                && (edge.getStartY()>=vertex.getCenterY()-5
                  && edge.getStartY()<=vertex.getCenterY()+5)){
               //check if settlement is there
               if(isSettlmentOnNextVertex(edge.getEndX(), edge.getEndY())){
                   return false;
               }
               for(HexEdge nextEdge : roadsOfCurrentPlayer){
                   if(((edge.getEndX()>=nextEdge.getStartX()-5
                            && edge.getEndX()<=nextEdge.getStartX()+5)
                        &&(edge.getEndY()>=nextEdge.getStartY()-5
                            && edge.getEndY()<=nextEdge.getStartY()+5))
                     || ((edge.getEndX()>=nextEdge.getEndX()-5
                           && edge.getEndX()<=nextEdge.getEndX()+5)
                           &&(edge.getEndY()>=nextEdge.getEndY()-5
                           && edge.getEndY()<=nextEdge.getEndY()+5))){
                       return true;
                   }
               }
           }
              if ((edge.getEndX()>= vertex.getCenterX()-5
                   && edge.getEndX()>= vertex.getCenterX()+5)
                && (edge.getEndY()>=vertex.getCenterY()-5
                   && edge.getEndY()<=vertex.getCenterY()+5)){
                  for(HexEdge nextEdge : roadsOfCurrentPlayer){
                      if(((edge.getStartX()>=nextEdge.getStartX()-5
                              && edge.getStartX()<=nextEdge.getStartX()+5)
                              &&(edge.getStartY()>=nextEdge.getStartY()-5
                              && edge.getStartY()<=nextEdge.getStartY()+5))
                              || ((edge.getStartX()>=nextEdge.getEndX()-5
                              && edge.getStartX()<=nextEdge.getEndX()+5)
                              &&(edge.getStartY()>=nextEdge.getEndY()-5
                              && edge.getStartY()<=nextEdge.getEndY()+5))){
                          return true;
                      }
                  }
           }
       }

        return false;
    }

    private boolean isSettlmentOnNextVertex(double x, double y) {
        for(HexCordinate vertex : gameBoard.getSettlementsOfEachPlayer().get(playerColor)){
            if(vertex.getCenterX()==x && vertex.getCenterY()==y){
                return true;
            }
        }
        return false;
    }

    public void addSettlementToPlayerHashMap(String playerColor){
        gameBoard.getSettlementsOfEachPlayer().get(playerColor).add(this);
    }
}
