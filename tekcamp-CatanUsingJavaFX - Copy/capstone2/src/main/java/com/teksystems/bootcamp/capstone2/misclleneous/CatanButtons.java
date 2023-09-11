package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.Catan;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class CatanButtons extends ImageView {
    Button button = new Button();

    public Button getButton(String imgURL, Integer fitHeight){
        Image diceImage = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(diceImage);
        this.setFitHeight(fitHeight);

        this.setPreserveRatio(true);
        button.setGraphic(this);
        return button;
    }
}
