package com.teksystems.bootcamp.capstone2.hex;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FormatEdgesAndVertices {
    GameBoard gameBoard;
    public FormatEdgesAndVertices(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    public void addAdjacentEdgesAndVetricesToEachOther() {
        addAjacentVertexToTheEdgeIfVertexIsNotNull();
        addAdjacentVerticesToVertex();
        addAdjacentEdgesToEachEdge();
    }
    public void addAjacentVertexToTheEdgeIfVertexIsNotNull() {
        for (String edgeCode : gameBoard.getEdges().keySet()) {
            String[] xy = edgeCode.split("-");
            for (String s : xy) {
                String vertexCode = getCorrectVertexCode(s);
                gameBoard.getEdges().get(edgeCode).getListOfConnecedVertexToThisEdge().add(vertexCode);
            }
        }
    }
    private String getCorrectVertexCode(String s) {
        HexCordinate x = gameBoard.getVertices().get(s);
        if (x == null){
            s= String.valueOf(Integer.parseInt(s)+5);
            x = gameBoard.getVertices().get(s);
        }
        return x.getvertexCordinateCode();
    }
    private void addAdjacentVerticesToVertex() {
        for(String vertexCode : gameBoard.getVertices().keySet()){
            List<String> adjacentEdges = getEdgesOfTheVertex(vertexCode);
            for(String edgeCode: adjacentEdges){
                List<String> verticesCodes = Arrays.stream(edgeCode.split("-")).toList();
                verticesCodes = fixVerticesCodes(verticesCodes);
                int i = (verticesCodes.indexOf(vertexCode)==0)?1:0;
                if(verticesCodes != null){
                    gameBoard.getVertices().get(vertexCode).getListOfAdjacentVerticesToThisVertex().add(verticesCodes.get(i));
                }
            }
        }

    }
     private List<String> fixVerticesCodes(List<String> verticesCodes) {
        List<String > vCodes = new ArrayList<>();
        for(String v: verticesCodes){
            vCodes.add(getCorrectVertexCode(v));
        }
        return vCodes;
    }
    private void addAdjacentEdgesToEachEdge() {
        for(String edgeCode:gameBoard.getEdges().keySet()){
            List<String> verticesOnThisEdge = Arrays.stream(edgeCode.split("-")).toList();
            for(String v: verticesOnThisEdge){
               List<String> edgesConnectedToThisVertex = getEdgesOfTheVertex(v);
               boolean b =  edgesConnectedToThisVertex.remove(edgeCode);
                gameBoard.getEdges().get(edgeCode).getListOfAdjacentEdgesToThisEdge().addAll(edgesConnectedToThisVertex);
            }
        }
    }
    private List<String> getEdgesOfTheVertex(String v){
        List<String> listOfEdgeCode = gameBoard.getEdges().keySet().stream().toList();
        List<String> edgesConnectedToThisVertex = new ArrayList<>(listOfEdgeCode.stream().filter(s->s.contains(v)).toList());
        edgesConnectedToThisVertex.addAll(listOfEdgeCode.stream().filter(s->s.contains((String.valueOf(Integer.parseInt(v)+5)))).toList());
        edgesConnectedToThisVertex.addAll(listOfEdgeCode.stream().filter(s->s.contains((String.valueOf(Integer.parseInt(v)-5)))).toList());
        return edgesConnectedToThisVertex;

    }
}