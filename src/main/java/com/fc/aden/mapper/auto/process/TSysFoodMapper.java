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

    List<TSysFood> selectFoodList(@Param("itemId") String itemId,@Param("keyword") String keyword);

    int updateStatusById(@Param("id") String id, @Param("status") Integer status);

    long countByExample(TSysFoodExample example);
    int updateByExampleSelective(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);
    int updateByExample(@Param("record") TSysFood record, @Param("example") TSysFoodExample example);
    //根据食品名称查询
    List<TSysFood> findByFood(String food);
    //根据项目点搜索
    List<TSysFood> findByFoodItems(String food,String itemsCode);
    //查询所有数据
    List<TSysFood> queryByFood(String food,String itemsCode);
    //根据食品种类编号查询
    List<TSysFood> findByFoodCode(String foodCode);
    //根据食品种类id查询
    TSysFood findByFoodId(String food);

}