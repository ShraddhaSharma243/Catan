package com.teksystems.bootcamp.capstone2.gameobjects;
import com.teksystems.bootcamp.capstone2.hex.*;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdge;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdges;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexVertices;
import com.teksystems.bootcamp.capstone2.hex.FormatEdgesAndVertices;
import com.teksystems.bootcamp.capstone2.misclleneous.ImageViewWithAnImage;
import com.teksystems.bootcamp.capstone2.misclleneous.TokenNumber;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class GameBoard extends Group {

    private final List<HexCordinate> centerPoints;
    private final HashMap<String,HexCordinate> vertices;
    private final HashMap<String, HexEdge> edges;
    private HashMap<String, List<HexEdge>> roadsOfEachPlayer = new HashMap<>();
    private final HashMap<String,Player> players;
    private Integer numbeOfPlayers;
    private Integer adjustInX = 100;
    private HashMap<String, List<HexCordinate>> settlemetsOfEachPlayer = new HashMap<>();

    public HashMap<String, Button> getButtonsOnTheBoard() {
        return buttonsOnTheBoard;
    }

    private HashMap<String, Button> buttonsOnTheBoard = new HashMap<>();
    public List<TokenNumber> getTokeNumbers() {
        return tokeNumbers;
    }
    private final List<TokenNumber> tokeNumbers = new ArrayList<>();
    @Override
    public ObservableList<Node> getChildren() {
        return children;
    }
    private final ObservableList<Node> children;
    public GameBoard(int w, int h) {
        children = super.getChildren();
        centerPoints = new ArrayList<>();
        vertices = new HashMap<>();
        edges = new HashMap<>();
        players = new HashMap<>();
// TODO  make randomize, and check token number
        children.add(new ImageViewWithAnImage().getImageViewWithAnImage(IMG_CATAN_GAMEBOARD_BACKGROUND, 900, 800));
        children.add(new Hex(adjustInX, 0, 600, IMG_BIG_HEX, -1, null));
        initializeHex(0, 1, h, HEX_PASTURE, 12, PASTURE, this);
        initializeHex(0, 3, h, HEX_FIELD, 2, FIELD, this);
        initializeHex(0, 2, h, HEX_MOUNTAINS, 9, MOUNTAINS, this);
        initializeHex(1, 0, h, HEX_FOREST, 12, FOREST, this);
        initializeHex(1, 1, h, HEX_HILL, 2, HILL, this);
        initializeHex(1, 2, h, HEX_PASTURE, 6, PASTURE, this);
        initializeHex(1, 3, h, HEX_HILL, 5, HILL, this);
        initializeHex(2, 0, h, HEX_MOUNTAINS, 8, MOUNTAINS, this);
        initializeHex(2, 1, h, HEX_FIELD, 8, FIELD, this);
        initializeHex(2, 2, h, HEX_PASTURE, 3, PASTURE, this);
        initializeHex(2, 3, h, HEX_MOUNTAINS, 11, MOUNTAINS, this);
        initializeHex(2, 4, h, HEX_FIELD, 10, FIELD, this);
        initializeHex(3, 0, h, HEX_FOREST, 4, FOREST, this);
        initializeHex(3, 1, h, HEX_HILL, 9, HILL, this);
        initializeHex(3, 2, h, HEX_PASTURE, 3, PASTURE, this);
        initializeHex(3, 3, h, HEX_FOREST, 6, FOREST, this);
        initializeHex(4, 1, h, HEX_MOUNTAINS, 5, MOUNTAINS, this);
        initializeHex(4, 2, h, HEX_FIELD, 4, FIELD, this);
        initializeHex(4, 3, h, HEX_FOREST, 8, FOREST, this);
        children.addAll(tokeNumbers);
        formatEdgesAndVertices();
        children.addAll(new ImageViewWithAnImage().getImageViewWithAnImage(IMG_CATAN_LOGO, 75, 200));

    }

    private void formatEdgesAndVertices() {
        new FormatEdgesAndVertices(this).addAdjacentEdgesAndVetricesToEachOther();
    }
    public void initializeHex(int row, int column, int windowHeight, String imgURL, int tokenNumber, String terrain, GameBoard gameBoard) {
        {
            int A12th = windowHeight/12;
            int A6th = windowHeight/6;
            int adjust = windowHeight/20;
            int x=(A12th)+((A6th)*column)+row%2*A12th + adjustInX;
            int y=(A12th)+((A6th)*row)-adjust*row;
            Hex hex = new Hex(x,y+50,A6th,imgURL,tokenNumber,terrain);
            //change it to Hashmap<Toke, ListOfHex
            gameBoard.getChildren().add(hex);
            hex.setHexVertices(new HexVertices(gameBoard).getAllVertices(x,y+50,A6th,hex));
            hex.setEdges(new HexEdges(gameBoard).getAllEdges(x,y+50,A6th));
            hex.setCenterPoint( new HexCordinate(x+(A12th),y+(A12th+50), "0", gameBoard,hex));
            gameBoard.getTokeNumbers().add(new TokenNumber(windowHeight,hex.getCenterPoint(),tokenNumber+".png"));
        }
    }
    public HashMap<String, HexCordinate> getVertices() {
        return vertices;
    }
    public HashMap<String, HexEdge> getEdges(){
         return edges;
}
    public HashMap<String,Player> getPlayers() {
        return players;
    }
    public Integer getNumbeOfPlayers() {
        return numbeOfPlayers;
    }
    public void setNumbeOfPlayers(Integer numbeOfPlayers) {
        this.numbeOfPlayers = numbeOfPlayers;
    }
    public HashMap<String, List<HexEdge>> getRoadsOfEachPlayer() {
        return roadsOfEachPlayer;
    }
    public HashMap<String, List<HexCordinate>> getSettlementsOfEachPlayer() {
         return settlemetsOfEachPlayer;
    }
}
