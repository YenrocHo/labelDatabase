package com.fc.aden.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @Description 基础Service实现类
*
* @author Created by zc on 2019/7/3
*/
public abstract class BaseServiceImpl<D extends BaseMapper<T, T2>, T extends BaseEntity, T2> implements BaseService<T, T2> {

    @Autowired
    protected D dao;

    @Override
    @Transactional
    public int deleteByPrimaryKey(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insertSelective(T record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return dao.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(T record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByExampleSelective(T record, T2 example) {
        return dao.updateByExampleSelective(record, example);
    }

    @Override
    @Transactional
    public int updateByExample(T record, T2 example) {
        return dao.updateByExample(record, example);
    }

    @Override
    public List<T> selectByExample(T2 example) {
        return dao.selectByExample(example);
    }

    @Override
    public long countByExample(T2 example) {
        return dao.countByExample(example);
    }

    @Override
    @Transactional
    public int deleteByExample(T2 example) {
        return dao.deleteByExample(example);
    }
}
