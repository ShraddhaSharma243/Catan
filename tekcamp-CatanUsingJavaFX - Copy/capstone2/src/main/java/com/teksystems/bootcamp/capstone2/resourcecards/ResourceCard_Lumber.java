package com.teksystems.bootcamp.capstone2.resourcecards;
import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_LUMBER;

public class ResourceCard_Lumber {
    private final String resourceCardLumberImgURL;
    private Integer ResourceCardLumberCount;

    public ResourceCard_Lumber() {
        this.resourceCardLumberImgURL = RESOURCECARD_LUMBER;
        this.ResourceCardLumberCount=0;
    }


    public void addResourcecardLumber() {
        ResourceCardLumberCount+=1;
    }

    public Integer getResourceCardLumberCount() {
        return ResourceCardLumberCount;
    }

}
