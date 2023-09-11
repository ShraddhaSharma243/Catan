package com.teksystems.bootcamp.capstone2.gameobjects;

import com.teksystems.bootcamp.capstone2.resourcecards.*;

public class Player {
    private final String playerName;
    private final String playerColor;
    private final String settlementImgURL;
    private final String roadImgURL;
    private final ResourceCard_Brick resourceCard_brick;
    private final ResourceCard_Wool resourceCard_wool;
    private final ResourceCard_Grain resourceCard_grain;
    private final ResourceCard_Ore resourceCard_ore;
   private final ResourceCard_Lumber resourceCard_lumber;
    Integer score;

    public Player(String playerColor,String playerName, String settlementImgURL, String roadImgURL) {
        this.playerColor=playerColor;
        this.playerName= playerName;
        this.settlementImgURL=settlementImgURL;
        this.roadImgURL=roadImgURL;
        this.score = 0;
        resourceCard_brick=new ResourceCard_Brick();
        resourceCard_wool = new ResourceCard_Wool();
        resourceCard_grain = new ResourceCard_Grain();
        resourceCard_ore = new ResourceCard_Ore();
        resourceCard_lumber = new ResourceCard_Lumber();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getSettlementImgURL() {
        return settlementImgURL;
    }

    public ResourceCard_Brick getResourceCard_brick() {
        return resourceCard_brick;
    }

    public ResourceCard_Wool getResourceCard_wool() {
        return resourceCard_wool;
    }

    public ResourceCard_Grain getResourceCard_grain() {
        return resourceCard_grain;
    }

    public ResourceCard_Ore getResourceCard_ore() {
        return resourceCard_ore;
    }

    public ResourceCard_Lumber getResourceCard_lumber() {
        return resourceCard_lumber;
    }

    public String getRoadImgURL() {
        return roadImgURL;
    }
}
