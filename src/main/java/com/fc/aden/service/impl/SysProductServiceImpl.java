package com.fc.aden.service.impl;

import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.service.SysProductService;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SysProductServiceImpl implements SysProductService {
    @Autowired
    private TSysProductMapper tSysProductMapper;
    /**
     * @Author Noctis
     * @Description //TODO 产品展示
     * @Date 2019/6/5 16:26
     * @Param [pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.fc.aden.model.custom.process.TSysProduct>
     **/
    @Override
    public PageInfo<TSysProduct> list(Tablepar tablepar,String searchTxt){
        List<TSysProduct> tSysProductList = null;
        if (StringUtils.isEmpty(searchTxt)){
            PageHelper.startPage(tablepar.getPageNum(),tablepar.getPageSize());
            tSysProductList = tSysProductMapper.selectList();
        }else {
            PageHelper.startPage(tablepar.getPageNum(),tablepar.getPageSize());
            searchTxt = "%"+searchTxt+"%";
            tSysProductList = tSysProductMapper.selectListBycNameOreName(searchTxt);
        }
        PageInfo<TSysProduct> pageInfo = new PageInfo<TSysProduct>(tSysProductList);
        return pageInfo;
    }

    /**
     * @Author Noctis
     * @Description //TODO
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     * @return int
     **/
    @Override
    public int checkcNameUnique(TSysProduct tSysProduct){
        return tSysProductMapper.selectProductBycName(tSysProduct.getcName());
    }
    /**
     * @Author Noctis
     * @Description //TODO
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     * @return int
     **/
    @Override
    public int insertProduct(TSysProduct tSysProduct){
        TSysProduct product = new TSysProduct();
        String productId = SnowflakeIdWorker.getUUID();
        product.setProductId(productId);
        product.setName(tSysProduct.getName());
        product.setcName(tSysProduct.getcName());
        product.seteName(tSysProduct.geteName());
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return tSysProductMapper.insertSelective(product);
    }
    
    @Override
    public int removeProduct(String ids){
        List<String> list = Convert.toListStrArray(ids);
        int i = tSysProductMapper.deleteProductByIds(list);
        return i;
    }

    @Override
    public TSysProduct selectProductById(String id){
        TSysProduct tSysProduct = tSysProductMapper.selectByPrimaryKey(id);
        if (tSysProduct != null){
            return tSysProduct;
        }else {
            return null;
        }
    }

    @Override
    public int updateProduct(TSysProduct tSysProduct){
        int i = tSysProductMapper.updateByPrimaryKeySelective(tSysProduct);
        if (i>0){
            return i;
        }else {
            return 0;
        }

    }
}
