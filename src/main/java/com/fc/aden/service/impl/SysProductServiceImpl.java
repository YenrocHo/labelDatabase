package com.fc.aden.service.impl;

import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.ImportProductDTO;
import com.fc.aden.model.custom.process.TSysFood;
import com.fc.aden.model.custom.process.TSysProduct;
import com.fc.aden.service.SysProductService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    @Autowired
    private TSysFoodMapper tSysFoodMapper;

    /**
     * @Author Noctis
     * @Description // 产品分页展示
     * @Date 2019/6/5 16:26
     * @Param [pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.fc.aden.model.custom.process.TSysProduct>
     **/
    @Override
    public PageInfo<ProductVO> list(Tablepar tablepar, String searchTxt,String itemsCode){
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysProduct> tSysProductList = null;
        if (StringUtils.isEmpty(searchTxt) && StringUtils.isEmpty(itemsCode)){
            if("2" != tsysUser.getRoles()&&!"2".equals(tsysUser.getRoles())) {
                //如果是项目管理员 根据项目编号搜索所有数据
                tSysProductList = tSysProductMapper.selectListByItems(searchTxt,tsysUser.getItemsCode());
            }else{
                tSysProductList = tSysProductMapper.findByProduct(searchTxt,itemsCode);
            }
        }else {
            if("2" != tsysUser.getRoles()&&!"2".equals(tsysUser.getRoles())) {
                //如果是项目管理员 根据项目编号搜索所有数据
                tSysProductList = tSysProductMapper.selectListByItems(searchTxt,tsysUser.getItemsCode());
            }else{
                tSysProductList = tSysProductMapper.findByProduct(searchTxt,itemsCode);
            }
        }
        if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<ProductVO> productVOList = new ArrayList<>();
        for(TSysProduct tSysProduct:tSysProductList){
            ProductVO productVO = new ProductVO();
            TSysFood tSysFood = tSysFoodMapper.selectByPrimaryKey(tSysProduct.getFoodName());
            BeanCopierEx.copy(tSysProduct,productVO);
            productVO.setFoodName(tSysFood.getFood());
            productVOList.add(productVO);
        }
        PageInfo<ProductVO> pageInfo = new PageInfo<ProductVO>(productVOList);
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
        String productId = SnowflakeIdWorker.getUUID().toString();
        product.setId(productId);
        product.setProduct(tSysProduct.getProduct());
        product.setName(tSysProduct.getProduct());
        product.setItemsCode(tSysProduct.getItemsCode());
        product.setFoodName(tSysProduct.getFoodName());
        product.setEnglishName(tSysProduct.getEnglishName());
        product.setStatus(1);
        if(tSysProduct.getShelfLife()==null||"".equals(tSysProduct.getShelfLife())){
            product.setShelfLife("看包装");
        }else {
            product.setShelfLife(tSysProduct.getShelfLife()+"小时");
        }
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return tSysProductMapper.insert(product);
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
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 验证文件，数据导入到DTO
     * @param dataList
     * @return
     */
    public ImportProductDTO importValid(List<Map<String, String>> dataList){
        List<String> projectNames = new ArrayList<>();
        ImportProductDTO importProductDTO = new ImportProductDTO();
        List<ImportTSysProductDTO> importTSysProductDTOS = new ArrayList<ImportTSysProductDTO>();
        int errNumber = 0;
        int successNumber = 0;
        for (Map<String, String> row : dataList) {
            String productName = row.get(ImportProductDTO.PRODUCT_NAME);
            String productCode = row.get(ImportProductDTO.PRODUCT_CODE);
            String shelfLife = row.get(ImportProductDTO.SHELF_LIFE);
            String foodName = row.get(ImportProductDTO.FOOD_NAME);
            String items = row.get(ImportProductDTO.ITEM);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysProductDTO importTSysProductDTO = new ImportTSysProductDTO();
            importTSysProductDTO.setCreateTime(df.format(new Date()));
            importTSysProductDTO.setUpdateTime(df.format(new Date()));
            importTSysProductDTO.setStatus(1);
            importTSysProductDTO.setId(UUID.randomUUID().toString());

            List<TSysProduct> productList = tSysProductMapper.selectByProduct(productName);
            if(StringUtils.isEmpty(productName)){
                errorMessage.append("产品名称不能为空；");
                pass = false;
            }else if(projectNames.contains(productName)||productList!=null && productList.size()>0){
                errorMessage.append("产品名称不能重复；");
                pass = false;
            }else {
                importTSysProductDTO.setProduct(productName);
                importTSysProductDTO.setName(productName);
            }
            //判断项目点
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if(StringUtils.isEmpty(items)){
                errorMessage.append("项目点不为空；");
                pass = false;
            }else if(tSysItemsList != null && tSysItemsList.size() > 0){
                importTSysProductDTO.setItemsCode(items);
            }else {
                errorMessage.append("项目点不存在；");
                pass = false;
            }
            //保质期
            if (StringUtils.isEmpty(shelfLife)){
                importTSysProductDTO.setShelfLife("见包装");
            }else {
                importTSysProductDTO.setShelfLife(shelfLife);
            }
            //食品种类 可以为null  如果不为空数据库必须存在
            List<TSysFood> tSysFood = tSysFoodMapper.findByFood(foodName);
            if(tSysFood != null && tSysFood.size() > 0){
                importTSysProductDTO.setFoodName(foodName);
            }else {
                errorMessage.append("食品种类不存在；");
                pass = false;
            }
            if(pass){
                errorMessage.append("成功！");
                successNumber++;
            }else {
                errNumber++;
            }
            importTSysProductDTO.setProductCode(productCode);
            importTSysProductDTO.setPass(pass);
            importTSysProductDTO.setMessages(errorMessage.toString());
            importTSysProductDTOS.add(importTSysProductDTO);
        }
        importProductDTO.setErrorNumber(errNumber);
        importProductDTO.setSuccessNumber(successNumber);
        importProductDTO.settSysProductDTOS(importTSysProductDTOS);
        return importProductDTO;
    }

    public List<ProductVO> getSuccessTSysProduct(List<ImportTSysProductDTO> importProductDTOS){
        if(importProductDTOS ==null) return new ArrayList<>();
        List<ProductVO> tsysUsers = new ArrayList<>();
        for (ImportTSysProductDTO importTSysProductDTO : importProductDTOS) {
            if(importTSysProductDTO.getPass()){
                tsysUsers.add(loadByDTO(importTSysProductDTO));
            }
        }
        return tsysUsers;
    }

   public ProductVO loadByDTO(ImportTSysProductDTO importTSysProductDTO){
       ProductVO productVO = new ProductVO();
       BeanCopierEx.copy(importTSysProductDTO,productVO);
       return productVO;
   }

    /**
     * 导入文件到数据库
     * @param productVOS
     */
    public void saveSysProduct(List<ProductVO> productVOS){
        for (ProductVO productVO : productVOS) {
            TSysFood tSysFood = tSysFoodMapper.findByFoodId(productVO.getFoodName());
            TSysProduct tSysProduct = new TSysProduct();
            String id= tSysFood.getId();
            productVO.setFoodName(id);
            tSysProduct.setUpdateTime(new Date());
            tSysProduct.setCreateTime(new Date());
            BeanCopierEx.copy(productVO,tSysProduct);
            tSysProductMapper.insertSelective(tSysProduct);
        }
    }

}
