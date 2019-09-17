package com.fc.aden.service;

import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.custom.process.ProductStore;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.vo.ProductStoreDTO;
import com.fc.aden.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStoreService {
    @Autowired
    private ProductStoreMapper productStoreMapper;
    @Autowired
    private TSysStoreMapper tSysStoreMapper;

    /**
     * 编辑产品 获取产品id
     * @param productId
     * @return
     */
    public List<ProductVO> findByProductIdList(String productId){
        List<ProductStore> productStores =  productStoreMapper.findByProductIdList(productId);
        List<ProductVO> productStoreDTOS = new ArrayList<>();
        for (ProductStore productStore:productStores){
            ProductVO productVO = new ProductVO();
            productVO.setShelfLife(productStore.getShelfLife());
            TSysStore tSysStore = tSysStoreMapper.selectByPrimaryKey(productStore.getStoreId());
            productVO.setStoreName(tSysStore.getName());
            productVO.setStoreId(productStore.getStoreId());
            productStoreDTOS.add(productVO);
        }
        return productStoreDTOS;
    }

    public int insertSelective(ProductStore productStore){
        return productStoreMapper.insertSelective(productStore);
    }

    public int updateByPrimaryKey(ProductStore productStore){
        return productStoreMapper.updateByPrimaryKey(productStore);
    }

    public ProductStore findByStoreId(String productId,String storeId){
        return productStoreMapper.findByStoreId(productId,storeId);
    }
}
