package com.teksystems.bootcamp.capstone2.resourcecards;
import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_LUMBER;

public class ResourceCard_Lumber extends ResourceCard {
    public ResourceCard_Lumber(int count) {
        this.resourceCardImgURL = RESOURCECARD_LUMBER;
        this.resourceCardCount=count;
    }
/*


    public void addResourcecardLumber() {
        ResourceCardLumberCount+=1;
    }

    public Integer getResourceCardLumberCount() {
        return ResourceCardLumberCount;
    }
*/

}
