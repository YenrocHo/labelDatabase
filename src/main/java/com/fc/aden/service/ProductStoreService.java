package com.fc.aden.service;

import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.model.custom.process.ProductStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStoreService {
    @Autowired
    private ProductStoreMapper productStoreMapper;

    public List<ProductStore> findByProductIdList(String productId){
        return productStoreMapper.findByProductIdList(productId);
    }
}
