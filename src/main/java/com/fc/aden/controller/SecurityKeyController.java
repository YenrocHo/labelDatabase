/**
 * projectName: labelDatabase
 * fileName: SecurityKeyController.java
 * packageName: com.fc.aden.controller
 * date: 2019-09-20
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.controller;

import com.fc.aden.common.conf.ObtainKeyConfig;
import com.fc.aden.util.dxm.result.ResponseCode;
import com.fc.aden.util.dxm.result.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: SecurityKeyController
 * @packageName: com.fc.aden.controller
 * @description: 获取安全密钥
 * @data: 2019-09-20
 **/


@RestController
@RequestMapping("/api/security")
public class SecurityKeyController {

    @Autowired
    private   ObtainKeyConfig obtainKeyConfig;

    /**
     * 获取安全KEY
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/key")
    public String getKey(){
        String key = obtainKeyConfig.getKey();
        ResponseResult result = new ResponseResult();
        result.setCodeMsg(ResponseCode.SUCCESS);
        result.setData(key);
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return JSONObject.fromObject(result).toString();
    }
}
