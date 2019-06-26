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
/**

* @Description:    产品的server层实现

* @Author:         Noctis

* @CreateDate:     2019/6/18 14:15

* @UpdateUser:     Noctis

* @UpdateDate:     2019/6/18 14:15

* @UpdateRemark:   修改内容

* @Version:        1.0

*/
@Service
public class SysProductServiceImpl implements SysProductService {
    @Autowired
    private TSysProductMapper tSysProductMapper;
    /**
     * @Author Noctis
     * @Description // 产品分页展示
     * @Date 2019/6/5 16:26
     * @Param [pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.fc.aden.model.custom.process.TSysProduct>
     **/
    @Override
    public PageInfo<TSysProduct> list(Tablepar tablepar,String searchTxt){
        List<TSysProduct> tSysProductList = null;
        if (StringUtils.isEmpty(searchTxt)){
            if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            tSysProductList = tSysProductMapper.selectList();
        }else {
            if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
                PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
            }
            searchTxt = "%"+searchTxt+"%";
            tSysProductList = tSysProductMapper.selectListBycNameOreName(searchTxt);
        }
        PageInfo<TSysProduct> pageInfo = new PageInfo<TSysProduct>(tSysProductList);
        return pageInfo;
    }

    /**
     * @Author Noctis
     * @Description //  产品添加保证名字唯一
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     * @return int
     **/
    @Override
    public int checkcNameUnique(TSysProduct tSysProduct){
        return tSysProductMapper.selectProductBycName(tSysProduct.getName());
    }
    /**
     * @Author Noctis
     * @Description //产品添加
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     * @return int
     **/
    @Override
    public int insertProduct(TSysProduct tSysProduct){
        TSysProduct product = new TSysProduct();
        String productId = SnowflakeIdWorker.getUUID();
        product.setId(productId);
        product.setProduct(tSysProduct.getProduct());
        product.setName(tSysProduct.getName());
        product.setEnglishName(tSysProduct.getEnglishName());
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return tSysProductMapper.insertSelective(product);
    }
    
    /**
     * @Author Noctis
     * @Description //产品删除
     * @Date 2019/6/18 14:17
     * @Param [ids]
     * @return int
     **/
    @Override
    public int removeProduct(String ids){
        List<String> list = Convert.toListStrArray(ids);
        int i = tSysProductMapper.deleteProductByIds(list);
        return i;
    }
    /**
     * @Author Noctis
     * @Description //根据ID查找产品
     * @Date 2019/6/18 14:18
     * @Param [id]
     * @return com.fc.test.model.custom.process.TSysProduct
     **/
    @Override
    public TSysProduct selectProductById(String id){
        TSysProduct tSysProduct = tSysProductMapper.selectByPrimaryKey(id);
        if (tSysProduct != null){
            return tSysProduct;
        }else {
            return null;
        }
    }
    /**
     * @Author Noctis
     * @Description //产品更新
     * @Date 2019/6/18 14:18
     * @Param [tSysProduct]
     * @return int
     **/
    @Override
    public int updateProduct(TSysProduct tSysProduct){
        int i = tSysProductMapper.updateByPrimaryKeySelective(tSysProduct);
        if (i>0){
            return i;
        }else {
            return 0;
        }

    }
    /**
     * @Author Noctis
     * @Description //产品状态改变
     * @Date 2019/6/18 14:20
     * @Param [tSysProduct]
     * @return com.fc.test.model.custom.process.TSysProduct
     **/
    @Override
    public TSysProduct updateStatus(TSysProduct tSysProduct) {
        int i = tSysProductMapper.updateStatusById(tSysProduct.getId(), tSysProduct.getStatus());
        if (i>0){
            TSysProduct product = tSysProductMapper.selectByPrimaryKey(tSysProduct.getId());
            return product;
        }else {
            return null;
        }
    }


}
