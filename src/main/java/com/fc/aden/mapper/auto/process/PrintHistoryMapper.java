package com.fc.aden.mapper.auto.process;

import com.fc.aden.common.base.BaseMapper;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 打印历史Mapper接口
 *
 * @author Created by zc on 2019/7/3
 */
public interface PrintHistoryMapper extends BaseMapper<PrintHistory, PrintHistoryExample> {
    long countByExample(PrintHistoryExample example);

    int deleteByExample(PrintHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrintHistory record);

    int insertSelective(PrintHistory record);

    List<PrintHistory> selectByExample(PrintHistoryExample example);

    PrintHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrintHistory record, @Param("example") PrintHistoryExample example);

    int updateByExample(@Param("record") PrintHistory record, @Param("example") PrintHistoryExample example);

    int updateByPrimaryKeySelective(PrintHistory record);

    int updateByPrimaryKey(PrintHistory record);

    /**
     * 批量插入
     *
     * @author Created by zc on 2019/7/3
     */
    public int insertBatch(@Param("printHistoryList") List<PrintHistory> printHistoryList);
}