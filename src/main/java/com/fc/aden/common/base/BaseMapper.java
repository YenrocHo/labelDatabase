package com.fc.aden.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 基础Mapper接口
*
* @author Created by zc on 2019/7/3
*/
public interface BaseMapper<T extends BaseEntity, T2> {
    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByExampleSelective(@Param("record") T record, @Param("example") T2 example);

    int updateByExample(@Param("record") T record, @Param("example") T2 example);

    List<T> selectByExample(T2 example);

    long countByExample(T2 example);

    int deleteByExample(T2 example);
}
