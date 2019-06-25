package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AStageService {
    AjaxResult selectStageList(int pageNum, int pageSize,String keyword);
}
