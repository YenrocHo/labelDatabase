package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.vo.importDto.ImportFoodDTO;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.service.SysFoodService2;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.vo.FoodVO;
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
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "食品记录")
@RequestMapping("FoodController")
public class FoodController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/food";
    private static Logger logger = LoggerFactory.getLogger(FoodController.class);

    /**
     * @Author Noctis
     * @Description //TODO 查看视频图片
     * @Date 2019/6/21 10:30
     * @Param [id, mmap]
     * @return java.lang.String
     **/
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap){
        TSysFood tSysFood = sysFoodService.selectByPrimaryKey(id);
       String imagrUrl = tSysFood.getPicture();
        mmap.put("imagrUrl",imagrUrl);
        mmap.put("foodName",tSysFood.getFood());
       /*  mmap.put("name",tSysFood.getName());
        mmap.put("EnglishName",tSysFood.getEnglishName());*/
        return prefix + "/detail";
    }

    @GetMapping("/view")
    @RequiresPermissions("system:food:view")
    public String view(Model model,ModelMap mp) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        mp.put("tSysItems", tSysItemsList);
        TsysUser tsysUser = ShiroUtils.getUser();
        mp.put("tsysUser", tsysUser);
        setTitle(model, new TitleVo("食品列表", "食品管理", false,"欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * @Author Noctis
     * @Description //TODO
     * @Date 2019/6/18 15:29
     * @Param [tablepar, foodName]
     * @return java.lang.Object
     **/
    @PostMapping("/list")
    @RequiresPermissions("system:food:list")
    @ResponseBody
     public Object list(Tablepar tablepar, String foodName,String itemsCode){
        PageInfo<TSysFood> page=sysFoodService.sysFoodList(tablepar,foodName,itemsCode);
        TableSplitResult<TSysFood> result=new TableSplitResult<TSysFood>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        TsysUser tsysUser = ShiroUtils.getUser();
        modelMap.put("tsysUser", tsysUser);
        modelMap.put("tSysItems", tSysItemsList);
        return prefix + "/add";
    }

    /**
     * 添加食品
     * @param tSysFood
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("system:food:add")
    @ResponseBody
    public AjaxResult add(TSysFood tSysFood) {
        int b = sysFoodService.insertSelective(tSysFood);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }
 /**
     * 编辑食品
     * @param tSysFood
     * @return
     */
    @RequiresPermissions("system:food:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(TSysFood tSysFood ,String pictureId) {
        int b = sysFoodService.updateTsysFood(tSysFood,pictureId);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                //插入文件存储表
                String id=sysDatasService.insertSelective(file);
                if(id!=null){
                    return AjaxResult.successData(200, id);
                }
            }
            return error();
        }
        catch (Exception e) {
            return error(e.getMessage());
        }
    }
    /**
     * 验证食品种类是否重名
     * @param request
     * @return
     */
    @PostMapping("/checkFoodUnique")
    @ResponseBody
    public AjaxResult checkStageUnique(HttpServletRequest request){
        String food = request.getParameter("food");
        String itemsCode = request.getParameter("itemsCode");
        int b = sysFoodService.checkFoodUnique(food,itemsCode);
        if(b>0){
            return error();
        }else{
            return success();
        }
    }

    /**
     * 验证食品编号是否重名
     * @param sysFood
     * @return
     */
    @PostMapping("/checkFoodCodeUnique")
    @ResponseBody
    public int checkFoodCodeUnique(TSysFood sysFood){
        int b = sysFoodService.checkFoodCodeUnique(sysFood);
        if(b>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 删除食物
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @RequiresPermissions("system:food:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int b=sysFoodService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }

    /**
     * 编辑页面
     * @param id
     * @param request
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request, ModelMap mmap){
        TSysFood tSysFood = sysFoodService.selectByPrimaryKey(id);
        String items = tSysFood.getItemsCode();
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        request.getSession().setAttribute("tSysFood", tSysFood);
        mmap.addAttribute("items",items);
        mmap.addAttribute("tSysItems",tSysItems);
        return prefix + "/edit";
    }


    @Autowired
    private SysFoodService2 sysFoodService2;
    @PostMapping("/freeze")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam String id) {
        TSysFood tSysFood_status = sysFoodService.selectByPrimaryKey(id);
        TSysFood food = sysFoodService2.updateStatus(tSysFood_status);
        if (food != null) {
            return retobject(1,food);
        }else{
            return error(0,"修改失败");
        }
    }

    @GetMapping("/upload")
    public String upload() {
        return prefix + "/upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Model model, MultipartFile myFile) {
        List<Map<String, String>> dataList;
        try {
            dataList = ExcelUtils.getExcelData(myFile, ImportFoodDTO.IMPORT_TABLE_HEADER);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return "admin/import_error";
        }
        ImportFoodDTO importFoodDTO = sysFoodService.importValid(dataList);
        List<FoodVO> foodVOList = sysFoodService.getSuccessTSysProduct(importFoodDTO.getImportFoodDTOS());
        sysFoodService.saveSysFood(foodVOList);
        model.addAttribute("importFoodDTO", importFoodDTO);
        return prefix + "/food_valid";
    }
}
