package com.fc.test.mapper.auto;


import com.fc.test.model.custom.TSysEmployees;
import com.fc.test.model.custom.TSysEmployeesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysEmployeesMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(TSysEmployees record);

    int updateByPrimaryKeySelective(TSysEmployees record);

    int countByExample(TSysEmployeesExample example);

    int deleteByExample(TSysEmployeesExample example);

    List<TSysEmployees> selectByExample(TSysEmployeesExample example);

    TSysEmployees selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TSysEmployees record, @Param("example") TSysEmployeesExample example);

    int updateByExample(@Param("record") TSysEmployees record, @Param("example") TSysEmployeesExample example);

}