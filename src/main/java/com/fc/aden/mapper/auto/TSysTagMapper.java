package com.fc.aden.mapper.auto;

import com.fc.aden.model.custom.TSysTag;
import com.fc.aden.model.custom.TSysTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysTagMapper {
    int countByExample(TSysTagExample example);

    int deleteByExample(TSysTagExample example);

    int deleteByPrimaryKey(String ids);

    int insert(TSysTag record);

    int insertSelective(TSysTag record);

    List<TSysTag> selectByExample(TSysTagExample example);

    TSysTag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TSysTag record, @Param("example") TSysTagExample example);

    int updateByExample(@Param("record") TSysTag record, @Param("example") TSysTagExample example);

    int updateByPrimaryKeySelective(TSysTag record);

    int updateByPrimaryKey(TSysTag record);
}