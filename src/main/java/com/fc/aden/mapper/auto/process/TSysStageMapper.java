package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysStageMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(TSysStage record);

    TSysStage selectByPrimaryKey(String id);

    int insert(TSysStage record);

    int updateByPrimaryKeySelective(TSysStage record);

    int updateByPrimaryKey(TSysStage record);

    List<TSysStage> selectByExample(TSysStageExample example);

    int deleteByExample(TSysStageExample example);

    List<TSysStage> selectStageList(@Param("keyword")String keyword);





    long countByExample(TSysStageExample example);
    int updateByExampleSelective(@Param("record") TSysStage record, @Param("example") TSysStageExample example);
    int updateByExample(@Param("record") TSysStage record, @Param("example") TSysStageExample example);
}