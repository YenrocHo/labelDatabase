package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.TSysItems;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * 制作阶段
 */
@Controller
@Api(value = "项目点管理")
@RequestMapping("ItemsController")
public class ItemsController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/items";

    @GetMapping("view")
    @RequiresPermissions("system:items:view")
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
    @RequiresPermissions("system:items:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt) {
        PageInfo<TSysItems> page = sysItemsService.sysIteamsList(tablepar, searchTxt);
        TableSplitResult<TSysItems> result = new TableSplitResult<TSysItems>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @PostMapping("add")
    @RequiresPermissions("system:items:add")
    @ResponseBody
    public AjaxResult add(TSysItems tSysItems) {
        int b = sysItemsService.insertSelective(tSysItems);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 验证项目点是否重名
     * @param tSysItems
     * @return
     */
    @PostMapping("checkItemsUnique")
    @ResponseBody
    public int checkStageUnique(TSysItems tSysItems){
        int b = sysItemsService.checkIteamsUnique(tSysItems);
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
        TSysItems tSysItems = sysItemsService.selectByPrimaryKey(id);
        mmap.put("tSysItems",tSysItems);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:items:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysItems tSysItems)
    {
        return toAjax(sysItemsService.updateByPrimaryKeySelective(tSysItems));
    }

    /**
     * 删除用户
     * @return
     */
    @PostMapping("remove")
    @RequiresPermissions("system:items:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int b=sysItemsService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }
}