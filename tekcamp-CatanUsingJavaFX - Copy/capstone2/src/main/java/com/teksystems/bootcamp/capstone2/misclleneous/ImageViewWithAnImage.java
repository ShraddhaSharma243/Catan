package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.Catan;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewWithAnImage {
    ImageView imageView;
    public ImageView getImageViewWithAnImage(String imgURL, int fitHeight, int fitWidth) {
         imageView = new ImageView(new Image(String.valueOf(Catan.class.getResource(imgURL))));
        imageView.setFitHeight(fitHeight);
        imageView.setFitWidth(fitWidth);
        return imageView;
    }
}
