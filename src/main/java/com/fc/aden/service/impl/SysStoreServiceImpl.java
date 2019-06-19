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
    
    /**
     * @Author Noctis
     * @Description //存储条件分页展示
     * @Date 2019/6/18 14:29
     * @Param [tablepar, searchTxt]
     * @return com.github.pagehelper.PageInfo<com.fc.test.model.custom.process.TSysStore>
     **/
    @Override
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt) {
        List<TSysStore> tSysStores = null;
        if (StringUtils.isEmpty(searchTxt)) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            tSysStores = tSysStoreMapper.selectList();
        } else {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            searchTxt = "%" + searchTxt + "%";
            tSysStores = tSysStoreMapper.selectListBycQuery(searchTxt);
        }
        PageInfo<TSysStore> pageInfo = new PageInfo<TSysStore>(tSysStores);
        return pageInfo;
    }
    /**
     * @Author Noctis
     * @Description //存储条件删除
     * @Date 2019/6/18 14:30
     * @Param [ids]
     * @return int
     **/
    @Override
    public int removeStore(String ids) {
        List<String> storeIdlist = Convert.toListStrArray(ids);
        int i = tSysStoreMapper.delectStoreByIds(storeIdlist);
        return i;
    }
    /**
     * @Author Noctis
     * @Description //存储条件添加
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
     * @return int
     **/
    @Override
    public int insertStore(TSysStore tSysStore) {
        tSysStore.setId(SnowflakeIdWorker.getUUID());
        tSysStore.setStatus(0);
        tSysStore.setCreatTime(new Date());
        tSysStore.setUpdateTime(new Date());
        int i = tSysStoreMapper.insertSelective(tSysStore);
        return i;
    }
    /**
     * @Author Noctis
     * @Description //存储条件根据ID查找
     * @Date 2019/6/18 14:30
     * @Param [id]
     * @return com.fc.test.model.custom.process.TSysStore
     **/
    @Override
    public TSysStore selectStoreById(String id) {
        TSysStore tSysStore = tSysStoreMapper.selectByPrimaryKey(id);
        if (tSysStore != null) {
            return tSysStore;
        } else {
            return null;
        }
    }
    /**
     * @Author Noctis
     * @Description //存储条件更新
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
     * @return int
     **/
    @Override
    public int updateStoreById(TSysStore tSysStore) {
        int i = tSysStoreMapper.updateByPrimaryKeySelective(tSysStore);
        if (i > 0) {
            return i;
        } else {
            return 0;
        }
    }
    /**
     * @Author Noctis
     * @Description //存储条件状态改变
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
     * @return com.fc.test.model.custom.process.TSysStore
     **/
    @Override
    public TSysStore updateStatus(TSysStore tSysStore) {
        int i = tSysStoreMapper.updateStatusById(tSysStore.getId(), tSysStore.getStatus());
        if (i>0){
            TSysStore store = tSysStoreMapper.selectByPrimaryKey(tSysStore.getId());
            return store;
        }else{
            return null;
        }
    }
}
