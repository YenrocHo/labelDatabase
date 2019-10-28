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
        PrintHistory result = printHistoryMapper.selectByPrimaryKey(id);
//        HashMap<String, Object> map = new HashMap<>();
//        if (result != null){
//            map.put("code",200);
//            map.put("msg","操作成功");
//            map.put("data",result);
//        }else {
//            map.put("code",240);
//            map.put("msg", "未查到数据");
//        }
//
//        JSONObject object = JSONObject.fromObject(map);

        return  AjaxResult.successData(200,result);
    }
}
