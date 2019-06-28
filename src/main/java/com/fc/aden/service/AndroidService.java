package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AndroidService {
    AjaxResult selectAllList(String itemId,String keyword,String statusToken,String number);

    AjaxResult selectOneList(String itemId, String keyword,String statusToken,String type,String number);

    AjaxResult login(String number);

    AjaxResult AllUserList(String statusToken,String number);
}
