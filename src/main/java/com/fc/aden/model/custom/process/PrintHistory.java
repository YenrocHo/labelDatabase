package com.fc.aden.model.custom.process;

import com.fc.aden.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 打印历史实体类
 *
 * @author Created by zc on 2019/7/3
 */
public class PrintHistory extends BaseEntity {

    private String itemsCode;

    private String originalId;

    private String printLableId;

    private String productName;

    private String productCategory;

    private String productWeight;

    private String correctStage;

    private String correctStorage;

    private String employerName;

    private Date printTime;

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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory == null ? null : productCategory.trim();
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

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

}