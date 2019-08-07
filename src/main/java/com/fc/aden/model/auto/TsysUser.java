package com.fc.aden.model.auto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class TsysUser implements Serializable {
    private String id;

    /**
     * 员工工号
     */
    private String username;
    /**
     * 员工姓名
     */
    private String name;
    private String password;

    private String number;
    /**
     * 英文名
     */
    private String englishName;
    /**
     * 项目点
     */
    private String itemsCode;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 更新时间
     */
    private String statusToken;

    private int admin;//‘0’ admin所有管理员权限  ‘1’  项目点管理员权限

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String updateTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String createTime;
    private static final long serialVersionUID = 1L;

    public TsysUser(String id, String username, String name, String password, String number, String englishName, String itemsCode, String phoneNumber,  String updateTime, String createTime,int admin) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.number = number;
        this.englishName = englishName;
        this.itemsCode = itemsCode;
        this.phoneNumber = phoneNumber;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.admin = admin;
    }

    public TsysUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatusToken() {
        return statusToken;
    }

    public void setStatusToken(String token) {
        this.statusToken = token;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "TsysUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", englishName='" + englishName + '\'' +
                ", itemsCode='" + itemsCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", statusToken='" + statusToken + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}