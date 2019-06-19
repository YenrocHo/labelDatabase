package com.fc.aden.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.model.auto.*;
import com.fc.aden.model.custom.ImportItemsDTO;
import com.fc.aden.model.custom.ImportUserDTO;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.vo.ImportTSysItemsDTO;
import com.fc.aden.vo.ImportTSysUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysRoleUserMapper;
import com.fc.aden.mapper.auto.TsysRoleMapper;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.mapper.custom.RoleDao;
import com.fc.aden.model.custom.RoleVo;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.util.MD5Util;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统用户
 *
 * @author fuce
 * @ClassName: SysUserService
 * @date 2018年8月26日
 */
@Service
public class SysUserService implements BaseService<TsysUser, TsysUserExample> {

    private static Logger logger = LoggerFactory.getLogger(SysUserService.class);
    //生成的用户dao
    @Autowired
    private TsysUserMapper tsysUserMapper;

    //生成的角色用户dao
    @Autowired
    private TSysRoleUserMapper tSysRoleUserMapper;

    //自定义角色dao
    @Autowired
    private RoleDao roleDao;

    //自动生成的角色dao
    @Autowired
    private TsysRoleMapper tsysRoleMapper;

    @Autowired
    private TSysItemsMapper tSysItemsMapper;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 分页查询
     *
     * @return
     */
    public PageInfo<TsysUser> list(Tablepar tablepar, String username,String number,String name) {
        TsysUserExample testExample = new TsysUserExample();
        testExample.setOrderByClause("id+0 desc");
        if (username != null && !"".equals(username)) {
            testExample.createCriteria().andUsernameLike("%" + username + "%");
        }
        if (number != null && !"".equals(number)) {
            testExample.createCriteria().andNumberLike("%" + number + "%");
        }
        if (name != null && !"".equals(name)) {
            testExample.createCriteria().andNameLike("%" + name + "%");
        }
        if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<TsysUser> list = tsysUserMapper.selectByExample(testExample);
        PageInfo<TsysUser> pageInfo = new PageInfo<TsysUser>(list);
        return  pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TsysUserExample example = new TsysUserExample();
        example.createCriteria().andIdIn(lista);
        return tsysUserMapper.deleteByExample(example);
    }

    /**
     * 添加用户
     */
    @Override
    public int insertSelective(TsysUser record) {
        return tsysUserMapper.insertSelective(record);
    }

    /**
     * 添加用户跟角色信息
     *
     * @param record
     * @param roles
     * @return
     */
    @Transactional
    public int insertUserRoles(TsysUser record, List<String> roles) {
        String userid = SnowflakeIdWorker.getUUID();
        record.setId(userid);
        record.setCreateTime(df.format(new Date()));
        record.setUpdateTime(df.format(new Date()));
        if (StringUtils.isNotEmpty(roles)) {
            for (String rolesid : roles) {
                TSysRoleUser roleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID(), userid, rolesid);
                tSysRoleUserMapper.insertSelective(roleUser);
            }
        }
        //密码加密
        record.setPassword(MD5Util.encode(record.getPassword()));
        return tsysUserMapper.insertSelective(record);
    }

    @Override
    public TsysUser selectByPrimaryKey(String id) {
        return tsysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TsysUser record) {
        record.setPassword(MD5Util.encode(record.getPassword()));
        return tsysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(TsysUser record, TsysUserExample example) {
        return tsysUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(TsysUser record, TsysUserExample example) {
        return tsysUserMapper.updateByExample(record, example);
    }

    @Override
    public List<TsysUser> selectByExample(TsysUserExample example) {
        return tsysUserMapper.selectByExample(example);
    }

    @Override
    public long countByExample(TsysUserExample example) {
        return tsysUserMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TsysUserExample example) {
        return tsysUserMapper.deleteByExample(example);
    }

    /**
     * 检查用户name
     *
     * @param tsysUser
     * @return
     */
    public int checkLoginNameUnique(TsysUser tsysUser) {
        TsysUserExample example = new TsysUserExample();
        example.createCriteria().andUsernameEqualTo(tsysUser.getUsername());
        List<TsysUser> list = tsysUserMapper.selectByExample(example);
        return list.size();
    }
    /**
     * 检查用户name
     *
     * @param tsysUser
     * @return
     */
    public int checkNumberUnique(TsysUser tsysUser) {
        TsysUserExample example = new TsysUserExample();
        example.createCriteria().andNumberEqualTo(tsysUser.getNumber());
        List<TsysUser> list = tsysUserMapper.selectByExample(example);
        return list.size();
    }

    /**
     * 获取所有权限 并且增加是否有权限字段
     *
     * @return
     */
    public List<RoleVo> getUserIsRole(String userid) {
        List<RoleVo> list = new ArrayList<RoleVo>();
        //查询出我的权限
        List<TsysRole> myRoles = roleDao.queryUserRole(userid);
        TsysRoleExample tsysRoleExample = new TsysRoleExample();
        //查询系统所有的角色
        List<TsysRole> tsysRoles = tsysRoleMapper.selectByExample(tsysRoleExample);
        if (StringUtils.isNotEmpty(tsysRoles)) {
            for (TsysRole tsysRole : tsysRoles) {
                Boolean isflag = false;
                RoleVo roleVo = new RoleVo(tsysRole.getId(), tsysRole.getName(), isflag);
                for (TsysRole myRole : myRoles) {
                    if (tsysRole.getId().equals(myRole.getId())) {
                        isflag = true;
                        break;
                    }
                }
                if (isflag) {
                    roleVo.setIscheck(true);
                    list.add(roleVo);
                } else {
                    list.add(roleVo);
                }
            }
        }
        return list;
    }

    /**
     * 修改用户密码
     *
     * @param record
     * @return
     */
    public int updateUserPassword(TsysUser record) {
        record.setPassword(MD5Util.encode(record.getPassword()));
        //修改用户信息
        record.setUpdateTime(df.format(new Date()));
        return tsysUserMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改用户信息以及角色信息
     *
     * @param record
     * @param roles
     * @return
     */
    @Transactional
    public int updateUserRoles(TsysUser record, List<String> roles) {
        //先删除这个用户的所有角色
        TSysRoleUserExample tSysRoleUserExample = new TSysRoleUserExample();
        tSysRoleUserExample.createCriteria().andSysUserIdEqualTo(record.getId());
        tSysRoleUserMapper.deleteByExample(tSysRoleUserExample);
        //修改用户信息
        record.setUpdateTime(df.format(new Date()));
        tsysUserMapper.updateByPrimaryKeySelective(record);
        for (String role : roles) {   //添加新的角色信息
            TSysRoleUser tSysRoleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID(), record.getId(), role);
            tSysRoleUserMapper.insertSelective(tSysRoleUser);
        }
        //修改用户信息
        return 1;
    }

    /**
     * 验证文件，数据导入到DTO
     * @param excelFile
     * @return
     */
    public ImportUserDTO importValid(MultipartFile excelFile){
        List<Map<String, String>> dataList;
        List<String> projectNames = new ArrayList<>();
        ImportUserDTO importUserDTO = new ImportUserDTO();
        List<ImportTSysUserDTO> importTSysUserDTOs = new ArrayList<>();
        try {
            dataList = ExcelUtils.getExcelData(excelFile, ImportUserDTO.IMPORT_TABLE_HEADER);
        } catch (Exception e) {
            logger.warn("数据异常，重新导入", e);
            //文件解析异常
            return null;
        }
        int errNumber = 0;
        int successNumber = 0;
        for (Map<String, String> row : dataList) {
            String number = row.get(ImportUserDTO.NUMBER);
            String loginName = row.get(ImportUserDTO.LOGIN_NAME);
            String chineseName = row.get(ImportUserDTO.CHINESE_NAME);
            String englishName = row.get(ImportUserDTO.ENGLISH_NAME);
            String sex = row.get(ImportUserDTO.SEX);
            String items = row.get(ImportUserDTO.ITEMS);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysUserDTO importTSysUserDTO = new ImportTSysUserDTO();
            importTSysUserDTO.setCreateTime(df.format(new Date()));
            importTSysUserDTO.setUpdateTime(df.format(new Date()));
            importTSysUserDTO.setId(UUID.randomUUID().toString());

            List<TsysUser> users = tsysUserMapper.selectByNumber(number);
             if(StringUtils.isEmpty(number)){
                errorMessage.append("工号名称不能为空；");
                pass = false;
            }else if(projectNames.contains(number)){
                errorMessage.append("工号名称不能重复；");
                pass = false;
            }else if(users!=null && users.size()>0) {
                 errorMessage.append("工号名称不能重复；");
                 pass = false;
            }else{
                importTSysUserDTO.setNumber(number);
            }
            List<TsysUser> userlist = tsysUserMapper.selectByName(loginName);
            if(StringUtils.isEmpty(loginName)){
                errorMessage.append("登录名称不能为空；");
                pass = false;
            }else if(projectNames.contains(loginName)){
                errorMessage.append("登录名称不能重复；");
                pass = false;
            }else if(userlist!=null && userlist.size()>0) {
                errorMessage.append("登录名称不能重复；");
                pass = false;
            }else{
                importTSysUserDTO.setUsername(loginName);
            }

            if(StringUtils.isEmpty(chineseName)){
                errorMessage.append("中文名称不能为空；");
                pass = false;
            }else{
                importTSysUserDTO.setName(chineseName);
            }
            if(!sex.equals("男") && !sex.equals("女")){
                errorMessage.append("性别不正确；");
                pass = false;
            }else{
                importTSysUserDTO.setSex(sex);
            }
            List<TSysItems> tSysItems = tSysItemsMapper.selectByItems(items);
            if(StringUtils.isEmpty(items)){
                errorMessage.append("项目点不为空；");
                pass = false;
            }else if(tSysItems != null && tSysItems.size() > 0){
                importTSysUserDTO.setItems(items);
            }else {
                errorMessage.append("项目点不存在；");
                pass = false;
            }
            if(pass){
                errorMessage.append("成功！");
                successNumber++;
            }else{
                errNumber++;
            }
            importTSysUserDTO.setEnglishName(englishName);
            importTSysUserDTO.setPass(pass);
            importTSysUserDTO.setMessages(errorMessage.toString());
            importTSysUserDTOs.add(importTSysUserDTO);

        }
        importUserDTO.setErrorNumber(errNumber);
        importUserDTO.setSuccessNumber(successNumber);
        importUserDTO.settSysUser(importTSysUserDTOs);
        return importUserDTO;

    }

    /**
     * 导入文件
     * @param tsysUserList
     */
    public void saveSysUser(List<TsysUser> tsysUserList){
        for (TsysUser tsysUser : tsysUserList) {
            tsysUserMapper.insertSelective(tsysUser);
        }
    }

    public List<TsysUser> getSuccessTSysItems(List<ImportTSysUserDTO> importTSysUserDTOS){
        if(importTSysUserDTOS ==null) return new ArrayList<>();
        List<TsysUser> tsysUsers = new ArrayList<>();
        for (ImportTSysUserDTO importTSysUserDTO : importTSysUserDTOS) {
            if(importTSysUserDTO.getPass()){
                tsysUsers.add(loadByDTO(importTSysUserDTO));
            }
        }
        return tsysUsers;
    }

    /**
     * 确认导入数据
     * @param dto
     * @return
     */
    private TsysUser loadByDTO(ImportTSysUserDTO dto){
        TsysUser tsysUser = new TsysUser();
        tsysUser.setId(dto.getId());
        tsysUser.setNumber(dto.getNumber());
        tsysUser.setUsername(dto.getUsername());
        tsysUser.setItems(dto.getItems());
        tsysUser.setName(dto.getName());
        tsysUser.setEnglishName(dto.getEnglishName());
        tsysUser.setCreateTime(dto.getCreateTime());
        tsysUser.setUpdateTime(dto.getUpdateTime());
        tsysUser.setSex(dto.getSex());
        //密码默认使用登录名称
        tsysUser.setPassword(MD5Util.encode(dto.getUsername()));
        return tsysUser;
    }
}
