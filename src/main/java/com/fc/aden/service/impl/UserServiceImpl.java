package com.fc.aden.service.impl;

import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.service.AUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AUserService {

    @Autowired
     private TsysUserMapper tsysUserMapper;

    @Override
    public AjaxResult login(String number){
        int resultCount = tsysUserMapper.checkNumber(number);
        if (resultCount == 0) {
            return AjaxResult.error(301,"用户不存在");
        }
        TsysUser tsysUser = tsysUserMapper.selectLogin(number);
        if(tsysUser == null){
            return AjaxResult.error(302,"工号错误");
        }
        return AjaxResult.successData(200,tsysUser);
    }
}
