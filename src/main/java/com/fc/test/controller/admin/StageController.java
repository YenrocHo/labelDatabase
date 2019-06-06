package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.model.custom.process.TSysStage;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 制作阶段
 */
@Controller
@Api(value = "制作阶段")
@RequestMapping("StageController")
public class StageController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/stage";

    @GetMapping("view")
    @RequiresPermissions("system:stage:view")
    public String view(Model model) {
        setTitle(model, new TitleVo("制作阶段", "阶段列表", false, "欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * 阶段列表
     *
     * @param tablepar
     * @param searchTxt 搜索字符
     * @return
     */
    @PostMapping("list")
    @RequiresPermissions("system:stage:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt) {
        PageInfo<TSysStage> page = sysStageService.sysStageList(tablepar, searchTxt);
        TableSplitResult<TSysStage> result = new TableSplitResult<TSysStage>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @PostMapping("add")
    @RequiresPermissions("system:stage:add")
    @ResponseBody
    public AjaxResult add(TSysStage tSysStage) {
        int b = sysStageService.insertSelective(tSysStage);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 验证阶段是否重名
     * @param tSysStage
     * @return
     */
    @PostMapping("checkStageUnique")
    @ResponseBody
    public int checkStageUnique(TSysStage tSysStage){
        int b = sysStageService.checkStageUnique(tSysStage);
        if(b>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 编辑阶段
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        //查询所有角色
       TSysStage tSysStageList = sysStageService.selectByPrimaryKey(id);
        mmap.put("tSysStageList",tSysStageList);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:stage:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysStage tSysStage)
    {
        return toAjax(sysStageService.updateByPrimaryKeySelective(tSysStage));
    }

    /**
     * 删除用户
     * @return
     */
    @PostMapping("remove")
    @RequiresPermissions("system:stage:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int b=sysStageService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }
}