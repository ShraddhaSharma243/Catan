package com.teksystems.bootcamp.capstone2.resourcecards;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_WOOL;

public class ResourceCard_Wool {
    private final String resourceCardWoolImgURL;
    private Integer ResourceCardWoolCount;

    public ResourceCard_Wool() {
        this.resourceCardWoolImgURL = RESOURCECARD_WOOL;
        this.ResourceCardWoolCount=0;
    }


    public void addResourceCardWool() {
        ResourceCardWoolCount+=1;
    }

    public Integer getResourceCardWoolCount() {
        return ResourceCardWoolCount;
    }

}
