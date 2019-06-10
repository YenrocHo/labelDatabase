package com.fc.test.mapper.auto.process;

import com.fc.test.model.custom.process.TSysStore;

public interface TSysStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysStore record);

    int insertSelective(TSysStore record);

    TSysStore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysStore record);

    int updateByPrimaryKey(TSysStore record);
}