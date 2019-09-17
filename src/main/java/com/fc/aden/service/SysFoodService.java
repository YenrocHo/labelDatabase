package com.fc.aden.service;

import com.fc.aden.common.conf.V2Config;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TsysDatasMapper;
import com.fc.aden.mapper.auto.TsysFileDataMapper;
import com.fc.aden.mapper.auto.TsysFileMapper;
import com.fc.aden.mapper.auto.process.TSysFoodPictureMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.model.auto.*;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.FileUtils;
import com.fc.aden.util.StringUtils;


import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.vo.FoodVO;
import com.fc.aden.vo.ImportTSysFoodDTO;
import com.fc.aden.vo.importDto.ImportFoodDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SysFoodService implements BaseService<TSysFood, TSysFoodExample> {

    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Autowired
    private TsysDatasMapper tsysDatasMapper;
    @Autowired
    private TSysProductMapper tSysProductMapper;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private TsysFileMapper tsysFileMapper;
    @Autowired
    private TsysFileDataMapper tsysFileDataMapper;

    /**
     * 删除食品分类
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andIdIn(lista);
        //删除关联图片
        TsysFile tsysFile = tsysFileMapper.selectByPrimaryKey(ids);
        if (tsysFile != null) {
            TsysFileData tsysFileData = tsysFileDataMapper.selectByFileId(tsysFile.getId());
            String fId = tsysFileData.getFileId();
            String dId = tsysFileData.getDataId();
            tsysFileDataMapper.deleteByFileId(fId);//删除关联数据
            tsysDatasMapper.deleteByPrimaryKey(dId);//删除关联数据
        }
        //删除关联的产品
        List<TSysProduct> productList = tSysProductMapper.selectList();
        if (productList != null && productList.size() > 0) {
            tSysProductMapper.deleteByFoodId(ids);
        }
        return tSysFoodMapper.deleteByExample(example);
    }

    @Override
    public int insertSelective(TSysFood tSysFood) {
        //添加主键id
        String id = SnowflakeIdWorker.getUUID().toString();
        tSysFood.setId(id);
        tSysFood.setCreateTime(new Date());//保存创建时间
        tSysFood.setUpdateTime(new Date());//保存更新时间
        tSysFood.setName(tSysFood.getFood());//保存更新时间
        String dataId = tSysFood.getPicture();
        String fileId = "";
        if (dataId != "" && !dataId.equals("")) {
            fileId = SnowflakeIdWorker.getUUID();
            TsysFile record = new TsysFile();// 文件
            record.setId(fileId);
            record.setFileName(tSysFood.getFood());
            record.setUpdateTime(new Date());
            sysFileService.insertSelective(record, dataId);
            tSysFood.setPicture(fileId);// 图片用的是fileId
        }
        return tSysFoodMapper.insertSelective(tSysFood);
    }

//    private TsysDatas insertFoodPictureAndGetNewPath(TSysFood tSysFood) {
//        TsysDatas tsysDatas = tsysDatasMapper.selectFileParhById(tSysFood.getPicture());
//        System.out.println("tsysDatas===:"+tSysFood.getPicture());
////        String[] strings = tsysDatas.getFilePath().split("/");
////        String pictureName = strings[strings.length - 1];
//        String oldPath = tsysDatas.getFilePath();
////        String newPath = V2Config.getProfile() + "image/" + getSystemTimeAndToString();
//        FileUtils.createFileDir(newPath);//创建图片存储路径
//        String newPicturePath = newPath + "/" + pictureName;
//        // 点击提交后
//        // 删除E:/Aden_lable/profile/ 下的图片
//        // 转移到E:/Aden_lable/profile/image中
//        FileUtils.moveFile(oldPath, newPicturePath);//从原来路径移动到新的路径
//        tsysDatas.setFilePath(newPicturePath);
//        int i =  tsysDatasMapper.updateByPrimaryKeySelective(tsysDatas);
//        System.out.println("TsysDatas.id===:"+tsysDatas.getId()+":===:"+tsysDatas.getFilePath());
////       tSysFoodPictureMapper.insertSelective(tSysFoodPicture);
//        if (i > 0) {
//            return tsysDatas;
//        } else {
//            return null;
//        }
//    }

    public int updateTsysFood(TSysFood tSysFood,String dataId) {
        //删除老数据
        //新增图片id
        String pictureId = tSysFood.getPicture();
        tSysFood.setUpdateTime(new Date());
        tSysFood.setName(tSysFood.getFood());
        TSysFood tSysFood1 = tSysFoodMapper.selectByPrimaryKey(tSysFood.getId());

        //如果图片为空 file表可能为空   如果图片不为空 file表也不为空
        if (!dataId.equals("") && dataId != "") {
            //获取旧数据
            TsysFile old_data = null;
            if(pictureId!="" && pictureId.equals("")) { //不为空编辑
                old_data = tsysFileMapper.selectByPrimaryKey(pictureId);
                //删除绑定数据
                TsysFileDataExample fileDataExample=new TsysFileDataExample();
                fileDataExample.createCriteria().andFileIdEqualTo(pictureId);
                tsysFileDataMapper.deleteByExample(fileDataExample);
                //插入关联表
                TsysFileData tsysFileData=new TsysFileData();
                tsysFileData.setId(SnowflakeIdWorker.getUUID());
                tsysFileData.setFileId(old_data.getId());
                tsysFileData.setDataId(dataId);
                tSysFood.setPicture(old_data.getId());
                tsysFileDataMapper.insert(tsysFileData);
                //插入修改人name
                old_data.setUpdateUserName(ShiroUtils.getLoginName());
                //插入修改时间
                old_data.setUpdateTime(new Date());
                tsysFileMapper.updateByPrimaryKey(old_data);
            }else{//为空新增
                old_data = new TsysFile();
                String file = SnowflakeIdWorker.getUUID();
                old_data.setId(file);
                old_data.setUpdateTime(new Date());
                old_data.setFileName(tSysFood.getFood());
                tSysFood.setPicture(file);
                sysFileService.insertSelective(old_data, dataId);
            }
        } else {
            if (tSysFood1.getPicture() != null) {
                //如果该食品种类图片id不为空 则直接在食品种类表上该id
                tSysFood.setPicture(tSysFood1.getPicture());
            }
        }
        return tSysFoodMapper.updateByPrimaryKeySelective(tSysFood);
    }

    private String getSystemTimeAndToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String time = df.format(new Date());
        return time;
    }

    @Override
    public TSysFood selectByPrimaryKey(String id) {
        return tSysFoodMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TSysFood tSysFood) {
        return tSysFoodMapper.updateByPrimaryKeySelective(tSysFood);
    }

    @Override
    public int updateByExampleSelective(TSysFood tSysFood, TSysFoodExample tSysFoodExample) {
        return tSysFoodMapper.updateByExampleSelective(tSysFood, tSysFoodExample);
    }

    @Override
    public int updateByExample(TSysFood tSysFood, TSysFoodExample tSysFoodExample) {
        return tSysFoodMapper.updateByExample(tSysFood, tSysFoodExample);
    }

    @Override
    public List<TSysFood> selectByExample(TSysFoodExample tSysFoodExample) {
        return tSysFoodMapper.selectByExample(tSysFoodExample);
    }


    @Override
    public long countByExample(TSysFoodExample tSysFoodExample) {
        return tSysFoodMapper.countByExample(tSysFoodExample);
    }

    public TSysFood findByFoodId(String foodCode) {
        return tSysFoodMapper.findByFoodId(foodCode);
    }

    @Override
    public int deleteByExample(TSysFoodExample tSysFoodExample) {
        return tSysFoodMapper.deleteByExample(tSysFoodExample);
    }

    @Autowired
    private TSysItemsMapper tSysItemsMapper;

    /**
     * 标签管理
     *
     * @param tablepar
     * @param foodName
     * @param itemsCode
     * @return
     */
    public PageInfo<TSysFood> sysFoodList(Tablepar tablepar, String foodName, String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysFood> tSysFoodList = null;
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            //如果是项目管理员 根据项目编号搜索所有数据
            tSysFoodList = tSysFoodMapper.findByFoodItems(foodName, tsysUser.getItemsCode());
        } else {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            //如果是后台管理员则按条件搜索所有数据
            tSysFoodList = tSysFoodMapper.queryByFood(foodName, itemsCode);
        }

        PageInfo<TSysFood> pageInfo = new PageInfo<TSysFood>(tSysFoodList);
        return pageInfo;
    }

    /**
     * 检查食品名称是否重名
     *
     * @param food itemsCode
     * @return
     */
    public int checkFoodUnique(String food, String itemsCode) {
        List<TSysFood> list = tSysFoodMapper.findByFood(food, itemsCode);
        return list.size();
    }

    /**
     * 检查食品编号是否重复
     *
     * @param tSysFood
     * @return
     */
    public int checkFoodCodeUnique(TSysFood tSysFood) {
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andFoodCodeEqualTo(tSysFood.getFoodCode());
        List<TSysFood> list = selectByExample(example);
        return list.size();
    }

    public List<TSysFood> queryFood() {
        TSysFoodExample example = new TSysFoodExample();
        return selectByExample(example);
    }

    public List<TSysFood> findByItemCode(String itemsCode) {
        return tSysFoodMapper.findByItemCode(itemsCode);
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 验证文件，数据导入到DTO
     *
     * @param dataList
     * @return
     */
    public ImportFoodDTO importValid(List<Map<String, String>> dataList) {
        List<String> projectNames = new ArrayList<>();
        ImportFoodDTO importFoodDTO = new ImportFoodDTO();
        List<ImportTSysFoodDTO> importTSysFoodDTOS = new ArrayList<ImportTSysFoodDTO>();
        int errNumber = 0;
        int successNumber = 0;
        for (int i = 1; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            String foodName = row.get(ImportFoodDTO.FOOD_NAME);
            String foodCode = row.get(ImportFoodDTO.FOOD_CODE);
            String englishName = row.get(ImportFoodDTO.ENGLISH_NAME);
            String items = row.get(ImportFoodDTO.ITEM);
            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysFoodDTO importTSysFoodDTO = new ImportTSysFoodDTO();
            importTSysFoodDTO.setCreateTime(df.format(new Date()));
            importTSysFoodDTO.setUpdateTime(df.format(new Date()));
            importTSysFoodDTO.setStatus(1);
            importTSysFoodDTO.setId(UUID.randomUUID().toString());
            List<TSysFood> tSysFoodList = tSysFoodMapper.findByFood(foodName, items);
            if (StringUtils.isEmpty(foodName)) {
                errorMessage.append("食品种类不能为空；");
                pass = false;
            } else if (projectNames.contains(foodName) || tSysFoodList != null && tSysFoodList.size() > 0) {
                importTSysFoodDTO.setFood(foodName);
                errorMessage.append("食品种类不能重复；");
                pass = false;
            } else {
                importTSysFoodDTO.setFood(foodName);
                importTSysFoodDTO.setName(foodName);
            }
            //判断食品编号
            List<TSysFood> tSysFoodCode = tSysFoodMapper.findByFoodCode(foodCode);
            if (StringUtils.isEmpty(foodName)) {
                errorMessage.append("食品种类编号不能为空；");
                pass = false;
            } else if (projectNames.contains(foodName) || tSysFoodCode != null && tSysFoodCode.size() > 0) {
                importTSysFoodDTO.setFoodCode(foodCode);
                errorMessage.append("食品种类编号不能重复；");
                pass = false;
            } else {
                importTSysFoodDTO.setFoodCode(foodCode);
            }
            //判断项目点
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if (StringUtils.isEmpty(items)) {
                errorMessage.append("项目点不为空；");
                pass = false;
            } else if (tSysItemsList != null || tSysItemsList.size() > 0) {
                importTSysFoodDTO.setItemsCode(items);
            } else {
                importTSysFoodDTO.setItemsCode(items);
                errorMessage.append("项目点不存在；");
                pass = false;
            }
            if (pass) {
                errorMessage.append("成功！");
                successNumber++;
            } else {
                errNumber++;
            }
            importTSysFoodDTO.setEnglishName(englishName);
            importTSysFoodDTO.setPass(pass);
            importTSysFoodDTO.setMessages(errorMessage.toString());
            importTSysFoodDTOS.add(importTSysFoodDTO);
        }
        importFoodDTO.setErrorNumber(errNumber);
        importFoodDTO.setSuccessNumber(successNumber);
        importFoodDTO.setImportFoodDTOS(importTSysFoodDTOS);
        return importFoodDTO;
    }

    /**
     * @param importTSysFoodDTOS
     * @return
     */
    public List<FoodVO> getSuccessTSysProduct(List<ImportTSysFoodDTO> importTSysFoodDTOS) {
        if (importTSysFoodDTOS == null) return new ArrayList<>();
        List<FoodVO> foodVOList = new ArrayList<>();
        for (ImportTSysFoodDTO importTSysFoodDTO : importTSysFoodDTOS) {
            if (importTSysFoodDTO.getPass()) {
                foodVOList.add(loadByDTO(importTSysFoodDTO));
            }
        }
        return foodVOList;
    }

    public FoodVO loadByDTO(ImportTSysFoodDTO importTSysFoodDTO) {
        FoodVO foodVO = new FoodVO();
        BeanCopierEx.copy(importTSysFoodDTO, foodVO);
        return foodVO;
    }

    /**
     * 导入文件到数据库
     *
     * @param foodVOS
     */
    public void saveSysFood(List<FoodVO> foodVOS) {
        for (FoodVO foodVO : foodVOS) {
            TSysFood tSysFood = new TSysFood();
            tSysFood.setUpdateTime(new Date());
            tSysFood.setCreateTime(new Date());
            BeanCopierEx.copy(foodVO, tSysFood);
            tSysFoodMapper.insertSelective(tSysFood);
        }
    }
}
