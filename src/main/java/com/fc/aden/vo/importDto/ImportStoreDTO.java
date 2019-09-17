package com.fc.aden.vo.importDto;

import com.fc.aden.vo.ImportTSysStageDTO;
import com.fc.aden.vo.ImportTSysStoreDTO;

import java.util.List;

public class ImportStoreDTO {
    public static final String ITEMS_CODE="项目点编号(必填)";
    public static final String STORE_NAME="存储条件名称（必填）";
    public static final String STORE_ENGLISH="存储条件英文名（非必填）";
    public static final String STORE_DEPICT="条件描述(非必填)";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{ITEMS_CODE, STORE_NAME, STORE_ENGLISH,STORE_DEPICT};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysStoreDTO> importTSysStoreDTOS;

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

    public List<ImportTSysStoreDTO> getImportTSysStoreDTOS() {
        return importTSysStoreDTOS;
    }

    public void setImportTSysStoreDTOS(List<ImportTSysStoreDTO> importTSysStoreDTOS) {
        this.importTSysStoreDTOS = importTSysStoreDTOS;
    }
}
