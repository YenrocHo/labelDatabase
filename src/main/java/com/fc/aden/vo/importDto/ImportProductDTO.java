package com.fc.aden.vo.importDto;

import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ImportTSysUserDTO;

import java.util.List;

public class ImportProductDTO {
    public static final String ITEM="项目点编号(必填)";
    public static final String FOOD_NAME="食品种类编号(必填)";
    public static final String PRODUCT_CODE="产品编号(非必填)";
    public static final String PRODUCT_NAME="产品名称（必填）";
    public static final String PRIORITY="产品优先级（非必填）";
    public static final String ROOM_TEMPERATURE="室温";
    public static final String FRIDGE="冰箱 低于5°C";
    public static final String FROZEN="冰柜 低于-15°C";
    public static final String ABOVE="高于65°C";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{ITEM, FOOD_NAME, PRODUCT_CODE,PRODUCT_NAME,PRIORITY,FRIDGE,FROZEN,ROOM_TEMPERATURE,ABOVE};

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
