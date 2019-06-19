package com.fc.aden.vo;


import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;

public class ImportTSysUserDTO extends TsysUser {

    //是否校验通过
    private Boolean pass;

    //校验错误信息，通过就没有
    private String messages;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }
}
