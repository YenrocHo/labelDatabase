package com.fc.aden.common.base;

import java.io.Serializable;
import java.util.Date;

/**
* @Description 基础类
*
* @author Created by zc on 2019/7/4
*/
public abstract class BaseEntity implements Serializable {

    protected final long serialVersionUID = 1L;

    /** 主键id */
    protected String id;
    /** 创建时间 */
    protected Date createTime;
    /** 更新时间 */
    protected Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
