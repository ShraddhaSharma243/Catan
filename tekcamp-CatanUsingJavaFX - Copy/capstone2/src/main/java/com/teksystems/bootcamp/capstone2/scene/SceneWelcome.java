package com.teksystems.bootcamp.capstone2.scene;

import com.teksystems.bootcamp.capstone2.misclleneous.CatanButtons;
import com.teksystems.bootcamp.capstone2.misclleneous.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class SceneWelcome extends Scene {

    public SceneWelcome(Stage stage){
        super(new VBox(20), 800,800);
        VBox welComeScreenLayout = (VBox) this.getRoot();

        Button bttnSelectPlayer = new CatanButtons().getButton(IMG_BTN_SELECT_NUMBEROFPLAYERS, 40);
        bttnSelectPlayer.setTranslateY(-80);
        bttnSelectPlayer.setTranslateX(300);
        bttnSelectPlayer.setOnAction(e-> stage.setScene(new SceneSelectNumberOfPlayers(stage)));

        ImageView imageView = new ImageViewWithAnImage().getImageViewWithAnImage(IMG_CATAN_HOMEPAGE, 800,800);

        welComeScreenLayout.getChildren().addAll(imageView,bttnSelectPlayer);
     }

}
