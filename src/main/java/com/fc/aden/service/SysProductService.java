package com.fc.aden.service;

import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.ImportProductDTO;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ProductVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysProductService {

    PageInfo list(Tablepar tablepar,String searchTxt,String itemsCode);

    int checkcNameUnique(String product,String itemsCode);

    int insertProduct(TSysProduct tSysProduct);

    int removeProduct(String ids);

    TSysProduct selectProductById(String id);

    int updateProduct(TSysProduct tSysProduct);

    TSysProduct updateStatus(TSysProduct tSysProduct);

    ImportProductDTO importValid(List<Map<String, String>> dataList);

    List<ProductVO> getSuccessTSysProduct(List<ImportTSysProductDTO> importProductDTOS);

    public void saveSysProduct(List<ProductVO> productVOS);
}
