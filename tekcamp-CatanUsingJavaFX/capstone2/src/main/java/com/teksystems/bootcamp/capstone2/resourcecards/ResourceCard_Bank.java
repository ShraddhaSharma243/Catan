package com.teksystems.bootcamp.capstone2.resourcecards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceCard_Bank extends ResourceCard {
    public HashMap<String, ResourceCard> getResourceCards() {
        return resourceCards;
    }

    HashMap<String, ResourceCard> resourceCards = new HashMap<>();
    int countOfEachResourceCards;
    public ResourceCard_Bank(){
        countOfEachResourceCards=19;
        resourceCards.put("Bricks",new ResourceCard_Brick(countOfEachResourceCards));
        resourceCards.put("Wool",new ResourceCard_Brick(countOfEachResourceCards));
        resourceCards.put("Ore",new ResourceCard_Brick(countOfEachResourceCards));
        resourceCards.put("Lumber",new ResourceCard_Brick(countOfEachResourceCards));
        resourceCards.put("Grain",new ResourceCard_Brick(countOfEachResourceCards));
    }

}
