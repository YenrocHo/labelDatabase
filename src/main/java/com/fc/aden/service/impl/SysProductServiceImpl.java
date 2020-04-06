package com.fc.aden.service.impl;

import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.ProductStoreMapper;
import com.fc.aden.mapper.auto.process.TSysFoodMapper;
import com.fc.aden.mapper.auto.process.TSysProductMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.service.SysProductService;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.ExcelUtils;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.ImportTSysProductDTO;
import com.fc.aden.vo.ProductFoodStoreVO;
import com.fc.aden.vo.ProductVO;
import com.fc.aden.vo.StoreVO;
import com.fc.aden.vo.importDto.ImportProductDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 产品的server层实现
 * @Author: Noctis
 * @CreateDate: 2019/6/18 14:15
 * @UpdateUser: Noctis
 * @UpdateDate: 2019/6/18 14:15
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class SysProductServiceImpl implements SysProductService {
    @Resource
    private TSysProductMapper tSysProductMapper;
    @Resource
    private TSysItemsMapper tSysItemsMapper;
    @Resource
    private TSysFoodMapper tSysFoodMapper;
    @Resource
    private ProductStoreMapper productStoreMapper;

    /**
     * @return com.github.pagehelper.PageInfo<com.fc.aden.model.custom.process.TSysProduct>
     * @Author Noctis
     * @Description // 产品分页展示
     * @Date 2019/6/5 16:26
     * @Param [pageNum, pageSize]
     **/
    @Override
    public PageInfo<TSysProduct> list(Tablepar tablepar, String searchTxt, String itemsCode, String foodName) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysProduct> tSysProductList = null;
        if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
            //如果是超级管理员 根据项目编号搜索所有数据
            tSysProductList = tSysProductMapper.selectListByItems(searchTxt, tsysUser.getItemsCode(), foodName);
        } else {
            tSysProductList = tSysProductMapper.findByProduct(searchTxt, itemsCode, foodName);
        }
        PageInfo<TSysProduct> pageInfo = new PageInfo<TSysProduct>(tSysProductList);
        return pageInfo;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //  产品添加保证名字唯一
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     **/
    @Override
    public ProductFoodStoreVO checkcNameUnique(String product, String itemsCode, String foodName) {
        ProductFoodStoreVO productFoodStoreVO = new ProductFoodStoreVO();
        int productList = tSysProductMapper.selectProductBycName(product, itemsCode);
        //如果是超级管理员判断选择的食品种类是否是该项目点下的
        List<TSysFood> foodList = tSysFoodMapper.findByFoodCodeOrItem(foodName, itemsCode);
        productFoodStoreVO.setFoodName(foodList.size());
        productFoodStoreVO.setProduct(productList);
        return productFoodStoreVO;
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品添加
     * @Date 2019/6/6 11:31
     * @Param [tSysProduct]
     **/
    @Transactional
    public int insertProduct(TSysProduct tSysProduct) {
        return tSysProductMapper.insertSelective(tSysProduct);
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品删除
     * @Date 2019/6/18 14:17
     * @Param [ids]
     **/
    @Override
    public int removeProduct(String ids) {
        List<String> list = Convert.toListStrArray(ids);
        int i = tSysProductMapper.deleteProductByIds(list);
        //删除产品关联的存储条件
        List<ProductStore> productStores = productStoreMapper.findByProductIdList(ids);
        if (productStores != null && productStores.size() > 0) {
            productStoreMapper.deleteProductId(ids);
        }
        return i;
    }

    /**
     * @return com.fc.test.model.custom.process.TSysProduct
     * @Author Noctis
     * @Description //根据ID查找产品
     * @Date 2019/6/18 14:18
     * @Param [id]
     **/
    @Override
    public TSysProduct selectProductById(String id) {
        TSysProduct tSysProduct = tSysProductMapper.selectByPrimaryKey(id);
        if (tSysProduct != null) {
            return tSysProduct;
        } else {
            return null;
        }
    }

    /**
     * @return int
     * @Author Noctis
     * @Description //产品更新
     * @Date 2019/6/18 14:18
     * @Param [tSysProduct]
     **/
    @Override
    public int updateProduct(TSysProduct tSysProduct) {
        int i = tSysProductMapper.updateByPrimaryKeySelective(tSysProduct);
        if (i > 0) {
            return i;
        } else {
            return 0;
        }

    }

    /**
     * @return com.fc.test.model.custom.process.TSysProduct
     * @Author Noctis
     * @Description //产品状态改变
     * @Date 2019/6/18 14:20
     * @Param [tSysProduct]
     **/
    @Override
    public TSysProduct updateStatus(TSysProduct tSysProduct) {
        int i = tSysProductMapper.updateStatusById(tSysProduct.getId(), tSysProduct.getStatus());
        if (i > 0) {
            TSysProduct product = tSysProductMapper.selectByPrimaryKey(tSysProduct.getId());
            return product;
        } else {
            return null;
        }
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public List<ProductVO> getSuccessTSysProduct(List<ImportTSysProductDTO> importProductDTOS) {
        if (importProductDTOS == null) return new ArrayList<>();
        List<ProductVO> tsysUsers = new ArrayList<>();
        for (ImportTSysProductDTO importTSysProductDTO : importProductDTOS) {
            if (importTSysProductDTO.getPass()) {
                tsysUsers.add(loadByDTO(importTSysProductDTO));
            }
        }
        return tsysUsers;
    }

    public ProductVO loadByDTO(ImportTSysProductDTO importTSysProductDTO) {
        ProductVO productVO = new ProductVO();
        BeanCopierEx.copy(importTSysProductDTO, productVO);
        return productVO;
    }

    /**
     * 导入文件到数据库
     *
     * @param productVOS
     */
    public void saveSysProduct(List<ProductVO> productVOS) {
        for (ProductVO productVO : productVOS) {
            //根据编号查询
            TSysFood tSysFood = tSysFoodMapper.findByFoodId(productVO.getFoodName(), productVO.getItemsCode());
            TSysProduct tSysProduct = new TSysProduct();
            List<ProductStore> productStoreList = new ArrayList<>();
            List<StoreVO> storeVOS = productVO.getStoreVOS();
            for (StoreVO storeVO : storeVOS) {
                ProductStore frozenStore = new ProductStore();
                frozenStore.setId(UUID.randomUUID().toString());
                frozenStore.setProductId(productVO.getId());
                frozenStore.setStoreId(storeVO.getId());
                frozenStore.setShelfLife(storeVO.getStore());
                frozenStore.setCreateTime(new Date());
                frozenStore.setUpdateTime(new Date());
                productStoreList.add(frozenStore);
            }
            productStoreMapper.insertBatch(productStoreList);

            String id = tSysFood.getFoodCode();
            productVO.setFoodName(id);
            tSysProduct.setUpdateTime(new Date());
            tSysProduct.setCreateTime(new Date());
            BeanCopierEx.copy(productVO, tSysProduct);
            tSysProductMapper.insertSelective(tSysProduct);
        }
    }

    @Resource
    private TSysStoreMapper tSysStoreMapper;

    public List<ProductVO> findByProductAndStore(String id, String items) {
        List<ProductVO> productVOList = new ArrayList<>();
        List<TSysStore> sysStores = tSysStoreMapper.findByStoreList(items);//所有存储条件
        List<ProductStore> productStores = productStoreMapper.findByProductIdList(id);//我自己选中的存储条件
        for (ProductStore productStore : productStores) {
            TSysStore sysStore = tSysStoreMapper.selectByPrimaryKey(productStore.getStoreId());
            ProductVO productVO = new ProductVO();
            productVO.setNoStoreId(productStore.getStoreId());
            productVO.setStoreName(sysStore.getName());
            productVO.setShelfLife(productStore.getShelfLife());
            productVOList.add(productVO);
        }
        return productVOList;
    }

    /**
     * 验证文件，数据导入到DTO
     *
     * @return
     */
    public ImportProductDTO importValid1(MultipartFile myFile, HttpServletRequest request) {
        ImportProductDTO importProductDTO = new ImportProductDTO();
        List<ImportTSysProductDTO> importTSysProductDTOS = new ArrayList<ImportTSysProductDTO>();
        int errNumber = 0;
        int successNumber = 0;
        List<List<String>> bigList = new ArrayList<>();
        //把MultipartFile转化为File 第一种
        Workbook workbook = ExcelUtils.getWorkBook(myFile);
        try {
            Sheet sheet = workbook.getSheetAt(0); //获取到工作表，因为一个excel可能有多个工作表
            //获取sheet中第一行行号
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            short temp = 0;
            for (int i = 1; i <= lastRowNum; i++) {
                List<String> stringList = new ArrayList<>();
                Row row = sheet.getRow(i);
                if (i == 1) {
                    temp = row.getLastCellNum();
                }
                for (int j = 0; j < temp; j++) {
                    Cell cellItem = row.getCell(j);
                    String item = "";
                    if (cellItem == null) {
                    } else {
//                        item = String.valueOf(cellItem);
                        item = ExcelUtils.getCellValue(cellItem);
                    }
                    stringList.add(item);
                }
                bigList.add(stringList);
            }
            List firstList = bigList.get(0);
            firstList.remove(0);
            firstList.remove(0);
            firstList.remove(0);
            firstList.remove(0);
            firstList.remove(0);
            bigList.remove(0);
            List<String> projectNames = new ArrayList<>();
            for (List<String> listList : bigList) {
                StringBuffer errorMessage = new StringBuffer();
                boolean pass = true;
                ImportTSysProductDTO importTSysProductDTO = new ImportTSysProductDTO();
                importTSysProductDTO.setCreateTime(df.format(new Date()));
                importTSysProductDTO.setUpdateTime(df.format(new Date()));
                importTSysProductDTO.setStatus(1);
                importTSysProductDTO.setId(UUID.randomUUID().toString());
                String item = listList.get(0);// 项目点编号
                List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(item);
                if (StringUtils.isEmpty(item)||(tSysItemsList==null&&tSysItemsList.size() == 0)) {
                    errorMessage.append("项目点编号错误;");
                    pass = false;
                } else if (tSysItemsList != null && tSysItemsList.size() > 0) {
                    importTSysProductDTO.setItemsCode(item);
                }
                String foodCode = listList.get(1);//食品种类
                List<TSysFood> tSysFood = tSysFoodMapper.findByFoodCodeOrItem(foodCode, item);
                if (StringUtils.isEmpty(foodCode) || foodCode == null || (tSysFood==null && tSysFood.size()==0)) {
                    errorMessage.append("食品种类编号错误;");
                    pass = false;
                } else if (tSysFood != null && tSysFood.size() > 0) {
                    importTSysProductDTO.setFoodName(foodCode);
                }
                String productCode = listList.get(2);//产品编号
                importTSysProductDTO.setProductCode(productCode);
                String productName = listList.get(3);//产品名称
                int productList = tSysProductMapper.selectProductBycName(productName, item);
                if (StringUtils.isEmpty(productName)) {
                    errorMessage.append("产品名称不能为空;");
                    pass = false;
                } else if (projectNames.contains(productName) || productList > 0) {
                    importTSysProductDTO.setProduct(productName);
                    errorMessage.append("产品名称不能重复;");
                    pass = false;
                } else {
                    importTSysProductDTO.setProduct(productName);
                    importTSysProductDTO.setName(productName);
                }
                String priority = listList.get(4);//产品优先级
                if (priority.equals("") || priority == null) {//优先级为空默认 2
                    importTSysProductDTO.setPriority(2);
                }
                int g = 5;
                List<StoreVO> storeVOList = new ArrayList<>();
                for (int i = 0; i < firstList.size(); i++) {
                    StoreVO storeVOS = new StoreVO();
                    String proStore = listList.get(g++);
                    String store = firstList.get(i).toString();//将object类型转成string类型
                        TSysStore tSysStoreF = tSysStoreMapper.selectByItemsAndNameF(item, store);
                    if (proStore != null && !proStore.equals("")) {
                        boolean result=proStore.matches("[0-9]+");
                        if(result == true) {
                            storeVOS.setId(tSysStoreF.getId());
                            storeVOS.setStore(proStore);//小时
                            storeVOList.add(storeVOS);
                        }else{
                            storeVOS.setId(tSysStoreF.getId());
                            storeVOS.setStore("见包装");
                            storeVOList.add(storeVOS);
                        }
                    } else {
                        storeVOS.setId(tSysStoreF.getId());
                        storeVOS.setStore("见包装");
                        storeVOList.add(storeVOS);
                    }
                    importTSysProductDTO.setStoreVOS(storeVOList);
                    System.out.println("测试数据:" + store);
                }
                if (pass) {
                    errorMessage.append("成功!");
                    successNumber++;
                } else {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importProductDTO;
    }


}
