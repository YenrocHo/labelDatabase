package com.fc.aden.service.impl;

import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.service.SysFoodService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFoodService2Impl implements SysFoodService2 {
    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Override
    public TSysFood updateStatus(TSysFood tSysFood){
        int i = tSysFoodMapper.updateStatusById(tSysFood.getId(), tSysFood.getStatus());
        if(i>0){
            TSysFood food = tSysFoodMapper.selectByPrimaryKey(tSysFood.getId());
            return food;
        }else {
            return null;
        }
    }
}
