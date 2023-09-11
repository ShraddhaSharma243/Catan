package com.teksystems.bootcamp.capstone2.sceneObjects;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.gameobjects.Player;
import com.teksystems.bootcamp.capstone2.resourcecards.ResourceCard;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class LabelSceneGame {
    public static void updateCaptionsForNextPlayer(String currentPlayerColor, GameBoard gameBoard) {

        lblRolledNumber.updateCaption("");
        updateResourceCardCount(gameBoard,currentPlayerColor);
    }

    public Label getLabel() {
        return label;
    }

    private Label label;
    private String  caption;
    private int x;
    private int y;
    private Font font;
    private Color fillColor;

    public LabelSceneGame(String caption, int x, int y, Font font, Color fillColor) {
        this.caption = caption;
        this.x = x;
        this.y = y;
        this.font = font;
        this.fillColor = fillColor;
        label = new Label();
        setLabel();
    }

    private void setLabel() {
        label.setText(caption);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(font);
        label.setTextFill(fillColor);
    }

    public void updateCaption(String str){
        label.setText(str);
    }
    public static void updateResourceCardCount(GameBoard gameBoard, String currentPlayerColor){
        Player player = gameBoard.getPlayers().get(currentPlayerColor);
        lblResourceCardCountPasture.updateCaption(player.getResourceCards().get(PASTURE).getResourceCardCount().toString());
        lblResourceCardCountMountains.updateCaption(player.getResourceCards().get(MOUNTAINS).getResourceCardCount().toString());
        lblResourceCardCountField.updateCaption(player.getResourceCards().get(FIELD).getResourceCardCount().toString());
        lblResourceCardCountForest.updateCaption(player.getResourceCards().get(FOREST).getResourceCardCount().toString());
        lblResourceCardCountHill.updateCaption(player.getResourceCards().get(HILL).getResourceCardCount().toString());
    }

    public static LabelSceneGame lblRolledNumber = new LabelSceneGame("",130,
            Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +10, LABEL_ROLLED_DICE_FONT,Color.GOLD);
    public static  LabelSceneGame lblResourceCardCountPasture = new LabelSceneGame(
            "0", 230, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +15, LABEL_DASHBOARD_FONT,Color.GOLD
    );
    public static  LabelSceneGame lblResourceCardCountMountains = new LabelSceneGame(
            "0", 340, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +15, LABEL_DASHBOARD_FONT,Color.GOLD
    );
    public static  LabelSceneGame lblResourceCardCountField = new LabelSceneGame(
            "0", 460, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +15, LABEL_DASHBOARD_FONT,Color.GOLD
    );
    public static  LabelSceneGame lblResourceCardCountForest = new LabelSceneGame(
            "0", 590, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +15, LABEL_DASHBOARD_FONT,Color.GOLD
    );
    public static  LabelSceneGame lblResourceCardCountHill = new LabelSceneGame(
            "0", 700, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER +15, LABEL_DASHBOARD_FONT,Color.GOLD
    );

}
