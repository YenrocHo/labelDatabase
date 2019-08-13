package com.fc.aden.mapper.auto;

import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.auto.TsysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsysUserMapper {
    int countByExample(TsysUserExample example);

    int deleteByExample(TsysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysUser record);

    int insertSelective(TsysUser record);

    List<TsysUser> selectByExample(TsysUserExample example);
    //根据项目点编号查询
    List<TsysUser> selectByListUser(String itemsCode);
    List<TsysUser> queryByUser(String items,String name,String username,String itemsCode);

    TsysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByExample(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByPrimaryKeySelective(TsysUser record);

    int updateByPrimaryKey(TsysUser record);

    //查询工号
    List<TsysUser> selectByName(String name);

    //查询全部
    List<TsysUser> selectAllUser();

    //唯一标识
    int checkUserName(String username);

    //查询工号
    TsysUser selectLogin(String number);
}