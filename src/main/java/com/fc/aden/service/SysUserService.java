package com.fc.aden.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.model.auto.*;
import com.fc.aden.vo.importDto.ImportUserDTO;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.*;
import com.fc.aden.vo.ImportTSysUserDTO;
import com.fc.aden.vo.UserVO;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
    public PageInfo<TsysUser> list(Tablepar tablepar, String username, String itemsCode, String number, String name, String phone) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TsysUser> tSysUsers = null;
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysUsers = tsysUserMapper.selectByUserItems(name, phone, username, tsysUser.getItemsCode());
        } else {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysUsers = tsysUserMapper.queryByUser(itemsCode, name, phone, username);
        }
        PageInfo<TsysUser> pageInfo = new PageInfo<TsysUser>(tSysUsers);
        return pageInfo;
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

    public List<TsysUser> selectAllUser(String itemsCode) {
        return tsysUserMapper.selectAllUser(itemsCode);
    }

    public TsysUser selectLogin(String number) {
        return tsysUserMapper.selectLogin(number);
    }

    /**
     * 添加用户跟角色信息
     *
     * @param record
     * @param roles
     * @return
     */
    @Transactional
    public int insertUserRoles(TsysUser record, String roles) {

        String userId = SnowflakeIdWorker.getUUID().toString();
        record.setId(userId);
        record.setCreateTime(df.format(new Date()));
        record.setUpdateTime(df.format(new Date()));
        record.setNumber(record.getUsername());
        record.setRoles(roles);
        if (StringUtils.isNotEmpty(roles)) {
            TSysRoleUser roleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID().toString(), userId, roles);
            tSysRoleUserMapper.insertSelective(roleUser);
        }
        //密码加密 密码是登录名
        record.setPassword(MD5Util.encode(record.getUsername()));
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
        int list = tsysUserMapper.checkUserName(tsysUser.getUsername());
        if (list > 0) {
            return 1;
        } else {
            return 0;
        }
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
     *
     * @param dataList
     * @return
     */
    public ImportUserDTO importValid(List<Map<String, String>> dataList) {
        List<String> projectNames = new ArrayList<>();
        ImportUserDTO importUserDTO = new ImportUserDTO();
        List<ImportTSysUserDTO> importTSysUserDTOs = new ArrayList<>();

        int errNumber = 0;
        int successNumber = 0;
        for (Map<String, String> row : dataList) {
            String loginName = row.get(ImportUserDTO.LOGIN_NAME);
            String chineseName = row.get(ImportUserDTO.CHINESE_NAME);
            String englishName = row.get(ImportUserDTO.ENGLISH_NAME);
            String items = row.get(ImportUserDTO.ITEMS);
            String phone = row.get(ImportUserDTO.PHONE);
            String roles = row.get(ImportUserDTO.ROLES);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysUserDTO importTSysUserDTO = new ImportTSysUserDTO();
            importTSysUserDTO.setCreateTime(df.format(new Date()));
            importTSysUserDTO.setUpdateTime(df.format(new Date()));
            importTSysUserDTO.setId(UUID.randomUUID().toString());

            List<TsysUser> userlist = tsysUserMapper.selectByName(loginName);
            if (StringUtils.isEmpty(loginName)) {
                errorMessage.append("工号不能为空；");
                pass = false;
            } else if (projectNames.contains(loginName)) {
                errorMessage.append("工号不能重复；");
                pass = false;
            } else if (userlist != null && userlist.size() > 0) {
                errorMessage.append("工号不能重复；");
                pass = false;
            } else {
                importTSysUserDTO.setUsername(loginName);
            }

            if (StringUtils.isEmpty(chineseName)) {
                errorMessage.append("中文名称不能为空；");
                pass = false;
            } else {
                importTSysUserDTO.setName(chineseName);
            }

            if (!roles.equals("0")  && !roles.equals("1") && !roles.equals("2")) {
                errorMessage.append("角色权限不存在；");
                pass = false;
            } else if (StringUtils.isEmpty(roles)) {
                errorMessage.append("角色不能为空；");
                pass = false;
            } else if (roles.equals("0")) {
                importTSysUserDTO.setRoles(roles);
            } else if (roles.equals("1")) {
                importTSysUserDTO.setRoles(roles);
            } else if (roles.equals("2")) {
                importTSysUserDTO.setRoles(roles);
            }

            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if (StringUtils.isEmpty(items)) {
                errorMessage.append("项目点不为空；");
                pass = false;
            } else if (tSysItemsList != null && tSysItemsList.size() > 0) {
                importTSysUserDTO.setItemsCode(items);//存入
            } else {
                errorMessage.append("项目点不存在；");
                pass = false;
            }
            if (pass) {
                errorMessage.append("成功！");
                successNumber++;
            } else {
                errNumber++;
            }
            importTSysUserDTO.setEnglishName(englishName);
            importTSysUserDTO.setPass(pass);
            importTSysUserDTO.setPhoneNumber(phone);
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
     *
     * @param userVOList
     */
    public void saveSysUser(List<UserVO> userVOList) {
        for (UserVO userVO : userVOList) {
            TsysUser tsysUser = new TsysUser();
            //权限存入管理员
            TSysRoleUser roleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID().toString(), userVO.getId(),userVO.getRoles());
            BeanCopierEx.copy(userVO, tsysUser);
            tSysRoleUserMapper.insertSelective(roleUser);
            tsysUserMapper.insertSelective(tsysUser);
        }
    }

    public List<UserVO> getSuccessTSysItems(List<ImportTSysUserDTO> importTSysUserDTOS) {
        if (importTSysUserDTOS == null) return new ArrayList<>();
        List<UserVO> tsysUsers = new ArrayList<>();
        for (ImportTSysUserDTO importTSysUserDTO : importTSysUserDTOS) {
            if (importTSysUserDTO.getPass()) {
                tsysUsers.add(loadByDTO(importTSysUserDTO));
            }
        }
        return tsysUsers;
    }

    /**
     * 确认导入数据
     *
     * @param dto
     * @return
     */
    private UserVO loadByDTO(ImportTSysUserDTO dto) {
        UserVO userVO = new UserVO();
        //密码默认使用登录名称
        String pw = MD5Util.encode(dto.getUsername());
        userVO.setId(dto.getId());
        userVO.setItemsCode(dto.getItemsCode());
        userVO.setName(dto.getName());
        userVO.setPhoneNumber(dto.getPhoneNumber());
        userVO.setEnglishName(dto.getEnglishName());
        userVO.setUsername(dto.getUsername());
        userVO.setUpdateTime(dto.getUpdateTime());
        userVO.setCreateTime(dto.getCreateTime());
        userVO.setNumber(dto.getUsername());
        userVO.setRoles(dto.getRoles());
        userVO.setPassword(pw);
        return userVO;
    }

    /**
     * 通过用户名和项目点判断是否存在
     *
     * @author Created by zc on 2020/2/19
     */
    public boolean isExist(String username, String itemsCode) {
        return tsysUserMapper.isExist(username, itemsCode);
    }

}
