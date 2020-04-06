package com.fc.aden.util;

public enum Order {
    Desc("desc"),
    Asc("asc");

    private String code;
    public static final String ORDERBY = "orderBy";

    private Order(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}