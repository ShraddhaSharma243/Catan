package com.teksystems.bootcamp.capstone2.gameobjects;

import com.teksystems.bootcamp.capstone2.resourcecards.*;

import java.util.HashMap;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.*;

public class Player {
    private final String playerNameImage;
    private final String playerColor;
    private final String settlementImgURL;
    private final String roadImgURL;
    public HashMap<String, ResourceCard> getResourceCards() {
        return resourceCards;
    }
    private HashMap<String, ResourceCard> resourceCards = new HashMap<>();

    public Score getScore() {
        return score;
    }

    Score score = new Score();

    public Player(String playerColor,String playerNameImageURL, String settlementImgURL, String roadImgURL) {
        this.playerColor=playerColor;
        this.playerNameImage = playerNameImageURL;
        this.settlementImgURL=settlementImgURL;
        this.roadImgURL=roadImgURL;
        initializeResourceCards();
    }

    private void initializeResourceCards() {
        resourceCards.put("Brick",new ResourceCard_Brick(0));
        resourceCards.put("Wool", new ResourceCard_Wool(0));
        resourceCards.put("Grain", new ResourceCard_Grain(0));
        resourceCards.put("Ore", new ResourceCard_Ore(0));
        resourceCards.put("Lumber", new ResourceCard_Lumber(0));
    }

    public String getPlayerNameImage() {
        return playerNameImage;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getSettlementImgURL() {
        return settlementImgURL;
    }

    /*public ResourceCard_Brick getResourceCard_brick() {
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
    }*/

    public String getRoadImgURL() {
        return roadImgURL;
    }

    public boolean checkResorceCardsForSettlement() {
        if(this.resourceCards.get("Brick").getResourceCardCount()>=1
           && this.resourceCards.get("Lumber").getResourceCardCount()>=1
            && this.resourceCards.get("Grain").getResourceCardCount()>=1
            && this.resourceCards.get("Wool").getResourceCardCount()>=1){
            return true;
        }
        return false;
    }

    public void giveCardsToBank(String pieceType, ResourceCard_Bank bank) {
        if(pieceType.equals("Settlement")){
            give4CardsForSettlementToBank(bank);
        }
        else if(pieceType.equals("Road")){
            give2CardsToBankForARoad(bank);
        }
        else if(pieceType.equals("City")){
            give5CardsToBankForACity(bank);
        }
    }

    private void give5CardsToBankForACity(ResourceCard_Bank bank) {
        this.resourceCards.get("Grain").reduceOneCard();
        this.resourceCards.get("Grain").reduceOneCard();
        this.resourceCards.get("Ore").reduceOneCard();
        this.resourceCards.get("Ore").reduceOneCard();
        this.resourceCards.get("Ore").reduceOneCard();
    bank.getResourceCards().get(FIELD).addCard(2);
    bank.getResourceCards().get(MOUNTAINS).addCard(3);
    }

    private void give2CardsToBankForARoad(ResourceCard_Bank bank) {
        this.resourceCards.get("Brick").reduceOneCard();
        this.resourceCards.get("Lumber").reduceOneCard();
        bank.getResourceCards().get(HILL).addCard(1);
        bank.getResourceCards().get(FOREST).addCard(1);
    }

    private void give4CardsForSettlementToBank(ResourceCard_Bank bank) {

        this.resourceCards.get("Brick").reduceOneCard();
         this.resourceCards.get("Lumber").reduceOneCard();
         this.resourceCards.get("Grain").reduceOneCard();
         this.resourceCards.get("Wool").reduceOneCard();
        bank.getResourceCards().get(HILL).addCard(1);
        bank.getResourceCards().get(FOREST).addCard(1);
        bank.getResourceCards().get(FIELD).addCard(1);
        bank.getResourceCards().get(PASTURE).addCard(1);

        }

    public boolean checkResorceCardsForRoad() {
        if (this.resourceCards.get("Brick").getResourceCardCount() >= 1
                && this.resourceCards.get("Lumber").getResourceCardCount() >= 1){
            return true;
        }
        return false;
    }

    public void exchangeCards(HashMap<String, Integer> cardsToAdd, HashMap<String, Integer> cardsToSubtract) {
        this.resourceCards.get("Wool").add(cardsToAdd.get("Wool"));
        this.resourceCards.get("Brick").add(cardsToAdd.get("Brick"));
        this.resourceCards.get("Grain").add( cardsToAdd.get("Grain"));
        this.resourceCards.get("Lumber").add( cardsToAdd.get("Lumber"));
        this.resourceCards.get("Ore").add( cardsToAdd.get("Ore"));
        this.resourceCards.get("Wool").subtractCard(cardsToSubtract.get("Wool"));
        this.resourceCards.get("Brick").subtractCard(cardsToSubtract.get("Brick"));
        this.resourceCards.get("Grain").subtractCard( cardsToSubtract.get("Grain"));
        this.resourceCards.get("Lumber").subtractCard( cardsToSubtract.get("Lumber"));
        this.resourceCards.get("Ore").subtractCard( cardsToSubtract.get("Ore"));
    }
}
