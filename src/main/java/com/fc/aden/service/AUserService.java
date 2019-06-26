package com.fc.aden.service;

import com.fc.aden.common.domain.AjaxResult;

public interface AUserService {
    AjaxResult login(String number);

    AjaxResult AllUserList(String statusToken);
}
