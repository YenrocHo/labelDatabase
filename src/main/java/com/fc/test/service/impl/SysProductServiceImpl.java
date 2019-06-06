package com.fc.test.service.impl;

import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.process.TSysProductMapper;
import com.fc.test.model.auto.TsysUser;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.process.TSysProduct;
import com.fc.test.service.SysProductService;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.Page;
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
     * @return com.github.pagehelper.PageInfo<com.fc.test.model.custom.process.TSysProduct>
     **/
    @Override
    public PageInfo<TSysProduct> list(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<TSysProduct> tSysProductList = tSysProductMapper.selectList();
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

    public TSysProduct selectProductById(String id){
        TSysProduct tSysProduct = tSysProductMapper.selectByPrimaryKey(id);
        if (tSysProduct != null){
            return tSysProduct;
        }else {
            return null;
        }
    }
}
