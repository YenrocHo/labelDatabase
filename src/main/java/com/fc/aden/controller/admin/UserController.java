package com.fc.aden.controller.admin;

import java.util.List;

import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.custom.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fc.aden.common.base.BaseController;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.common.log.Log;
import com.fc.aden.model.auto.TsysRole;
import com.fc.aden.model.auto.TsysUser;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("UserController")
@Api(value = "用户数据")
public class UserController extends BaseController {

    private String prefix = "admin/user";

    @GetMapping("view")
    @RequiresPermissions("system:user:view")
    public String view(Model model) {
        setTitle(model, new TitleVo("用户列表", "用户管理", true, "欢迎进入用户页面", true, false));
        return prefix + "/list";
    }


    @Log(title = "用户集合查询", action = "111")
    @PostMapping("list")
    @RequiresPermissions("system:user:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String username, String number, String name) {
        PageInfo<TsysUser> page = sysUserService.list(tablepar, username, number, name);
        TableSplitResult<TsysUser> result = new TableSplitResult<TsysUser>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        //添加角色列表
        List<TsysRole> tsysRoleList = sysRoleService.queryList();
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        modelMap.put("tsysRoleList", tsysRoleList);
        modelMap.put("tSysItems", tSysItems);
        return prefix + "/add";
    }


    @PostMapping("add")
    @RequiresPermissions("system:user:add")
    @ResponseBody
    public AjaxResult add(TsysUser user, @RequestParam(value = "roles", required = false) List<String> roles) {
        int b = sysUserService.insertUserRoles(user, roles);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @PostMapping("remove")
    @RequiresPermissions("system:user:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int b = sysUserService.deleteByPrimaryKey(ids);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 检查用户
     *
     * @param tsysUser
     * @return
     */
    @PostMapping("checkLoginNameUnique")
    @ResponseBody
    public int checkLoginNameUnique(TsysUser tsysUser) {
        int b = sysUserService.checkLoginNameUnique(tsysUser);
        if (b > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 检查用户工号
     *
     * @param tsysUser
     * @return
     */
    @PostMapping("checkNumberUnique")
    @ResponseBody
    public int checkNumberUnique(TsysUser tsysUser) {
        int b = sysUserService.checkNumberUnique(tsysUser);
        if (b > 0) {
            return 1;
        } else {
            return 0;
        }
    }


    /**
     * 修改用户
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //查询所有角色
        List<RoleVo> roleVos = sysUserService.getUserIsRole(id);
        TsysUser user = sysUserService.selectByPrimaryKey(id);
        String ite = user.getItems();
        List<TSysItems> tSysItems = sysItemsService.queryItems();
        mmap.put("roleVos", roleVos);
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        mmap.put("tSysItems", tSysItems);
        mmap.put("ite", ite);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TsysUser tsysUser, @RequestParam(value = "roles", required = false) List<String> roles) {
        return toAjax(sysUserService.updateUserRoles(tsysUser, roles));
    }

    /**
     * 修改用户密码
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/editPwd/{id}")
    public String editPwd(@PathVariable("id") String id, ModelMap mmap) {
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        return prefix + "/editPwd";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:editPwd")
    @PostMapping("/editPwd")
    @ResponseBody
    public AjaxResult editPwdSave(TsysUser tsysUser) {
        return toAjax(sysUserService.updateUserPassword(tsysUser));
    }

    /**
     * 跳转导入页面
     * @return
     */
    @GetMapping("/upload")
    public String upload() {
        return prefix + "/upload";
    }

    @PostMapping("/upload")
    @RequiresPermissions("system:user:upload")
    public String upload(TSysItems tSysItems) {
        return prefix + "/upload";
    }


    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile myFile, Model model) {
        ImportUserDTO importUserDTO = sysUserService.importValid(myFile);
        List<TsysUser> tsysUsers = sysUserService.getSuccessTSysItems(importUserDTO.gettSysUser());
        sysUserService.saveSysUser(tsysUsers);
        model.addAttribute("importUserDTO", importUserDTO);
//        return sysLearnFileService.inportItems(request);
        return prefix+"/user_valid";
    }

}


