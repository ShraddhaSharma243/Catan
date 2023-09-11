module com.teksystems.bootcamp.capstone2.capstone2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.teksystems.bootcamp.capstone2 to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2;
    exports com.teksystems.bootcamp.capstone2.scene;
    opens com.teksystems.bootcamp.capstone2.scene to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2.resourcecards;
    opens com.teksystems.bootcamp.capstone2.resourcecards to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2.dashboards;
    opens com.teksystems.bootcamp.capstone2.dashboards to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2.hex;
    opens com.teksystems.bootcamp.capstone2.hex to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2.gameobjects;
    opens com.teksystems.bootcamp.capstone2.gameobjects to javafx.fxml;
    exports com.teksystems.bootcamp.capstone2.misclleneous;
    opens com.teksystems.bootcamp.capstone2.misclleneous to javafx.fxml;
}