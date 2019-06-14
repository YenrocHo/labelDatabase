package com.fc.test.model.custom.process;

import java.io.Serializable;
import java.util.Date;

public class TSysStore implements Serializable {
    private String id;

    private String query;

    private String cCondition;

    private String eCondition;

    private String temperature;

    private Integer status;

    private Date creatTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public TSysStore(String id, String query, String cCondition, String eCondition, String temperature, Integer status, Date creatTime, Date updateTime) {
        this.id = id;
        this.query = query;
        this.cCondition = cCondition;
        this.eCondition = eCondition;
        this.temperature = temperature;
        this.status = status;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public TSysStore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getcCondition() {
        return cCondition;
    }

    public void setcCondition(String cCondition) {
        this.cCondition = cCondition;
    }

    public String geteCondition() {
        return eCondition;
    }

    public void seteCondition(String eCondition) {
        this.eCondition = eCondition;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "TSysStore{" +
                "id='" + id + '\'' +
                ", query='" + query + '\'' +
                ", cCondition='" + cCondition + '\'' +
                ", eCondition='" + eCondition + '\'' +
                ", temperature='" + temperature + '\'' +
                ", status=" + status +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}