package com.fc.aden.service.impl;

import com.fc.aden.mapper.auto.process.TSysStageMapper;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.service.SysStageService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysStageService2Impl implements SysStageService2 {
    @Autowired
    private TSysStageMapper tSysStageMapper;
    @Override
    public TSysStage updateStatus(TSysStage tSysStage){
        int i = tSysStageMapper.updateStatusById(tSysStage.getId(), tSysStage.getStatus());
        if (i>0){
            TSysStage stage = tSysStageMapper.selectByPrimaryKey(tSysStage.getId());
            return stage;
        }else {
            return null;
        }

    }
}
