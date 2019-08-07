package com.fc.aden.model.custom.process;

import java.util.Date;

public class TSysStage {
    private String id;

    private String itemsCode;

    private String stage;

    private String name;

    private String englishName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public TSysStage(String id, String itemsCode, String stage, String name, String englishName, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.stage = stage;
        this.name = name;
        this.englishName = englishName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TSysStage() {
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "TSysStage{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", stage='" + stage + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}