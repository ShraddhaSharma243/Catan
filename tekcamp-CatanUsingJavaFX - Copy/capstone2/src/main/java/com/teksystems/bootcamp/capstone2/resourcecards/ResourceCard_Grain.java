package com.teksystems.bootcamp.capstone2.resourcecards;
import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_GRAIN;

public class ResourceCard_Grain {
    private final String resourceCardGrainImgURL;
    private Integer ResourceCardGrainCount;

    public ResourceCard_Grain() {
        this.resourceCardGrainImgURL = RESOURCECARD_GRAIN;
        this.ResourceCardGrainCount=0;
    }

    public void addResourceGrainGrain() {
        ResourceCardGrainCount+=1;
    }

    public Integer getResourceCardGrainCount() {
        return ResourceCardGrainCount;
    }

}
