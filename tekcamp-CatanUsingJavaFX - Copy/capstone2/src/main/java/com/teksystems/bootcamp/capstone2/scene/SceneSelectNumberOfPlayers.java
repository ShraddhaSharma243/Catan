package com.teksystems.bootcamp.capstone2.scene;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.misclleneous.CatanButtons;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class SceneSelectNumberOfPlayers extends Scene {
    public SceneSelectNumberOfPlayers(Stage stage) {
        super(new VBox(20), 800,800);
        VBox selectNumberOfPlayersScreenLayout = (VBox) this.getRoot();

        ToggleGroup toggleGroup = new ToggleGroup();
        Label heading = new Label("Select number of players!");

        RadioButton rdo2Player= getRadioButtonWithAllSettings("2 Players", -715);
        rdo2Player.setToggleGroup(toggleGroup);
        rdo2Player.setSelected(true);

        RadioButton rdo3Player = getRadioButtonWithAllSettings("3 Players", - 705);
        rdo3Player.setToggleGroup(toggleGroup);

        RadioButton rdo4Player = getRadioButtonWithAllSettings("4 Players", -695);
        rdo4Player.setToggleGroup(toggleGroup);

        heading.setTranslateX(550);
        heading.setTranslateY(-720);
        heading.setFont(RDOBUTTONFONT);
        heading.setTextFill(Color.DARKRED);

        Button bttnNext =new CatanButtons().getButton(IMG_BTN_LETSPLAY, 40);
        bttnNext.setOnAction(e -> {
            int n= getSelectedPlayerNumber(toggleGroup);
            stage.setScene(new SceneGame(stage, n ));
        });
        bttnNext.setTranslateX(350);
        bttnNext.setTranslateY(-250);

        ImageView imageView = new ImageView(new Image(String.valueOf(Catan.class.getResource(IMG_CATAN_CHOOSENUMBEROFPLAYERS))));
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);

        selectNumberOfPlayersScreenLayout.getChildren().addAll(imageView, heading,rdo2Player,rdo3Player,rdo4Player,bttnNext);

    }

    private RadioButton getRadioButtonWithAllSettings(String btnText, Integer y) {
        RadioButton rbn= new RadioButton(btnText);
        rbn.setFont(RDOBUTTONFONT);
        rbn.setTextFill(Color.DARKRED);
        rbn.setTranslateX(560);
        rbn.setTranslateY(y);
   return rbn;
    }

    private Integer getSelectedPlayerNumber(ToggleGroup toggleGroup) {
        RadioButton selectedRadioButton = (RadioButton)toggleGroup.getSelectedToggle();
        switch (selectedRadioButton.getText()){
            case "2 Players":
                return 2;
            case "3 Players":
                return 3;
            case "4 Players":
                return 4;
        }
        return 0;
    }

}
