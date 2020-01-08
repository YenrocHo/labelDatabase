package com.fc.aden.mapper.auto.process;


import com.fc.aden.model.auto.TsysFile;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysFoodExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysFoodMapper {

    TSysFood selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insertSelective(TSysFood record);

    int insert(TSysFood record);

    int updateByPrimaryKeySelective(TSysFood record);

    int updateByPrimaryKey(TSysFood record);

    List<TSysFood> selectByExample(TSysFoodExample example);

    int deleteByExample(TSysFoodExample example);

    List<TSysFood> selectFoodList(@Param("itemsCode") String itemsCode,@Param("keyword") String keyword);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    long countByExample(TSysFoodExample example);
    List<TSysFood> countByFood();
    int updateByExampleSelective(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);
    int updateByExample(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);

    //查询该项目点编号下的食品种类编号
    List<TSysFood> findByFoodCodeOrItem(@Param("foodCode")String foodCode,@Param("itemsCode")String itemsCode);
    //根据项目点搜索
    List<TSysFood> findByFoodItems(String food,String itemsCode);
    //查询所有数据·
    List<TSysFood> queryByFood(String food,String itemsCode);
    List<TSysFood> findByItemCode(@Param("itemsCode") String itemsCode);
    //根据食品种类编号查询
    TSysFood findByFoodId(String foodCode,String itemsCode);

}