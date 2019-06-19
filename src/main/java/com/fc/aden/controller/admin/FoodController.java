package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.TSysFood;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Api(value = "食品记录")
@RequestMapping("FoodController")
public class FoodController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/food";

    private String imgPrefix = "http://www.image.com/image/";

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap){
        TSysFood tSysFood = sysFoodService.selectByPrimaryKey(id);
        String[] strings = tSysFood.getPicture().split("/");
        mmap.put("imagrUrl",imgPrefix+strings[3]);
        mmap.put("foodName",tSysFood.getName());
        return prefix + "/detail";
    }

    @GetMapping("view")
    @RequiresPermissions("system:food:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("食品管理", "食品列表", false,"欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * @Author Noctis
     * @Description //TODO
     * @Date 2019/6/18 15:29
     * @Param [tablepar, foodName]
     * @return java.lang.Object
     **/
    @PostMapping("list")
    @RequiresPermissions("system:food:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String foodName){
        PageInfo<TSysFood> page=sysFoodService.sysFoodList(tablepar,foodName);
        TableSplitResult<TSysFood> result=new TableSplitResult<TSysFood>(page.getPageNum(), page.getTotal(), page.getList());
        /*AjaxResult ajaxResult = AjaxResult.success("读取成功");
        ajaxResult.put(AjaxResult.AJAX_DATA,result);*/
        return result;
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 添加食品
     * @param tSysFood
     * @return
     */
    @PostMapping("add")
    @RequiresPermissions("system:food:add")
    @ResponseBody
    public AjaxResult add(TSysFood tSysFood, HttpServletRequest request) {
        int b = sysFoodService.insertSelective(tSysFood);
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
     * 验证阶段是否重名
     * @param sysFood
     * @return
     */
    @PostMapping("checkFoodUnique")
    @ResponseBody
    public int checkStageUnique(TSysFood sysFood){
        int b = sysFoodService.checkFoodUnique(sysFood);
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
    @PostMapping("remove")
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
}
