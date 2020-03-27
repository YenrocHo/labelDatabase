package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.*;
import com.fc.aden.vo.FoodVO;
import com.fc.aden.vo.ProductFoodStoreVO;
import com.fc.aden.vo.ProductStoreDTO;
import com.fc.aden.vo.ProductVO;
import com.fc.aden.vo.importDto.ImportProductDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public String view(Model model, ModelMap mp) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        mp.put("tSysItems", tSysItemsList);
        TsysUser tsysUser = ShiroUtils.getUser();
        mp.put("tsysUser", tsysUser);
        List<TSysFood> tSysFoodList = sysFoodService.findByItemCode(tsysUser.getItemsCode());
        mp.put("tSysFood", tSysFoodList);
        setTitle(model, new TitleVo("产品列表", "产品管理", false, "欢迎进入产品页面", false, false));
        return prefix + "/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        List<TSysFood> tSysFoods = null;
        TsysUser tsysUser = ShiroUtils.getUser();
        if (tsysUser.getRoles() != "2" && !"2".equals(tsysUser.getRoles())) {
            tSysFoods = sysFoodService.findByItemCode(tsysUser.getItemsCode());
        } else {
            tSysFoods = sysFoodService.queryFood();
        }
        List<FoodVO> foodVOList = new ArrayList<>();
        for (TSysFood tSysFood : tSysFoods) {//获取食品种类
            FoodVO foodVO = new FoodVO();
            foodVO.setId(tSysFood.getId());
            foodVO.setFoodCode(tSysFood.getFoodCode());
            foodVO.setFood(tSysFood.getFood());
            foodVOList.add(foodVO);
        }
        modelMap.put("tsysUser", tsysUser);
        modelMap.put("tSysItems", tSysItemsList);
        modelMap.put("foodVOList", foodVOList);
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request, ModelMap mmap) {
        TSysProduct tSysProduct = sysProductService.selectProductById(id);
        String items = tSysProduct.getItemsCode();//当前用户项目点
        List<TSysItems> tSysItems = sysItemsService.queryItems();//获取全部项目点
        TSysFood tSysFood = sysFoodService.findByFoodId(tSysProduct.getFoodName(), items);//当前食品种类
        //储存条件保质期
        List<ProductVO> productStores = productStoreService.findByProductIdList(id);
        List<TSysFood> tSysFoodList = sysFoodService.findByItemCode(items);//获取用户食品种类
        mmap.addAttribute("tSysFood", tSysFood);
        mmap.addAttribute("productStores", productStores);
        mmap.addAttribute("tSysFoodList", tSysFoodList);
        mmap.addAttribute("items", items);
        mmap.addAttribute("tSysItems", tSysItems);
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
    public Object list(Tablepar tablepar, String searchTxt, String itemsCode, String foodName) {
        PageInfo<TSysProduct> page = sysProductService.list(tablepar, searchTxt, itemsCode, foodName);
        TableSplitResult<TSysProduct> result = new TableSplitResult<TSysProduct>(page.getPageNum(), page.getTotal(), page.getList());
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
    public AjaxResult add(TSysProduct tSysProduct, HttpServletRequest request, @RequestParam(value = "store", required = false) List<String> store) {
        TSysProduct product = new TSysProduct();
        String pId = SnowflakeIdWorker.getUUID().toString();
        product.setId(pId);
        product.setStatus(1);
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setName(tSysProduct.getProduct());
        product.setProduct(tSysProduct.getProduct());
        product.setFoodName(tSysProduct.getFoodName());
        product.setItemsCode(tSysProduct.getItemsCode());
        product.setPriority(tSysProduct.getPriority());
        //循环保存存储条件和保质期
        for (String storeID : store) {
            String sId = SnowflakeIdWorker.getUUID().toString();
            //获取存储条件id下输入的保质期
            String s = request.getParameter(storeID);
            ProductStore productStore = new ProductStore();
            productStore.setStoreId(storeID);
            productStore.setId(sId);
            productStore.setProductId(pId);
            if (s != "") {
                boolean b = isNumeric(s);
                if (b == true) {//为true则是数字
                    productStore.setShelfLife(s + "小时");
                } else {
                    productStore.setShelfLife("见包装");
                }
            } else {
                productStore.setShelfLife("见包装");
            }
            productStore.setCreateTime(new Date());
            productStore.setUpdateTime(new Date());
            productStoreService.insertSelective(productStore);
        }
        int i = sysProductService.insertProduct(product);
        if (i > 0) {
            return success();
        } else {
            return error();
        }
    }

    //判断数据是否为int类型
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
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
    public AjaxResult editSave(TSysProduct tSysProduct, HttpServletRequest request, @RequestParam(value = "store", required = false) List<String> store) {
        TSysProduct product = (TSysProduct) request.getSession().getAttribute("tSysProduct");
        tSysProduct.setId(product.getId());
        tSysProduct.setUpdateTime(new Date());
        tSysProduct.setName(tSysProduct.getProduct());
        //循环保存存储条件和保质期
        for (String storeID : store) {
            //获取存储条件id下输入的保质期
            String s = request.getParameter(storeID);
            ProductStore productStore = productStoreService.findByStoreId(product.getId(), storeID);
            productStore.setProductId(product.getId());
            productStore.setShelfLife(s);
            productStore.setUpdateTime(new Date());
            productStoreService.updateByPrimaryKey(productStore);
        }
        return toAjax(sysProductService.updateProduct(tSysProduct));
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
    public AjaxResult checkcNameUnique(HttpServletRequest request) {
        String product = request.getParameter("product");
        String itemsCode = request.getParameter("itemsCode");
        String foodCode = request.getParameter("foodName");
        ProductFoodStoreVO b = sysProductService.checkcNameUnique(product, itemsCode, foodCode);
        return AjaxResult.successData(200, b);
    }

    /**
     * 查询存储条件
     *
     * @param itemsCode
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/findStore")
    @ResponseBody
    public AjaxResult findStore(String itemsCode) {
        List<TSysStore> sysStores = sysStoreService.findByStoreList(itemsCode);
        return AjaxResult.successData(200, sysStores);
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
    public String upload(ModelMap modelMap) {
        TsysUser tsysUser = ShiroUtils.getUser();
        String icode = tsysUser.getItemsCode();
        String s = "";//去掉最后一个逗号
        if (icode != null && !icode.equals("")) {
            modelMap.put("itemCode", tsysUser.getItemsCode());
        } else {
            List<TSysItems> items = sysItemsService.queryItems();
            if(items==null || items.size()==0){
                modelMap.put("itemCode", s);
            }else {
                StringBuffer buf = new StringBuffer();
                for (TSysItems tSysItems : items) {
                    String ite = tSysItems.getItemsCode();
                    buf.append(ite).append(",");
                }
                s = buf.deleteCharAt(buf.length() - 1).toString();//去掉最后一个逗号
                modelMap.put("itemCode", s);
            }
        }
        return prefix + "/upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Model model, MultipartFile myFile, HttpServletRequest request) throws IOException {
        ExcelUtils.checkFile(myFile);
        String[] portProduct = null;
/*        List<Map<String, String>> dataList;
        try {
//            dataList = ProductExcelUtils.getExcelData(myFile, ImportProductDTO.IMPORT_TABLE_HEADER);
            dataList = ProductExcelUtils.getExcelData(myFile);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return prefix + "/import_error";
        }*/
        ImportProductDTO importProductDTO = sysProductService.importValid1(myFile, request);
        List<ProductVO> productVOList = sysProductService.getSuccessTSysProduct(importProductDTO.gettSysProductDTOS());
        sysProductService.saveSysProduct(productVOList);
        model.addAttribute("importProductDTO", importProductDTO);
        return prefix + "/product_valid";
    }


    /**
     * 产品导入 模版下载
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/uploadTemplate/{item}")
    public void uploadTemplate(@PathVariable("item") String item, HttpServletResponse response,HttpServletRequest request) throws Exception {
         String path = this.getClass().getResource("/").getPath();
        System.out.println("pathURl="+path);
//        FileInputStream fs = new FileInputStream("E:/apache-tomcat-desmartNew/webapps/labelprint/WEB-INF/classes/static/templates/product_template.xls");
        FileInputStream fs = new FileInputStream(path+"/static/templates/product_template.xls");//获取tomcat下解压的文件路径
//        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\templates\\product_template.xls");//本地项目路径
        POIFSFileSystem ps = new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        HSSFWorkbook wb = new HSSFWorkbook(ps);
        HSSFSheet sheet = wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row = sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
//        System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格
//        FileOutputStream out=new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\templates\\product_template.xls");
        row = sheet.getRow(1); //在现有行号后追加数据
        List<TSysStore> sysStore = null;
        if (item != null && !item.equals("")) {
            sysStore = sysStoreService.findByStoreList(item);
            int j = 4;
            for (TSysStore tSysStore1 : sysStore) {
                j++;
                row.createCell(j).setCellValue(tSysStore1.getName()); //设置第一个（从0开始）单元格的数据
            }
            FileUtils.createFile(response, wb, item + "_产品模板");
        }

    }


}
