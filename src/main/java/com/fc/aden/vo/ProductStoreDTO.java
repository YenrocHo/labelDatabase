package com.fc.aden.vo;

public class ProductStoreDTO {
    private String storeId;
    private String shelfLife;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public ProductStoreDTO(String storeId, String shelfLife) {
        this.storeId = storeId;
        this.shelfLife = shelfLife;
    }

    public ProductStoreDTO() {
    }

    @Override
    public String toString() {
        return "ProductStoreDTO{" +
                "storeId='" + storeId + '\'' +
                ", shelfLife='" + shelfLife + '\'' +
                '}';
    }
}
