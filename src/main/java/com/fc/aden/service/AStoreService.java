package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AStoreService {
    AjaxResult selectStoreList(String productId, String keyword,String statusToken);
}
