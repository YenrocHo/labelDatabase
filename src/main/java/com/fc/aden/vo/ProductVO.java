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
    private String frozenID;//冰柜id
    private String frozen;//冰柜
    private String fridgeID;//冰箱id
    private String fridge;//冰箱
    private String temperatureID;//室温ID
    private String temperature;//室温ID
    private String aboveID;//高于65°C
    private String above;//高于65°C

    private Integer priority;//优先级

    //产品更新时间
    private String updateTime;
    private String storeId;//选中id
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFrozenID() {
        return frozenID;
    }

    public void setFrozenID(String frozenID) {
        this.frozenID = frozenID;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }

    public String getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(String fridgeID) {
        this.fridgeID = fridgeID;
    }

    public String getFridge() {
        return fridge;
    }

    public void setFridge(String fridge) {
        this.fridge = fridge;
    }

    public String getTemperatureID() {
        return temperatureID;
    }

    public void setTemperatureID(String temperatureID) {
        this.temperatureID = temperatureID;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAboveID() {
        return aboveID;
    }

    public void setAboveID(String aboveID) {
        this.aboveID = aboveID;
    }

    public String getAbove() {
        return above;
    }

    public void setAbove(String above) {
        this.above = above;
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
