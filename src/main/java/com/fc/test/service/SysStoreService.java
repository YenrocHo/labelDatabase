package com.fc.test.service;

import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.process.TSysStore;
import com.github.pagehelper.PageInfo;

public interface SysStoreService  {
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt);

    public int removeStore (String ids);

    public int insertStore(TSysStore tSysStore);

    public TSysStore selectStoreById(String id);

    public int updateStoreById(TSysStore tSysStore);

    public TSysStore updateStatus(TSysStore tSysStore);
}
