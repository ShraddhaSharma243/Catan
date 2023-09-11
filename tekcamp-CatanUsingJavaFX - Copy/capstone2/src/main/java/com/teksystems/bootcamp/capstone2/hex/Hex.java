package com.teksystems.bootcamp.capstone2.hex;
import com.teksystems.bootcamp.capstone2.Catan;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Hex extends ImageView {

    private List<HexCordinate> hexVertices;

    public List<HexEdge> getHexEdges() {
        return hexEdges;
    }

    public void setHexEdges(List<HexEdge> hexEdges) {
        this.hexEdges = hexEdges;
    }

    private List<HexEdge> hexEdges;
    private HexCordinate centerPoint;
    private final Integer tokenNumber;
    private final String terrain;

    public HexCordinate getCenterPoint() {
        return centerPoint;
    }
    public String getTerrain() {
        return terrain;
    }

    public Hex(int x, int y, int fitWidth, String imgURL, int tokenNumber, String terrain) {
        this.tokenNumber=tokenNumber;
        this.terrain=terrain;
        Image hexImage= new Image(String.valueOf(Catan.class.getResource(imgURL)));

        this.setImage(hexImage);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(fitWidth);
        this.setFitHeight(fitWidth);

    }

    public Integer getTokenNumber() {
     return tokenNumber;
    }

    public List<HexCordinate> getHexVertices() {
        return hexVertices;
    }

    public void setHexVertices(List<HexCordinate> hexVertices) {
        this.hexVertices = hexVertices;
    }


    public void setCenterPoint(HexCordinate centerPoint) {
        this.centerPoint = centerPoint;
    }

    public void setEdges(List<HexEdge> hexEdges) {
        this.hexEdges = hexEdges;
    }
}
