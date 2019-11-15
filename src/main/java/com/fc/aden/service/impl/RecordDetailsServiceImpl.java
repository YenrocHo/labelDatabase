/**
 * projectName: labelDatabase
 * fileName: RecordDetailsServiceImpl.java
 * packageName: com.fc.aden.service.impl
 * date: 2019-10-25
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.service.impl;

import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.process.PrintHistoryMapper;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.service.RecordDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: RecordDetailsServiceImpl
 * @packageName: com.fc.aden.service.impl
 * @description: 标签记录详情
 * @data: 2019-10-25
 **/
@Service
public class RecordDetailsServiceImpl implements RecordDetailsService {
@Resource
    private PrintHistoryMapper printHistoryMapper;

    @Override
    public AjaxResult getRecordDetails(String id) {

        // 根据ID查询 记录
        List<PrintHistory> resultList = printHistoryMapper.selectByOriginalId(id);
        PrintHistory result = resultList.get(0);
        return  AjaxResult.successData(200,result);
    }
}
