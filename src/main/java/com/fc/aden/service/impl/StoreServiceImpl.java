package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.AStoreService;
import com.fc.aden.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;

@Service
public class StoreServiceImpl implements AStoreService {
    @Autowired
    private TSysStoreMapper tSysStoreMapper;
    @Override
    public AjaxResult selectStoreList(int pageNum, int pageSize, String productId, String keyword){
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        if(productId != null){
            productId = fuzzyQuery(productId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TSysStore> tSysStoreList = tSysStoreMapper.selectStoreList(productId, keyword);
        if (tSysStoreList != null){
            PageInfo<TSysStore> pageInfo = new PageInfo<TSysStore>(tSysStoreList);
            if (tSysStoreList.size() == 0){
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
