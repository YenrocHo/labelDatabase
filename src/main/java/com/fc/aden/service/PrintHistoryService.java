package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;

import java.util.Date;
import java.util.List;

/**
* @Description 打印历史Service
*
* @author Created by zc on 2019/7/3
*/
public interface PrintHistoryService extends BaseService<PrintHistory, PrintHistoryExample> {

    /**
     * 批量插入
     *
     * @author Created by zc on 2019/7/3
     */
    public int insertBatch(List<PrintHistory> printHistoryList);

    /**
     * 查询临期和过期的总数量
     *
     * @author Created by zc on 2020/2/18
     */
    int countExpired(String itemsCode, String expiredType);

    /**
     * 查询临期和过期数据列表
     *
     * @author Created by zc on 2020/2/19
     */
    List<PrintHistory> listExpired(String itemsCode, String expiredType, int pageNum, int pageSize);

    /**
     * 此标签是否在此项目点
     *
     * @author Created by zc on 2020/2/21
     */
    boolean isExistPrintIdAndItemCode(String printLableId, String itemsCode);

    /**
     * 批量核销
     *
     * @author Created by zc on 2020/2/21
     */
    int writeOffBatch(String writeOffOperatorNo,
                      String writeOffOperatorName,
                      Date writeOffTime,
                      List<String> printLabelIdList);

    /**
     * 通过标签打印id查询
     *
     * @author Created by zc on 2020/2/24
     */
    PrintHistory selectByPrintLabelId(String printLableId);
}
