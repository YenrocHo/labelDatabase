package com.fc.aden.model.custom.process;

import java.io.Serializable;
import java.util.Date;

public  class TSysTag implements Serializable {
    private String id;

    private String labelId;

    private String originalId;

    private String items;

    private String stage;

    private String food;

    private String product;

    private String expiration;

    private String store;

    private String printUser;

    private Date printTime;

    private Date endTime;

    private Date creatTime;

    private static final long serialVersionUID = 1L;

    public TSysTag(String id, String labelId, String originalId, String items, String stage, String food, String product, String expiration, String store, String printUser, Date printTime, Date endTime, Date creatTime) {
        this.id = id;
        this.labelId = labelId;
        this.originalId = originalId;
        this.items = items;
        this.stage = stage;
        this.food = food;
        this.product = product;
        this.expiration = expiration;
        this.store = store;
        this.printUser = printUser;
        this.printTime = printTime;
        this.endTime = endTime;
        this.creatTime = creatTime;
    }

    public TSysTag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPrintUser() {
        return printUser;
    }

    public void setPrintUser(String printUser) {
        this.printUser = printUser;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "TSysTag{" +
                "id='" + id + '\'' +
                ", labelId='" + labelId + '\'' +
                ", originalId='" + originalId + '\'' +
                ", items='" + items + '\'' +
                ", stage='" + stage + '\'' +
                ", food='" + food + '\'' +
                ", product='" + product + '\'' +
                ", expiration='" + expiration + '\'' +
                ", store='" + store + '\'' +
                ", printUser='" + printUser + '\'' +
                ", printTime=" + printTime +
                ", endTime=" + endTime +
                ", creatTime=" + creatTime +
                '}';
    }
}