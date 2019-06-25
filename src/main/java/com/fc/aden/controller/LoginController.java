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
import com.fc.aden.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import javafx.stage.Stage;
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
import static com.fc.aden.common.domain.AjaxResult.CODE_SUCCESS;

@Controller
@Api(value = "Android调用接口")
@RequestMapping("/androidController")
public class LoginController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    /////////////////登录校验接口//////////////////////////


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




    /*李源*/
    @Autowired
    private AStageService aStageService;
    @Autowired
    private AUserService aUserService;
    @Autowired
    private AFoodService aFoodService;
    @Autowired
    private AProductService aProductService;
    @Autowired
    private AStoreService aStoreService;

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
        request.getSession().setAttribute("current_user",result.get("current_user"));
        return result;
    }

    /***
     * @Author Noctis
     * @Description //安卓阶段列表（列表展示，按条件搜索）
     * @Date 2019/6/24 19:06
     * @Param [tablepar, stage]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/stage-list")
    @ResponseBody
    public AjaxResult stageList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        AjaxResult result = aStageService.selectStageList(pageNum,pageSize,keyword);
        return result;
    }

    /**
     * @Author Noctis
     * @Description //安卓食品列表（列表展示，按条件搜索）
     * @Date 2019/6/24 17:25
     * @Param [pageNum, pageSize, stageId, keyword]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/food-list")
    @ResponseBody
    public AjaxResult list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(value = "stageId", required = false) String stageId,
                           @RequestParam(value = "keyword", required = false) String keyword) {
        AjaxResult result = aFoodService.selectFoodList(pageNum,pageSize,stageId,keyword);
        return result;
    }

    /**
     * @Author Noctis
     * @Description //安卓产品列表（列表展示，按条件搜索）
     * @Date 2019/6/24 17:36
     * @Param [pageNum, pageSize, stageId, keyword]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/product-list")
    @ResponseBody
    public AjaxResult productList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "stageId", required = false) String foodId,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        AjaxResult result = aProductService.selectProductList(pageNum,pageSize,foodId,keyword);
        return result;
    }
    /**
     * @Author Noctis
     * @Description //安卓存储条件列表（列表展示，按条件搜索）
     * @Date 2019/6/24 18:34
     * @Param [tablepar, store]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/store-list")
    @ResponseBody
    public AjaxResult storeList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(value = "stageId", required = false) String productId,
                                @RequestParam(value = "keyword", required = false) String keyword) {

        AjaxResult result = aStoreService.selectStoreList(pageNum,pageSize,productId,keyword);
        return result;
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/allData")
    @ResponseBody
    public AjaxResult allList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        AjaxResult ajaxResult = new AjaxResult();
        AjaxResult stage = aStageService.selectStageList(pageNum,pageSize,null);
        AjaxResult food = aFoodService.selectFoodList(pageNum,pageSize,null,null);
        AjaxResult product = aFoodService.selectFoodList(pageNum,pageSize,null,null);
        AjaxResult store = aStoreService.selectStoreList(pageNum,pageSize,null,null);
        PageInfo stages = (PageInfo)stage.get(AJAX_DATA);
        PageInfo foods = (PageInfo)food.get(AJAX_DATA);
        PageInfo products = (PageInfo)product.get(AJAX_DATA);
        PageInfo stores = (PageInfo)store.get(AJAX_DATA);
        ajaxResult.put("store",stores);
        ajaxResult.put("product",products);
        ajaxResult.put("food",foods);
        ajaxResult.put("stage",stages);
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

