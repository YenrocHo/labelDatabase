package com.fc.aden.service.impl;


import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.custom.Tablepar;

import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.StoreVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysStoreServiceImpl implements SysStoreService {

    @Autowired
    private TSysStoreMapper tSysStoreMapper;

    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    /**
     * @return com.github.pagehelper.PageInfo<com.fc.test.model.custom.process.TSysStore>
     * @Author Noctis
     * @Description //存储条件分页展示
     * @Date 2019/6/18 14:29
     * @Param [tablepar, searchTxt]
     **/
    @Override
    public PageInfo<StoreVO> list(Tablepar tablepar, String searchTxt) {
        List<TSysStore> tSysStores = null;
        if (StringUtils.isEmpty(searchTxt)) {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysStores = tSysStoreMapper.selectList();
        } else {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            searchTxt = "%" + searchTxt + "%";
            tSysStores = tSysStoreMapper.selectListBycQuery(searchTxt);
        }
        List<StoreVO> storeVOS = new ArrayList<>();
        for(TSysStore tSysStore:tSysStores){
            StoreVO storeVO = new StoreVO();
            TSysItems tSysItems = tSysItemsMapper.selectByPrimaryKey(tSysStore.getItemId());
            storeVO.setItem(tSysItems.getItems());
            BeanCopierEx.copy(tSysStore, storeVO);
            storeVOS.add(storeVO);
        }
        PageInfo<StoreVO> pageInfo = new PageInfo<StoreVO>(storeVOS);
        return pageInfo;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //存储条件删除
     * @Date 2019/6/18 14:30
     * @Param [ids]
     **/
    @Override
    public int removeStore(String ids) {
        List<String> storeIdlist = Convert.toListStrArray(ids);
        int i = tSysStoreMapper.delectStoreByIds(storeIdlist);
        return i;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //存储条件添加
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
     **/
    @Override
    public int insertStore(TSysStore tSysStore) {
        tSysStore.setId(SnowflakeIdWorker.getUUID().toString());
        tSysStore.setCreatTime(new Date());
        tSysStore.setUpdateTime(new Date());
        int i = tSysStoreMapper.insertSelective(tSysStore);
        return i;
    }

    /**
     * @return com.fc.test.model.custom.process.TSysStore
     * @Author Noctis
     * @Description //存储条件根据ID查找
     * @Date 2019/6/18 14:30
     * @Param [id]
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
     * @return int
     * @Author Noctis
     * @Description //存储条件更新
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
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
     * @return com.fc.test.model.custom.process.TSysStore
     * @Author Noctis
     * @Description //存储条件状态改变
     * @Date 2019/6/18 14:30
     * @Param [tSysStore]
     **/
    @Override
    public TSysStore updateStatus(TSysStore tSysStore) {
        int i = tSysStoreMapper.updateStatusById(tSysStore.getId(), tSysStore.getStatus());
        if (i > 0) {
            TSysStore store = tSysStoreMapper.selectByPrimaryKey(tSysStore.getId());
            return store;
        } else {
            return null;
        }
    }
}
