package com.fc.aden.service.impl;

import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.service.SysProductService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ProductFoodStoreVO;
import com.fc.aden.vo.ProductVO;
import com.fc.aden.vo.importDto.ImportProductDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 产品的server层实现
 * @Author: Noctis
 * @CreateDate: 2019/6/18 14:15
 * @UpdateUser: Noctis
 * @UpdateDate: 2019/6/18 14:15
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class SysProductServiceImpl implements SysProductService {
    @Autowired
    private TSysProductMapper tSysProductMapper;
    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Autowired
    private ProductStoreMapper productStoreMapper;

    /**
     * @return com.github.pagehelper.PageInfo<com.fc.aden.model.custom.process.TSysProduct>
     * @Author Noctis
     * @Description // 产品分页展示
     * @Date 2019/6/5 16:26
     * @Param [pageNum, pageSize]
     **/
    @Override
    public PageInfo<TSysProduct> list(Tablepar tablepar, String searchTxt, String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysProduct> tSysProductList = null;
        if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            //如果是项目管理员 根据项目编号搜索所有数据
            tSysProductList = tSysProductMapper.selectListByItems(searchTxt, tsysUser.getItemsCode());
        } else {
            tSysProductList = tSysProductMapper.findByProduct(searchTxt, itemsCode);
        }
        PageInfo<TSysProduct> pageInfo = new PageInfo<TSysProduct>(tSysProductList);
        return pageInfo;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //  产品添加保证名字唯一
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     **/
    @Override
    public ProductFoodStoreVO checkcNameUnique(String product, String itemsCode, String foodName) {
        ProductFoodStoreVO productFoodStoreVO = new ProductFoodStoreVO();
        int productList = tSysProductMapper.selectProductBycName(product, itemsCode);
        //如果是超级管理员判断选择的食品种类是否是该项目点下的
        List<TSysFood> foodList = tSysFoodMapper.findByFoodCodeOrItem(foodName, itemsCode);
        productFoodStoreVO.setFoodName(foodList.size());
        productFoodStoreVO.setProduct(productList);
        return productFoodStoreVO;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品添加
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     **/
    @Transactional
    public int insertProduct(TSysProduct tSysProduct) {
        return tSysProductMapper.insertSelective(tSysProduct);
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品删除
     * @Date 2019/6/18 14:17
     * @Param [ids]
     **/
    @Override
    public int removeProduct(String ids) {
        List<String> list = Convert.toListStrArray(ids);
        int i = tSysProductMapper.deleteProductByIds(list);
        //删除产品关联的存储条件
        List<ProductStore> productStores = productStoreMapper.findByProductIdList(ids);
        if (productStores != null && productStores.size() > 0) {
            productStoreMapper.deleteProductId(ids);
        }
        return i;
    }

    /**
     * @return com.fc.test.model.custom.process.TSysProduct
     * @Author Noctis
     * @Description //根据ID查找产品
     * @Date 2019/6/18 14:18
     * @Param [id]
     **/
    @Override
    public TSysProduct selectProductById(String id) {
        TSysProduct tSysProduct = tSysProductMapper.selectByPrimaryKey(id);
        if (tSysProduct != null) {
            return tSysProduct;
        } else {
            return null;
        }
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品更新
     * @Date 2019/6/18 14:18
     * @Param [tSysProduct]
     **/
    @Override
    public int updateProduct(TSysProduct tSysProduct) {
        int i = tSysProductMapper.updateByPrimaryKeySelective(tSysProduct);
        if (i > 0) {
            return i;
        } else {
            return 0;
        }

    }

    /**
     * @return com.fc.test.model.custom.process.TSysProduct
     * @Author Noctis
     * @Description //产品状态改变
     * @Date 2019/6/18 14:20
     * @Param [tSysProduct]
     **/
    @Override
    public TSysProduct updateStatus(TSysProduct tSysProduct) {
        int i = tSysProductMapper.updateStatusById(tSysProduct.getId(), tSysProduct.getStatus());
        if (i > 0) {
            TSysProduct product = tSysProductMapper.selectByPrimaryKey(tSysProduct.getId());
            return product;
        } else {
            return null;
        }
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 验证文件，数据导入到DTO
     *
     * @param dataList
     * @return
     */
    public ImportProductDTO importValid(List<Map<String, String>> dataList) {
        List<String> projectNames = new ArrayList<>();
        ImportProductDTO importProductDTO = new ImportProductDTO();
        List<ImportTSysProductDTO> importTSysProductDTOS = new ArrayList<ImportTSysProductDTO>();
        int errNumber = 0;
        int successNumber = 0;
        for (int i = 1; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            String productName = row.get(ImportProductDTO.PRODUCT_NAME);
            String productCode = row.get(ImportProductDTO.PRODUCT_CODE);
            String foodName = row.get(ImportProductDTO.FOOD_NAME);
            String items = row.get(ImportProductDTO.ITEM);
            String fridge = row.get(ImportProductDTO.FRIDGE); //冰箱
            String frozen = row.get(ImportProductDTO.FROZEN);  //冰柜
            String temperature = row.get(ImportProductDTO.ROOM_TEMPERATURE); //室温
            String above = row.get(ImportProductDTO.ABOVE); //高于65°C

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysProductDTO importTSysProductDTO = new ImportTSysProductDTO();
            importTSysProductDTO.setCreateTime(df.format(new Date()));
            importTSysProductDTO.setUpdateTime(df.format(new Date()));
            importTSysProductDTO.setStatus(1);
            importTSysProductDTO.setId(UUID.randomUUID().toString());

            List<TSysProduct> productList = tSysProductMapper.selectByProduct(productName);
            if (StringUtils.isEmpty(productName)) {
                errorMessage.append("产品名称不能为空；");
                pass = false;
            } else if (projectNames.contains(productName) || productList != null && productList.size() > 0) {
                errorMessage.append("产品名称不能重复；");
                pass = false;
            } else {
                importTSysProductDTO.setProduct(productName);
                importTSysProductDTO.setName(productName);
            }
            //判断项目点
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if (StringUtils.isEmpty(items)) {
                errorMessage.append("项目点编号不为空；");
                pass = false;
            } else if (tSysItemsList != null && tSysItemsList.size() > 0) {
                importTSysProductDTO.setItemsCode(items);
            } else if (projectNames.contains(productName)) {
                errorMessage.append("项目点编号重复；");
                pass = false;
            } else {
                errorMessage.append("项目点编号不存在；");
                pass = false;
            }
            //
            List<TSysFood> tSysFood = tSysFoodMapper.findByFoodCodeOrItemsCode(foodName, items);
            if (tSysFood != null && tSysFood.size() > 0) {
                importTSysProductDTO.setFoodName(foodName);
            } else {
                errorMessage.append("食品种类不存在；");
                pass = false;
            }

            TSysStore tSysStore = tSysStoreMapper.selectByItemsAndName(items, "冰柜");
            if (frozen != null && !frozen.equals("")) {
                importTSysProductDTO.setFrozenID(tSysStore.getId());
                importTSysProductDTO.setFrozen(frozen + "小时");
            } else {
                importTSysProductDTO.setFrozenID(tSysStore.getId());
                importTSysProductDTO.setFrozen("见包装");
            }

            TSysStore tSysStoreF = tSysStoreMapper.selectByItemsAndNameF(items, "冰箱");
            if (fridge != null && !fridge.equals("")) {
                importTSysProductDTO.setFridgeID(tSysStoreF.getId());
                importTSysProductDTO.setFridge(fridge + "小时");
            } else {
                importTSysProductDTO.setFridgeID(tSysStoreF.getId());
                importTSysProductDTO.setFridge("见包装");
            }
            TSysStore tSysStoreT = tSysStoreMapper.selectByItemsAndNameT(items, "室温");
            if (temperature != null && !temperature.equals("")) {
                importTSysProductDTO.setTemperatureID(tSysStoreT.getId());
                importTSysProductDTO.setTemperature(temperature + "小时");
            } else {
                importTSysProductDTO.setTemperatureID(tSysStoreT.getId());
                importTSysProductDTO.setTemperature("见包装");
            }
            TSysStore tSysStoreA = tSysStoreMapper.selectByItemsAndNameA(items, "高于65°C");
            if (above != null && !above.equals("")) {
                importTSysProductDTO.setAboveID(tSysStoreA.getId());
                importTSysProductDTO.setAbove(above + "小时");
            } else {
                importTSysProductDTO.setAboveID(tSysStoreA.getId());
                importTSysProductDTO.setAbove("见包装");
            }
            if (pass) {
                errorMessage.append("成功！");
                successNumber++;
            } else {
                errNumber++;
            }
            importTSysProductDTO.setProductCode(productCode);
            importTSysProductDTO.setPass(pass);
            importTSysProductDTO.setMessages(errorMessage.toString());
            importTSysProductDTOS.add(importTSysProductDTO);
        }
        importProductDTO.setErrorNumber(errNumber);
        importProductDTO.setSuccessNumber(successNumber);
        importProductDTO.settSysProductDTOS(importTSysProductDTOS);
        return importProductDTO;
    }

    public List<ProductVO> getSuccessTSysProduct(List<ImportTSysProductDTO> importProductDTOS) {
        if (importProductDTOS == null) return new ArrayList<>();
        List<ProductVO> tsysUsers = new ArrayList<>();
        for (ImportTSysProductDTO importTSysProductDTO : importProductDTOS) {
            if (importTSysProductDTO.getPass()) {
                tsysUsers.add(loadByDTO(importTSysProductDTO));
            }
        }
        return tsysUsers;
    }

    public ProductVO loadByDTO(ImportTSysProductDTO importTSysProductDTO) {
        ProductVO productVO = new ProductVO();
        BeanCopierEx.copy(importTSysProductDTO, productVO);
        return productVO;
    }

    /**
     * 导入文件到数据库
     *
     * @param productVOS
     */
    public void saveSysProduct(List<ProductVO> productVOS) {
        for (ProductVO productVO : productVOS) {
            //根据编号查询
            TSysFood tSysFood = tSysFoodMapper.findByFoodId(productVO.getFoodName());
            TSysProduct tSysProduct = new TSysProduct();
            List<ProductStore> productStoreList = new ArrayList<>();

            //冰柜
            ProductStore frozenStore = new ProductStore();
            frozenStore.setId(UUID.randomUUID().toString());
            frozenStore.setProductId(productVO.getId());
            frozenStore.setStoreId(productVO.getFrozenID());
            frozenStore.setShelfLife(productVO.getFrozen());
            frozenStore.setCreateTime(new Date());
            frozenStore.setUpdateTime(new Date());
            productStoreList.add(frozenStore);

            //冰箱
            ProductStore fridgeStore = new ProductStore();
            fridgeStore.setId(UUID.randomUUID().toString());
            fridgeStore.setProductId(productVO.getId());
            fridgeStore.setStoreId(productVO.getFridgeID());
            fridgeStore.setShelfLife(productVO.getFridge());
            fridgeStore.setCreateTime(new Date());
            fridgeStore.setUpdateTime(new Date());
            productStoreList.add(fridgeStore);

            //室温
            ProductStore temperatureStore = new ProductStore();
            temperatureStore.setId(UUID.randomUUID().toString());
            temperatureStore.setProductId(productVO.getId());
            temperatureStore.setStoreId(productVO.getTemperatureID());
            temperatureStore.setShelfLife(productVO.getTemperature());
            temperatureStore.setCreateTime(new Date());
            temperatureStore.setUpdateTime(new Date());
            productStoreList.add(temperatureStore);

            //高于65°
            ProductStore aboveStore = new ProductStore();
            aboveStore.setId(UUID.randomUUID().toString());
            aboveStore.setProductId(productVO.getId());
            aboveStore.setStoreId(productVO.getAboveID());
            aboveStore.setShelfLife(productVO.getAbove());
            aboveStore.setCreateTime(new Date());
            aboveStore.setUpdateTime(new Date());
            productStoreList.add(aboveStore);

            productStoreMapper.insertBatch(productStoreList);

            String id = tSysFood.getFoodCode();
            productVO.setFoodName(id);
            tSysProduct.setUpdateTime(new Date());
            tSysProduct.setCreateTime(new Date());
            BeanCopierEx.copy(productVO, tSysProduct);
//            productStoreMapper.insertSelective(productStore);
            tSysProductMapper.insertSelective(tSysProduct);
        }
    }

    @Autowired
    private TSysStoreMapper tSysStoreMapper;

    public List<ProductVO> findByProductAndStore(String id, String items) {
        List<ProductVO> productVOList = new ArrayList<>();
        List<TSysStore> sysStores = tSysStoreMapper.findByStoreList(items);//所有存储条件
        List<ProductStore> productStores = productStoreMapper.findByProductIdList(id);//我自己选中的存储条件
        for (ProductStore productStore : productStores) {
            TSysStore sysStore = tSysStoreMapper.selectByPrimaryKey(productStore.getStoreId());
            ProductVO productVO = new ProductVO();
            productVO.setNoStoreId(productStore.getStoreId());
            productVO.setStoreName(sysStore.getName());
            productVO.setShelfLife(productStore.getShelfLife());
            productVOList.add(productVO);
        }
        return productVOList;
    }

}
