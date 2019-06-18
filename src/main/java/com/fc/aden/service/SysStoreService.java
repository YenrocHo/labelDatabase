package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysStore;
import com.github.pagehelper.PageInfo;

public interface SysStoreService  {
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt);

    public int removeStore (String ids);

    public int insertStore(TSysStore tSysStore);

    public TSysStore selectStoreById(String id);

    public int updateStoreById(TSysStore tSysStore);

    public int updateStatus(String id,String status);
}
