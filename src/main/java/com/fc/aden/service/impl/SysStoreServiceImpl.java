package com.fc.aden.service.impl;


import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.custom.Tablepar;

import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
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
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt) {
        List<TSysStore> tSysStores = null;
        if (StringUtils.isEmpty(searchTxt)) {
            if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysStores = tSysStoreMapper.selectList();
        } else {
            if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            searchTxt = "%" + searchTxt + "%";
            tSysStores = tSysStoreMapper.selectListBycQuery(searchTxt);
        }
        PageInfo<TSysStore> pageInfo = new PageInfo<TSysStore>(tSysStores);
        return pageInfo;
    }

    @Override
    public int removeStore(String ids) {
        List<String> storeIdlist = Convert.toListStrArray(ids);
        int i = tSysStoreMapper.delectStoreByIds(storeIdlist);
        return i;
    }

    @Override
    public int insertStore(TSysStore tSysStore) {
        tSysStore.setId(SnowflakeIdWorker.getUUID());
        tSysStore.setStatus(0);
        tSysStore.setCreatTime(new Date());
        tSysStore.setUpdateTime(new Date());
        int i = tSysStoreMapper.insertSelective(tSysStore);
        return i;
    }

    @Override
    public TSysStore selectStoreById(String id) {
        TSysStore tSysStore = tSysStoreMapper.selectByPrimaryKey(id);
        if (tSysStore != null) {
            return tSysStore;
        } else {
            return null;
        }
    }

    @Override
    public int updateStoreById(TSysStore tSysStore) {
        int i = tSysStoreMapper.updateByPrimaryKeySelective(tSysStore);
        if (i > 0) {
            return i;
        } else {
            return 0;
        }
    }

    @Override
    public int updateStatus(String id, String status) {
        Integer num_status = Integer.parseInt(status);
        int i = tSysStoreMapper.updateStatusById(id, num_status);
        return i;
    }
}
