package com.teksystems.bootcamp.capstone2.scene;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.hex.HexEdge;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Road extends ImageView {
    public Road(HexEdge edge, String currentRoadImgUrl) {


        Image roadImage = new Image(String.valueOf(Catan.class.getResource(currentRoadImgUrl)));
        this.setX(edge.getStartX());
        this.setY(edge.getStartY());
        //super.setRotate(theta);
        this.setImage(roadImage);
        this.setFitWidth(25);
        this.setFitHeight(70);
    }



}
