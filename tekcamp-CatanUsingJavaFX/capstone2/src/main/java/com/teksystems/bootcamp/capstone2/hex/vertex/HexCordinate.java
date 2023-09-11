package com.teksystems.bootcamp.capstone2.hex.vertex;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.Hex;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdge;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class HexCordinate extends Circle {
    private boolean hasSettlement;
    private String playerColor;
    private String vertexCordinateCode;
    private GameBoard gameBoard;
    private List<String> listOfAdjacentEdgesToThisVertexIAMACTINGWEIRD;
    private List<String> listOfAdjacentVerticesToThisVertex;
    public List<String> getListOfAdjacentVerticesToThisVertex() {
        return listOfAdjacentVerticesToThisVertex;
    }

    public List<Hex> getListOfConnectedHexes() {
        return listOfConnectedHexes;
    }

    public  List<Hex> listOfConnectedHexes = new ArrayList<>();
    public HexCordinate(double x, double y, String vertexUniqueCode, GameBoard gameBoard, Hex hex) {
        this.gameBoard = gameBoard;
        setCenterX(x);
        setCenterY(y);
        setRadius(10);
        this.gameBoard=gameBoard;
        this.vertexCordinateCode = vertexUniqueCode;
        hasSettlement=false;
        listOfAdjacentVerticesToThisVertex = new ArrayList<>();
        listOfAdjacentEdgesToThisVertexIAMACTINGWEIRD = new ArrayList<>();
        listOfConnectedHexes.add(hex);
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
    public boolean canSettlementBePlacedHere(String currentPlayerColor) {
        HexCordinate vertexToPlaceSettlement = this;
        if(nextVertexHasSettlement(vertexToPlaceSettlement)){
            return false;
        }
   /*    boolean isItTheFirstRound = FirstRound.chekcIsItFirstRound(currentPlayerColor,gameBoard);
        if(isItTheFirstRound){
           return true;
      }*/
        // doees player have sufficient cards for settlement, if yes then deduct cards else return false.
       return next2edgesHaveRoads(vertexToPlaceSettlement, currentPlayerColor);
    }
    public boolean canSettlementBePlacedHereForFirst2Rounds(String currentPlayerColor){
        HexCordinate vertexToPlaceSettlement = this;
        if(nextVertexHasSettlement(vertexToPlaceSettlement)){
            return false;
        }
        List<String> edgesConnectedToThisVertex = getListOfEdgecodeConnectedToTheGivenVertex(vertexToPlaceSettlement);
        HexEdge edge;
        for (String edgeCode : edgesConnectedToThisVertex) {
            edge = gameBoard.getEdges().get(edgeCode);
            if (edge.getHasRoad()) {
                if (edge.getPlayerColor().equals(currentPlayerColor)) {
                    return true;
                }
            }
        }
        return false;

    }
    private boolean next2edgesHaveRoads(HexCordinate vertexToPlaceSettlement, String currentPplayerColor) {

        List<String> edgesConnectedToThisVertex = getListOfEdgecodeConnectedToTheGivenVertex(vertexToPlaceSettlement);
        return (checkIf2ContinuousEdgeHaveRoadsAndBelongToSamePlayer(edgesConnectedToThisVertex,0,currentPplayerColor,"",""));
    }

    private List<String> getListOfEdgecodeConnectedToTheGivenVertex(HexCordinate vertexToPlaceSettlement) {
        List<String> listOfEdgeCode = getListOfAllEdgeCodesOnGameBoard();
        String v = vertexToPlaceSettlement.getvertexCordinateCode();
        List<String> edgesConnectedToThisVertex = getConnectedEdges(listOfEdgeCode, v);
        return edgesConnectedToThisVertex;
    }

    private List<String> getListOfAllEdgeCodesOnGameBoard() {
        List<String> listOfEdgeCode = gameBoard.getEdges().keySet().stream().toList();
        return listOfEdgeCode;
    }

    private List<String> getConnectedEdges(List<String> listOfEdgeCode, String v) {
        List<String> edgesConnectedToThisVertex = new ArrayList<>(listOfEdgeCode.stream().filter(s->s.contains(v)).toList());
        edgesConnectedToThisVertex.addAll(listOfEdgeCode.stream().filter(s->s.contains((String.valueOf(Integer.parseInt(v)+5)))).toList());
        edgesConnectedToThisVertex.addAll(listOfEdgeCode.stream().filter(s->s.contains((String.valueOf(Integer.parseInt(v)-5)))).toList());
        return edgesConnectedToThisVertex;
    }

    private boolean checkIf2ContinuousEdgeHaveRoadsAndBelongToSamePlayer(List<String> connectedEdges, int depth, String currentPlayerColor, String v1, String v2){
        HexEdge edge;
        if(depth==2){
            return true;
        }
        for (String edgeCode : connectedEdges){
            edge = gameBoard.getEdges().get(edgeCode);
            if(edge.getHasRoad()) {
                if (edge.getPlayerColor().equals(currentPlayerColor)) {
                    v1=edge.getEdgeCode().split("-")[0];
                    v2=edge.getEdgeCode().split("-")[1];
                    List<String> nextConnectedEdges=new ArrayList<>(edge.getListOfAdjacentEdgesToThisEdge());
                    String finalV = v1;
                    nextConnectedEdges.stream().filter(s->s.contains(finalV)).toList();
                    String finalV1 = v2;
                    nextConnectedEdges.stream().filter(s->s.contains((finalV1))).toList();
                    return checkIf2ContinuousEdgeHaveRoadsAndBelongToSamePlayer(nextConnectedEdges, depth + 1,currentPlayerColor,v1,v2);
                }
            }
        }
        return false;
    }

    private boolean nextVertexHasSettlement(HexCordinate vertexToPlaceSettlement) {
        List<String> connectedVertices = vertexToPlaceSettlement.getListOfAdjacentVerticesToThisVertex();
        for(String connectedVertex:connectedVertices){
            HexCordinate thisVertex =gameBoard.getVertices().get(connectedVertex);
            if(thisVertex== null){
                connectedVertex = String.valueOf(Integer.parseInt(connectedVertex)+5);
                thisVertex = gameBoard.getVertices().get(connectedVertex);
            }
            if(thisVertex.hasSettlement){
                return true;
            }
        }
        return false;
    }



    public void addSettlementToPlayerHashMap(String playerColor){
        gameBoard.getSettlementsOfEachPlayer().get(playerColor).add(this);
    }

    public String getvertexCordinateCode() {
        return vertexCordinateCode;
    }

}
