package com.example.suno.boostcamp2.data;

import java.util.Date;

/**
 * Created by suno on 2017. 7. 13..
 */

public class FamousPlace extends ItemType{
    private int id;
    private String name;
    private String imgUrl;
    private String explanation;
    private int recommandationCnt;
    private int distance;
    private int date;
    private int flag;

    public FamousPlace(int viewType){
        super(viewType);
    }

    public FamousPlace(int viewType, int id, String name, String imgUrl, String explanation, int recommandationCnt, int distance, int date
    , int flag) {
        super(viewType);
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.explanation = explanation;
        this.recommandationCnt = recommandationCnt;
        this.distance = distance;
        this.date = date;
        this.flag = flag;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getRecommandationCnt() {
        return recommandationCnt;
    }

    public void setRecommandationCnt(int recommandationCnt) {
        this.recommandationCnt = recommandationCnt;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int check) {
        this.flag = check;
    }
}
