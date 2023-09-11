package com.teksystems.bootcamp.capstone2.piece;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import com.teksystems.bootcamp.capstone2.hex.vertex.HexCordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Settlement extends ImageView {
    GameBoard gameBoard;
    public Settlement(HexCordinate vertex, String imgURL, GameBoard gameBoard) {
        Image hexImage = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(hexImage);
        this.setX(vertex.getCenterX() - 17.5);
        this.setY(vertex.getCenterY() - 17.5);
        this.setFitWidth(35);
        this.setFitHeight(35);
        this.gameBoard=gameBoard;

    }


}
