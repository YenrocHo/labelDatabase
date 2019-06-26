package com.fc.aden.model.auto;

import java.io.Serializable;
import java.util.Date;

public class TSysItems implements Serializable {
    private String id;

    private String items;

    private String name;

    private String englishName;

    private Date createTime;

    private Date updateTime;

    public TSysItems(String id, String items, String name, String englishName, Date createTime, Date updateTime) {
        this.id = id;
        this.items = items;
        this.name = name;
        this.englishName = englishName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TSysItems() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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

    @Override
    public String toString() {
        return "TSysItems{" +
                "id='" + id + '\'' +
                ", items='" + items + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}