package com.fc.aden.model.custom.process;

import java.io.Serializable;
import java.util.Date;

public class TSysStore implements Serializable {
    private String id;

    private String itemId;

    private String store;

    private String name;

    private String englishName;

    private String temperature;

    private Integer status;

    private Date creatTime;

    private Date updateTime;

    public TSysStore(String id, String itemId, String store, String name, String englishName, String temperature, Integer status, Date creatTime, Date updateTime) {
        this.id = id;
        this.itemId = itemId;
        this.store = store;
        this.name = name;
        this.englishName = englishName;
        this.temperature = temperature;
        this.status = status;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public TSysStore() {
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TSysStore{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", store='" + store + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", temperature='" + temperature + '\'' +
                ", status=" + status +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}