package com.teksystems.bootcamp.capstone2.gameobjects;

public class Score {
    public void setMaximumDepthOfRoad(Integer maximumDepthOfRoad) {
        this.maximumDepthOfRoad = maximumDepthOfRoad;
    }

    public Integer getMaximumDepthOfRoad() {
        return maximumDepthOfRoad;
    }

    private Integer maximumDepthOfRoad;
    private Integer settlementPonits;
    private Integer cityPoints;
    private Boolean largestArmy;
    private Integer victoryPoints;
    private Boolean longestRoad;

    public Integer getScore(){
        int points;
        points = longestRoad?2:0;
        points+= largestArmy ?2:0;
        points+= settlementPonits+cityPoints+victoryPoints;
        return points;
    }
    public void setLongestRoad(Boolean longestRoad) {
        this.longestRoad = longestRoad;
    }

    public void setLargestArmy(Boolean largestArmy) {
        this.largestArmy = largestArmy;
    }
    public void increaseSettlementPoints(){
        settlementPonits+=1;
    }

    public void decreaseSettlementPoints(){
        settlementPonits-=1;
    }
    public void increaseCityPoints(){
        cityPoints+=1;
    }

    public void increaseVictoryPoints(){
        victoryPoints+=1;
    }

}
