package com.fc.aden.model.custom.process;

import java.io.Serializable;
import java.util.Date;

public class TSysProduct implements Serializable {
    //产品Id
    private String id;

    private String itemId;

    //产品名
    private String product;

    //产品中文名
    private String name;

    //产品英文名
    private String englishName;

    //食品状态
    private Integer status;

    //产品创建时间
    private Date createTime;

    //产品更新时间
    private Date updateTime;

    public TSysProduct(String id, String itemId, String product, String name, String englishName, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.itemId = itemId;
        this.product = product;
        this.name = name;
        this.englishName = englishName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TSysProduct() {
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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
        return "TSysProduct{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", product='" + product + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}