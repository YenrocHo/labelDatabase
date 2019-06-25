package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.Tablepar;

public interface AFoodService {
    AjaxResult selectFoodList(int pageNum,int pageSize,String stageId, String keyword);
}
