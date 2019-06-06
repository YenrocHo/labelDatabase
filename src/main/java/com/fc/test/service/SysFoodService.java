package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.process.TSysFoodMapper;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.process.TSysFood;
import com.fc.test.model.custom.process.TSysFoodExample;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysFoodService implements BaseService<TSysFood, TSysFoodExample> {

    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Override
    public int deleteByPrimaryKey(String ids){
        List<String> lista = Convert.toListStrArray(ids);
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andIdIn(lista);
        return tSysFoodMapper.deleteByExample(example);
    }

    @Override
    public int insertSelective(TSysFood tSysFood){
        //添加主键id
        String id= SnowflakeIdWorker.getUUID();
        tSysFood.setId(id);
        tSysFood.setCreateTime(new Date());//保存创建时间
        tSysFood.setUpdateTime(new Date());//保存更新时间
        return tSysFoodMapper.insertSelective(tSysFood);
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
     * @param searchTxt
     * @return
     */
    public com.github.pagehelper.PageInfo<TSysFood> sysFoodList(Tablepar tablepar, String searchTxt){
        TSysFoodExample tSysFoodExample = new TSysFoodExample();
        tSysFoodExample.setOrderByClause("id+0 desc");
        if(searchTxt!=null&&!"".equals(searchTxt)){
            tSysFoodExample.createCriteria().andProductNameLike("%"+searchTxt+"%");
        }
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysFood> list= selectByExample(tSysFoodExample);
        com.github.pagehelper.PageInfo<TSysFood> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }

    /**
     * 检查阶段名称是否重名
     * @param tSysFood
     * @return
     */
    public int checkFoodUnique(TSysFood tSysFood){
        TSysFoodExample example = new TSysFoodExample();
        example.createCriteria().andStageEqualTo(tSysFood.getFoodName());
        List<TSysFood> list = selectByExample(example);
        return list.size();
    }

}
