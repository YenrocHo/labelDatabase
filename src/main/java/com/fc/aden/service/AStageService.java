package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AStageService {
    AjaxResult selectStageList(String keyword,String statusToken);
}
