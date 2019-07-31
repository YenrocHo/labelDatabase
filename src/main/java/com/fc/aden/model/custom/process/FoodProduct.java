package com.fc.aden.model.custom.process;

/**
 * 食品产品关系表
 * lb
 * 2019-07-30 14:18
 */
public class FoodProduct {
    private String id;
    private String foodId;
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
