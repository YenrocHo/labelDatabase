package com.fc.aden.model.custom.process;

import java.util.Date;

/**
 * 产品存储条件关系表
 */
public class ProductStore {

    private String id;
    private String productId;//产品id
    private String storeId;//储存条件

    //保质期
    private String shelfLife;
    private Date createTime;

    private Date updateTime;

    public ProductStore() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public ProductStore(String id, String productId, String storeId, Date createTime, Date updateTime,String shelfLife) {
        this.id = id;
        this.productId = productId;
        this.storeId = storeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shelfLife = shelfLife;
    }
}
