package com.fc.test.model.custom.process;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;
import java.util.Date;

public class TSysProduct implements Serializable {
    //产品Id
    private String productId;
    //产品名
    private String name;
    //产品中文名
    private String cName;
    //产品英文名
    private String eName;

    private Integer status;
    //产品创建时间
    private Date createTime;
    //产品更新时间
    private Date updateTime;

    public TSysProduct(String productId, String name, String cName, String eName, Integer status, Date createTime, Date updateTime) {
        this.productId = productId;
        this.name = name;
        this.cName = cName;
        this.eName = eName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TSysProduct() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
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
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", cName='" + cName + '\'' +
                ", eName='" + eName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}