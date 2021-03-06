package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.file.FileUploadUtils;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TSysLearnFileMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TSysLearnFile;
import com.fc.aden.model.auto.TSysLearnFileExample;
import com.fc.aden.vo.importDto.ImportItemsDTO;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.DateUtils;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.vo.ImportTSysItemsDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 文件导入
 *
 * @author: 栾冰
 * @date:2019/06/14
 */
@Service
public class SysLearnFileService implements BaseService<TSysLearnFile, TSysLearnFileExample> {
    private static Logger logger = LoggerFactory.getLogger(SysLearnFileService.class);
    @Autowired
    private TSysLearnFileMapper tSysLearnFileMapper;
    @Autowired
    private TSysItemsMapper tSysItemsMapper;

    private final static String ROOT_PATH = "D:/adenFile/";//




    private static String[] ITEM_MODE_TITLE = new String[]{"ITMES", "NAME", "NAME_EN"};

    @Override
    public long countByExample(TSysLearnFileExample example) {
        return tSysLearnFileMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TSysLearnFileExample example) {
        return tSysLearnFileMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String fileId) {
        return tSysLearnFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public int insertSelective(TSysLearnFile record) {
        return tSysLearnFileMapper.insertSelective(record);
    }

    @Override
    public List<TSysLearnFile> selectByExample(TSysLearnFileExample example) {
        return tSysLearnFileMapper.selectByExample(example);
    }

    @Override
    public TSysLearnFile selectByPrimaryKey(String fileId) {
        return tSysLearnFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int updateByExampleSelective(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example) {
        return tSysLearnFileMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example) {
        return tSysLearnFileMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TSysLearnFile record) {
        return tSysLearnFileMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TSysLearnFile record) {
        return tSysLearnFileMapper.updateByPrimaryKey(record);
    }

    public List<TSysItems> selectByItems(String items) {
        return tSysItemsMapper.selectByItems(items);
    }

    /**
     * 验证文件，数据导入到DTO
     * @param dataList
     * @return
     */
    public ImportItemsDTO importValid(List<Map<String, String>> dataList){

        List<String> projectNames = new ArrayList<>();
        ImportItemsDTO importItemsDTO = new ImportItemsDTO();
        List<ImportTSysItemsDTO> importTSysItemsDTOS = new ArrayList<>();
        int errNumber = 0;
        int successNumber = 0;
         for (Map<String, String> row : dataList) {
//        for (int i = 1; i < dataList.size(); i++) {//从文件的第二行开始
//            Map<String, String> row = dataList.get(i);
            String itemsCode = row.get(ImportItemsDTO.PROJECT_NAME);
            String chineseName = row.get(ImportItemsDTO.CHINESE_NAME);
            String englishName = row.get(ImportItemsDTO.ENGLISH_NAME);
            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysItemsDTO importTSysItemsDTO = new ImportTSysItemsDTO();
            importTSysItemsDTO.setCreateTime(new Date());
            importTSysItemsDTO.setUpdateTime(new Date());
            importTSysItemsDTO.setStatus(1);
            importTSysItemsDTO.setId(UUID.randomUUID().toString());

            List<TSysItems> items = tSysItemsMapper.selectByItems(itemsCode);
            if(StringUtils.isEmpty(itemsCode)){
                errorMessage.append("项目点编号不能为空；");
                pass = false;
            }else if(projectNames.contains(itemsCode)||items!=null&&items.size()>0){
                errorMessage.append("项目点编号不能重复；");
                pass = false;
            }else {
                importTSysItemsDTO.setItemsCode(itemsCode);
            }
            if(StringUtils.isEmpty(chineseName)){
                errorMessage.append("中文名称不能为空；");
                pass = false;
            }else{
                importTSysItemsDTO.setName(chineseName);
            }
            if(pass){
                errorMessage.append("成功！");
                successNumber++;
            }else{
                errNumber++;
            }
            importTSysItemsDTO.setEnglishName(englishName);
            importTSysItemsDTO.setPass(pass);
            importTSysItemsDTO.setMessages(errorMessage.toString());
            importTSysItemsDTOS.add(importTSysItemsDTO);
        }
        importItemsDTO.setErrorNumber(errNumber);
        importItemsDTO.setSuccessNumber(successNumber);
        importItemsDTO.settSysItems(importTSysItemsDTOS);
        return importItemsDTO;

    }

    /**
     * 导入文件
     * @param itemsList
     */
    public void saveSysItems(List<TSysItems> itemsList){
        for (TSysItems tSysItems : itemsList) {
            tSysItemsMapper.insertSelective(tSysItems);
        }
    }

    public List<TSysItems> getSuccessTSysItems(List<ImportTSysItemsDTO> importTSysItemsDTOS){
        if(importTSysItemsDTOS ==null) return new ArrayList<>();
        List<TSysItems> tSysItemsList = new ArrayList<>();
        for (ImportTSysItemsDTO importTSysItemsDTO : importTSysItemsDTOS) {
            if(importTSysItemsDTO.getPass()){
                tSysItemsList.add(loadByDTO(importTSysItemsDTO));
            }
        }
        return tSysItemsList;
    }

    /**
     * 确认导入数据
     * @param dto
     * @return
     */
    private TSysItems loadByDTO(ImportTSysItemsDTO dto){
        TSysItems tSysItems = new TSysItems();
        BeanCopierEx.copy(dto,tSysItems);
        return tSysItems;
    }
}
