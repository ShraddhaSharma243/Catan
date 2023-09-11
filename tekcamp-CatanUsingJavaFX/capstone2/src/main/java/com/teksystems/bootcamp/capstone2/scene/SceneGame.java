package com.teksystems.bootcamp.capstone2.scene;
import com.teksystems.bootcamp.capstone2.dashboards.DashboardImage;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Player;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdgeClicked;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexVertexClicked;
import com.teksystems.bootcamp.capstone2.piece.Dice;
import com.teksystems.bootcamp.capstone2.piece.Road;
import com.teksystems.bootcamp.capstone2.piece.Settlement;
import com.teksystems.bootcamp.capstone2.hex.Hex;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;
import com.teksystems.bootcamp.capstone2.hex.edge.HexEdge;
import com.teksystems.bootcamp.capstone2.misclleneous.*;
import com.teksystems.bootcamp.capstone2.resourcecards.ResourceCard;
import com.teksystems.bootcamp.capstone2.resourcecards.ResourceCard_Bank;
import com.teksystems.bootcamp.capstone2.sceneObjects.LabelSceneGame;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.*;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;
public class SceneGame extends Scene{
    Integer roundNumber = 0;
    String currentSettlementImgURL = "";
    String currentRoadImgURL="";
    Integer numberRolled;
   Player playerWhoHaslongestRoad;
   // Label lblRolledNumber;



    String currentPlayerColor = "Blue";
    List<String> playerColors= addPlayerColor();
    List<String> playerSettlementImageURLs = addPlayerSettlementImgUrl();
    List<String> playerRoadImageURLs = addPlayerRoadImgUrl();
    List<String> playerNameImageURLs = addPlayerNameImgURL();
    GameBoard gameBoard;
    HexEdge edgeFound;
    HexCordinate[] vertexFound = new HexCordinate[1];
    ResourceCard_Bank bank = new ResourceCard_Bank();
    Stage stage;


    public SceneGame(Stage stage, Integer numberOfPlayers) {
        super(new GameBoard(600,600), 800, 900);
     gameBoard = (GameBoard) getRoot();
     prepareGameBoard(numberOfPlayers, roundNumber);
     this.stage = stage;
    }

    private void prepareGameBoard(Integer numberOfPlayers, Integer roundNumber) {
        gameBoard.setNumbeOfPlayers(numberOfPlayers);
        addPlayer(numberOfPlayers);
        createEmptyRoadListForAllPlayers();
        createEmptySettlementListForAllPlayers();
        addButtonsOnTheBoard();
        setActionOnDice();
        setActionButtonNext();
        addLabels();
        setActionOnButtonSettlement();
        setActionOnnButtonRoad();
        setActionOnButtonTrade();
        disableButtons();
        addResourceCardImages();
        DashboardImage.updateCurrentPlayerName(gameBoard,currentPlayerColor);
       //updateLabels.setResourceCardCountLabels(currentPlayerColor);
        setActionOnGameBoard();
    }

    private void addResourceCardImages() {
        gameBoard.getChildren().addAll(DashboardImage.getResourceCardWool(),
                DashboardImage.getResourceCardBrick(),DashboardImage.getResourceCardLumber(),
                DashboardImage.getResourceCardOre(),DashboardImage.getResourceCardGrain());
    }

    private void addButtonsOnTheBoard() {
        gameBoard.getChildren().addAll(CatanButtons.btnDice.getButton(),
                CatanButtons.btnNext.getButton(),
                CatanButtons.btnDevelopmentCard.getButton(),
                CatanButtons.btnSettlement.getButton(),
                CatanButtons.btnRoad.getButton(),
                CatanButtons.btnTrade.getButton());

    }

    private void addLabels() {
        gameBoard.getChildren().addAll(LabelSceneGame.lblRolledNumber.getLabel(),
                LabelSceneGame.lblResourceCardCountPasture.getLabel(),
                LabelSceneGame.lblResourceCardCountMountains.getLabel(),
                LabelSceneGame.lblResourceCardCountField.getLabel(),
                LabelSceneGame.lblResourceCardCountForest.getLabel(),
                LabelSceneGame.lblResourceCardCountHill.getLabel());
    }

    private void setActionOnButtonTrade() {
        Button btnTrade = CatanButtons.btnTrade.getButton();
        btnTrade.setOnAction(e->stage.setScene(new SceneTrade(stage, gameBoard, currentPlayerColor, this )));

    }

    private void disableButtons() {
       CatanButtons.btnDevelopmentCard.setButtonDisable(true);
       // btnSettlement.setDisable(true);
      //  btnRoad.setDisable(true);
       CatanButtons.btnTrade.setButtonDisable(true);
    }

    private void setActionOnnButtonRoad() {
        Button btnRoad = CatanButtons.btnRoad.getButton();
        btnRoad.setOnAction(actionEvent -> {
            Player player = gameBoard.getPlayers().get(currentPlayerColor);
            boolean doesThePlayerHaveSufficientCardForARoad = player.checkResorceCardsForRoad();
            boolean playerDoesNotHaveRoadForRound1 = (roundNumber ==0 && gameBoard.getRoadsOfEachPlayer().get(player.getPlayerColor()).size()<1);
            boolean playerDoesNotHaveRoadForRound2 = (roundNumber ==1 && gameBoard.getRoadsOfEachPlayer().get(player.getPlayerColor()).size()<2);
            if(playerDoesNotHaveRoadForRound1 || playerDoesNotHaveRoadForRound2 || doesThePlayerHaveSufficientCardForARoad)
            {
                btnRoad.setDisable(true);
            }
        });
    }
    private void setActionOnButtonSettlement() {
        Button btnSettlement = CatanButtons.btnSettlement.getButton();
        btnSettlement.setOnAction(actionEvent -> {
            Player player = gameBoard.getPlayers().get(currentPlayerColor);
            boolean doesPlayerHaveSufficientcardsForAPlacement =player.checkResorceCardsForSettlement();
            boolean playerDoesNotHaveSettlementForRound1 = (roundNumber ==0 && gameBoard.getSettlementsOfEachPlayer().get(player.getPlayerColor()).size()<1);
            boolean playerDoesNotHaveSettlementForRound2 = (roundNumber ==1 && gameBoard.getSettlementsOfEachPlayer().get(player.getPlayerColor()).size()<2);
            if(playerDoesNotHaveSettlementForRound1 || playerDoesNotHaveSettlementForRound2 || doesPlayerHaveSufficientcardsForAPlacement){
                btnSettlement.setDisable(true);
            }
        });
    }
    private void setRoad( HexEdge edgeFound, Player player) {
            boolean edgeDoNotHaveARoad = !edgeFound.getHasRoad();
            if(edgeDoNotHaveARoad){
                if(roundNumber<=1){
                    placeRoadOnBoard(edgeFound,player,1);
                    return;
                }
                Integer roadDepthforConnectedRoad = edgeFound.checkConnectedRoads(currentPlayerColor );
                if( roadDepthforConnectedRoad!=-1){
                    player.giveCardsToBank("Road", bank);
                  //  updateLabels.removeResourceCardCountLabels();
                    LabelSceneGame.updateResourceCardCount(gameBoard,currentPlayerColor);
                    placeRoadOnBoard(edgeFound,player,roadDepthforConnectedRoad+1);
                    //check if round >2 and player still has card for road , then enable road button
                }
        }
    }
    private void setSettlement( HexCordinate vertexFound, Player player) {
        if (!vertexFound.getHasSettlement()) {
            if (roundNumber <= 1 && vertexFound.canSettlementBePlacedHereForFirst2Rounds(player.getPlayerColor())){
                placeSettlementOnBoard(player, vertexFound);
                player.getScore().increaseSettlementPoints();
                if(roundNumber==1){
                    distributeResourceForRound1(vertexFound,player);
                    LabelSceneGame.updateResourceCardCount(gameBoard,currentPlayerColor);
                }
                return;
            }
            boolean settlementCanBePlaced = vertexFound.canSettlementBePlacedHere(currentPlayerColor);
            if (settlementCanBePlaced) {
                player.giveCardsToBank("Settlement", bank);
                LabelSceneGame.updateResourceCardCount(gameBoard,currentPlayerColor);
                placeSettlementOnBoard(player, vertexFound);
                player.getScore().increaseSettlementPoints();
            }
        }
    }

    private void distributeResourceForRound1(HexCordinate vertexFound, Player player) {
        for(Hex hex : vertexFound.getListOfConnectedHexes()){
            addResourceToPlayerAndSubtractFromBank(hex.getTerrain(),player);
        }
    }
    private void setActionOnGameBoard() {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
           public void handle(MouseEvent mouseEvent) {
                edgeFound=null;
                vertexFound[0] = null;
                Player player = gameBoard.getPlayers().get(currentPlayerColor);
                if(CatanButtons.btnRoad.isButtonDisable()== true) {
                    edgeFound = new HexEdgeClicked(gameBoard).isClickCordinateMatchedWithEdge(mouseEvent.getX(),
                            mouseEvent.getY());
                    if(edgeFound!=null) {
                        setRoad(edgeFound, player);
                        enableButtons();
                    }
                }
                if(CatanButtons.btnSettlement.isButtonDisable()== true) {
                    vertexFound[0] = new HexVertexClicked(gameBoard).isClickCordinateMatchedWithVertices(mouseEvent.getX(), mouseEvent.getY());
                    if(vertexFound[0]!=null) {
                        setSettlement(vertexFound[0], player);
                    enableButtons();
                    }
                }
            }
        });
    }
    private void placeRoadOnBoard(HexEdge edgeFound, Player player, Integer roadDepth) {
        currentRoadImgURL = player.getRoadImgURL();
        Road rd = new Road(edgeFound, currentRoadImgURL);
        rd.setRotate(edgeFound.getAngle());
        rd.setX(edgeFound.getStartX() + edgeFound.getxOffSet());
        rd.setY(edgeFound.getStartY() + edgeFound.getyOffSet());
        gameBoard.getChildren().add(rd);
        edgeFound.setHasRoadAndPlayerColor(true, currentPlayerColor);
        edgeFound.addRoadToPlayerHashMap(currentPlayerColor);
        edgeFound.setRoadDepth(roadDepth);
        player.getScore().setMaximumDepthOfRoad(roadDepth);
            if((playerWhoHaslongestRoad==null ||
                    playerWhoHaslongestRoad.getScore().getMaximumDepthOfRoad()<roadDepth)
                    && roadDepth>=7){
                playerWhoHaslongestRoad.getScore().setLongestRoad(false);
                playerWhoHaslongestRoad=player;
                player.getScore().setLongestRoad(true);
            }
    }
    private void placeSettlementOnBoard(Player player, HexCordinate vertexFound) {
        currentSettlementImgURL = player.getSettlementImgURL();
        gameBoard.getChildren().add(new Settlement(vertexFound, currentSettlementImgURL, gameBoard));
        vertexFound.setHasSettlementAndPlayerColor(true, currentPlayerColor);
        vertexFound.addSettlementToPlayerHashMap(currentPlayerColor);
    }
    private void setActionButtonNext() {
        Button btn = CatanButtons.btnNext.getButton();
        btn.setOnAction(actionEvent -> {
            currentPlayerColor=getNextPlayer(currentPlayerColor);
            if(currentPlayerColor.equals("Blue") && roundNumber<2){
                roundNumber+=1;
            }
            if(roundNumber == 2){
                enableButtons();
            }
            DashboardImage.updateCurrentPlayerName(gameBoard,currentPlayerColor);
            LabelSceneGame.updateCaptionsForNextPlayer(currentPlayerColor,gameBoard);
         //  updateLabels.updateLabelRollNumber("",lblRolledNumber);
            CatanButtons.btnDice.setButtonDisable(false);
        });
    }

    private void enableButtons() {
        CatanButtons.btnDevelopmentCard.setButtonDisable(false);
        CatanButtons.btnSettlement.setButtonDisable(false);
        CatanButtons.btnRoad.setButtonDisable(false);
        CatanButtons.btnTrade.setButtonDisable(false);
    }

    private void setActionOnDice() {
        Button btn = CatanButtons.btnDice.getButton();
        btn.setOnAction(actionEvent -> {
            //each player should be able to roll only once
            numberRolled = new Dice().rollDice();
            LabelSceneGame.lblRolledNumber.updateCaption(numberRolled.toString());
            findRightHexAndDistributeResources(numberRolled);
LabelSceneGame.updateResourceCardCount(gameBoard,currentPlayerColor);
           //updateLabels.setResourceCardCountLabels(currentPlayerColor);
            btn.setDisable(true);
        });
    }



    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);

    }
    private String getNextPlayer(String currentPlayerColor){
      int totalNumberOfPlayers = gameBoard.getNumbeOfPlayers();
      int currentPlayerIndex = playerColors.indexOf(currentPlayerColor);
      int nextPlayerIndex;
      String nextPlayer;
        nextPlayerIndex= currentPlayerIndex < totalNumberOfPlayers - 1 ? currentPlayerIndex + 1 : 0;
        nextPlayer = playerColors.get(nextPlayerIndex);
        return nextPlayer;
    }







    private List<String> addPlayerColor() {
        List<String> colors = new ArrayList<>();
        colors.add(BLUE);
        colors.add(RED);
        colors.add(ORANGE);
        colors.add(WHITE);
        return colors;
    }
    private void addPlayer(Integer numberOfPlayers) {
        String playerColor;
        for(int i=0; i<numberOfPlayers; i++){
            playerColor=playerColors.get(i);
            gameBoard.getPlayers().put(playerColor,
                    new Player(playerColor, playerNameImageURLs.get(i),playerSettlementImageURLs.get(i),
                            playerRoadImageURLs.get(i) ));
        }
    }
    private void createEmptyRoadListForAllPlayers() {
        for(String player : gameBoard.getPlayers().keySet()){
          gameBoard.getRoadsOfEachPlayer().put(player, new ArrayList<>());
        }
    }
    private void createEmptySettlementListForAllPlayers() {
        for(String player : gameBoard.getPlayers().keySet()){
            gameBoard.getSettlementsOfEachPlayer().put(player, new ArrayList<>());
        }
    }
    private void findRightHexAndDistributeResources(Integer numberRolled) {
        for (int i = 0; i < gameBoard.getChildren().size(); i++) {
            if (gameBoard.getChildren().get(i).getClass().equals(Hex.class)) {
                Hex isThisTheHex = findHexWithNumberRolledToken(gameBoard.getChildren().get(i), numberRolled);
                if (isThisTheHex != null) {
                    findTheVertexThatHasSettlement(isThisTheHex);
                }
            }
        }
    }

    private void findTheVertexThatHasSettlement(Hex hexWithRolledNumber) {
        List<HexCordinate> vertexOnCurrentHex ;
        vertexOnCurrentHex = hexWithRolledNumber.getHexVertices();
        for (HexCordinate vertex : vertexOnCurrentHex) {
            if (vertex.getHasSettlement()) {
                distributeResources(hexWithRolledNumber.getTerrain(),vertex.getPlayerColor());
            }
        }
    }

    private void distributeResources(String terrain, String playerColor) {
        Player player = gameBoard.getPlayers().get(playerColor);
                addResourceToPlayerAndSubtractFromBank(terrain, player);
    }
    private void addResourceToPlayerAndSubtractFromBank(String terrain, Player player) {
        ResourceCard resourceCardToBeAdded =  player.getResourceCards().get(terrain);
        resourceCardToBeAdded.addOneResourceCard();
        bank.getResourceCards().get(terrain).subtractCard(1);
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