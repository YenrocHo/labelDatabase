package com.fc.test.model.custom.process;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;
import java.util.Date;

public class TSysProduct implements Serializable {
    //产品Id
    private String productId;
    //产品中文名
    private String cName;
    //产品英文名
    private String eName;
    //产品创建时间
    private Date createTime;
    //产品更新时间
    private Date updateTime;


    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
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
                ", cName='" + cName + '\'' +
                ", eName='" + eName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}