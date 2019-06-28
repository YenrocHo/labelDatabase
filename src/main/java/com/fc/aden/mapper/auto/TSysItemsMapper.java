package com.fc.aden.mapper.auto;

import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TSysItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysItemsMapper {
    long countByExample(TSysItemsExample example);

    int deleteByExample(TSysItemsExample example);

    int deleteByPrimaryKey(String id);

    int insert(TSysItems record);

    int insertSelective(TSysItems record);

    List<TSysItems> selectByExample(TSysItemsExample example);

    TSysItems selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TSysItems record, @Param("example") TSysItemsExample example);

    int updateByExample(@Param("record") TSysItems record, @Param("example") TSysItemsExample example);

    int updateByPrimaryKeySelective(TSysItems record);

    int updateByPrimaryKey(TSysItems record);


    List<TSysItems> selectByItems(String items);
    int deleteByItems(List<TSysItems> example);

    TSysItems selectItemByUserNumber(@Param("number")String number);

    List<TSysItems> selectItemList(@Param("keyword")String keyword);
}