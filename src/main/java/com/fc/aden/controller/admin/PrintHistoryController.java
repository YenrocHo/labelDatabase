package com.fc.aden.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.service.PrintHistoryService;
import com.fc.aden.service.SysUserService;
import com.fc.aden.util.StringUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
* 
*
* @author Created by zc on 2020/2/19
*/
@RestController()
@RequestMapping(value = "/printHistory")
@Api("打印历史数据APP接口")
public class PrintHistoryController {

    @Autowired
    private PrintHistoryService printHistoryService;

    @Autowired
    private SysUserService userService;

    private static Logger logger = LoggerFactory.getLogger(PrintHistoryController.class);

    /**
     * 查询临期和过期总数量
     *
     * @author Created by zc on 2020/2/18
     */
    @PostMapping(value = "/countExpired")
    public AjaxResult countExpired(@RequestBody String bodyStr) {
        try {
            JSONObject bodyJsonObject = JSONObject.parseObject(bodyStr);
            String userName = bodyJsonObject.getString("userName");
            String itemsCode = bodyJsonObject.getString("itemsCode");
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(itemsCode)) {
                throw new JSONException("参数错误");
            }
            if (!userService.isExist(userName, itemsCode)) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "该用户名在此项目点不存在");
            }
            int willExpiredCount = printHistoryService.countExpired(itemsCode, "1");
            int expiredCount = printHistoryService.countExpired(itemsCode, "2");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("willExpiredCount", willExpiredCount);
            dataMap.put("expiredCount", expiredCount);
            return AjaxResult.success(AjaxResult.CODE_SUCCESS, "查询成功", dataMap);
        } catch (JSONException e) {
            logger.error("参数错误");
            return AjaxResult.error(AjaxResult.CODE_PARAM_ERROR, "参数错误");
        } catch (Exception e) {
            logger.error("系统错误", e);
            return AjaxResult.error(AjaxResult.CODE_SERVER_ERROR, "系统错误");
        }
    }

    /**
     * 查询临期和过期列表
     *
     * @author Created by zc on 2020/2/19
     */
    @PostMapping(value = "/listExpired")
    public AjaxResult listExpired(@RequestBody String bodyStr) {
        try {
            JSONObject bodyJsonObject = JSONObject.parseObject(bodyStr);
            String userName = bodyJsonObject.getString("userName");
            String itemsCode = bodyJsonObject.getString("itemsCode");
            String expiredType = bodyJsonObject.getString("expiredType");
            int pageNum = bodyJsonObject.getIntValue("pageNum");
            int pageSize = bodyJsonObject.getIntValue("pageSize");
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(itemsCode) || StringUtils.isEmpty(expiredType)) {
                throw new JSONException("参数错误");
            }
            if (!userService.isExist(userName, itemsCode)) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "该用户名在此项目点不存在");
            }
            List<PrintHistory> printHistoryList = printHistoryService.listExpired(itemsCode, expiredType, pageNum, pageSize);
            return AjaxResult.success(AjaxResult.CODE_SUCCESS, "查询成功", printHistoryList);
        } catch (JSONException e) {
            logger.error("参数错误");
            return AjaxResult.error(AjaxResult.CODE_PARAM_ERROR, "参数错误");
        } catch (Exception e) {
            logger.error("系统错误", e);
            return AjaxResult.error(AjaxResult.CODE_SERVER_ERROR, "系统错误");
        }
    }

    /**
     * 核销
     *
     * @author Created by zc on 2020/2/20
     */
    @PostMapping("/writeOff")
    public AjaxResult writeOff(@RequestBody String bodyStr) {
        try {
            JSONObject bodyJsonObject = JSONObject.parseObject(bodyStr);
            String userName = bodyJsonObject.getString("userName");
            String writeOffOperatorName = bodyJsonObject.getString("writeOffOperatorName");
            String itemsCode = bodyJsonObject.getString("itemsCode");
            JSONArray printLabelArray =bodyJsonObject.getJSONArray("printLabelIds");

            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(itemsCode) || StringUtils.isEmpty(printLabelArray)) {
                throw new JSONException("参数错误");
            }
            if (!userService.isExist(userName, itemsCode)) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "该用户名在此项目点不存在");
            }
            if (printLabelArray.size() == 1
                    && !printHistoryService.isExistPrintIdAndItemCode(printLabelArray.getString(0), itemsCode)) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "该用户无核销此标签的权限");
            }
            List<String> printIdList = new ArrayList<>();
            for (Object o : printLabelArray) {
                printIdList.add((String) o);
            }
            int writeOffCount = printHistoryService.writeOffBatch(userName, writeOffOperatorName, new Date(), printIdList);
//            if (writeOffCount != printLabelArray.size()) {
            if (writeOffCount < 1) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "核销失败，请重试");
            }
        } catch (JSONException e) {
            logger.error("参数错误");
            return AjaxResult.error(AjaxResult.CODE_PARAM_ERROR, "参数错误");
        } catch (Exception e) {
            logger.error("系统错误", e);
            return AjaxResult.error(AjaxResult.CODE_SERVER_ERROR, "系统错误");
        }
        return AjaxResult.success(AjaxResult.CODE_SUCCESS, "核销成功");
    }

    @PostMapping(value = "/queryByPrintLabelId")
    public AjaxResult queryByPrintLabelId(@RequestBody String bodyStr) {
        try {
            JSONObject bodyJsonObject = JSONObject.parseObject(bodyStr);
            String userName = bodyJsonObject.getString("userName");
            String itemsCode = bodyJsonObject.getString("itemsCode");
            String printLabelId = bodyJsonObject.getString("printLabelId");
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(itemsCode) || StringUtils.isEmpty(printLabelId)) {
                throw new JSONException("参数错误");
            }
            if (!userService.isExist(userName, itemsCode)) {
                return AjaxResult.error(AjaxResult.CODE_ERROR, "该用户名在此项目点不存在");
            }
            PrintHistory printHistory = printHistoryService.selectByPrintLabelId(printLabelId);
            return AjaxResult.success(AjaxResult.CODE_SUCCESS, "查询成功", printHistory);
        } catch (JSONException e) {
            logger.error("参数错误");
            return AjaxResult.error(AjaxResult.CODE_PARAM_ERROR, "参数错误");
        } catch (Exception e) {
            logger.error("系统错误", e);
            return AjaxResult.error(AjaxResult.CODE_SERVER_ERROR, "系统错误");
        }
    }
}
