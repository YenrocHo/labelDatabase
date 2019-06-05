package com.fc.test.mapper.auto.process;

import com.fc.test.model.custom.process.TSysFoodPicture;

public interface TSysFoodPictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysFoodPicture record);

    int insertSelective(TSysFoodPicture record);

    TSysFoodPicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysFoodPicture record);

    int updateByPrimaryKey(TSysFoodPicture record);
}