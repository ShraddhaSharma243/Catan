package com.teksystems.bootcamp.capstone2.gameobjects;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.hex.HexCordinate;
import com.teksystems.bootcamp.capstone2.hex.HexEdge;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Settlement extends ImageView {
    GameBoard gameBoard;
    public Settlement(HexCordinate vertex, String imgURL, GameBoard gameBoard) {
        Image hexImage = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(hexImage);
        this.setX(vertex.getCenterX() - 10);
        this.setY(vertex.getCenterY() - 10);
        this.setFitWidth(35);
        this.setFitHeight(35);
        this.gameBoard=gameBoard;

    }


}
