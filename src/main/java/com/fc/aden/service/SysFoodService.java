package com.fc.aden.service;

import com.fc.aden.common.conf.V2Config;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TsysDatasMapper;
import com.fc.aden.mapper.auto.process.TSysFoodPictureMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysDatas;
import com.fc.aden.model.custom.process.*;
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
    private TSysFoodPictureMapper tSysFoodPictureMapper;
    @Autowired
    private TSysProductMapper tSysProductMapper;


    /**
     * 删除食品分类
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andIdIn(lista);
        List<TSysFoodPicture> tSysFoodPictures = tSysFoodPictureMapper.selectByFoodId(ids);
        //如果有图片也删掉
        if (tSysFoodPictures != null && tSysFoodPictures.size() > 0) {
            tSysFoodPictureMapper.deleteByFoodIds(ids);
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
        String newPath = insertFoodPictureAndGetNewPath(tSysFood);
        if (StringUtils.isNotEmpty(newPath)) {
            tSysFood.setPicture(newPath);
            return tSysFoodMapper.insertSelective(tSysFood);
        } else {
            return 0;
        }
    }

    private String insertFoodPictureAndGetNewPath(TSysFood tSysFood) {
        TsysDatas tsysDatas = tsysDatasMapper.selectFileParhById(tSysFood.getPicture());
        String[] strings = tsysDatas.getFilePath().split("/");
        String pictureName = strings[strings.length - 1];
        TSysFoodPicture tSysFoodPicture = new TSysFoodPicture();
        String FoodPictueId = SnowflakeIdWorker.getUUID();
        tSysFoodPicture.setId(FoodPictueId);
        tSysFoodPicture.setFoodId(tSysFood.getId());
        tSysFoodPicture.setDataId(tsysDatas.getId());
        tSysFoodPicture.setCreateTime(new Date());
        tSysFoodPicture.setUpdateTime(new Date());
        String oldPath = tsysDatas.getFilePath();
        String newPath = V2Config.getProfile() + "image/" + getSystemTimeAndToString();
        FileUtils.createFileDir(newPath);
        String newPicturePath = newPath + "/" + pictureName;
        // 点击提交后
        // 删除E:/Aden_lable/profile/ 下的图片
        // 转移到E:/Aden_lable/profile/image中
        FileUtils.moveFile(oldPath, newPicturePath);
        tsysDatas.setFilePath(newPicturePath);
        tsysDatasMapper.updateByPrimaryKeySelective(tsysDatas);
        int i = tSysFoodPictureMapper.insertSelective(tSysFoodPicture);
        if (i > 0) {
            return newPicturePath;
        } else {
            return null;
        }
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
     * @param
     * @return
     */
    public PageInfo<FoodVO> sysFoodList(Tablepar tablepar, String foodName) {
        TSysFoodExample tSysFoodExample = new TSysFoodExample();
        tSysFoodExample.setOrderByClause("id+0 desc");
        if (foodName != null && !"".equals(foodName)) {
            tSysFoodExample.createCriteria().andfoodNameLike("%" + foodName + "%");
        }
        if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<TSysFood> list = selectByExample(tSysFoodExample);
        List<FoodVO> foodVOList = new ArrayList<>();
        for (TSysFood tSysFood : list) {
            FoodVO foodVO = new FoodVO();
            TSysItems tSysItems = tSysItemsMapper.selectByItemCode(tSysFood.getItemsCode());
            foodVO.setId(tSysFood.getId());
            foodVO.setItemsCode(tSysItems.getItemsCode());
            BeanCopierEx.copy(tSysFood, foodVO);
            foodVOList.add(foodVO);
        }
        PageInfo<FoodVO> pageInfo = new PageInfo<FoodVO>(foodVOList);
        return pageInfo;
    }

    /**
     * 检查阶段名称是否重名
     *
     * @param tSysFood
     * @return
     */
    public int checkFoodUnique(TSysFood tSysFood) {
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andStageEqualTo(tSysFood.getFood());
        List<TSysFood> list = selectByExample(example);
        return list.size();
    }

    public List<TSysFood> queryFood() {
        TSysFoodExample example = new TSysFoodExample();
        return selectByExample(example);
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
        for (Map<String, String> row : dataList) {
            String foodName = row.get(ImportFoodDTO.FOOD_NAME);
            String englishName = row.get(ImportFoodDTO.ENGLISH_NAME);
            String items = row.get(ImportFoodDTO.ITEM);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysFoodDTO importTSysFoodDTO = new ImportTSysFoodDTO();
            importTSysFoodDTO.setCreateTime(df.format(new Date()));
            importTSysFoodDTO.setUpdateTime(df.format(new Date()));
            importTSysFoodDTO.setStatus(1);
            importTSysFoodDTO.setId(UUID.randomUUID().toString());
            List<TSysFood> tSysFoodList = tSysFoodMapper.findByFood(foodName);
            if (StringUtils.isEmpty(foodName)) {
                errorMessage.append("食品种类不能为空；");
                pass = false;
            } else if(projectNames.contains(foodName)){
                errorMessage.append("食品种类不能重复；");
                pass = false;
            }else if(tSysFoodList!=null && tSysFoodList.size()>0) {
                errorMessage.append("食品种类不能重复；");
                pass = false;
            }else{
                importTSysFoodDTO.setFood(foodName);
                importTSysFoodDTO.setName(foodName);
            }
            //判断项目点
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if (StringUtils.isEmpty(items)) {
                errorMessage.append("项目点不为空；");
                pass = false;
            } else if (tSysItemsList != null && tSysItemsList.size() > 0) {
                importTSysFoodDTO.setItemsCode(items);
            } else {
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
