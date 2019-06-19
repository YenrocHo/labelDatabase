package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysStore record);

    int insertSelective(TSysStore record);

    TSysStore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysStore record);

    int updateByPrimaryKey(TSysStore record);

    List<TSysStore> selectList();

    int delectStoreByIds(@Param("storeIdlist") List<String> storeIdlist);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    List<TSysStore> selectListBycQuery(String searchTxt);
}