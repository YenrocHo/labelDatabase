package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.ImportProductDTO;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.service.SysProductService;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.vo.FoodVO;
import com.fc.aden.vo.ImportTSysUserDTO;
import com.fc.aden.vo.ItemsVO;
import com.fc.aden.vo.ProductVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 产品管理
 * @Author: Liyuan
 * @CreateDate: 2019/6/5 13:50
 * @UpdateUser: Liyuan
 * @UpdateDate: 2019/6/5 13:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@Api(value = "产品管理")
@RequestMapping("ProductController")
public class ProductController extends BaseController {

    //跳转页面参数
    private String prefix = "admin/product";

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * @return java.lang.String
     * @Author Liyuan
     * @Description //页面跳转
     * @Date 2019/6/5 13:50
     * @Param [model]
     **/
    @GetMapping("view")
    @RequiresPermissions("system:product:view")
    public String view(Model model,ModelMap mp) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        mp.put("tSysItems", tSysItemsList);
        setTitle(model, new TitleVo("产品列表", "产品管理", false, "欢迎进入产品页面", false, false));
        return prefix + "/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        List<TSysFood> tSysFoods = sysFoodService.queryFood();
        List<FoodVO> foodVOList = new ArrayList<>();
        for (TSysFood tSysFood : tSysFoods) {//获取食品种类
            FoodVO foodVO = new FoodVO();
            foodVO.setId(tSysFood.getId());
            foodVO.setFood(tSysFood.getFood());
            foodVOList.add(foodVO);
        }
        modelMap.put("tSysItems", tSysItemsList);
        modelMap.put("foodVOList", foodVOList);
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request, ModelMap mmap) {
        TSysProduct tSysProduct = sysProductService.selectProductById(id);
        String items = tSysProduct.getItemsCode();
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        TSysFood tSysFood = sysFoodService.selectByPrimaryKey(tSysProduct.getFoodName());
        List<TSysFood> tSysFoodList = sysFoodService.queryFood();
        mmap.addAttribute("tSysFood",tSysFood);
        mmap.addAttribute("tSysFoodList",tSysFoodList);
        mmap.addAttribute("items",items);
        mmap.addAttribute("tSysItems",tSysItems);
        request.getSession().setAttribute("tSysProduct", tSysProduct);
        return prefix + "/edit";
    }

    //===============================================业务处理================================================//


    /**
     * @return java.lang.Object
     * @Author Noctis
     * @Description //产品分页展示
     * @Date 2019/6/10 16:54
     * @Param [tablepar, searchTxt]
     **/
    @PostMapping("/list")
    @RequiresPermissions("system:product:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt, String itemsCode) {
        PageInfo<ProductVO> page = sysProductService.list(tablepar, searchTxt, itemsCode);
        TableSplitResult<ProductVO> result = new TableSplitResult<ProductVO>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * @return com.fc.aden.common.domain.AjaxResult
     * @Author Noctis
     * @Description //产品添加
     * @Date 2019/6/6 10:57
     * @Param [tSysProduct]
     **/
    @PostMapping("/add")
    @RequiresPermissions("system:product:add")
    @ResponseBody
    public AjaxResult add(TSysProduct tSysProduct) {
        int i = sysProductService.insertProduct(tSysProduct);
        if (i > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品添加时保证名字唯一
     * @Date 2019/6/6 10:57
     * @Param [tSysProduct]
     **/
    @PostMapping("/checkcNameUnique")
    @ResponseBody
    public int checkcNameUnique(TSysProduct tSysProduct) {
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
    public AjaxResult remove(String ids) {
        int i = sysProductService.removeProduct(ids);
        if (i > 0) {
            return success();
        } else {
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
    public AjaxResult editSave(TSysProduct tSysProduct, HttpServletRequest request) {
        TSysProduct product = (TSysProduct) request.getSession().getAttribute("tSysProduct");

        tSysProduct.setId(product.getId());
        tSysProduct.setUpdateTime(new Date());
        tSysProduct.setName(tSysProduct.getProduct());
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
        if (product != null) {
            return retobject(1, product);
        } else {
            return error(0, "修改失败");
        }
    }

    /**
     * 跳转导入页面
     *
     * @return
     */
    @GetMapping("/upload")
    public String upload() {
        return prefix + "/upload";
    }

    @PostMapping("/upload")
    public String uploadProduct() {
        return prefix + "/upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Model model, MultipartFile myFile) {
        List<Map<String, String>> dataList;
        try {
            dataList = ExcelUtils.getExcelData(myFile, ImportProductDTO.IMPORT_TABLE_HEADER);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return prefix + "/import_error";
        }
        ImportProductDTO importProductDTO = sysProductService.importValid(dataList);
        List<ProductVO> productVOList = sysProductService.getSuccessTSysProduct (importProductDTO.gettSysProductDTOS());
        sysProductService.saveSysProduct(productVOList);
        model.addAttribute("importProductDTO", importProductDTO);
        return prefix + "/product_valid";
    }


}
