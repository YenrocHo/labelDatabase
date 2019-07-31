package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysTag;
import com.fc.aden.model.custom.process.TSysTagExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysTagMapper {
    long countByExample(TSysTagExample example);

    int deleteByExample(TSysTagExample example);

    int deleteByPrimaryKey(String id);

    int insert(TSysTag record);

    int insertSelective(TSysTag record);

    List<TSysTag> selectByExample(TSysTagExample example);

    TSysTag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TSysTag record, @Param("example") TSysTagExample example);

    int updateByExample(@Param("record") TSysTag record, @Param("example") TSysTagExample example);

    int updateByPrimaryKeySelective(TSysTag record);

    int updateByPrimaryKey(TSysTag record);

    List<TSysTag> selectList();

    List<TSysTag> selectByTag(String stage, String food, String product, String items, String printUser, Date start, Date end);
}