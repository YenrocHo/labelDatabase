package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.file.FileUploadUtils;
import com.fc.test.controller.admin.FileController;
import com.fc.test.mapper.auto.TSysLearnFileMapper;
import com.fc.test.model.auto.TSysItems;
import com.fc.test.model.auto.TSysItemsExample;
import com.fc.test.model.auto.TSysLearnFile;
import com.fc.test.model.auto.TSysLearnFileExample;
import com.fc.test.util.DateUtils;
import com.fc.test.util.ExcelUtils;
import com.fc.test.util.SnowflakeIdWorker;
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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SysLearnFileService implements BaseService<TSysLearnFile, TSysLearnFileExample> {
    private static Logger logger = LoggerFactory.getLogger(SysLearnFileService.class);
    @Autowired
    private TSysLearnFileMapper tSysLearnFileMapper;

    private final static String ROOT_PATH = "D:/adenFile/";//

    private static String[] ITEM_MODE_TITLE = new String[]{"ITMES","NAME","NAME_EN"};

   @Override
    public long countByExample(TSysLearnFileExample example){
        return tSysLearnFileMapper.countByExample(example);
    }
    @Override
    public int deleteByExample(TSysLearnFileExample example){
        return tSysLearnFileMapper.deleteByExample(example);
    }
    @Override
    public int deleteByPrimaryKey(String fileId){
        return tSysLearnFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
   public int insertSelective(TSysLearnFile record){
        return tSysLearnFileMapper.insertSelective(record);
   }

   @Override
   public List<TSysLearnFile> selectByExample(TSysLearnFileExample example){
        return tSysLearnFileMapper.selectByExample(example);
   }

   @Override
    public TSysLearnFile selectByPrimaryKey(String fileId){
        return tSysLearnFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
   public int updateByExampleSelective(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example){
        return tSysLearnFileMapper.updateByExampleSelective(record,example);
   }

   @Override
   public int updateByExample(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example){
        return tSysLearnFileMapper.updateByExample(record,example);
    }

    @Override
   public int updateByPrimaryKeySelective(TSysLearnFile record){
        return tSysLearnFileMapper.updateByPrimaryKeySelective(record);
   }

   public int updateByPrimaryKey(TSysLearnFile record){
        return tSysLearnFileMapper.updateByPrimaryKey(record);
   }

    public String insert( HttpServletRequest request) throws Exception {
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
                if (file != null) {
                    // 解析excel 中的数据，进行保存
                    List<TSysItems> tSysItems = new ArrayList<>();
                    List<Map<String, String>> dataList = ExcelUtils.getExcelData(file, ITEM_MODE_TITLE);
                    for (int i = 0; i < dataList.size(); i++) {
                        Map<String, String> column = dataList.get(i);
                        TSysItems tSysItem = new TSysItems();
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
                        logger.info("上传文件超过设定.");
                        return "redirect:/ItemsController/upload";
                    }
                    // 上传到文件
                    file.transferTo(new File(path));
                    String fileurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                            "/adenFile/" + dateStylePath + "/" + file.getOriginalFilename();
                    TSysLearnFile tSysLearnFile = new TSysLearnFile(id,file.getOriginalFilename(), fileurl, DateUtils.longToString(startTime,DateUtils.DATE_TIME_PATTERN));
                   tSysLearnFileMapper.insert(tSysLearnFile);
                    // 文件上传成功之后，导入文件中的数据
                   for (int i = 0; i < tSysItems.size(); i++) {
                       TSysItems tSysItem = tSysItems.get(i);
                       TSysItemsExample tSysItemsExample = new TSysItemsExample();
                       // 通过项目点查询是否有数据，有则同步更新该数据
                       tSysItemsExample.createCriteria().andItemsEqualTo("%"+tSysItem.getItems()+"%");
                       System.out.println("开始验证项目名：" + tSysItem.getItems());
                       // TODO 没有数据的话，则创建数据
                   }
                } else {
                    logger.warn("上传文件不能为空");
                    return "redirect:/ItemsController/upload";
                }
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("上传所花时间：" + String.valueOf(endTime - startTime) + "ms");
        return null;
    }
}
