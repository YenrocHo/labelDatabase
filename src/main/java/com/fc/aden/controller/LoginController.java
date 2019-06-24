package com.fc.aden.controller;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.BootstrapThree;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.AUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Controller
@Api(value = "Android调用接口")
@RequestMapping("/loginController")
public class LoginController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    /////////////////登录校验接口//////////////////////////

    @Autowired
    private AUserService aUserService;
    /**
     * @Author Noctis
     * @Description //安卓用户登录
     * @Date 2019/6/24 11:58
     * @Param [number, request]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/android/login")
    @ResponseBody
    public AjaxResult login(String number, HttpServletRequest request) {
        AjaxResult result = aUserService.login(number);
        if (result != null){
            request.getSession().setAttribute("current_user",result.get(AJAX_DATA));
        }
        return result;
    }


//////////////////////基础信息列表接口///////////////////////////////


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

    /**
     * 获取食品列表接口
     * @param tablepar
     * @param foodName
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/food-list")
    @ResponseBody
    public AjaxResult list(Tablepar tablepar, String foodName) {
        logger.info("获取食品列表接口========================");
        PageInfo<TSysFood> page=sysFoodService.sysFoodList(tablepar,foodName);
        TableSplitResult<TSysFood> result=new TableSplitResult<TSysFood>(page.getPageNum(), page.getTotal(), page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA,result);
        return ajaxResult;
    }

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
     * 制作阶段列表接口
     * @param tablepar
     * @param stage
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/stage-list")
    @ResponseBody
    public AjaxResult stageList(Tablepar tablepar, String stage) {
        logger.info("制作阶段列表接口========================");
        PageInfo<TSysStage> page = sysStageService.sysStageList(tablepar, stage);
        TableSplitResult<TSysStage> result = new TableSplitResult<TSysStage>(page.getPageNum(), page.getTotal(), page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA,result);
        return ajaxResult;
    }

    /**
     * 存储条件列表接口
     * @param tablepar
     * @param store
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/store-list")
    @ResponseBody
    public AjaxResult storeList(Tablepar tablepar, String store) {
        logger.info("存储条件列表接口========================");
        PageInfo<TSysStore> page = sysStoreService.list(tablepar, store);
        TableSplitResult<TSysStore> result = new TableSplitResult<TSysStore>(page.getPageNum(), page.getTotal(), page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA, result);
        return ajaxResult;
    }

    /**
     * 产品获取列表接口
     * @param tablepar
     * @param searchTxt
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/product-list")
    @ResponseBody
    public AjaxResult productList(Tablepar tablepar,String searchTxt){
        logger.info("产品获取列表接口=======================");
        PageInfo<TSysProduct> page = sysProductService.list(tablepar,searchTxt) ;
        TableSplitResult<TSysProduct> result = new TableSplitResult<TSysProduct>(page.getPageNum(),page.getTotal(),page.getList());
        AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AJAX_DATA,result);
        return ajaxResult;
    }

    ////////////////////////////////////////基础信息提交接口//////////////////////////

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
}

