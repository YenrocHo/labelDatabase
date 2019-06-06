package com.fc.test.mapper.auto.process;

import com.fc.test.model.custom.process.TSysProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysProductMapper {
    int deleteByPrimaryKey(String productId);

    int insert(TSysProduct record);

    int insertSelective(TSysProduct record);

    TSysProduct selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(TSysProduct record);

    int updateByPrimaryKey(TSysProduct record);

    List<TSysProduct> selectList();

    int selectProductBycName(String cName);

    int deleteProductByIds(@Param("productIdList") List<String> productIdList);

}