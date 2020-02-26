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

    private String itemsCode;//项目点编号

    private String originalId;//历史id Android传的id

    private String printLableId;//打印id

    private String productName;//产品名称

    private String foodCategory;//食品种类

    private String productWeight;

    private String correctStage;//制作阶段

    private String correctStorage;//储存条件

    private String employerName;//员工姓名
    private String employerId;// 员工工号
    //保质期
    private String shelfLife;

    private Date printTime;//打印时间

    /** 是否核销（0 - 否；1 - 是） */
    private Integer writeOffFlag;

    /** 核销人用户账号 */
    private String writeOffOperatorNo;

    /** 核销人姓名 */
    private String writeOffOperatorName;

    /** 核销时间 */
    private Date writeOffTime;

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

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public Integer getWriteOffFlag() {
        return writeOffFlag;
    }

    public void setWriteOffFlag(Integer writeOffFlag) {
        this.writeOffFlag = writeOffFlag;
    }

    public String getWriteOffOperatorNo() {
        return writeOffOperatorNo;
    }

    public void setWriteOffOperatorNo(String writeOffOperatorNo) {
        this.writeOffOperatorNo = writeOffOperatorNo;
    }

    public String getWriteOffOperatorName() {
        return writeOffOperatorName;
    }

    public void setWriteOffOperatorName(String writeOffOperatorName) {
        this.writeOffOperatorName = writeOffOperatorName;
    }

    public Date getWriteOffTime() {
        return writeOffTime;
    }

    public void setWriteOffTime(Date writeOffTime) {
        this.writeOffTime = writeOffTime;
    }
}