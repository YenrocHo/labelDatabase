package com.fc.aden.service.impl;


import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;

import com.fc.aden.model.custom.process.ProductStore;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
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
    @Autowired
    private ProductStoreMapper productStoreMapper;
    /**
     * @return com.github.pagehelper.PageInfo<com.fc.test.model.custom.process.TSysStore>
     * @Author Noctis
     * @Description //存储条件分页展示
     * @Date 2019/6/18 14:29
     * @Param [tablepar, searchTxt]
     **/
    @Override
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt,String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysStore> tSysStores = null;
        if (StringUtils.isEmpty(searchTxt)&& StringUtils.isEmpty(itemsCode)) {
            if("2" != tsysUser.getRoles()&&!"2".equals(tsysUser.getRoles())) {
                //如果是项目管理员 根据项目编号搜索所有数据
                tSysStores = tSysStoreMapper.selectListByItems(searchTxt,tsysUser.getItemsCode());
            }else{
                tSysStores = tSysStoreMapper.selectList(itemsCode,searchTxt);
            }
        } else {
            if("2" != tsysUser.getRoles()&&!"2".equals(tsysUser.getRoles())) {
                //如果是项目管理员 根据项目编号搜索所有数据
                tSysStores = tSysStoreMapper.selectListByItems(searchTxt,tsysUser.getItemsCode());
            }else{
                tSysStores = tSysStoreMapper.selectList(itemsCode,searchTxt);
            }
        }
        if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        PageInfo<TSysStore> pageInfo = new PageInfo<TSysStore>(tSysStores);
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
        //删除产品关联的存储条件
        List<ProductStore> productStores = productStoreMapper.findByStoreIdList(ids);
        if (productStores!=null && productStores.size()>0){
            productStoreMapper.deleteStoreId(ids);
        }
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

    @Override
    public TSysStore findByStore(String itemsCode){
       return tSysStoreMapper.findByStore(itemsCode);
    }
    @Override
    public List<TSysStore> findByStoreList(String itemsCode){
       return tSysStoreMapper.findByStoreList(itemsCode);
    }

    @Override
    public  List<TSysStore> queryStoreList(){
       return tSysStoreMapper.queryStoreList();
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

    public int checkStore(String name,String itemsCode){
        List<TSysStore> tSysStores = tSysStoreMapper.checkStoreItems(name,itemsCode);
        return tSysStores.size();
    }
}
