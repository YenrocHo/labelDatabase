package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.service.AFoodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;


@Service
public class FoodServiceImpl implements AFoodService {

    @Autowired
    private TSysFoodMapper tSysFoodMapper;

    @Override
    public AjaxResult selectFoodList(String stageId, String keyword,String statusToken){
        if (StringUtils.isBlank(statusToken)) {
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        if(stageId != null){
            stageId = fuzzyQuery(stageId);
        }
        List<TSysFood> tSysFoodList = tSysFoodMapper.selectFoodList(stageId,keyword);
        if (tSysFoodList != null){
            if (tSysFoodList.size() == 0){
                AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.noObject.getCode(),Const.CodeEnum.noObject.getValue());
                return ajaxResult;
            }
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            ajaxResult.put(AJAX_DATA,tSysFoodList);
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
