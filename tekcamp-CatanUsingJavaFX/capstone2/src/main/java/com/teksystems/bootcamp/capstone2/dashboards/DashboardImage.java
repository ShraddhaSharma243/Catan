package com.teksystems.bootcamp.capstone2.dashboards;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class DashboardImage extends ImageView {

    public DashboardImage() {

    }
    public DashboardImage(String imgURL, int x, int y) {
        Image playerName = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(playerName);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(FIT_WIDTH_RESOURCECARDIMAGE);
        this.setFitHeight(FIT_HEIGHT_RESOURCECARDIMAGE_AND_PLAYERNAMELABEL);
    }

    public static DashboardImage getResourceCardWool() {
        return resourceCardWool;
    }

    public static DashboardImage getResourceCardOre() {
        return resourceCardOre;
    }

    public static DashboardImage getResourceCardGrain() {
        return resourceCardGrain;
    }

    public static DashboardImage getResourceCardLumber() {
        return resourceCardLumber;
    }

    public static DashboardImage getResourceCardBrick() {
        return resourceCardBrick;
    }

    public static DashboardImage getCurrentPlayerName() {
        return currentPlayerName;
    }

    private static DashboardImage resourceCardWool = new DashboardImage(RESOURCECARD_WOOL,180,Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL );
    private static DashboardImage resourceCardOre = new DashboardImage(RESOURCECARD_ORE,300,Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL );
    private static DashboardImage resourceCardGrain = new DashboardImage(RESOURCECARD_GRAIN,420 ,Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL);
    private static DashboardImage resourceCardLumber = new DashboardImage(RESOURCECARD_LUMBER,540,Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL );
    private static DashboardImage resourceCardBrick = new DashboardImage(RESOURCECARD_BRICK,660,Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL );

    public static DashboardImage getResourceCardWoolForSceneTrade(int y) {
        return new DashboardImage(RESOURCECARD_WOOL,100,y );
    }

    public static DashboardImage getResourceCardOreForSceneTrade(int y) {
        return new DashboardImage(RESOURCECARD_ORE,230,y);
    }

    public static DashboardImage getResourceCardGrainForSceneTrade(int y) {
        return new DashboardImage(RESOURCECARD_GRAIN,360 ,y);
    }

    public static DashboardImage getResourceCardLumberForSceneTrade(int y) {
        return new DashboardImage(RESOURCECARD_LUMBER,490,y);
    }

    public static DashboardImage getResourceCardBrickForSceneTrade(int y) {
        return new DashboardImage(RESOURCECARD_BRICK,620, y);
    }



    private static DashboardImage currentPlayerName = new DashboardImage();
    public static void updateCurrentPlayerName(GameBoard gameBoard, String currentPlayerColor){
        String playerNameImage = gameBoard.getPlayers().get(currentPlayerColor).getPlayerNameImage();
        Image currentPlayerNameImg = new Image(String.valueOf(Catan.class.getResource(playerNameImage)));
        currentPlayerName.setImage(currentPlayerNameImg);
        currentPlayerName.setX(30);
        currentPlayerName.setY(Y_FOR_RESOURCECARD_AND_PLAYERNAMELABEL);
        currentPlayerName.setFitWidth(DASHBOARD_PLAERNAME_FIT_WIDTH);
        currentPlayerName.setFitHeight(FIT_HEIGHT_RESOURCECARDIMAGE_AND_PLAYERNAMELABEL);
        if(currentPlayerName != null) {
            gameBoard.getChildren().remove(currentPlayerName);
        }
        gameBoard.getChildren().add(currentPlayerName);
    }


}
