package com.teksystems.bootcamp.capstone2.resourcecards;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_ORE;

public class ResourceCard_Ore {
    private final String resourceCardOreImgURL;
    private Integer ResourceCardOreCount;

    public ResourceCard_Ore() {
        this.resourceCardOreImgURL = RESOURCECARD_ORE;
        this.ResourceCardOreCount=0;
    }


    public void addResourceCardOre() {
        ResourceCardOreCount+=1;
    }

    public Integer getResourceCardOreCount() {
        return ResourceCardOreCount;
    }

}
