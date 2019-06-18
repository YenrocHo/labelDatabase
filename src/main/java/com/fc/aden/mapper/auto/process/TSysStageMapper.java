package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysStageMapper {
    int deleteByPrimaryKey(String id);
    int insertSelective(TSysStage record);
    TSysStage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysStage record);

    int updateByExampleSelective(@Param("record") TSysStage record, @Param("example") TSysStageExample example);

    int updateByExample(@Param("record") TSysStage record, @Param("example") TSysStageExample example);

    List<TSysStage> selectByExample(TSysStageExample example);

    long countByExample(TSysStageExample example);

    int deleteByExample(TSysStageExample example);
}