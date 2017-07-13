package com.example.suno.boostcamp2.data;

import java.util.Date;

/**
 * Created by suno on 2017. 7. 13..
 */

public class FamousPlace {
    private int recommandationCnt;
    private Date date;
    private int imgId;
    private String name;
    private String explanation;

    public FamousPlace(int recommandationCnt, Date date, int imgId, String name, String explanation) {
        this.recommandationCnt = recommandationCnt;
        this.date = date;
        this.imgId = imgId;
        this.name = name;
        this.explanation = explanation;
    }

    public int getRecommandationCnt() {
        return recommandationCnt;
    }

    public void setRecommandationCnt(int recommandationCnt) {
        this.recommandationCnt = recommandationCnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
