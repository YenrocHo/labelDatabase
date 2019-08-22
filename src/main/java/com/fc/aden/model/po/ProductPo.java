/**
 * projectName: labelDatabase
 * fileName: ProductPo.java
 * packageName: com.fc.aden.model.po
 * date: 2019-08-22
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.model.po;

import com.fc.aden.model.custom.process.ProductStore;

import java.util.Date;
import java.util.List;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: ProductPo
 * @packageName: com.fc.aden.model.po
 * @description: 产品关联详情信息
 * @data: 2019-08-22
 **/
public class ProductPo {
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

    //产品状态
    private Integer status;

    //产品创建时间
    private Date createTime;

    //产品更新时间
    private Date updateTime;

    private List<ProductStore> productStores;


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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    public List<ProductStore> getProductStores() {
        return productStores;
    }

    public void setProductStores(List<ProductStore> productStores) {
        this.productStores = productStores;
    }
}
