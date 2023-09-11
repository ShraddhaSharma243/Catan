package com.teksystems.bootcamp.capstone2.scene;

import com.teksystems.bootcamp.capstone2.dashboards.DashBoardImages;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Player;
import com.teksystems.bootcamp.capstone2.gameobjects.Settlement;
import com.teksystems.bootcamp.capstone2.hex.Hex;
import com.teksystems.bootcamp.capstone2.hex.HexCordinate;
import com.teksystems.bootcamp.capstone2.hex.HexEdge;
import com.teksystems.bootcamp.capstone2.misclleneous.CatanButtons;
import com.teksystems.bootcamp.capstone2.misclleneous.Dice;
import com.teksystems.bootcamp.capstone2.misclleneous.MatchingMouseClickToHexEdge;
import com.teksystems.bootcamp.capstone2.misclleneous.MatchingMouseClickToHexVertices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;
public class SceneGame extends Scene{
    public static final int DASHBOARD_FIT_WIDTH = 120;
    public static final int DASHBOARD_PLAERNAME_FIT_WIDTH = 150;
    public static final int DASHBOARD_FIT_HEIGHT = 150;
    public static final int DASHBOARD_Y = 650;
    public static final int DASHBOARD_TOP_LINE_Y = 590;
    public static final int DASHBOARD_TOP_LINE_FIT_HEIGHT = 40;
    Integer currentPlayer = 0;
    boolean isItFirstRound = true;
    String currentSettlementImgURL = "";
    String currentRoadImgURL="";
    DashBoardImages currentPlayerNameOnDashBoard;
    DashBoardImages resourceCardWool;
    DashBoardImages resourceCardOre;
    DashBoardImages resourceCardGrain;
    DashBoardImages resourceCardLumber;
    DashBoardImages resourceCardBrick;

    Label lblWoolRsourceCardEachPlayerCount;
    Label lblOreRsourceCardEachPlayerCount;
    Label lblGrainRsourceCardEachPlayerCount;
    Label lblLumberRsourceCardEachPlayerCount;
    Label lblBrickRsourceCardEachPlayerCount;

    Button btnDice;
    Integer numberRolled;
    Label lblRolledNumber;
    String currentPlayerColor = "";
    List<String> playerColors= addPlayerColor();
    List<String> playerSettlementImageURLs = addPlayerSettlementImgUrl();
    List<String> playerRoadImageURLs = addPlayerRoadImgUrl();
    List<String> playerNamesURLs = addPlayerNameImgURL();


    public SceneGame(Stage stage, Integer numberOfPlayers) {
        super(new GameBoard(600,600), 800, 800);
    GameBoard gameBoard = (GameBoard) getRoot();
        gameBoard.setNumbeOfPlayers(numberOfPlayers);
        addPlayer(numberOfPlayers,gameBoard);
        createEmptyRoadListForAllPlayers(gameBoard);
        createEmptySettlementListForAllPlayers(gameBoard);
        btnDice = new CatanButtons().getButton(IMG_CATAN_DICE, DASHBOARD_TOP_LINE_FIT_HEIGHT);
        btnDice.setLayoutX(50);
        btnDice.setLayoutY(DASHBOARD_TOP_LINE_Y);

        lblRolledNumber= getLabel(gameBoard,0,"",130,DASHBOARD_TOP_LINE_Y+10,LABEL_ROLLED_DICE_FONT, Color.GOLD);
        currentPlayerNameOnDashBoard =
                new DashBoardImages(gameBoard.getPlayers().get(currentPlayer).getPlayerName(),
                        30, DASHBOARD_Y, DASHBOARD_PLAERNAME_FIT_WIDTH, DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().add(currentPlayerNameOnDashBoard);
        setResourceCardCountLabels(gameBoard);
        btnDice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //each player should be able to roll only once
                numberRolled = new Dice().rollDice();
                updateLabelRollNumber(gameBoard,numberRolled.toString());
                findRightHexAndDistributeResources(numberRolled, gameBoard);

                removeResourceCardCountLabels(gameBoard);
                setResourceCardCountLabels(gameBoard);

                btnDice.setDisable(true);
            }
        });
        gameBoard.getChildren().add(btnDice);

// Refactor it to factory method
        MatchingMouseClickToHexVertices matchClick = new MatchingMouseClickToHexVertices();

        MatchingMouseClickToHexEdge matchClickOnEdge = new MatchingMouseClickToHexEdge();
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                HexCordinate vertexFound = matchClick.isClickCordinateMatchedWithVertices(mouseEvent.getX(), mouseEvent.getY(), gameBoard);
                currentPlayerColor = gameBoard.getPlayers().get(currentPlayer).getPlayerColor();
                if (vertexFound != null) {
                    if (!vertexFound.getHasSettlement()) {
                        if(vertexFound.canSettlementBePlacedHere(currentPlayerColor,vertexFound)){
                            currentSettlementImgURL = gameBoard.getPlayers().get(currentPlayer).getSettlementImgURL();
                            gameBoard.getChildren().add(new Settlement(vertexFound, currentSettlementImgURL,gameBoard));
                            vertexFound.setHasSettlementAndPlayerColor(true, currentPlayerColor);
                            vertexFound.addSettlementToPlayerHashMap(currentPlayerColor);

                        }
                }

                     }
                // refactor it
                HexEdge edgeClicked = matchClickOnEdge.isClickCordinateMatchedWithEdge(mouseEvent.getX(), mouseEvent.getY(), gameBoard);
                boolean isItAnEdge =(edgeClicked != null)?true:false;
                if(isItAnEdge) {
                    boolean edgeDoNotHaveARoad = !edgeClicked.getHasRoad();
                    boolean newRoadIsConnectedToexistingRoadForSamePlayer = edgeClicked.checkConnectedRoads(currentPlayerColor, isItFirstRound);
                    if (edgeDoNotHaveARoad && newRoadIsConnectedToexistingRoadForSamePlayer) {
                        currentRoadImgURL = gameBoard.getPlayers().get(currentPlayer).getRoadImgURL();
                        Road rd = new Road(edgeClicked, currentRoadImgURL);
                        rd.setRotate(edgeClicked.getAngle());
                        rd.setX(edgeClicked.getStartX() + edgeClicked.getxOffSet());
                        rd.setY(edgeClicked.getStartY() + edgeClicked.getyOffSet());


                        gameBoard.getChildren().add(rd);
                        //gameBoard.getChildren().add(new Road(vertexFound,currentRoadImgUrl));
                        edgeClicked.setHasRoadAndPlayerColor(true, currentPlayerColor);
                        edgeClicked.addRoadToPlayerHashMap(currentPlayerColor);
                    }
                }
            }
        });

        Button btnNext =new CatanButtons().getButton(IMF_BTN_NEXTPLAYER,DASHBOARD_TOP_LINE_FIT_HEIGHT);
        btnNext.setLayoutX(700);
        btnNext.setLayoutY(10);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentPlayer = currentPlayer < gameBoard.getNumbeOfPlayers() - 1 ? currentPlayer + 1 : 0;
                updateCurrentPlayerName(gameBoard);

                removeResourceCardCountLabels(gameBoard);
                setResourceCardCountLabels(gameBoard);

                updateLabelRollNumber(gameBoard,"");
                btnDice.setDisable(false);
            }
        });
        gameBoard.getChildren().add(btnNext);
    }

    private void updateLabelRollNumber(GameBoard gameBoard, String str) {
        gameBoard.getChildren().remove(lblRolledNumber);
        lblRolledNumber.setText(str);
        gameBoard.getChildren().add(lblRolledNumber);
    }

    private void removeResourceCardCountLabels(GameBoard gameBoard) {
        gameBoard.getChildren().remove(lblWoolRsourceCardEachPlayerCount);
        gameBoard.getChildren().remove(lblOreRsourceCardEachPlayerCount);
        gameBoard.getChildren().remove(lblGrainRsourceCardEachPlayerCount);
        gameBoard.getChildren().remove(lblLumberRsourceCardEachPlayerCount);
        gameBoard.getChildren().remove(lblBrickRsourceCardEachPlayerCount);
    }

    private void setResourceCardCountLabels(GameBoard gameBoard) {
        lblWoolRsourceCardEachPlayerCount =
                getLabel(gameBoard,currentPlayer,PASTURE,
                        230,DASHBOARD_TOP_LINE_Y+15,LABEL_DASHBOARD_FONT,Color.GOLD);
        resourceCardWool = new DashBoardImages(RESOURCECARD_WOOL,180,DASHBOARD_Y, DASHBOARD_FIT_WIDTH,DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().addAll(lblWoolRsourceCardEachPlayerCount, resourceCardWool);

        lblOreRsourceCardEachPlayerCount =
                getLabel(gameBoard,currentPlayer,MOUNTAINS,
                        340,DASHBOARD_TOP_LINE_Y+15,LABEL_DASHBOARD_FONT,Color.GOLD);
        resourceCardOre = new DashBoardImages(RESOURCECARD_ORE,300,DASHBOARD_Y,DASHBOARD_FIT_WIDTH,DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().addAll(lblOreRsourceCardEachPlayerCount, resourceCardOre);

        lblGrainRsourceCardEachPlayerCount =
                getLabel(gameBoard,currentPlayer,FIELD,
                        460,DASHBOARD_TOP_LINE_Y+15,LABEL_DASHBOARD_FONT,Color.GOLD);
        resourceCardGrain = new DashBoardImages(RESOURCECARD_GRAIN,420,DASHBOARD_Y,DASHBOARD_FIT_WIDTH,DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().addAll(lblGrainRsourceCardEachPlayerCount, resourceCardGrain);

        lblLumberRsourceCardEachPlayerCount =
                getLabel(gameBoard,currentPlayer,FOREST,
                        590,DASHBOARD_TOP_LINE_Y+15,LABEL_DASHBOARD_FONT,Color.GOLD);
        resourceCardLumber = new DashBoardImages(RESOURCECARD_LUMBER,540,DASHBOARD_Y,DASHBOARD_FIT_WIDTH,DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().addAll(lblLumberRsourceCardEachPlayerCount, resourceCardLumber);

        lblBrickRsourceCardEachPlayerCount =
                getLabel(gameBoard,currentPlayer,HILL,
                        700,DASHBOARD_TOP_LINE_Y+15,LABEL_DASHBOARD_FONT,Color.GOLD);
        resourceCardBrick = new DashBoardImages(RESOURCECARD_BRICK,660,DASHBOARD_Y,DASHBOARD_FIT_WIDTH,DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().addAll(lblBrickRsourceCardEachPlayerCount, resourceCardBrick);
    }

    private void updateCurrentPlayerName(GameBoard gameBoard) {
        gameBoard.getChildren().remove(currentPlayerNameOnDashBoard);
        currentPlayerNameOnDashBoard =
                new DashBoardImages(gameBoard.getPlayers().get(currentPlayer).getPlayerName(),
                        30, DASHBOARD_Y, DASHBOARD_PLAERNAME_FIT_WIDTH, DASHBOARD_FIT_HEIGHT);
        gameBoard.getChildren().add(currentPlayerNameOnDashBoard);
    }



    private Label getLabel(GameBoard gameBoard, int currentPlayer,String terrain, int X, int layoutY, Font font,Color color) {
        Label lbl = new Label();
        String txt="";
        switch (terrain){
            case PASTURE:{
                txt=gameBoard.getPlayers().get(currentPlayer).getResourceCard_wool().getResourceCardWoolCount().toString();
                break;
            }
            case MOUNTAINS:{
                txt=gameBoard.getPlayers().get(currentPlayer).getResourceCard_ore().getResourceCardOreCount().toString();
                break;
            }
            case FIELD:{
                txt=gameBoard.getPlayers().get(currentPlayer).getResourceCard_grain().getResourceCardGrainCount().toString();
                break;
            }
            case FOREST:{
                txt=gameBoard.getPlayers().get(currentPlayer).getResourceCard_lumber().getResourceCardLumberCount().toString();
                break;
            }
            case HILL:{
                txt=gameBoard.getPlayers().get(currentPlayer).getResourceCard_brick().getResourceCardBrickCount().toString();
                break;
            }
        }

        lbl.setText(txt);
        lbl.setLayoutX(X);
        lbl.setLayoutY(layoutY);
        lbl.setFont(font);
        lbl.setTextFill(color);
        return lbl;
    }

    private List<String> addPlayerColor() {
        List<String> colors = new ArrayList<>();
        colors.add(BLUE);
        colors.add(RED);
        colors.add(ORANGE);
        colors.add(WHITE);
        return colors;
    }
    private void addPlayer(Integer numberOfPlayers, GameBoard gameBoard) {
        for(int i=0; i<numberOfPlayers; i++){
            gameBoard.getPlayers().add(
                    new Player(playerColors.get(i),playerNamesURLs.get(i),playerSettlementImageURLs.get(i),playerRoadImageURLs.get(i) ));
        }
    }
    private void createEmptyRoadListForAllPlayers(GameBoard gameBoard) {
        for(Player player : gameBoard.getPlayers()){
          gameBoard.getRoadsOfEachPlayer().put(player.getPlayerColor(), new ArrayList<HexEdge>());
        }
    }
    private void createEmptySettlementListForAllPlayers(GameBoard gameBoard) {
        for(Player player : gameBoard.getPlayers()){
            gameBoard.getSettlementsOfEachPlayer().put(player.getPlayerColor(), new ArrayList<HexCordinate>());
        }
    }
    private void findRightHexAndDistributeResources(Integer numberRolled, GameBoard gameBoard) {
        for (int i = 0; i < gameBoard.getChildren().size(); i++) {
            if (gameBoard.getChildren().get(i).getClass().equals(Hex.class)) {
                Hex isThisTheHex = findHexWithNumberRolledToken(gameBoard.getChildren().get(i), numberRolled);
                if (isThisTheHex != null) {
                    findTheVertexThatHasSettlement(isThisTheHex, gameBoard);
                }
            }
        }
    }

    private void findTheVertexThatHasSettlement(Hex hexWithRolledNumber, GameBoard  gameBoard) {
        List<HexCordinate> vertexOnCurrentHex = new ArrayList<>();
        vertexOnCurrentHex = hexWithRolledNumber.getHexVertices();
        for (HexCordinate vertex : vertexOnCurrentHex) {
            if (vertex.getHasSettlement()) {
                distributResources(hexWithRolledNumber.getTerrain(),vertex.getPlayerColor(), gameBoard);
            }
        }
    }

    private void distributResources(String terrain, String playerColor, GameBoard gameBoard) {
        for(Player player: gameBoard.getPlayers()){
            if(player.getPlayerColor().equals(playerColor)){
                addResource(terrain, player);
            }
        }
    }
    private void addResource(String terrain, Player player) {
        switch (terrain){
            case PASTURE:{
                player.getResourceCard_wool().addResourceCardWool();
                break;
            }
            case MOUNTAINS:{
                player.getResourceCard_ore().addResourceCardOre();
                break;
            }
            case FIELD:{
                player.getResourceCard_grain().addResourceGrainGrain();
                break;
            }
            case FOREST:{
                player.getResourceCard_lumber().addResourcecardLumber();
                break;
            }
            case HILL:{
                player.getResourceCard_brick().addResourceCardBrick();
            }
        }
    }


    private Hex findHexWithNumberRolledToken(Node isThisTheHex, Integer numberRolled) {
        Hex hexWithRolledNumber = (Hex) isThisTheHex;
        if (Objects.equals(hexWithRolledNumber.getTokenNumber(), numberRolled)) {
            return hexWithRolledNumber;
        }
        return null;
    }
    private List<String> addPlayerSettlementImgUrl() {
        List<String> settlementImages = new ArrayList<>();
        settlementImages.add(IMGURL_SETTLEMENT_BLUE);
        settlementImages.add(IMGURL_SETTLEMENT_RED);
        settlementImages.add(IMGURL_SETTLEMENT_ORANGE);
        settlementImages.add(IMGURL_SETTLEMENT_WHITE);
        return settlementImages;
    }
    private List<String> addPlayerRoadImgUrl() {
        List<String> roadImages = new ArrayList<>();
        roadImages.add(IMGURL_ROAD_BLUE);
        roadImages.add(IMGURL_ROAD_RED);
        roadImages.add(IMGURL_ROAD_ORANGE);
        roadImages.add(IMGURL_ROAD_WHITE);
        return roadImages;
    }
    private List<String> addPlayerNameImgURL() {
        List<String> playerNameImages = new ArrayList<>();
        playerNameImages.add(IMGURL_PPLAYER_BLUE);
        playerNameImages.add(IMGURL_PPLAYER_RED);
        playerNameImages.add(IMGURL_PPLAYER_ORANGE);
        playerNameImages.add(IMGURL_PPLAYER_WHITE);
        return playerNameImages;
    }

}