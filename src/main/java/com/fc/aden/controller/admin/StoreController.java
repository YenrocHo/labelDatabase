package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.vo.ItemsVO;
import com.fc.aden.vo.StoreVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**

* @Description:    java类作用描述

* @Author:         Noctis

* @CreateDate:     2019/6/18 14:25

* @UpdateUser:     Noctis

* @UpdateDate:     2019/6/18 14:25

* @UpdateRemark:   修改内容

* @Version:        1.0

*/
@Controller
@Api(value = "存储条件")
@RequestMapping("StoreController")
public class StoreController extends BaseController {
    private String prefix = "admin/store";

    @Autowired
    private SysStoreService sysStoreService;
    
    /**
     * @Author Noctis
     * @Description //页面跳转
     * @Date 2019/6/18 14:26
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("/view")
    @RequiresPermissions("system:store:view")
    public String view(Model model) {
        setTitle(model, new TitleVo("存储条件列表", "存储条件", false, "欢迎进入存储条件页面", false, false));
        return prefix + "/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        List<ItemsVO> itemsVOS = new ArrayList<>();
        for(TSysItems tSysItems:tSysItemsList){
            ItemsVO itemsVO = new ItemsVO();
            itemsVO.setItemsId(tSysItems.getId());
            itemsVO.setItems(tSysItems.getItemsCode());
            itemsVOS.add(itemsVO);
        }
        modelMap.put("tSysItems", itemsVOS);
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request,ModelMap mmap) {
        TSysStore tSysStore = sysStoreService.selectStoreById(id);
        String ite = tSysStore.getItemId();
        TSysItems tSysItem = sysItemsService.selectByPrimaryKey(ite);
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        request.getSession().setAttribute("tSysStore", tSysStore);
        mmap.put("tSysItems", tSysItems);
        mmap.put("ite", tSysItem.getItemsCode());
        return prefix + "/edit";
    }

    //===============================================业务处理================================================//
    /**
     * @Author Noctis
     * @Description //TODO 
     * @Date 2019/6/18 14:27
     * @Param [tablepar, searchTxt]
     * @return java.lang.Object
     **/
    @PostMapping("/list")
    @RequiresPermissions("system:store:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt) {
        PageInfo<StoreVO> page = sysStoreService.list(tablepar, searchTxt);
        TableSplitResult<StoreVO> result = new TableSplitResult<StoreVO>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }
    /**
     * @Author Noctis
     * @Description //TODO 
     * @Date 2019/6/18 14:28
     * @Param [ids]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/remove")
    @RequiresPermissions("system:store:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int i = sysStoreService.removeStore(ids);
        if (i > 0) {
            return success();
        } else {
            return error();
        }
    }
    /**
     * @Author Noctis
     * @Description //TODO 
     * @Date 2019/6/18 14:28
     * @Param [tSysStore]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/add")
    @RequiresPermissions("system:store:add")
    @ResponseBody
    public AjaxResult add(TSysStore tSysStore) {
        int i = sysStoreService.insertStore(tSysStore);
        if (i > 0) {
            return success();
        } else {
            return error();
        }
    }
    /**
     * @Author Noctis
     * @Description //TODO 
     * @Date 2019/6/18 14:28
     * @Param [tSysStore, request]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/edit")
    @RequiresPermissions("system:store:edit")
    @ResponseBody
    public AjaxResult editStore(TSysStore tSysStore, HttpServletRequest request) {
        TSysStore store = (TSysStore) request.getSession().getAttribute("tSysStore");
        tSysStore.setId(store.getId());
        return toAjax(sysStoreService.updateStoreById(tSysStore));
    }
    /**
     * @Author Noctis
     * @Description //TODO 
     * @Date 2019/6/18 14:28
     * @Param [id]
     * @return com.fc.test.common.domain.AjaxResult
     **/
    @PostMapping("/freeze")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam String id) {
        TSysStore tSysStore_status = sysStoreService.selectStoreById(id);
        TSysStore store = sysStoreService.updateStatus(tSysStore_status);
        if (store != null) {
            return retobject(1,store);
        }else{
            return error(0,"修改失败");
        }
    }

}
