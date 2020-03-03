package com.fc.aden.mapper.auto.process;

import com.fc.aden.common.base.BaseMapper;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;

import java.util.Date;
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

    List<PrintHistory> selectByOriginalId(String originalId);

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

    List<PrintHistory> selectList(String stage, String food, String product,String items,String printUser,Date start,Date end,String itemsCode,Date dateToday,int period,int writeOffFlag);

    List<PrintHistory> selectByTag(String stage, String food, String product, String itemsCode, String printUser, Date start, Date end,Date dateToday,int period,int writeOffFlag);

    /**
     * 查询临期和过期的总数量
     *
     * @author Created by zc on 2020/2/18
     */
    int countExpired(@Param("itemsCode") String itemsCode, @Param("expiredType") String expiredType);

    /**
     * 查询临期和过期数据列表
     *
     * @author Created by zc on 2020/2/19
     */
    List<PrintHistory> listExpired(@Param("itemsCode") String itemsCode, @Param("expiredType") String expiredType);

    /**
     * 此标签是否在此项目点
     *
     * @author Created by zc on 2020/2/21
     */
    boolean isExistPrintIdAndItemCode(@Param("printLableId") String printLableId, @Param("itemsCode") String itemsCode);

    /**
     * 批量核销
     *
     * @author Created by zc on 2020/2/21
     */
    int writeOffBatch(@Param("writeOffOperatorNo") String writeOffOperatorNo,
                      @Param("writeOffOperatorName") String writeOffOperatorName,
                      @Param("writeOffTime") Date writeOffTime,
                      @Param("printLabelIdList") List<String> printLabelIdList);

    /**
     * 通过标签打印id查询
     *
     * @author Created by zc on 2020/2/24
     */
    PrintHistory selectByPrintLabelId(@Param("printLableId") String printLableId);
}