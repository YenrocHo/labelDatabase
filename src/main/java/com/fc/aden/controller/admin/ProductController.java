package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.service.SysProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

* @Description:    产品管理

* @Author:        Liyuan

* @CreateDate:     2019/6/5 13:50

* @UpdateUser:     Liyuan

* @UpdateDate:     2019/6/5 13:50

* @UpdateRemark:   修改内容

* @Version:        1.0

*/
@Controller
@Api(value = "产品管理")
@RequestMapping("ProductController")
public class ProductController extends BaseController {

    //跳转页面参数
    private String prefix = "admin/product";

    /**
     * @Author Liyuan
     * @Description //页面跳转
     * @Date 2019/6/5 13:50
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("view")
    @RequiresPermissions("system:product:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("产品列表", "产品管理", false,"欢迎进入产品页面", false, false));
        return prefix + "/list";
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request){
        TSysProduct tSysProduct = sysProductService.selectProductById(id);
        request.getSession().setAttribute("tSysProduct",tSysProduct);
        return prefix + "/edit";
    }

    //===============================================业务处理================================================//


    /**
     * @Author Noctis
     * @Description //产品分页展示
     * @Date 2019/6/10 16:54
     * @Param [tablepar, searchTxt]
     * @return java.lang.Object
     **/
    @PostMapping("/list")
    @RequiresPermissions("system:product:list")
    @ResponseBody
    public Object list(Tablepar tablepar,String searchTxt){
        PageInfo<TSysProduct> page = sysProductService.list(tablepar,searchTxt) ;
        TableSplitResult<TSysProduct> result = new TableSplitResult<TSysProduct>(page.getPageNum(),page.getTotal(),page.getList());
        return result;
    }
    /**
     * @Author Noctis
     * @Description //产品添加
     * @Date 2019/6/6 10:57
     * @Param [tSysProduct]
     * @return com.fc.aden.common.domain.AjaxResult
     **/
    @PostMapping("/add")
    @RequiresPermissions("system:product:add")
    @ResponseBody
    public AjaxResult add(TSysProduct tSysProduct){
        int i = sysProductService.insertProduct(tSysProduct);
        if(i>0){
            return success();
        }else{
            return error();
        }
    }

    /**
     * @Author Noctis
     * @Description //产品添加时保证名字唯一
     * @Date 2019/6/6 10:57
     * @Param [tSysProduct]
     * @return int
     **/
    @PostMapping("/checkcNameUnique")
    @ResponseBody
    public int checkcNameUnique(TSysProduct tSysProduct){
        return sysProductService.checkcNameUnique(tSysProduct);
    }

    /***
     * @Author Noctis
     * @Description //产品移除
     * @Date 2019/6/18 14:12
     * @Param [ids]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/remove")
    @RequiresPermissions("system:product:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int i = sysProductService.removeProduct(ids);
        if(i>0){
            return success();
        }else{
            return error();
        }
    }
    /***
     * @Author Noctis
     * @Description //产品修改
     * @Date 2019/6/18 14:13
     * @Param [tSysProduct, request]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @RequiresPermissions("system:product:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysProduct tSysProduct,HttpServletRequest request){
        TSysProduct product = (TSysProduct)request.getSession().getAttribute("tSysProduct");
        tSysProduct.setId(product.getId());
        return toAjax(sysProductService.updateProduct(tSysProduct));
    }
    /***
     * @Author Noctis
     * @Description //产品状态改变
     * @Date 2019/6/18 14:14
     * @Param [id]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/freeze")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam String id) {
        TSysProduct product_status = sysProductService.selectProductById(id);
        TSysProduct product = sysProductService.updateStatus(product_status);
        if (product != null){
            return retobject(1,product);
        }else {
            return error(0,"修改失败");
        }
    }

}