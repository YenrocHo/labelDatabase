package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.vo.ImportTSysStoreDTO;
import com.fc.aden.vo.ProductVO;
import com.fc.aden.vo.StoreVO;
import com.fc.aden.vo.importDto.ImportStoreDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysStoreService  {
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt,String itemsCode);

    public int removeStore (String ids);
    public int insertStore(TSysStore tSysStore);

    public TSysStore selectStoreById(String id);
    public TSysStore findByStore(String itemsCode);
    public List<TSysStore> findByStoreList(String itemsCode);
    public List<TSysStore> queryStoreList();

    public int updateStoreById(TSysStore tSysStore);

    public TSysStore updateStatus(TSysStore tSysStore);

    public int checkStore(String name,String itemsCode);

    public ImportStoreDTO importValid(List<Map<String, String>> dataList);

    public List<StoreVO> getSuccessTSysStore(List<ImportTSysStoreDTO> importTSysStoreDTOS);
    public void saveSysStore(List<StoreVO> storeVOS);
}
