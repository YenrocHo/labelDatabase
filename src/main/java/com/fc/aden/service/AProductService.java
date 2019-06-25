package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AProductService {
    AjaxResult selectProductList(int pageNum, int pageSize, String foodId, String keyword);
}
