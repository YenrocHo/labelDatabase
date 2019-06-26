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

    TsysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByExample(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByPrimaryKeySelective(TsysUser record);

    int updateByPrimaryKey(TsysUser record);

    List<TsysUser> selectByName(String name);
    List<TsysUser> selectByNumber(String number);
    List<TsysUser> selectAllUser();

    int checkNumber(String number);

    TsysUser selectLogin(String number);
}