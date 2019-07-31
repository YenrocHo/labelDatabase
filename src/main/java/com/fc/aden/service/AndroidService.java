package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AndroidService {
    AjaxResult selectAllList(String itemId,String keyword,String statusToken,String username);

    AjaxResult selectOneList(String itemId, String keyword,String statusToken,String type,String username);

    AjaxResult login(String username);

    AjaxResult AllUserList(String statusToken,String username);

    AjaxResult submit(String jsonString);
}
