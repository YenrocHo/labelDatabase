package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "员工记录")
@RequestMapping("EmployeesController")
public class EmployeesController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/employees";

    @GetMapping("view")
    @RequiresPermissions("system:employees:view")
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
    @RequiresPermissions("system:employees:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt){
        PageInfo<TSysEmployees> page=sysEmployeesService.sysEmployeesList(tablepar,searchTxt);
        TableSplitResult<TSysEmployees> result=new TableSplitResult<TSysEmployees>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
    }


}
