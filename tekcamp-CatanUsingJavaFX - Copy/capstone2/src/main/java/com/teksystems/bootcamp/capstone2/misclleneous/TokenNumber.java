package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.hex.HexCordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TokenNumber extends ImageView {

    public TokenNumber(int windowHeight, HexCordinate cenerPoint, String imgURL) {
        Image imgTokenNumber = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(imgTokenNumber);
        int s = 30;
        this.setX(cenerPoint.getCenterX()-15);
        this.setY(cenerPoint.getCenterY()-15);
        this.setFitWidth(s);
        this.setFitHeight(s);
    }
}
