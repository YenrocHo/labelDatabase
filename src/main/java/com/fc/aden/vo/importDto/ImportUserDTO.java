package com.fc.aden.vo.importDto;

import com.fc.aden.vo.ImportTSysItemsDTO;
import com.fc.aden.vo.ImportTSysUserDTO;

import java.util.List;

public class ImportUserDTO {
    public static final String LOGIN_NAME="工号（必填）";
    public static final String CHINESE_NAME="中文名";
    public static final String ENGLISH_NAME="英文名";
    public static final String ITEMS="所属项目点(必填)";
    public static final String PHONE="手机号";
    public static final String ROLES="用户角色";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{LOGIN_NAME, CHINESE_NAME, ENGLISH_NAME,ITEMS,PHONE,ROLES};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysUserDTO> tSysUser;

    public int getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(int successNumber) {
        this.successNumber = successNumber;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public List<ImportTSysUserDTO> gettSysUser() {
        return tSysUser;
    }

    public void settSysUser(List<ImportTSysUserDTO> tSysUser) {
        this.tSysUser = tSysUser;
    }
}
