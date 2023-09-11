package com.teksystems.bootcamp.capstone2.resourcecards;

import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_ORE;

public class ResourceCard_Ore extends ResourceCard {
    public ResourceCard_Ore(int count) {
        this.resourceCardImgURL = RESOURCECARD_ORE;
        this.resourceCardCount=count;
    }
/*


    public void addResourceCardOre() {
        ResourceCardOreCount+=1;
    }

    public Integer getResourceCardOreCount() {
        return ResourceCardOreCount;
    }
*/

}
