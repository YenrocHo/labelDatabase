package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysFoodExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysFoodMapper {

    TSysFood selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insertSelective(TSysFood record);

    int insert(TSysFood record);

    int updateByPrimaryKeySelective(TSysFood record);

    int updateByPrimaryKey(TSysFood record);

    List<TSysFood> selectByExample(TSysFoodExample example);

    int deleteByExample(TSysFoodExample example);

    List<TSysFood> selectFoodList(@Param("itemId") String itemId,@Param("keyword") String keyword);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);




    long countByExample(TSysFoodExample example);
    int updateByExampleSelective(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);
    int updateByExample(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);

}