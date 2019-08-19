package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysWeight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysWeightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSysWeight record);

    int insertSelective(TSysWeight record);

    TSysWeight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSysWeight record);

    int updateByPrimaryKey(TSysWeight record);

    List<TSysWeight> selectWeightList(@Param("itemsCode")String itemsCode,@Param("keyword") String keyword);
}