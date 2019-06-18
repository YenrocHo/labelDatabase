package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.file.FileUploadUtils;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TSysLearnFileMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TSysLearnFile;
import com.fc.aden.model.auto.TSysLearnFileExample;
import com.fc.aden.model.custom.ImportDTO;
import com.fc.aden.util.DateUtils;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.vo.ImportTSysIemsDTO;
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
     * 导入文档
     * @param request
     * @return
     * @throws Exception
     */
    public ImportDTO inportItems(HttpServletRequest request) throws Exception {
        ImportDTO result = new ImportDTO();
        String id = SnowflakeIdWorker.getUUID();
        long startTime = System.currentTimeMillis();
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (null == file) {
                    logger.warn("上传文件不能为空");
                    continue;// 结束本次文件的上传
                }
                // 解析excel 中的数据，进行保存
                List<TSysItems> tSysItems = new ArrayList<>();
                List<Map<String, String>> dataList = null;
                try {
                    dataList = ExcelUtils.getExcelData(file, ITEM_MODE_TITLE);
                } catch (Exception e) {
                    logger.warn("数据异常，重新导入", e);
                    continue;// 下面代码不再执行
                }
                for (int i = 0; i < dataList.size(); i++) {
                    Map<String, String> column = dataList.get(i);
                    TSysItems tSysItem = new TSysItems();
                    //项目名不能为空
                    if (StringUtils.isBlank(column.get(ITEM_MODE_TITLE[0]))) {
                        logger.error("项目名不能为空");
                        continue;
                    }
                    //名称不能为空
                    if (StringUtils.isBlank(column.get(ITEM_MODE_TITLE[1]))) {
                        logger.error("名称不能为空");
                        continue;
                    }
                    tSysItem.setItems(column.get(ITEM_MODE_TITLE[0]));
                    tSysItem.setName(column.get(ITEM_MODE_TITLE[1]));
                    tSysItem.setEnglishName(column.get(ITEM_MODE_TITLE[2]));
                    tSysItems.add(tSysItem);
                }
                //file.getOriginalFilename() 获取上传时的文件名
                String dateStylePath = FileUploadUtils.dateStylePath();
                String path = ROOT_PATH + dateStylePath + File.separator + file.getOriginalFilename();
                if (!FileUploadUtils.isFileExist(path)) {
                    FileUploadUtils.createFileDir(path);
                }
                if (path.length() > 20 * 1024 * 1024) {
                    continue;
                }
                // 上传文件到本地adenFile 文件夹下
                file.transferTo(new File(path));
                String fileurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                        "/adenFile/" + dateStylePath + "/" + file.getOriginalFilename();
                TSysLearnFile tSysLearnFile = new TSysLearnFile(id, file.getOriginalFilename(), fileurl, DateUtils.longToString(startTime, DateUtils.DATE_TIME_PATTERN));
                tSysLearnFileMapper.insert(tSysLearnFile);
                // 文件上传成功之后，导入文件中的数据
                for (int i = 0; i < tSysItems.size(); i++) {
                    TSysItems tSysItem = tSysItems.get(i);
                    // 通过项目点查询是否有数据，有则删除该数据
                    List<TSysItems> tSysItemsList = selectByItems(tSysItem.getItems());
                    tSysItemsMapper.deleteByItems(tSysItemsList);
                }
                for (int i = 0; i < tSysItems.size(); i++) {
                    TSysItems tSysItem = tSysItems.get(i);
                    // 导入数据
                    tSysItem.setId(id);
                    tSysItem.setUpdateTime(new Date());
                    tSysItem.setCreateTime(new Date());
                    tSysItemsMapper.insertSelective(tSysItem);
                    logger.info("导入完成!数据数量:" + i);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("上传所花时间：" + String.valueOf(endTime - startTime) + "ms");
        return result;
    }

    public ImportDTO importValid(MultipartFile excelFile){
        List<Map<String, String>> dataList;
        List<String> projectNames = new ArrayList<>();
        ImportDTO importDTO = new ImportDTO();
        List<ImportTSysIemsDTO> importTSysIemsDTOS = new ArrayList<>();
        try {
            dataList = ExcelUtils.getExcelData(excelFile, ImportDTO.IMPORT_TABLE_HEADER);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return null;
        }
        int errNumber = 0;
        int successNumber = 0;
        for (Map<String, String> row : dataList) {
            String projectName = row.get(ImportDTO.PROJECT_NAME);
            String chineseName = row.get(ImportDTO.CHINESE_NAME);
            String englishName = row.get(ImportDTO.ENGLISH_NAME);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysIemsDTO importTSysIemsDTO = new ImportTSysIemsDTO();
            importTSysIemsDTO.setCreateTime(new Date());
            importTSysIemsDTO.setUpdateTime(new Date());
            importTSysIemsDTO.setId(SnowflakeIdWorker.getUUID());

            if(StringUtils.isEmpty(projectName)){
                errorMessage.append("项目点名称不能为空；");
                pass = false;
            }else if(projectNames.contains(projectName)){
                errorMessage.append("项目点名称不能重复；");
                pass = false;
            }else{
                importTSysIemsDTO.setItems(projectName);
            }

            if(StringUtils.isEmpty(chineseName)){
                errorMessage.append("中文名称不能为空；");
                pass = false;
            }else{
                importTSysIemsDTO.setName(chineseName);
            }

            if(pass){
                errorMessage.append("成功！");
                successNumber++;
            }else{
                errNumber++;
            }
            importTSysIemsDTO.setEnglishName(englishName);
            importTSysIemsDTO.setPass(pass);
            importTSysIemsDTO.setMessages(errorMessage.toString());
            importTSysIemsDTOS.add(importTSysIemsDTO);

        }
        importDTO.setErrorNumber(errNumber);
        importDTO.setSuccessNumber(successNumber);
        importDTO.settSysItems(importTSysIemsDTOS);
        return importDTO;

    }

    public void saveSysItems(List<TSysItems> itemsList){
        for (TSysItems tSysItems : itemsList) {
            tSysItemsMapper.insertSelective(tSysItems);
        }
    }

    public List<TSysItems> getSuccessTSysItems(List<ImportTSysIemsDTO> importTSysIemsDTOS){
        if(importTSysIemsDTOS==null) return new ArrayList<>();
        List<TSysItems> tSysItemsList = new ArrayList<>();
        for (ImportTSysIemsDTO importTSysIemsDTO : importTSysIemsDTOS) {
            if(importTSysIemsDTO.getPass()){
                tSysItemsList.add(loadByDTO(importTSysIemsDTO));
            }
        }
        return tSysItemsList;
    }

    private TSysItems loadByDTO(ImportTSysIemsDTO dto){
        TSysItems tSysItems = new TSysItems();
        tSysItems.setId(dto.getId());
        tSysItems.setItems(dto.getItems());
        tSysItems.setName(dto.getName());
        tSysItems.setEnglishName(dto.getEnglishName());
        tSysItems.setCreateTime(dto.getCreateTime());
        tSysItems.setUpdateTime(dto.getUpdateTime());
        return tSysItems;
    }
}
