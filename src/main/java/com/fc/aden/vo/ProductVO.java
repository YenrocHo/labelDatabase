package com.fc.aden.vo;

public class ProductVO {
    //产品Id
    private String id;

    private String itemsCode;
    private String productCode;
    //产品名
    private String product;

    //产品中文名
    private String name;

    //产品英文名
    private String englishName;

    //食品种类
    private String foodName;

    //保质期
    private String shelfLife;

    //食品状态
    private Integer status;

    //产品创建时间
    private String createTime;

    //产品更新时间
    private String updateTime;


    private Boolean storeId;//选中id
    private String storeName;//选中id
    private String noStoreId;//没有选中id
    public ProductVO(){
    }


    public ProductVO(String id, String itemsCode, String productCode, String product, String name, String englishName, String foodName, String shelfLife, Integer status, String createTime, String updateTime) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.productCode = productCode;
        this.product = product;
        this.name = name;
        this.englishName = englishName;
        this.foodName = foodName;
        this.shelfLife = shelfLife;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Boolean getStoreId() {
        return storeId;
    }

    public void setStoreId(Boolean storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getNoStoreId() {
        return noStoreId;
    }

    public void setNoStoreId(String noStoreId) {
        this.noStoreId = noStoreId;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id='" + id + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", product='" + product + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", shelfLife='" + shelfLife + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
