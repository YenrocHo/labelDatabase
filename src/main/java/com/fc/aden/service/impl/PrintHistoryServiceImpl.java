package com.fc.aden.service.impl;

import com.fc.aden.common.base.BaseServiceImpl;
import com.fc.aden.mapper.auto.process.PrintHistoryMapper;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.model.custom.process.PrintHistoryExample;
import com.fc.aden.service.PrintHistoryService;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SqlParameter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 查询临期和过期的总数量
     *
     * @param itemsCode 项目点编号
     * @author Created by zc on 2020/2/18
     */
    @Override
    public int countExpired(String itemsCode, String expiredType) {
        return printHistoryMapper.countExpired(itemsCode, expiredType);
    }

    /**
     * 查询临期和过期数据列表
     *
     * @param itemsCode 项目点编号
     * @author Created by zc on 2020/2/19
     */
    @Override
    public List<PrintHistory> listExpired(String itemsCode, String expiredType, int pageNum, int pageSize) {
       return  printHistoryMapper.listExpired(SqlParameter.getParameter().addLimit(pageNum,
                pageSize).addQuery("itemsCode", itemsCode)
                .addQuery("expiredType",expiredType).getMap());
    }

    /**
     * 此标签是否在此项目点
     *
     * @param printLableId 打印标签id
     * @param itemsCode 项目点编号
     * @author Created by zc on 2020/2/21
     */
    @Override
    public boolean isExistPrintIdAndItemCode(String printLableId, String itemsCode) {
        return printHistoryMapper.isExistPrintIdAndItemCode(printLableId, itemsCode);
    }

    /**
     * 批量核销
     *
     * @param writeOffOperatorNo 核销人账号
     * @param writeOffOperatorName 核销人姓名
     * @param writeOffTime 核销时间
     * @param printLabelIdList 核销标签id集合
     * @author Created by zc on 2020/2/21
     */
    @Override
    public int writeOffBatch(String writeOffOperatorNo, String writeOffOperatorName, Date writeOffTime, List<String> printLabelIdList) {
        return printHistoryMapper.writeOffBatch(writeOffOperatorNo, writeOffOperatorName, writeOffTime, printLabelIdList);
    }

    /**
     * 通过标签打印id查询
     *
     * @param printLableId 标签打印id
     * @author Created by zc on 2020/2/24
     */
    @Override
    public PrintHistory selectByPrintLabelId(String printLableId) {
        return printHistoryMapper.selectByPrintLabelId(printLableId);
    }
}
