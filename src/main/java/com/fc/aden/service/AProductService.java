package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AProductService {
    AjaxResult selectProductList(String foodId, String keyword,String statusToken);
}
