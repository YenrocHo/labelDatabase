package com.fc.aden.service.impl;


import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;

import com.fc.aden.model.custom.process.ProductStore;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.service.SysStoreService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.ImportTSysStoreDTO;
import com.fc.aden.vo.StoreVO;
import com.fc.aden.vo.importDto.ImportStoreDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public PageInfo<TSysStore> list(Tablepar tablepar, String searchTxt, String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();

        List<TSysStore> tSysStores = null;
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            //如果是项目管理员 根据项目编号搜索所有数据
            tSysStores = tSysStoreMapper.selectListByItems(searchTxt, tsysUser.getItemsCode());
        } else {
            if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysStores = tSysStoreMapper.selectList(itemsCode, searchTxt);
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
        if (productStores != null && productStores.size() > 0) {
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
    public TSysStore findByStore(String itemsCode) {
        return tSysStoreMapper.findByStore(itemsCode);
    }

    @Override
    public List<TSysStore> findByStoreList(String itemsCode) {
        return tSysStoreMapper.findByStoreList(itemsCode);
    }

    @Override
    public List<TSysStore> queryStoreList() {
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

    public int checkStore(String name, String itemsCode) {
        List<TSysStore> tSysStores = tSysStoreMapper.checkStoreItems(name, itemsCode);
        return tSysStores.size();
    }

    ////数据导入
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 验证文件，数据导入到DTO
     *
     * @param dataList
     * @return
     */
    public ImportStoreDTO importValid(List<Map<String, String>> dataList) {
        List<String> projectNames = new ArrayList<>();
        ImportStoreDTO importStoreDTO = new ImportStoreDTO();
        List<ImportTSysStoreDTO> importTSysStoreDTOS = new ArrayList<ImportTSysStoreDTO>();
        int errNumber = 0;
        int successNumber = 0;
        for (int i = 1; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            String itemsCode = row.get(ImportStoreDTO.ITEMS_CODE);
            String storeName = row.get(ImportStoreDTO.STORE_NAME);
            String storeDepict = row.get(ImportStoreDTO.STORE_DEPICT);
            String storeEnglish = row.get(ImportStoreDTO.STORE_ENGLISH);
            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysStoreDTO importTSysStoreDTO = new ImportTSysStoreDTO();
            importTSysStoreDTO.setCreatTime(df.format(new Date()));
            importTSysStoreDTO.setUpdateTime(df.format(new Date()));
            importTSysStoreDTO.setStatus(1);
            importTSysStoreDTO.setId(UUID.randomUUID().toString());
            importTSysStoreDTO.setEnglishName(storeEnglish);
            importTSysStoreDTO.setStore(storeDepict);

            List<TSysStore> storeList = tSysStoreMapper.checkStoreItems(storeName, itemsCode);
            if (StringUtils.isEmpty(storeName)) {
                errorMessage.append("储存条件不能为空；");
                pass = false;
            } else if (projectNames.contains(storeName) || storeList != null && storeList.size() > 0) {
                errorMessage.append("储存条件不能重复；");
                pass = false;
            } else {
                importTSysStoreDTO.setName(storeName);
            }
            //判断项目点
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(itemsCode);
            if (StringUtils.isEmpty(itemsCode)) {
                errorMessage.append("项目点编号不为空；");
                pass = false;
            } else if (tSysItemsList != null && tSysItemsList.size() > 0) {
                importTSysStoreDTO.setItemsCode(itemsCode);
            } else {
                errorMessage.append("项目点编号不存在；");
                pass = false;
            }

            if (pass) {
                errorMessage.append("成功！");
                successNumber++;
            } else {
                errNumber++;
            }
            importTSysStoreDTO.setPass(pass);
            importTSysStoreDTO.setMessages(errorMessage.toString());
            importTSysStoreDTOS.add(importTSysStoreDTO);
        }
        importStoreDTO.setErrorNumber(errNumber);
        importStoreDTO.setSuccessNumber(successNumber);
        importStoreDTO.setImportTSysStoreDTOS(importTSysStoreDTOS);
        return importStoreDTO;
    }

    public List<StoreVO> getSuccessTSysStore(List<ImportTSysStoreDTO> importTSysStoreDTOS) {
        if (importTSysStoreDTOS == null) return new ArrayList<>();
        List<StoreVO> tsysUsers = new ArrayList<>();
        for (ImportTSysStoreDTO importTSysStoreDTO : importTSysStoreDTOS) {
            if (importTSysStoreDTO.getPass()) {
                tsysUsers.add(loadByDTO(importTSysStoreDTO));
            }
        }
        return tsysUsers;
    }


    public StoreVO loadByDTO(ImportTSysStoreDTO importTSysStoreDTO) {
        StoreVO storeVO = new StoreVO();
        BeanCopierEx.copy(importTSysStoreDTO, storeVO);
        return storeVO;
    }

    /**
     * 导入文件到数据库
     *
     * @param storeVOS
     */
    public void saveSysStore(List<StoreVO> storeVOS) {
        for (StoreVO storeVO : storeVOS) {
            TSysStore tSysStore = new TSysStore();
            tSysStore.setId(storeVO.getId());
            tSysStore.setUpdateTime(new Date());
            tSysStore.setCreatTime(new Date());
            tSysStore.setItemsCode(storeVO.getItemsCode());
            tSysStore.setEnglishName(storeVO.getEnglishName());
            tSysStore.setName(storeVO.getName());
            tSysStore.setStore(storeVO.getStore());
            System.out.println("store:::::" + storeVO.getStore());
            tSysStoreMapper.insertSelective(tSysStore);
        }
    }
}
