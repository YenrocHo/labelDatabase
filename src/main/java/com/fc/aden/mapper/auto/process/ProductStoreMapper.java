package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.custom.process.ProductStore;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStoreMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective( ProductStore record);

    ProductStore selectByPrimaryKey(String id);

    int insert(ProductStore record);

    int updateByPrimaryKeySelective(ProductStore record);

    int updateByPrimaryKey(ProductStore record);

    List<ProductStore> findByProductIdList(String productId);
    List<ProductStore> findByStoreIdList(String storeId);
    int deleteStoreId(String storeId);
    int deleteProductId(String productId);

}