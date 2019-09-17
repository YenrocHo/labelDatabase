package com.fc.aden.controller.admin;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.TableSplitResult;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.TitleVo;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.vo.ImportTSysStoreDTO;
import com.fc.aden.vo.ProductVO;
import com.fc.aden.vo.StoreVO;
import com.fc.aden.vo.importDto.ImportProductDTO;
import com.fc.aden.vo.importDto.ImportStoreDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    private static Logger logger = LoggerFactory.getLogger(StoreController.class);

    /**
     * @Author Noctis
     * @Description //页面跳转
     * @Date 2019/6/18 14:26
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("/view")
    @RequiresPermissions("system:store:view")
    public String view(Model model,ModelMap mp) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        mp.put("tSysItems", tSysItemsList);
        TsysUser tsysUser = ShiroUtils.getUser();
        mp.put("tsysUser", tsysUser);
        setTitle(model, new TitleVo("条件列表", "存储条件", false, "欢迎进入存储条件页面", false, false));
        return prefix + "/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TSysItems> tSysItemsList = sysItemsService.queryItems();
        modelMap.put("tSysItems", tSysItemsList);
        TsysUser tsysUser = ShiroUtils.getUser();
        modelMap.put("tsysUser", tsysUser);
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request,ModelMap mmap) {
        TSysStore tSysStore = sysStoreService.selectStoreById(id);
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        request.getSession().setAttribute("tSysStore", tSysStore);
        mmap.put("tSysItems", tSysItems);
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
    public Object list(Tablepar tablepar, String searchTxt,String itemsCode) {
        PageInfo<TSysStore> page = sysStoreService.list(tablepar, searchTxt,itemsCode);
        TableSplitResult<TSysStore> result = new TableSplitResult<TSysStore>(page.getPageNum(), page.getTotal(), page.getList());
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
    @PostMapping("/checkStoreUnique")
    @ResponseBody
    public AjaxResult checkStoreUnique(HttpServletRequest request){
        String name = request.getParameter("name");
        String itemsCode = request.getParameter("itemsCode");
        int s = sysStoreService.checkStore(name,itemsCode);
        if (s>0){
            return error();
        }else{
            return success();
        }
    }

    //////////批量导入

    /**
     * 跳转导入页面
     *
     * @return
     */
    @GetMapping("/upload")
    public String upload() {
        return prefix + "/upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Model model, MultipartFile myFile) {
        List<Map<String, String>> dataList;
        try {
            dataList = ExcelUtils.getExcelData(myFile, ImportStoreDTO.IMPORT_TABLE_HEADER);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return prefix + "/import_error";
        }
        ImportStoreDTO importStoreDTO = sysStoreService.importValid(dataList);
        List<StoreVO> storeVOS = sysStoreService.getSuccessTSysStore (importStoreDTO.getImportTSysStoreDTOS());
        sysStoreService.saveSysStore(storeVOS);
        model.addAttribute("importStoreDTO", importStoreDTO);
        return prefix + "/store_valid";
    }
}
