package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysProduct;
import com.github.pagehelper.PageInfo;

public interface SysProductService {

    PageInfo list(Tablepar tablepar,String searchTxt);

    int checkcNameUnique(TSysProduct tSysProduct);

    int insertProduct(TSysProduct tSysProduct);

    int removeProduct(String ids);

    TSysProduct selectProductById(String id);

    int updateProduct(TSysProduct tSysProduct);

    TSysProduct updateStatus(TSysProduct tSysProduct);
}
