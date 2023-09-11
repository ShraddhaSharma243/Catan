package com.teksystems.bootcamp.capstone2.scene;

import com.teksystems.bootcamp.capstone2.dashboards.DashboardImage;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Player;
import com.teksystems.bootcamp.capstone2.misclleneous.CatanButtons;
import com.teksystems.bootcamp.capstone2.sceneObjects.LabelSceneGame;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
public class SceneTrade extends Scene {
    int height = 160;
    int width= 180;
    int x = 120;
    int y = 90;
    GameBoard gameBoard;
    final ToggleGroup toggleGroupWhoWantsToTrade = new ToggleGroup();
    public SceneTrade(Stage stage, GameBoard gameBoard, String currentPlayerColor, Scene sceneGame) {
        super(new VBox(20), 800,800);
        VBox tradeScreenLayout = (VBox) this.getRoot();
        this.gameBoard = gameBoard;
        Pane whatYouAsking = getResourceCardPane("What are you asking :", x,y,height);
        Pane whatCanYouGive = getResourceCardPane("What can you give :", x , y, height);
        Pane whoWantsToTrade = getPlayerPane(toggleGroupWhoWantsToTrade, currentPlayerColor,  x,  y, width, height);
        Button tradeButton = new CatanButtons().getButton("",20);
        tradeButton.setText("Trade");
        tradeScreenLayout.getChildren().addAll(whatYouAsking, whatCanYouGive, whoWantsToTrade,tradeButton);
        tradeButton.setOnAction(actionEvent -> {
        trade(tradeScreenLayout, currentPlayerColor, sceneGame, stage);
    });
    }

    private void trade(VBox tradeScreenLayout, String currentPlayerColor, Scene sceneGame, Stage stage) {
        String playerColorWhoWantsToTrade;
        Boolean doesCurrentPlayerHasSufficentCards;
        Boolean doesPlayeWhoIsTradingrHasSufficentCards;
        Boolean bothPlayersHaveSufficientcardToTrade;
        HashMap<String , Integer> ask =  getTradingCards((Pane) tradeScreenLayout.getChildren().get(0));
        HashMap<String , Integer> give = getTradingCards((Pane) tradeScreenLayout.getChildren().get(1));
        RadioButton rdoPlayerWhoWantsToTrade = (RadioButton) toggleGroupWhoWantsToTrade.getSelectedToggle();
        if(rdoPlayerWhoWantsToTrade!=null) {
            playerColorWhoWantsToTrade    = rdoPlayerWhoWantsToTrade.getId();
            doesCurrentPlayerHasSufficentCards = playersHaveSufficientCardToExchange(currentPlayerColor, give);
            doesPlayeWhoIsTradingrHasSufficentCards =playersHaveSufficientCardToExchange(playerColorWhoWantsToTrade,ask);
           bothPlayersHaveSufficientcardToTrade = doesCurrentPlayerHasSufficentCards && doesPlayeWhoIsTradingrHasSufficentCards;
           if(bothPlayersHaveSufficientcardToTrade) {
               exchangeCards(currentPlayerColor, playerColorWhoWantsToTrade, ask, give);
           }
       }
       LabelSceneGame.updateResourceCardCount(gameBoard,currentPlayerColor);
       stage.setScene(sceneGame);
    }

    private void exchangeCards(String currentPlayerColor, String playerWhoWantsToTradeColor, HashMap<String ,
            Integer> cardsAsk, HashMap<String , Integer> cardsToGive) {
        Player player = gameBoard.getPlayers().get(currentPlayerColor);
        Player playerWhoWantsToTrade = gameBoard.getPlayers().get(playerWhoWantsToTradeColor);
        player.exchangeCards(cardsAsk, cardsToGive);
        playerWhoWantsToTrade.exchangeCards(cardsToGive,cardsAsk);
    }

    private boolean playersHaveSufficientCardToExchange(String playerColor,  HashMap<String , Integer> cardsToexchane) {
        Player player = gameBoard.getPlayers().get(playerColor);
        for (String card : cardsToexchane.keySet()) {
            if (player.getResourceCards().get(card).getResourceCardCount() < cardsToexchane.get(card)) {
                return false;
            }
        }
        return true;
    }
    private HashMap<String, Integer> getTradingCards(Pane pane) {
        HashMap<String, Integer> cardsToExchange = new HashMap<>();
    cardsToExchange.put(( pane.getChildren().get(6)).getId(),
            Integer.parseInt(((TextField)pane.getChildren().get(6)).getText()));
        cardsToExchange.put(( pane.getChildren().get(7)).getId(),
                Integer.parseInt(((TextField)pane.getChildren().get(7)).getText()));
        cardsToExchange.put(( pane.getChildren().get(8)).getId(),
                Integer.parseInt(((TextField)pane.getChildren().get(8)).getText()));
        cardsToExchange.put(( pane.getChildren().get(9)).getId(),
                Integer.parseInt(((TextField)pane.getChildren().get(9)).getText()));
        cardsToExchange.put(( pane.getChildren().get(10)).getId(),
                Integer.parseInt(((TextField)pane.getChildren().get(10)).getText()));
return  cardsToExchange;
    }

    private Pane getPlayerPane(ToggleGroup toggleGroupWhoWantsToTrade,  String currentPlayeColor, int x, int y, int width, int height) {
        Pane playerPane = new Pane();
        HashMap<String, Player> players = gameBoard.getPlayers();

        for(Player player : players.values()){
            if(!player.getPlayerColor().equals(currentPlayeColor)) {
                String playerName = player.getPlayerColor();
                RadioButton rd1 = new RadioButton(playerName);
                DashboardImage dashBoardImage = new DashboardImage(player.getPlayerNameImage(), x,y);
                rd1.setGraphic(dashBoardImage);
                rd1.setId(playerName);
                rd1.setLayoutX(x);
                rd1.setToggleGroup(toggleGroupWhoWantsToTrade);
                playerPane.getChildren().add(rd1);
                x = x + width ;
                y=y+height;
            }
        }
        return playerPane;
    }
    private Pane getResourceCardPane(String title, int x, int y, int height) {
        Pane pane = new Pane();
        Label heading = new Label(title);
        heading.setLayoutX(x);
        heading.setLayoutY(y-30);
        pane.getChildren().addAll(heading,
                DashboardImage.getResourceCardWoolForSceneTrade(y),
                DashboardImage.getResourceCardBrickForSceneTrade(y),
                DashboardImage.getResourceCardGrainForSceneTrade(y),
                DashboardImage.getResourceCardLumberForSceneTrade(y),
                DashboardImage.getResourceCardOreForSceneTrade(y));
        addResourceCardTextFields(x+30,y+height, pane);
        return pane;
    }

    private void addResourceCardTextFields(int x, int y, Pane pane ) {
        createTextBoxForEachResourceCardAndAddItToPan(x,y, pane, "Wool");
        createTextBoxForEachResourceCardAndAddItToPan(x+125,y, pane ,"Ore");
        createTextBoxForEachResourceCardAndAddItToPan(x+250,y, pane,"Grain");
        createTextBoxForEachResourceCardAndAddItToPan(x+380, y, pane,"Lumber");
        createTextBoxForEachResourceCardAndAddItToPan(x+515 ,y, pane,"Brick");
    }
    private void createTextBoxForEachResourceCardAndAddItToPan(int x,int y, Pane pane, String id) {
        TextField txtBox = new TextField("0");
        txtBox.setId(id);
        txtBox.setLayoutX(x);
        txtBox.setLayoutY(y);
        txtBox.setPrefColumnCount(2);
        pane.getChildren().addAll(txtBox);
    }
}
