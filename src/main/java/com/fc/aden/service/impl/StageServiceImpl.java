package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.process.TSysStageMapper;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.service.AStageService;
import com.fc.aden.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Service
public class StageServiceImpl implements AStageService {
    @Autowired
    private TSysStageMapper tSysStageMapper;

    @Override
    public AjaxResult selectStageList(String keyword,String statusToken){
        if (StringUtils.isBlank(statusToken)) {
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        List<TSysStage> tSysStageList = tSysStageMapper.selectStageList(keyword);
        if (tSysStageList != null){
            if (tSysStageList.size() == 0){
                AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.noObject.getCode(),Const.CodeEnum.noObject.getValue());
                return ajaxResult;
            }
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            ajaxResult.put(AJAX_DATA,tSysStageList);
            return ajaxResult;
        }else {
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
            return ajaxResult;
        }

    }
    private String fuzzyQuery(String str){
        str = new StringBuilder().append("%").append(str).append("%").toString();
        return str;
    }
}
