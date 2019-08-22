/**
 * projectName: labelDatabase
 * fileName: ProductStorePo.java
 * packageName: com.fc.aden.model.po
 * date: 2019-08-22
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.model.po;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: ProductStorePo
 * @packageName: com.fc.aden.model.po
 * @description: 产品储存条件关联
 * @data: 2019-08-22
 **/
public class ProductStorePo {

    private String productId;//产品id
    private String storeId;//储存条件id
    private String storeName; //储存条件名称
    private String shelfLife;//保质期


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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }
}
