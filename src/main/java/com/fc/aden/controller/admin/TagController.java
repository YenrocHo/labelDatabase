package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.TSysTag;
import com.fc.aden.vo.TagVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@Api(value = "标签记录")
@RequestMapping("TagController")
public class TagController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/tag";

    @GetMapping("/view")
    @RequiresPermissions("system:tag:view")
    public String view(Model model) {
        setTitle(model, new TitleVo("标签管理", "标签列表", false, "欢迎进入图片页面", false, false));
        return prefix + "/list";
    }

    /**
     * 文件列表
     *
     * @param tablepar
     * @param stage
     * @param food
     * @param product
     * @param items
     * @param printUser
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("system:tag:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String stage, String food, String product, String items, String printUser, String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            if (startTime != null && !"".equals(startTime)) {
                start = dateFormat.parse(startTime);
            }
            if (endTime != null && !"".equals(endTime)) {
                end = dateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageInfo<TagVO> page = sysTagService.sysTagList(tablepar, stage, food, product, items, printUser, start, end);
        TableSplitResult<TagVO> result = new TableSplitResult<TagVO>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * 删除食物
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @RequiresPermissions("system:tag:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int b = sysTagService.deleteByPrimaryKey(ids);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id, ModelMap modelMap) {
        TSysTag tag = sysTagService.selectByPrimaryKey(id);
        modelMap.put("tag", tag);
        return prefix + "/details";
    }

    @GetMapping("/details")
    @RequiresPermissions("system:tag:details")
    public String upload(TSysTag tSysTag) {
        return prefix + "/details";
    }
}

