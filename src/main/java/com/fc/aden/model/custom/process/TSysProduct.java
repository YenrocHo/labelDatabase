package com.fc.aden.model.custom.process;

import java.io.Serializable;
import java.util.Date;

public class TSysProduct implements Serializable {
    //产品Id
    private String id;

    private String itemsCode;

    //产品名
    private String product;

    //产品中文名
    private String name;

    //产品英文名
    private String englishName;

    //食品名称
    private String foodName;

    //保质期
    private String shelfLife;

    //产品状态
    private Integer status;

    //产品创建时间
    private Date createTime;

    //产品更新时间
    private Date updateTime;

    public TSysProduct(String id, String itemsCode, String product, String name, String englishName, Integer status,String shelfLife,String foodName, Date createTime, Date updateTime) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.product = product;
        this.name = name;
        this.englishName = englishName;
        this.status = status;
        this.createTime = createTime;
        this.shelfLife = shelfLife;
        this.foodName = foodName;
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

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Override
    public String toString() {
        return "TSysProduct{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", product='" + product + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", foodName=" + foodName +
                ", shelfLife=" + shelfLife +
                ", updateTime=" + updateTime +
                '}';
    }
}