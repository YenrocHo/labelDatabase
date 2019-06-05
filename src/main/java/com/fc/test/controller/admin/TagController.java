package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TSysTag;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
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
@Api(value = "标签记录")
@RequestMapping("TagController")
public class TagController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/tag";

    @GetMapping("view")
    @RequiresPermissions("system:tag:view")
    public String view(Model model)
    {
        setTitle(model, new TitleVo("标签管理", "标签列表", false,"欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * 文件列表
     * @param tablepar
     * @param searchTxt 搜索字符
     * @return
     */
    @PostMapping("list")
    @RequiresPermissions("system:tag:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt){
        PageInfo<TSysTag> page=sysTagService.sysTagList(tablepar,searchTxt);
        TableSplitResult<TSysTag> result=new TableSplitResult<TSysTag>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
    }
}
