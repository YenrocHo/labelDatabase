package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.service.AProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Service
public class ProductServiceImpl implements AProductService {
    @Autowired
    private TSysProductMapper tSysProductMapper;

    @Override
    public AjaxResult selectProductList(int pageNum, int pageSize, String foodId, String keyword){
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        if(foodId != null){
            foodId = fuzzyQuery(foodId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TSysProduct> tSysProductList = tSysProductMapper.selectProductList(foodId, keyword);
        if (tSysProductList != null){
            PageInfo<TSysProduct> pageInfo = new PageInfo<TSysProduct>(tSysProductList);
            if (tSysProductList.size() == 0){
                AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.noObject.getCode(),Const.CodeEnum.noObject.getValue());
                return ajaxResult;
            }
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            ajaxResult.put(AJAX_DATA,pageInfo);
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
