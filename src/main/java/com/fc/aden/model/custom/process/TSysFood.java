package com.fc.aden.model.custom.process;

import java.util.Date;

public class TSysFood {
    private String id;

    private String foodName;

    private String name;

    private String englishName;

    private String picture;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String stageId;

    public TSysFood(String id, String foodName, String name, String englishName, String picture, Date createTime, Date updateTime, Integer status, String stageId) {
        this.id = id;
        this.foodName = foodName;
        this.name = name;
        this.englishName = englishName;
        this.picture = picture;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.stageId = stageId;
    }

    public TSysFood() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    @Override
    public String toString() {
        return "TSysFood{" +
                "id='" + id + '\'' +
                ", foodName='" + foodName + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}