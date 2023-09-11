package com.teksystems.bootcamp.capstone2.resourcecards;
import static com.teksystems.bootcamp.capstone2.misclleneous.Constants.RESOURCECARD_BRICK;

public class ResourceCard_Brick {
 private final String resourceCardImgURL;
  Integer ResourceCardBrickCount;

  public ResourceCard_Brick() {
        this.resourceCardImgURL = RESOURCECARD_BRICK;
        this.ResourceCardBrickCount=0;
    }
    public void addResourceCardBrick() {
        ResourceCardBrickCount+=1;
    }
    public Integer getResourceCardBrickCount() {
        return ResourceCardBrickCount;
    }
}
