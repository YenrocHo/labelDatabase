package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.service.AUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Service
public class UserServiceImpl implements AUserService {

    @Autowired
     private TsysUserMapper tsysUserMapper;

    @Override
    public AjaxResult login(String number){
        int resultCount = tsysUserMapper.checkNumber(number);
        if (resultCount == 0) {
            AjaxResult ajaxResult = AjaxResult.error(Const.CodeEnum.noExistent.getCode(),Const.CodeEnum.noExistent.getValue());
            return ajaxResult;
        }
        TsysUser tsysUser = tsysUserMapper.selectLogin(number);
        if(tsysUser == null){
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
        ajaxResult.put("data",tsysUser);
        return ajaxResult;
    }
}
