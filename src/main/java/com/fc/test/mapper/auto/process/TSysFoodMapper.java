package com.fc.test.mapper.auto.process;


import com.fc.test.model.custom.process.TSysFood;
import com.fc.test.model.custom.process.TSysFoodExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysFoodMapper {
    int deleteByPrimaryKey(String id);
    int insertSelective(TSysFood record);
    TSysFood selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysFood record);

    int updateByExampleSelective(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);

    int updateByExample(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);

    List<TSysFood> selectByExample(TSysFoodExample example);

    long countByExample(TSysFoodExample example);

    int deleteByExample(TSysFoodExample example);
}