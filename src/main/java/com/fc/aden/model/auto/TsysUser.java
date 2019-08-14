package com.fc.aden.model.auto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class TsysUser implements Serializable {

    /**
     * 用户管理员
     */
    public static final Integer USER_MANAGE = 1;
    /**
     * 普通员工
     */
    public static final Integer EMPLOYEES = 0;


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

    private String roles;//1 admin所有管理员权限  0  项目点管理员权限 2 admin管理员

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String updateTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String createTime;
    private static final long serialVersionUID = 1L;

    public TsysUser(String id, String username, String name, String password, String number, String englishName, String itemsCode, String phoneNumber,  String updateTime, String createTime,String roles) {
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
        this.roles = roles;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
                ", roles='" + roles + '\'' +
                '}';
    }
}