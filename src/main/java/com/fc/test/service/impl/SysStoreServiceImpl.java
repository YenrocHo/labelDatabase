package com.fc.test.service.impl;


import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.process.TSysStoreMapper;
import com.fc.test.model.custom.Tablepar;

import com.fc.test.model.custom.process.TSysStore;
import com.fc.test.service.SysStoreService;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SysStoreServiceImpl implements SysStoreService {

    @Autowired
    private TSysStoreMapper tSysStoreMapper;

    @Override
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt){
        List<TSysStore> tSysStoreList = null;
        PageHelper.startPage(tablepar.getPageNum(),tablepar.getPageSize());
        tSysStoreList = tSysStoreMapper.selectList();
        PageInfo<TSysStore> pageInfo = new PageInfo<TSysStore>(tSysStoreList);
        return  pageInfo;
    }

    @Override
    public int removeStore(String ids){
        List<String> storeIdlist = Convert.toListStrArray(ids);
        int i = tSysStoreMapper.delectStoreByIds(storeIdlist);
        return i;
    }

    @Override
    public int insertStore(TSysStore tSysStore){
        tSysStore.setId(SnowflakeIdWorker.getUUID());
        tSysStore.setStatus(0);
        tSysStore.setCreatTime(new Date());
        tSysStore.setUpdateTime(new Date());
        int i = tSysStoreMapper.insertSelective(tSysStore);
        return i;
    }

    @Override
    public TSysStore selectStoreById(String id){
        TSysStore tSysStore = tSysStoreMapper.selectByPrimaryKey(id);
        if (tSysStore != null){
            return tSysStore;
        }else {
            return null;
        }
    }

    @Override
    public int updateStoreById(TSysStore tSysStore){
        int i = tSysStoreMapper.updateByPrimaryKeySelective(tSysStore);
        if (i>0){
            return i;
        }else {
            return 0;
        }
    }
    @Override
    public int updateStatus(String id,String status){
        Integer num_status = Integer.parseInt(status);
        int i = tSysStoreMapper.updateStatusById(id, num_status);
        return i;
    }
}
