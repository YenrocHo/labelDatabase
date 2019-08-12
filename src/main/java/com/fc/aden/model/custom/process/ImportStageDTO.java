package com.fc.aden.model.custom.process;

import com.fc.aden.vo.ImportTSysStageDTO;
import com.fc.aden.vo.ImportTSysUserDTO;

import java.util.List;

public class ImportStageDTO {
    public static final String ITEMS_CODE="项目点编号(必填)";
    public static final String STAGE_NAME="制作阶段名称（必填）";
    public static final String STAGE_ENGLISH="制作阶段英文名(非必填)";

    public static final String[] IMPORT_TABLE_HEADER = new String[]{ITEMS_CODE, STAGE_NAME, STAGE_ENGLISH};

    /**
     * 成功导入数量
     */
    private int successNumber;
    /**
     * 导入失败数量
     */
    private int errorNumber;


    private List<ImportTSysStageDTO> tSysStageDTOS;

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

    public List<ImportTSysStageDTO> gettSysStageDTOS() {
        return tSysStageDTOS;
    }

    public void settSysStageDTOS(List<ImportTSysStageDTO> tSysStageDTOS) {
        this.tSysStageDTOS = tSysStageDTOS;
    }
}
