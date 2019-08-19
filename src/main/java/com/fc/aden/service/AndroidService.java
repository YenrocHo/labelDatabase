package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AndroidService {
    AjaxResult selectAllList(String itemsCode,String keyword,String statusToken,String username,String foodCode);

    AjaxResult selectOneList(String keyword,String statusToken,String type,String foodCode,String username);

    AjaxResult login(String username);

    AjaxResult AllUserList(String statusToken,String username);

    AjaxResult submit(String jsonString);
}
