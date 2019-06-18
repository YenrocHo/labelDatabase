package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysProduct;
import com.github.pagehelper.PageInfo;

public interface SysProductService {

    public PageInfo list(Tablepar tablepar,String searchTxt);

    public int checkcNameUnique(TSysProduct tSysProduct);

    public int insertProduct(TSysProduct tSysProduct);

    public int removeProduct(String ids);

    public TSysProduct selectProductById(String id);

    public int updateProduct(TSysProduct tSysProduct);
}
