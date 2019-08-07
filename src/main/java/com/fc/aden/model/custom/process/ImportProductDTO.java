package com.fc.aden.model.custom.process;

import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ImportTSysUserDTO;

import java.util.List;

public class ImportProductDTO {
    public static final String PRODUCT_NAME="产品名称（必填）";
    public static final String ENGLISH_NAME="英文名(English Name)";
    public static final String SHELF_LIFE="保质期";
    public static final String FOOD_NAME="食品种类(必填)";
    public static final String ITEM="项目点";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{PRODUCT_NAME, ENGLISH_NAME, SHELF_LIFE,FOOD_NAME,ITEM};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysProductDTO> tSysProductDTOS;

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

    public List<ImportTSysProductDTO> gettSysProductDTOS() {
        return tSysProductDTOS;
    }

    public void settSysProductDTOS(List<ImportTSysProductDTO> tSysProductDTOS) {
        this.tSysProductDTOS = tSysProductDTOS;
    }
}
