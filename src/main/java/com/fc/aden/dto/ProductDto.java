/**
 * projectName: labelDatabase
 * fileName: ProductDto.java
 * packageName: com.fc.aden.dto
 * date: 2019-08-22
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.dto;

import com.fc.aden.model.custom.process.ProductStore;

import java.util.Date;
import java.util.List;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: ProductDto
 * @packageName: com.fc.aden.dto
 * @description: 产品返回数据
 * @data: 2019-08-22
 **/
public class ProductDto {
    //产品Id
    private String id;

    private String itemsCode;

    //产品名
    private String product;
    private String productCode;

    //产品中文名
    private String name;

    //产品英文名
    private String englishName;

    //食品名称
    private String foodName;

    //产品状态
    private Integer status;

    //产品创建时间
    private Date createTime;

    //产品更新时间
    private Date updateTime;

    //储存条件
    private List<ProductStore> productStores;

}
