package com.fc.aden.model.custom.process;

import java.util.Date;

public class TSysFood {
    private String id;

    private String itemsCode;

    private String food;
    private String foodCode;

    private String name;

    private String englishName;

    private String picture;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public TSysFood(String id, String itemsCode, String foodCode, String food, String name, String englishName, String picture, Date createTime, Date updateTime, Integer status) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.foodCode = foodCode;
        this.food = food;
        this.name = name;
        this.englishName = englishName;
        this.picture = picture;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public TSysFood() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    @Override
    public String toString() {
        return "TSysFood{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", foodCode='" + foodCode + '\'' +
                ", food='" + food + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
        ", status=" + status +
                '}';
    }
}