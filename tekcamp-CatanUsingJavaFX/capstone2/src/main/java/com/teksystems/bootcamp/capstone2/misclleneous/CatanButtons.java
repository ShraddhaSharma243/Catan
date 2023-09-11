package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.Catan;
import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class CatanButtons extends ImageView {
    public Button getButton() {
        return button;
    }

    private Button button = new Button();
    public CatanButtons(){}
    public CatanButtons(String imgURL,  int x, int y, String btnName){
        Image diceImage = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(diceImage);
        this.setFitHeight(BTN_HEIGHT);

        this.setPreserveRatio(true);

        button.setGraphic(this);
        button.setLayoutX(x);
        button.setLayoutY(y);



    }
    public Button getButton(String imgURL, Integer fitHeight){
        Image diceImage = new Image(String.valueOf(Catan.class.getResource(imgURL)));
        this.setImage(diceImage);
        this.setFitHeight(fitHeight);

        this.setPreserveRatio(true);

        button.setGraphic(this);
        return button;
    }
    public void setButtonDisable(boolean disable){
        button.setDisable(disable);
    }
    public boolean isButtonDisable(){
        return button.isDisable();
    }
   public static CatanButtons btnDice = new CatanButtons(IMG_CATAN_DICE, 50, Y_FOR_BTNDICE_AND_LABELROLLEDNUMBER,"btnDice" );
    public static CatanButtons btnNext = new CatanButtons(IMF_BTN_NEXTPLAYER,625, 830,"btnNext" );
    public static CatanButtons btnDevelopmentCard = new CatanButtons(IMF_BTN_DEVELOPMENTCARD,50, Y_FOR_BUTTONS,"btnDevelopmentCard" );
    public static CatanButtons btnSettlement = new CatanButtons(IMF_BTN_SETTLEMENT,255, Y_FOR_BUTTONS,"btnSettlement" );
    public static CatanButtons btnRoad = new CatanButtons(IMF_BTN_ROAD,415, Y_FOR_BUTTONS,"btnRoad" );
    public static CatanButtons btnTrade = new CatanButtons(IMF_BTN_TRADE,520, Y_FOR_BUTTONS,"btnTrade" );



}
