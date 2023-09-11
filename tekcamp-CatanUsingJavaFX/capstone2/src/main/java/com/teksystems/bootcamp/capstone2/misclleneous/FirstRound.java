package com.teksystems.bootcamp.capstone2.misclleneous;

import com.teksystems.bootcamp.capstone2.gameobjects.GameBoard;

public class FirstRound {
    public static boolean chekcIsItFirstRound(String playerColor, GameBoard gameBoard, String settlementOrRoad) {
        if(playerColor.equals("")){
            return true;
        }
        if(settlementOrRoad.equals("settlement")) {
            Integer countOfSettlementsForThisPlayer = gameBoard.getSettlementsOfEachPlayer().get(playerColor).size();
            return (countOfSettlementsForThisPlayer <= 1);
        }
        if(settlementOrRoad.equals("road")){
            Integer countOfRoadsForThisPlayer = gameBoard.getRoadsOfEachPlayer().get(playerColor).size();
            return (countOfRoadsForThisPlayer <= 1);
        }
        return false;
    }
}
