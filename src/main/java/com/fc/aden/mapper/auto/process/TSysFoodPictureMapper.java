package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysFoodPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysFoodPictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysFoodPicture record);

    int insertSelective(TSysFoodPicture record);

    TSysFoodPicture selectByPrimaryKey(String id);
    List<TSysFoodPicture> selectByFoodId(String foodId);

    int updateByPrimaryKeySelective(TSysFoodPicture record);

    int updateByPrimaryKey(TSysFoodPicture record);

    int deleteByFoodIds(String foodId);
}