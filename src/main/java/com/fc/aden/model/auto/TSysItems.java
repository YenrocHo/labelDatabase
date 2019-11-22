package com.fc.aden.model.auto;

import java.io.Serializable;
import java.util.Date;

public class TSysItems implements Serializable {
    private String id;

    private String itemsCode;//项目点编号

    private String name;//项目点名称

    private String englishName;//英文名

    private Integer status;// 项目点状态 1 项目点正在运行中 0 项目点已经停止运行

    private Date createTime;

    private Date updateTime;

    public TSysItems(String id, String itemsCode, String name, String englishName,Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.name = name;
        this.englishName = englishName;
        this.status = status;
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

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TSysItems{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}