package com.teksystems.bootcamp.capstone2.dashboards;

import com.teksystems.bootcamp.capstone2.Catan;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashBoardImages extends ImageView {

    public DashBoardImages(String imgURL,int x, int y, int fitWidth, int fitHeight) {
        Image playerName = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(playerName);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(fitWidth);
        this.setFitHeight(fitHeight);
    }
}
