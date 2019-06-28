package com.fc.aden.model.custom.process;

import java.io.Serializable;

public class TSysWeight implements Serializable {
    private Integer id;

    private Integer number;

    private String unit;

    private String item_id;

    public TSysWeight(Integer id, Integer number, String unit, String item_id) {
        this.id = id;
        this.number = number;
        this.unit = unit;
        this.item_id = item_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    @Override
    public String toString() {
        return "TSysWeight{" +
                "id=" + id +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", item_id='" + item_id + '\'' +
                '}';
    }
}