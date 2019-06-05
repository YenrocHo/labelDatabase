package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.model.custom.process.TSysFood;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Api(value = "食品记录")
@RequestMapping("FoodController")
public class FoodController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/food";

    @GetMapping("view")
    @RequiresPermissions("system:food:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("员工管理", "员工列表", false,"欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * 文件列表
     * @param tablepar
     * @param searchTxt 搜索字符
     * @return
     */
    @PostMapping("list")
    @RequiresPermissions("system:food:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt){
        PageInfo<TSysFood> page=sysFoodService.sysFoodList(tablepar,searchTxt);
        TableSplitResult<TSysFood> result=new TableSplitResult<TSysFood>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
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
    public AjaxResult add(TSysFood tSysFood) {
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
}
