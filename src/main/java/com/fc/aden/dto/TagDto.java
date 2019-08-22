/**
 * projectName: labelDatabase
 * fileName: TagDto.java
 * packageName: com.fc.aden.dto
 * date: 2019-08-21
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.dto;

import com.fc.aden.model.custom.Tablepar;

import java.util.Date;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: TagDto
 * @packageName: com.fc.aden.dto
 * @description:
 * @data: 2019-08-21
 **/
public class TagDto extends Tablepar {
    private String stage;
    private String food;
    private String product;
    private String items;
    private String printUser;
    private Date startTime;
    private Date endTime;


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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrintUser() {
        return printUser;
    }

    public void setPrintUser(String printUser) {
        this.printUser = printUser;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "stage='" + stage + '\'' +
                ", food='" + food + '\'' +
                ", product='" + product + '\'' +
                ", items='" + items + '\'' +
                ", printUser='" + printUser + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
