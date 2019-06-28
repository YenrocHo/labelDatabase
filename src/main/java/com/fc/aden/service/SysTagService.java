package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.mapper.auto.process.TSysTagMapper;


import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysTag;
import com.fc.aden.model.custom.process.TSysTagExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTagService implements BaseService<TSysTag, TSysTagExample> {

    @Autowired
    private TSysTagMapper tagMapper;

    @Override
    public int deleteByPrimaryKey(String ids){
        return tagMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insertSelective(TSysTag tSysTag){
        return tagMapper.insertSelective(tSysTag);
    }

    @Override
    public TSysTag selectByPrimaryKey(String id){
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int  updateByPrimaryKeySelective(TSysTag tSysTag){
        return tagMapper.updateByPrimaryKeySelective(tSysTag);
    }

    @Override
    public int updateByExampleSelective(TSysTag tSysTag,TSysTagExample tSysTagExample){
        return tagMapper.updateByExampleSelective(tSysTag,tSysTagExample);
    }

    @Override
    public int updateByExample(TSysTag tSysTag,TSysTagExample tSysTagExample){
        return tagMapper.updateByExample(tSysTag,tSysTagExample);
    }

    @Override
    public List<TSysTag> selectByExample(TSysTagExample tSysTagExample){
        return tagMapper.selectByExample(tSysTagExample);
    }

    @Override
    public long countByExample(TSysTagExample tSysTagExample){
        return tagMapper.countByExample(tSysTagExample);
    }

    @Override
    public int deleteByExample(TSysTagExample tSysTagExample){
        return tagMapper.deleteByExample(tSysTagExample);
    }

    /**
     * 标签管理
     * @param tablepar
     * @param stage
     * @param variety
     * @param productName
     * @param items
     * @param printUser
     * @return
     */
    public com.github.pagehelper.PageInfo<TSysTag> sysTagList(Tablepar tablepar, String stage,String variety,String productName,String items,String printUser){
        TSysTagExample tSysTagExample = new TSysTagExample();
        tSysTagExample.setOrderByClause("id+0 desc");
        if(stage!=null&&!"".equals(stage)){
            tSysTagExample.createCriteria().andStageLike("%"+stage+"%");
        }
        if(variety!=null&&!"".equals(variety)){
            tSysTagExample.createCriteria().andVarietyLike("%"+variety+"%");
        }
        if(productName!=null&&!"".equals(productName)){
            tSysTagExample.createCriteria().andProductNameLike("%"+productName+"%");
        }
        if(items!=null&&!"".equals(items)){
            tSysTagExample.createCriteria().andItemsLike("%"+items+"%");
        }
        if(printUser!=null&&!"".equals(printUser)){
            tSysTagExample.createCriteria().andPrintUserLike("%"+printUser+"%");
        }

        if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<TSysTag> list= selectByExample(tSysTagExample);
        com.github.pagehelper.PageInfo<TSysTag> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }
}
