package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.custom.process.TSysStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysStore record);

    int insertSelective(TSysStore record);

    TSysStore selectByPrimaryKey(String id);
    TSysStore findByStore(String itemsCode);
    List<TSysStore> findByStoreList(String itemsCode);
    List<TSysStore> queryStoreList();
    List<TSysStore> checkStoreItems(String name,String itemsCode);
    int updateByPrimaryKeySelective(TSysStore record);

    int updateByPrimaryKey(TSysStore record);

    List<TSysStore> selectList(String itemsCode,String searchTxt);

    int delectStoreByIds(@Param("storeIdlist") List<String> storeIdlist);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    List<TSysStore> selectListByItems(String searchTxt,String itemsCode);

    List<TSysStore> selectStoreList(@Param("itemsCode") String itemsCode, @Param("keyword") String keyword);
}