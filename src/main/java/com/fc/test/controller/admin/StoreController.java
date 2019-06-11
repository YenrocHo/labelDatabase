package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.model.custom.process.TSysStore;
import com.fc.test.service.SysStoreService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(value = "存储条件")
@RequestMapping("StoreController")
public class StoreController extends BaseController {
    private String prefix = "admin/store";
    @Autowired
    private SysStoreService sysStoreService;

    @GetMapping("/view")
    @RequiresPermissions("system:product:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("存储条件列表", "存储条件", false,"欢迎进入存储条件页面", false, false));
        return prefix + "/list";
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request){
        TSysStore tSysStore = sysStoreService.selectStoreById(id);
        request.getSession().setAttribute("tSysStore",tSysStore);
        return prefix + "/edit";
    }

    //===============================================业务处理================================================//

    @PostMapping("/list")
    @RequiresPermissions("system:store:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt){
        PageInfo<TSysStore> page = sysStoreService.list(tablepar, searchTxt);
        TableSplitResult<TSysStore> result = new TableSplitResult<TSysStore>(page.getPageNum(),page.getTotal(),page.getList());
        return result;
    }

    @PostMapping("/remove")
    @RequiresPermissions("system:store:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int i = sysStoreService.removeStore(ids);
        if(i>0){
            return success();
        }else{
            return error();
        }
    }

    @PostMapping("/add")
    @RequiresPermissions("system:store:add")
    @ResponseBody
    public AjaxResult add(TSysStore tSysStore){
        int i = sysStoreService.insertStore(tSysStore);
        if(i>0){
            return success();
        }else{
            return error();
        }
    }
    @PostMapping("/edit")
    @RequiresPermissions("system:store:edit")
    @ResponseBody
    public AjaxResult editStore(TSysStore tSysStore,HttpServletRequest request){
        TSysStore store = (TSysStore)request.getSession().getAttribute("tSysStore");
        tSysStore.setId(store.getId());
        return toAjax(sysStoreService.updateStoreById(tSysStore));
    }

    @PostMapping("/freeze")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam String id,@RequestParam String status){
        return toAjax(sysStoreService.updateStatus(id,status));
    }

}
