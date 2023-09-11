package com.teksystems.bootcamp.capstone2.resourcecards;

public abstract class ResourceCard {
    protected String resourceCardImgURL;
    protected Integer resourceCardCount;
    public void addOneResourceCard(){
        resourceCardCount +=1;
    }
    public Integer getResourceCardCount(){
        return resourceCardCount;
    }
    protected String getResourceCardImgURL(){
        return resourceCardImgURL;
    }

    public void reduceOneCard() {
        resourceCardCount-=1;
    }

    public void add(Integer count) {
        resourceCardCount+=count;

    }

    public void subtractCard(Integer count ) {
        resourceCardCount-=count;
    }
    public void addCard(Integer count ) {
        resourceCardCount+=count;
    }
}
