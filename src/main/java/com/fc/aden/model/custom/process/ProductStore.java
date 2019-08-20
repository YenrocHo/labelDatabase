package com.fc.aden.model.custom.process;

import java.util.Date;

/**
 * 产品存储条件关系表
 */
public class ProductStore {

    private String id;
    private String productId;
    private String storeId;
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

    public ProductStore(String id, String productId, String storeId, Date createTime, Date updateTime) {
        this.id = id;
        this.productId = productId;
        this.storeId = storeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
