package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.model.po.ProductPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysProductMapper {
    int deleteByPrimaryKey(String productId);

    int insertSelective(TSysProduct record);

    TSysProduct selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(TSysProduct record);

    int updateByPrimaryKey(TSysProduct record);

    List<TSysProduct> selectList();

    int selectProductBycName(String product,String itemsCode);

    int deleteByFoodId(String foodName);

    int selectItemsCode(String itemsCode);

    List<TSysProduct> selectByProduct(String product);

    List<TSysProduct> findByProduct(String searchTxt,String itemsCode,String foodName);

    int deleteProductByIds(@Param("productIdList") List<String> productIdList);

    //根据项目点编号查询
    List<TSysProduct> selectListByItems(String searchTxt,String itemsCode,String foodName);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    List<TSysProduct> selectProductList(@Param("itemsCode") String itemsCode,@Param("keyword") String keyword,@Param("foodCode")String foodCode);

    List<ProductPo> selectAllProductList(@Param("itemsCode") String itemsCode, @Param("keyword") String keyword, @Param("foodCode")String foodCode);
}