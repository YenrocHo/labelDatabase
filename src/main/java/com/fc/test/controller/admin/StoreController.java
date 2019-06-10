package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "存储条件")
@RequestMapping("StoreController")
public class StoreController extends BaseController {
    private String prefix = "admin/store";

    @GetMapping("view")
    @RequiresPermissions("system:product:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("存储条件列表", "存储条件", false,"欢迎进入存储条件页面", false, false));
        return prefix + "/list";
    }

    //===============================================业务处理================================================//

    @PostMapping("list")
    @RequiresPermissions("system:store:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt){

        return null;
    }
}
