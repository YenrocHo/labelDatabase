package com.fc.aden.service;

import com.fc.aden.common.conf.V2Config;
import com.fc.aden.mapper.auto.TsysDatasMapper;
import com.fc.aden.mapper.auto.process.TSysFoodPictureMapper;
import com.fc.aden.model.auto.TsysDatas;
import com.fc.aden.model.custom.process.TSysFoodPicture;
import com.fc.aden.util.FileUtils;
import com.fc.aden.util.StringUtils;


import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysFoodExample;
import com.fc.aden.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysFoodService implements BaseService<TSysFood, TSysFoodExample> {

    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Autowired
    private TsysDatasMapper tsysDatasMapper;
    @Autowired
    private TSysFoodPictureMapper tSysFoodPictureMapper;

    private final String IMG_TAG = "<image src='{0}'></image>";

    // 正常图片的地址
    private final String IMG_STR = "{0}/FileController/viewImg/{1}";

    // 缩略图的图片的地址
    private final String IMG_SMALL_STR = "{0}/FileController/viewImgSmall/{1}";

    @Override
    public int deleteByPrimaryKey(String ids){
        List<String> lista = Convert.toListStrArray(ids);
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andIdIn(lista);
        int i = tSysFoodPictureMapper.deleteByFoodIds(lista);
        if(i>0){
            return tSysFoodMapper.deleteByExample(example);
        }else {
            return 0;
        }

    }

    @Override
    public int insertSelective(TSysFood tSysFood){
        //添加主键id
        String id= SnowflakeIdWorker.getUUID();
        tSysFood.setId(id);
        tSysFood.setCreateTime(new Date());//保存创建时间
        tSysFood.setUpdateTime(new Date());//保存更新时间
        String newPath = insertFoodPictureAndGetNewPath(tSysFood);
        if (StringUtils.isNotEmpty(newPath)){
            tSysFood.setPicture(newPath);
            return tSysFoodMapper.insertSelective(tSysFood);
        }else {
            return 0;
        }
    }
    private String insertFoodPictureAndGetNewPath(TSysFood tSysFood){
        TsysDatas tsysDatas = tsysDatasMapper.selectFileParhById(tSysFood.getPicture());
        String[] strings = tsysDatas.getFilePath().split("/");
        String pictureName = strings[strings.length-1];
        TSysFoodPicture tSysFoodPicture = new TSysFoodPicture();
        String FoodPictueId= SnowflakeIdWorker.getUUID();
        tSysFoodPicture.setId(FoodPictueId);
        tSysFoodPicture.setFoodId(tSysFood.getId());
        tSysFoodPicture.setDataId(tsysDatas.getId());
        tSysFoodPicture.setCreateTime(new Date());
        tSysFoodPicture.setUpdateTime(new Date());
        //V2Config.getProfile() = E:/Aden_lable/profile/
        String oldPath= tsysDatas.getFilePath();
        String newPath = V2Config.getProfile()+"image/"+getSystemTimeAndToString();
        FileUtils.createFileDir(newPath);
        String newPicturePath = newPath+"/"+pictureName;
        // 点击提交后
        // 删除E:/Aden_lable/profile/ 下的图片
        // 转移到E:/Aden_lable/profile/image中
        FileUtils.moveFile(oldPath,newPicturePath);
        tsysDatas.setFilePath(newPicturePath);
        tsysDatasMapper.updateByPrimaryKeySelective(tsysDatas);
        int i = tSysFoodPictureMapper.insertSelective(tSysFoodPicture);
        if(i>0){
            return newPicturePath;
        }else {
            return null;
        }
    }
    private String getSystemTimeAndToString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String time = df.format(new Date());
        return time;
    }
    @Override
    public TSysFood selectByPrimaryKey(String id){
        return tSysFoodMapper.selectByPrimaryKey(id);
    }

    @Override
    public int  updateByPrimaryKeySelective(TSysFood tSysFood){
        return tSysFoodMapper.updateByPrimaryKeySelective(tSysFood);
    }

    @Override
    public int updateByExampleSelective(TSysFood tSysFood,TSysFoodExample tSysFoodExample){
        return tSysFoodMapper.updateByExampleSelective(tSysFood,tSysFoodExample);
    }

    @Override
    public int updateByExample(TSysFood tSysFood,TSysFoodExample tSysFoodExample){
        return tSysFoodMapper.updateByExample(tSysFood,tSysFoodExample);
    }

    @Override
    public List<TSysFood> selectByExample(TSysFoodExample tSysFoodExample){
        return tSysFoodMapper.selectByExample(tSysFoodExample);
    }

    @Override
    public long countByExample(TSysFoodExample tSysFoodExample){
        return tSysFoodMapper.countByExample(tSysFoodExample);
    }

    @Override
    public int deleteByExample(TSysFoodExample tSysFoodExample){
        return tSysFoodMapper.deleteByExample(tSysFoodExample);
    }


    /**
     * 标签管理
     * @param tablepar
     * @param foodName
     * @param
     * @return
     */
    public PageInfo<TSysFood> sysFoodList(Tablepar tablepar, String foodName){
        TSysFoodExample tSysFoodExample = new TSysFoodExample();
        tSysFoodExample.setOrderByClause("id+0 desc");
        if(foodName!=null&&!"".equals(foodName)){
            tSysFoodExample.createCriteria().andfoodNameLike("%"+foodName+"%");
        }
        if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<TSysFood> list= selectByExample(tSysFoodExample);
        PageInfo<TSysFood> pageInfo = new PageInfo<TSysFood>(list);
        return  pageInfo;
    }

    /**
     * 检查阶段名称是否重名
     * @param tSysFood
     * @return
     */
    public int checkFoodUnique(TSysFood tSysFood){
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andStageEqualTo(tSysFood.getFood());
        List<TSysFood> list = selectByExample(example);
        return list.size();
    }

}
