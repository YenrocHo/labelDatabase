package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.mapper.auto.TSysTagMapper;
import com.fc.test.model.custom.TSysTag;
import com.fc.test.model.custom.TSysTagExample;
import com.fc.test.model.custom.Tablepar;
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
    public int deleteByPrimaryKey(String id){
        return tagMapper.deleteByPrimaryKey(id);
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
     * @param searchTxt
     * @return
     */
    public com.github.pagehelper.PageInfo<TSysTag> sysTagList(Tablepar tablepar, String searchTxt){
        TSysTagExample tSysTagExample = new TSysTagExample();
        tSysTagExample.setOrderByClause("id+0 desc");
        if(searchTxt!=null&&!"".equals(searchTxt)){
            tSysTagExample.createCriteria().andProductNameLike("%"+searchTxt+"%");
        }
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysTag> list= selectByExample(tSysTagExample);
        com.github.pagehelper.PageInfo<TSysTag> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }
}
