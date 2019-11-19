package com.fc.aden.model.custom.process;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TSysProduct implements Serializable {
    //产品Id
    private String id;

    private String itemsCode;

    //产品名
    private String product;
    private String productCode;

    //产品中文名
    private String name;

    //产品英文名
    private String englishName;

    //食品名称
    private String foodName;

    //保质期
    // private String shelfLife;

    //产品状态
    private Integer status;

    //产品创建时间
    private Date createTime;

    //产品更新时间
    private Date updateTime;

    private Integer priority;//优先级

    private List<ProductStore> productStores;

    public TSysProduct(String id, String itemsCode, String product,String productCode, String name, String englishName, Integer status ,String foodName, Date createTime, Date updateTime,Integer priority) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.product = product;
        this.productCode = productCode;
        this.name = name;
        this.englishName = englishName;
        this.status = status;
        this.createTime = createTime;
        this.foodName = foodName;
        this.updateTime = updateTime;
        this.priority = priority;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TSysProduct{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", product='" + product + '\'' +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", foodName=" + foodName +
                ", updateTime=" + updateTime +
                ", priority=" + priority +
                '}';
    }
}