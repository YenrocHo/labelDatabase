package com.fc.aden.mapper.auto;

import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.auto.TsysUserExample;
import java.util.List;

import com.fc.aden.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface TsysUserMapper {
    int countByExample(TsysUserExample example);

    int deleteByExample(TsysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysUser record);

    int insertSelective(TsysUser record);

    List<TsysUser> selectByExample(TsysUserExample example);
    //根据项目点编号查询
    List<TsysUser> selectByUserItems(String name,String phone,String username,String itemsCode);
    //管理员查询
    List<TsysUser> queryByUser(String itemsCode,String name,String phone,String username);

    TsysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByExample(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByPrimaryKeySelective(TsysUser record);

    int updateByPrimaryKey(TsysUser record);

    //查询工号
    List<TsysUser> selectByName(String name);

    //根据项目点查询全部用户
    List<TsysUser> selectAllUser(String itemsCode);

    //唯一标识
    int checkUserName(@Param("username") String username);

    //查询工号
    TsysUser selectLogin(String number);

    UserVO selectLoginVo(@Param("number") String number);
    UserVO selectLoginOrItem(String number);

    /**
     * 通过用户名和项目点判断是否存在
     *
     * @author Created by zc on 2020/2/19
     */
    boolean isExist(@Param("username") String username, @Param("itemsCode") String itemsCode);
}