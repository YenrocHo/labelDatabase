package com.fc.aden.mapper.auto.process;

import com.fc.aden.model.custom.process.TSysFoodPicture;

public interface TSysFoodPictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysFoodPicture record);

    int insertSelective(TSysFoodPicture record);

    TSysFoodPicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysFoodPicture record);

    int updateByPrimaryKey(TSysFoodPicture record);
}