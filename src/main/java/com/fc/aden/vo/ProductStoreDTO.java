package com.fc.aden.vo;

import java.util.List;

public class ProductStoreDTO {
    private List<String> storeId;
    private List<String> shelfLife;

    public List<String> getStoreId() {
        return storeId;
    }

    public void setStoreId(List<String> storeId) {
        this.storeId = storeId;
    }

    public List<String> getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(List<String> shelfLife) {
        this.shelfLife = shelfLife;
    }

    public ProductStoreDTO(List<String> storeId, List<String> shelfLife) {
        this.storeId = storeId;
        this.shelfLife = shelfLife;
    }

    public ProductStoreDTO() {
    }

}
