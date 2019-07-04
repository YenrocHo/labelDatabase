package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;

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

}
