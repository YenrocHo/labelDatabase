package com.fc.aden.mapper.auto;

import com.fc.aden.model.auto.TsysFile;
import com.fc.aden.model.auto.TsysFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsysFileMapper {
    int countByExample(TsysFileExample example);

    int deleteByExample(TsysFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysFile record);

    int insertSelective(TsysFile record);

    List<TsysFile> selectByExample(TsysFileExample example);

    TsysFile selectByPrimaryKey(String id);
    List<TsysFile> selectByIdList(String id);

    int updateByExampleSelective(@Param("record") TsysFile record, @Param("example") TsysFileExample example);

    int updateByExample(@Param("record") TsysFile record, @Param("example") TsysFileExample example);

    int updateByPrimaryKeySelective(TsysFile record);

    int updateByPrimaryKey(TsysFile record);
}