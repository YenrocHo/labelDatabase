/**
 * projectName: labelDatabase
 * fileName: RecordDetailsController.java
 * packageName: com.fc.aden.controller.admin
 * date: 2019-10-25
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.controller.admin;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.service.RecordDetailsService;
import com.fc.aden.util.dxm.result.ResponseCode;
import com.fc.aden.util.dxm.result.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: RecordDetailsController
 * @packageName: com.fc.aden.controller.admin
 * @description: 标签记录详情
 * @data: 2019-10-25
 **/

@RestController
@CrossOrigin
@RequestMapping(value = "/record")
public class RecordDetailsController {

    @Resource
    private RecordDetailsService recordDetailsService;

    @RequestMapping(value = "/getRecordDetails", method = RequestMethod.GET)
    public AjaxResult getRecordDetails(String id) {
        if (id == null || id.equals("")) {
            return AjaxResult.error(Const.CodeEnum.wrongParam.getValue());
        }
        AjaxResult ajaxResult = recordDetailsService.getRecordDetails(id);
        return ajaxResult;
    }
}
