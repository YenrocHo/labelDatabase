package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.base.TokenCache;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.service.AUserService;
import com.fc.aden.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        String statusToken = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKRN_PREFIX + number, statusToken);
        AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
        ajaxResult.put("data",tsysUser);
        ajaxResult.put("statusToken",statusToken);
        return ajaxResult;
    }
    @Override
    public AjaxResult AllUserList(String statusToken){
        if (StringUtils.isBlank(statusToken)) {
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        List<TsysUser> tsysUserList = tsysUserMapper.selectAllUser();
        if (tsysUserList != null){
            if (tsysUserList.size() == 0){
                AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.noObject.getCode(),Const.CodeEnum.noObject.getValue());
                return ajaxResult;
            }
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            ajaxResult.put(AJAX_DATA,tsysUserList);
            return ajaxResult;
        }else {
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
            return ajaxResult;
        }

    }
}
