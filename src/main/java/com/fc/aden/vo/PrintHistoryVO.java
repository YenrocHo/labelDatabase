package com.fc.aden.vo;

import com.fc.aden.model.custom.process.PrintHistory;

import java.util.Date;
import java.util.List;

public class PrintHistoryVO {


    private String itemsCode;

    private String originalId;

    private String printLableId;

    private String productName;

    private String foodCategory;

    private String productWeight;

    private String correctStage;

    private String correctStorage;

    private String employerName;

    private String printTime;
    private String createTime;
    private String updateTime;
    private String endTime;

    private static final long serialVersionUID = 1L;

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId == null ? null : originalId.trim();
    }

    public String getPrintLableId() {
        return printLableId;
    }

    public void setPrintLableId(String printLableId) {
        this.printLableId = printLableId == null ? null : printLableId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getCorrectStage() {
        return correctStage;
    }

    public void setCorrectStage(String correctStage) {
        this.correctStage = correctStage == null ? null : correctStage.trim();
    }

    public String getCorrectStorage() {
        return correctStorage;
    }

    public void setCorrectStorage(String correctStorage) {
        this.correctStorage = correctStorage == null ? null : correctStorage.trim();
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName == null ? null : employerName.trim();
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
