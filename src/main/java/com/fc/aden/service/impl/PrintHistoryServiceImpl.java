package com.fc.aden.service.impl;

import com.fc.aden.common.base.BaseServiceImpl;
import com.fc.aden.mapper.auto.process.PrintHistoryMapper;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;
import com.fc.aden.service.PrintHistoryService;
import com.fc.aden.util.BeanCopierEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @Description 打印历史记录Service实现类
*
* @author Created by zc on 2019/7/3
*/
@Service
public class PrintHistoryServiceImpl extends BaseServiceImpl<PrintHistoryMapper, PrintHistory, PrintHistoryExample> implements PrintHistoryService {

    @Autowired
    private PrintHistoryMapper printHistoryMapper;

    /**
     * 批量插入
     *
     * @param printHistoryList
     * @author Created by zc on 2019/7/3
     */
    @Override
    @Transactional
    public int insertBatch(List<PrintHistory> printHistoryList) {
        return printHistoryMapper.insertBatch(printHistoryList);
    }
}
