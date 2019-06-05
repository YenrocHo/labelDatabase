package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.mapper.auto.TSysEmployeesMapper;

import com.fc.test.model.custom.TSysEmployees;
import com.fc.test.model.custom.TSysEmployeesExample;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysEmployeesService implements BaseService<TSysEmployees, TSysEmployeesExample> {

    @Autowired
    private TSysEmployeesMapper tSysEmployeesMapper;

    @Override
    public int deleteByPrimaryKey(String id){
        return tSysEmployeesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TSysEmployees tSysEmployees){
        return tSysEmployeesMapper.insertSelective(tSysEmployees);
    }

    @Override
    public TSysEmployees selectByPrimaryKey(String id){
        return tSysEmployeesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int  updateByPrimaryKeySelective(TSysEmployees tSysEmployees){
        return tSysEmployeesMapper.updateByPrimaryKeySelective(tSysEmployees);
    }

    @Override
    public int updateByExampleSelective(TSysEmployees tSysEmployees, TSysEmployeesExample tSysEmployeesExample){
        return tSysEmployeesMapper.updateByExampleSelective(tSysEmployees,tSysEmployeesExample);
    }

    @Override
    public int updateByExample(TSysEmployees tSysEmployees, TSysEmployeesExample tSysEmployeesExample){
        return tSysEmployeesMapper.updateByExample(tSysEmployees,tSysEmployeesExample);
    }

    @Override
    public List<TSysEmployees> selectByExample(TSysEmployeesExample tSysEmployeesExample){
        return tSysEmployeesMapper.selectByExample(tSysEmployeesExample);
    }

    @Override
    public long countByExample(TSysEmployeesExample tSysEmployees){
        return tSysEmployeesMapper.countByExample(tSysEmployees);
    }

    @Override
    public int deleteByExample(TSysEmployeesExample tSysEmployees){
        return tSysEmployeesMapper.deleteByExample(tSysEmployees);
    }

    /**
     * 标签管理
     * @param tablepar
     * @param searchTxt
     * @return
     */
    public PageInfo<TSysEmployees> sysEmployeesList(Tablepar tablepar, String searchTxt){
        TSysEmployeesExample tSysTagExample = new TSysEmployeesExample();
        tSysTagExample.setOrderByClause("id+0 desc");
        if(searchTxt!=null&&!"".equals(searchTxt)){
            tSysTagExample.createCriteria().andProductNameLike("%"+searchTxt+"%");
        }
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysEmployees> list= tSysEmployeesMapper.selectByExample(tSysTagExample);
        PageInfo<TSysEmployees> pageInfo = new PageInfo<TSysEmployees>(list);
        return  pageInfo;
    }
}
