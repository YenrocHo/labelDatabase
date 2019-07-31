package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.vo.StoreVO;
import com.github.pagehelper.PageInfo;

public interface SysStoreService  {
    public PageInfo<StoreVO> list(Tablepar tablepar, String searchTxt);

    public int removeStore (String ids);

    public int insertStore(TSysStore tSysStore);

    public TSysStore selectStoreById(String id);

    public int updateStoreById(TSysStore tSysStore);

    public TSysStore updateStatus(TSysStore tSysStore);
}
