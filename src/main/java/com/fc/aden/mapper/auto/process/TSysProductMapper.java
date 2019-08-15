package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysProduct;
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

    int selectProductBycName(String product,String itemsCode);

    int deleteByFoodId(String foodName);

    List<TSysProduct> selectByProduct(String product);

    List<TSysProduct> findByProduct(String searchTxt,String itemsCode);

    int deleteProductByIds(@Param("productIdList") List<String> productIdList);

    //根据项目点编号查询
    List<TSysProduct> selectListByItems(String searchTxt,String itemsCode);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    List<TSysProduct> selectProductList(@Param("itemId") String itemId,@Param("keyword") String keyword);
}