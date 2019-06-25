package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AStoreService {
    AjaxResult selectStoreList(int pageNum, int pageSize, String productId, String keyword);
}
