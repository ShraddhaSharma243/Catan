package com.teksystems.bootcamp.capstone2;

import com.teksystems.bootcamp.capstone2.scene.SceneWelcome;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Catan extends Application {
    Scene welcomeScene;
    @Override
    public void start(Stage stage) {
        stage.setScene(new SceneWelcome(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}