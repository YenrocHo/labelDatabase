package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.mapper.auto.process.TSysStageMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TSysItemsExample;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.vo.ProductFoodStoreVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysItemsService implements BaseService<TSysItems, TSysItemsExample> {
    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    @Autowired
    private TSysProductMapper tSysProductMapper;
    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Autowired
    private TsysUserMapper tsysUserMapper;
    @Autowired
    private TSysStoreMapper sysStoreMapper;
    @Autowired
    private TSysStageMapper sysStageMapper;

    @Override
    public int deleteByPrimaryKey(String ids) {
        return tSysItemsMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 新增项目点
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(TSysItems record) {
        //添加主键id
        String id = SnowflakeIdWorker.getUUID();
        record.setId(id);
        record.setCreateTime(new Date());//保存创建时间
        record.setUpdateTime(new Date());//保存更新时间
        record.setStatus(1);
        return tSysItemsMapper.insertSelective(record);
    }

    @Override
    public TSysItems selectByPrimaryKey(String id) {
        return tSysItemsMapper.selectByPrimaryKey(id);
    }

    public int selectById(String id){
        TSysItems items = tSysItemsMapper.selectByPrimaryKey(id);
        Integer i = items.getStatus();
        if (i == 1){//1为运行中
            items.setStatus(0);
        }else{//0为停运中
            items.setStatus(1);
        }
        return tSysItemsMapper.updateByPrimaryKeySelective(items);//修改数据
    }

    public ProductFoodStoreVO findByItems(String itemsId) {
        TSysItems items = tSysItemsMapper.selectByPrimaryKey(itemsId);
        System.out.println("itemsCode===" + items.getItemsCode());
        ProductFoodStoreVO productFoodStoreVO = new ProductFoodStoreVO();
        int productList = tSysProductMapper.selectItemsCode(items.getItemsCode());
        //如果是超级管理员判断选择的食品种类是否是该项目点下的
        List<TSysFood> foodList = tSysFoodMapper.findByItemCode(items.getItemsCode());
        List<TsysUser> users = tsysUserMapper.selectAllUser(items.getItemsCode());
        List<TSysStore> stores = sysStoreMapper.findByStoreList(items.getItemsCode());
        List<TSysStage> sysStages = sysStageMapper.selectItemsCode(items.getItemsCode());
        productFoodStoreVO.setFoodName(foodList.size());
        productFoodStoreVO.setProduct(productList);
        productFoodStoreVO.setStore(stores.size());
        productFoodStoreVO.setStage(sysStages.size());
        productFoodStoreVO.setUser(users.size());
        return productFoodStoreVO;
    }

    @Override
    public int updateByPrimaryKeySelective(TSysItems record) {
        record.setUpdateTime(new Date());//更新时间
        return tSysItemsMapper.updateByPrimaryKeySelective(record);//修改数据
    }

    @Override
    public int updateByExampleSelective(@Param("record") TSysItems record, @Param("example") TSysItemsExample example) {
        return tSysItemsMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") TSysItems record, @Param("example") TSysItemsExample example) {
        return tSysItemsMapper.updateByExample(record, example);
    }

    @Override
    public List<TSysItems> selectByExample(TSysItemsExample example) {
        return tSysItemsMapper.selectByExample(example);
    }

    @Override
    public long countByExample(TSysItemsExample example) {
        return tSysItemsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TSysItemsExample example) {
        return tSysItemsMapper.deleteByExample(example);
    }

    /**
     * 项目点管理列表
     *
     * @param tablepar
     * @param itemsCode
     * @return
     */
    public PageInfo<TSysItems> sysIteamsList(Tablepar tablepar, String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysItems> list = null;
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            list = tSysItemsMapper.selectByItems(tsysUser.getItemsCode());
        } else {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            list = tSysItemsMapper.selectByItemCode(itemsCode);
        }
        PageInfo<TSysItems> pageInfo = new PageInfo<TSysItems>(list);
        return pageInfo;
    }

    /**
     * 检查阶段名称是否重名
     *
     * @param tSysItems
     * @return
     */
    public int checkIteamsUnique(TSysItems tSysItems) {
        TSysItemsExample example = new TSysItemsExample();
        example.createCriteria().andItemsEqualTo(tSysItems.getItemsCode());
        List<TSysItems> list = selectByExample(example);
        return list.size();
    }

    /**
     * 获取项目点
     *
     * @return
     */
    public List<TSysItems> queryItems() {
        TSysItemsExample example = new TSysItemsExample();
        return selectByExample(example);
    }
}
