/**
 * projectName: labelDatabase
 * fileName: ExpireController.java
 * packageName: com.fc.aden.controller.admin
 * date: 2020-02-27 14:42
 * copyright(c) 德慧
 */
package com.fc.aden.controller.admin;


import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.common.support.Convert;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.PrintHistory;
import com.fc.aden.shiro.util.ShiroUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "食品记录")
@RequestMapping("ExpireController")
public class ExpireController extends BaseController {
    //跳转页面参数
    private String prefix = "admin/tag";

    @GetMapping("/view")
    @RequiresPermissions("system:expire:view")
    public String view(Model model, ModelMap mp) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        mp.put("tSysItems", tSysItemsList);
        TsysUser tsysUser = ShiroUtils.getUser();
        mp.put("tsysUser", tsysUser);
        setTitle(model, new TitleVo("标签到期列表", "标签到期管理", false, "欢迎进入图片页面", false, false));
        return prefix + "/expireList";
    }

    /**
     * 打印历史列标签过期表
     *
     * @param stage
     * @param food
     * @param product
     * @param items
     * @param printUser
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("system:expire:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String stage, String food, String product, String items, String printUser, String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        Date date = new Date();//用于过期数据区别 勿修改
        int period = 0;// 用于临期数据区别 勿修改
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
        PageInfo<PrintHistory> page = sysTagService.sysTagList(tablepar, stage, food, product, items, printUser, start, end,date,period);
        TableSplitResult<PrintHistory> result = new TableSplitResult<PrintHistory>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * 核销历史数据
     *
     * @param id
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(String id) {
        int b = sysTagService.update(id);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 批量核销历史数据
     * @param ids
     * @return
     */
    @PostMapping("updateList")
    @RequiresPermissions("system:expire:updateList")
    @ResponseBody
    public AjaxResult updateList(String ids){
        List<String> lista= Convert.toListStrArray(ids);
        int b = 0;
        for (String id:lista) {
            b = sysTagService.update(id);
        }
        if(b>0){
            return success();
        }else{
            return error();
        }
    }
}
