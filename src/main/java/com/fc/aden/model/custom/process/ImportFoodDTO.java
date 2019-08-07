package com.fc.aden.model.custom.process;

import com.fc.aden.vo.ImportTSysFoodDTO;
import com.fc.aden.vo.ImportTSysProductDTO;

import java.util.List;

public class ImportFoodDTO {
    public static final String FOOD_NAME="食品种类（必填）";
    public static final String ENGLISH_NAME="英文名(English Name)";
    public static final String ITEM="项目点";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{FOOD_NAME, ENGLISH_NAME,ITEM};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysFoodDTO> importFoodDTOS;

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

    public List<ImportTSysFoodDTO> getImportFoodDTOS() {
        return importFoodDTOS;
    }

    public void setImportFoodDTOS(List<ImportTSysFoodDTO> importFoodDTOS) {
        this.importFoodDTOS = importFoodDTOS;
    }
}
