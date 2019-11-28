package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.mapper.auto.process.PrintHistoryMapper;
import com.fc.aden.mapper.auto.process.TSysTagMapper;


import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.TSysTag;
import com.fc.aden.model.custom.process.TSysTagExample;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.vo.PrintHistoryVO;
import com.fc.aden.vo.TagVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysTagService implements BaseService<TSysTag, TSysTagExample> {

    @Autowired
    private TSysTagMapper tagMapper;

    @Autowired
    private PrintHistoryMapper printHistoryMapper;

    @Override
    public int deleteByPrimaryKey(String ids) {
        return tagMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insertSelective(TSysTag tSysTag) {
        return tagMapper.insertSelective(tSysTag);
    }

    @Override
    public TSysTag selectByPrimaryKey(String id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TSysTag tSysTag) {
        return tagMapper.updateByPrimaryKeySelective(tSysTag);
    }

    @Override
    public int updateByExampleSelective(TSysTag tSysTag, TSysTagExample tSysTagExample) {
        return tagMapper.updateByExampleSelective(tSysTag, tSysTagExample);
    }

    @Override
    public int updateByExample(TSysTag tSysTag, TSysTagExample tSysTagExample) {
        return tagMapper.updateByExample(tSysTag, tSysTagExample);
    }

    @Override
    public List<TSysTag> selectByExample(TSysTagExample tSysTagExample) {
        return tagMapper.selectByExample(tSysTagExample);
    }

    @Override
    public long countByExample(TSysTagExample tSysTagExample) {
        return tagMapper.countByExample(tSysTagExample);
    }

    @Override
    public int deleteByExample(TSysTagExample tSysTagExample) {
        return tagMapper.deleteByExample(tSysTagExample);
    }

    /**
     * 标签管理
     *
     * @param stage
     * @param food
     * @param product
     * @param items
     * @param printUser
     * @return
     */
    public PageInfo<PrintHistory> sysTagList(Tablepar tablepar, String stage, String food, String product, String items, String printUser, Date start, Date end) {
        List<PrintHistory> list = null;
        TsysUser tsysUser = ShiroUtils.getUser();
            //第一次进入列表
            if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
                if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                    PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
                }
               //项目点管理员查询
                list = printHistoryMapper.selectList(stage, food, product,items, printUser, start, end,tsysUser.getItemsCode());
            }else{
                if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                    PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
                }
                //超级管理员查询
                list = printHistoryMapper.selectByTag(stage, food, product, items, printUser, start, end);
            }
        PageInfo<PrintHistory> pageInfo = new PageInfo<PrintHistory>(list);
        return pageInfo;
    }
}
