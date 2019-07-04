package com.fc.aden.controller;


import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.service.*;
import com.fc.aden.util.DateUtils;
import com.fc.aden.util.IDGenerator;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Controller
@Api(value = "Android调用接口")
@RequestMapping("/androidController")
public class LoginController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PrintHistoryService printHistoryService;








    /*李源*/
    @Autowired
    private AndroidService androidService;

    /**
     * @Author Noctis
     * @Description //安卓用户登录
     * @Date 2019/6/24 11:58
     * @Param [number, request]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/login")
    @ResponseBody
    public AjaxResult login(String number, HttpServletRequest request) {
        AjaxResult result = androidService.login(number);
        System.out.println(result.get("data"));
        request.getSession().setAttribute("current_user",result.get("data"));
        return result;
    }
    /***
     * @Author Noctis
     * @Description //获取所有数据(stage food product store ，keyword为关键字只能搜索中文名，itemId为项目点Id用来搜索特定itemid)
     * @Date 2019/6/28 10:42
     * @Param [itemId, keyword, statusToken, number]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/allData")
    @ResponseBody
    public AjaxResult MyAllData(@RequestParam(value = "itemId", required = false) String itemId,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @Param("statusToken") String statusToken,
                                @Param("number") String number) {
        AjaxResult ajaxResult = androidService.selectAllList(itemId,keyword,statusToken,number);
        return ajaxResult;
    }
    /***
     * @Author Noctis
     * @Description //获取所有用户数据
     * @Date 2019/6/28 10:43
     * @Param [statusToken, number]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/allUser")
    @ResponseBody
    public AjaxResult allUserList(String statusToken,String number){
        AjaxResult result = androidService.AllUserList(statusToken,number);
        return result;
    }

    /***
     * @Author Noctis
     * @Description //查找指定类型数据 type【Item Stage Food Product Store（都要大写)】添加itemID和keyword来检索
     * @Date 2019/6/28 10:41
     * @Param [itemId, keyword, type, statusToken, number]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/selectOne")
    @ResponseBody
    public AjaxResult test(@RequestParam(value = "itemId", required = false) String itemId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @Param("type") String type,
                           @Param("statusToken") String statusToken,
                           @Param("number")String number){
        AjaxResult ajaxResult = androidService.selectOneList(itemId, keyword, statusToken, type, number);
        return ajaxResult;
    }

    /***
     * @Author Noctis
     * @Description //提交
     * @Date 2019/6/27 14:56
     * @Param [jsonString]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/submittext")
    @ResponseBody

    public AjaxResult submit(@RequestBody String jsonString){
        return androidService.submit(jsonString);
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/submit")
    @ResponseBody
    public AjaxResult submit2(@RequestBody String jsonString){
        logger.debug("打印历史记录：" + jsonString);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray printDataJsonArr = jsonObject.getJSONArray("printData");
            // 打印历史记录集合
            List<PrintHistory> printHistoryList = new ArrayList<>();
            for (int i = 0; i < printDataJsonArr.length(); i++) {
                JSONObject printData = printDataJsonArr.optJSONObject(i);
                PrintHistory printHistory = new PrintHistory();
                printHistory.setId(IDGenerator.getUUID());
                printHistory.setItemId(printData.getString("itemId"));
                printHistory.setOriginalId(printData.getString("originalId"));
                printHistory.setPrintLableId(printData.getString("printLableId"));
                printHistory.setProductName(printData.getString("product"));
                printHistory.setProductCategory(printData.getString("productCategeroy"));
                printHistory.setProductWeight(printData.getString("weight"));
                printHistory.setCorrectStage(printData.getString("correctStage"));
                printHistory.setCorrectStorage(printData.getString("correctStorage"));
                printHistory.setEmployerName(printData.getString("employerName"));
                printHistory.setPrintTime(DateUtils.parseStrToDate(printData.getString("printTime"), "yyyy-MM-dd HH:mm:ss"));
                // 添加到集合
                printHistoryList.add(printHistory);
            }
            // 执行批量保存
            printHistoryService.insertBatch(printHistoryList);
        } catch (JSONException e) {
            e.printStackTrace();
            return AjaxResult.error(414, "参数错误");
        } catch (ParseException e) {
            e.printStackTrace();
            return AjaxResult.error(414, "参数错误：printTime格式错误。期望格式：yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(900, "操作失败：系统错误");
        }

        return AjaxResult.success(AjaxResult.CODE_SUCCESS, "操作成功");
    }
































































    ////////////////////////////////////////基础信息提交接口//////////////////////////

    /**
     * 项目点列表接口
     * @param tablepar
     * @param items
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/items-list")
    @ResponseBody
    public AjaxResult foodList(Tablepar tablepar, String items) {
        logger.info("项目点列表接口========================");
        PageInfo<TSysItems> page = sysItemsService.sysIteamsList(tablepar, items);
        TableSplitResult<TSysItems> result = new TableSplitResult<TSysItems>(page.getPageNum(), page.getTotal(), page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA,result);
        return ajaxResult;
    }

    /**
     * 添加食品
     * 所传参数  add-food?foodName=食品名称&&name=中文名称&&englishName=英文名称
     * @param tSysFood
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-food")
    @ResponseBody
    public AjaxResult addFood(TSysFood tSysFood) {
        int b = sysFoodService.insertSelective(tSysFood);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 添加项目点接口
     * 所传参数  add-items?items=项目点名称&&name=项目点中文名12&&englishName=英文名称
     * @param tSysItems
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-items")
    @ResponseBody
    public AjaxResult addItems(TSysItems tSysItems) {
        int b = sysItemsService.insertSelective(tSysItems);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 产品添加接口
     * 所传参数  add-product?name=产品名称&&cName=产品中文名12&&eName=英文名称
     * @param tSysProduct
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-product")
    @ResponseBody
    public AjaxResult addProduct(TSysProduct tSysProduct){
        int i = sysProductService.insertProduct(tSysProduct);
        if(i>0){
            return success();
        }else{
            return error();
        }
    }

    /**
     * 阶段添加接口
     * 所传参数 add-stage?stage=阶段名称&&name=阶段中文名12&&englishName=英文名称
     * @param tSysStage
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-stage")
    @ResponseBody
    public AjaxResult addStage(TSysStage tSysStage) {
        int b = sysStageService.insertSelective(tSysStage);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 存储条件添加接口
     * 所传参数  add-store?query=存储条件名称&&cCondition=存储条件中文名12&&eCondition=英文名称
     * @param tSysStore
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-store")
    @ResponseBody
    public AjaxResult addStore(TSysStore tSysStore) {
        int i = sysStoreService.insertStore(tSysStore);
        if (i > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 员工接口
     * 所传参数 add-user?number=工号&&username=登录名&&name=姓名&&password=密码&&englishName=英文名&&phoneNumber=手机号&&sex=性别&&items=项目点&&roles=权限Id
     * @param user
     * @param roles
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "add-user")
    @ResponseBody
    public AjaxResult addUser(TsysUser user, @RequestParam(value = "roles", required = false) List<String> roles) {
        int b = sysUserService.insertUserRoles(user, roles);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }



    /**
     * 获取登录员工信息接口
     * @param tablepar
     * @param username
     * @param number
     * @param name
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user-list")
    @ResponseBody
    public AjaxResult list(Tablepar tablepar, String username, String number, String name) {
        logger.info("登录员工信息接口---------------");
        PageInfo<TsysUser> page = sysUserService.list(tablepar, username, number, name);
        TableSplitResult<TsysUser> result = new TableSplitResult<TsysUser>(page.getPageNum(), page.getTotal(),page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA,result);
        return ajaxResult;
    }

}

