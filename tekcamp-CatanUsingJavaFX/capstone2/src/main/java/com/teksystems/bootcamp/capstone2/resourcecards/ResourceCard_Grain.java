package com.teksystems.bootcamp.capstone2.resourcecards;
import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_GRAIN;

public class ResourceCard_Grain extends ResourceCard {
        public ResourceCard_Grain(int count) {
        this.resourceCardImgURL = RESOURCECARD_GRAIN;
        this.resourceCardCount=count;
    }

    /*public void addResourceGrainGrain() {
        ResourceCardGrainCount+=1;
    }

    public Integer getResourceCardGrainCount() {
        return ResourceCardGrainCount;
    }
*/
}
