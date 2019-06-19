package com.fc.aden.mapper.auto;

import com.fc.aden.model.auto.TSysLearnFile;
import com.fc.aden.model.auto.TSysLearnFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysLearnFileMapper {
    long countByExample(TSysLearnFileExample example);

    int deleteByExample(TSysLearnFileExample example);

    int deleteByPrimaryKey(String fileId);

    int insertSelective(TSysLearnFile record);

    List<TSysLearnFile> selectByExample(TSysLearnFileExample example);

    TSysLearnFile selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example);

    int updateByExample(@Param("record") TSysLearnFile record, @Param("example") TSysLearnFileExample example);

    int updateByPrimaryKeySelective(TSysLearnFile record);

    int updateByPrimaryKey(TSysLearnFile record);

    /**
     * 上传文件
     * @param lf
     * @return
     * @throws Exception
     */
    public boolean insert(TSysLearnFile lf) throws Exception;
}