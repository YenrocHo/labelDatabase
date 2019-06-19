package com.fc.aden.model.custom;

import com.fc.aden.vo.ImportTSysIemsDTO;

import java.util.List;

public class ImportDTO {
    public static final String PROJECT_NAME="项目点名称（必填）";
    public static final String CHINESE_NAME="中文名";
    public static final String ENGLISH_NAME="英文名";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{PROJECT_NAME, CHINESE_NAME, ENGLISH_NAME};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysIemsDTO> tSysItems;

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

    public List<ImportTSysIemsDTO> gettSysItems() {
        return tSysItems;
    }

    public void settSysItems(List<ImportTSysIemsDTO> tSysItems) {
        this.tSysItems = tSysItems;
    }
}
