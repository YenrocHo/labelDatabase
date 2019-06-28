package com.fc.aden.model.custom.process;

import java.util.Date;

public class TSysFood {
    private String id;

    private String itemId;

    private String food;

    private String name;

    private String englishName;

    private String picture;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public TSysFood(String id, String itemId, String food, String name, String englishName, String picture, Date createTime, Date updateTime, Integer status) {
        this.id = id;
        this.itemId = itemId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    @Override
    public String toString() {
        return "TSysFood{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
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