package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.model.custom.Tablepar;

public interface AFoodService {
    AjaxResult selectFoodList(String stageId, String keyword,String statusToken);
}
