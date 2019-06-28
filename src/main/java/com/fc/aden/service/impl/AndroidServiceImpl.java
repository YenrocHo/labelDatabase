package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.base.TokenCache;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.mapper.auto.process.*;

import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.process.*;
import com.fc.aden.service.AndroidService;
import com.fc.aden.util.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.fc.aden.common.domain.AjaxResult.AJAX_DATA;
@Service
public class AndroidServiceImpl implements AndroidService {
    @Autowired
    private TSysStageMapper tSysStageMapper;
    @Autowired
    private TSysFoodMapper tSysFoodMapper;
    @Autowired
    private TSysProductMapper tSysProductMapper;
    @Autowired
    private TSysStoreMapper tSysStoreMapper;
    @Autowired
    private TSysWeightMapper tSysWeightMapper;
    @Autowired
    private TsysUserMapper tsysUserMapper;
    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    @Autowired
    private TSysTagMapper tSysTagMapper;

    @Override
    public AjaxResult login(String number){
        int resultCount = tsysUserMapper.checkNumber(number);
        if (resultCount == 0) {
            AjaxResult ajaxResult = AjaxResult.error(Const.CodeEnum.noExistent.getCode(),Const.CodeEnum.noExistent.getValue());
            return ajaxResult;
        }
        TsysUser tsysUser = tsysUserMapper.selectLogin(number);
        TSysItems tSysItem = tSysItemsMapper.selectItemByUserNumber(tsysUser.getNumber());
        if(tsysUser == null){
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        String statusToken = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKRN_PREFIX + number, statusToken);
        AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
        ajaxResult.put("data",tsysUser).put("userItem",tSysItem);
        ajaxResult.put("statusToken",statusToken);
        return ajaxResult;
    }

    @Override
    public AjaxResult AllUserList(String statusToken,String number){
        if (!token(number,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        List<TsysUser> tsysUserList;
        try {
            tsysUserList = tsysUserMapper.selectAllUser();
        }catch (Exception e){
            System.out.println(e);
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        AjaxResult tsysUserListResult = isNull(tsysUserList);
        return tsysUserListResult;
    }

    @Override
    public AjaxResult selectAllList(String itemId,String keyword,String statusToken,String number){
        if (!token(number,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<TSysStage> stageList;
        List<TSysFood> foodList;
        List<TSysProduct> productList;
        List<TSysStore> storeList;
        List<TSysWeight> weightList;
        try{
            stageList = tSysStageMapper.selectStageList(itemId,keyword);
            foodList = tSysFoodMapper.selectFoodList(itemId,keyword);
            productList = tSysProductMapper.selectProductList(itemId,keyword);
            storeList = tSysStoreMapper.selectStoreList(itemId,keyword);
            weightList = tSysWeightMapper.selectWeightList(itemId,keyword);
        }catch (Exception e){
            System.out.println(e);
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        AjaxResult stageListAjaxResult = isNull(stageList);
        AjaxResult foodListAjaxResult = isNull(foodList);
        AjaxResult productListAjaxResult = isNull(productList);
        AjaxResult storeListAjaxResult = isNull(storeList);
        AjaxResult weightListAjaxResult = isNull(weightList);
        ajaxResult.put("Stage",stageListAjaxResult);
        ajaxResult.put("Food",foodListAjaxResult);
        ajaxResult.put("Product",productListAjaxResult);
        ajaxResult.put("Store",storeListAjaxResult);
        ajaxResult.put("Weight",weightListAjaxResult);
        return ajaxResult;

    }
    @Override
    public AjaxResult selectOneList(String itemId, String keyword,String statusToken,String type,String number){
        if (!token(number,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        AjaxResult listResult = new AjaxResult();
        AjaxResult ajaxResult = new AjaxResult();
        List<String> typeList = Convert.toListStrArray(type);
        for(String str:typeList){
            List searchResult = searchList(str, itemId, keyword);
            if (searchResult.size() == 0){

            }else if(searchResult.get(0).equals("参数错误")){
                listResult = AjaxResult.error(Const.CodeEnum.wrongParam.getCode(),Const.CodeEnum.wrongParam.getValue());
            }else if(searchResult.get(0).equals("SQL错误")){
                listResult = AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
            }else {
                listResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            }
            listResult.put(AJAX_DATA,searchResult);
            ajaxResult.put(str,listResult);
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult submit(String jsonString){
        JSONArray jsonStr = new JSONArray(jsonString);
        JSONObject jsonObject = jsonStr.getJSONObject(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        TSysTag tSysTag = new TSysTag();
        tSysTag.setId(SnowflakeIdWorker.getUUID());
        tSysTag.setStage(jsonObject.getString("stage"));
        tSysTag.setFood(jsonObject.getString("food"));
        tSysTag.setProduct(jsonObject.getString("product"));
        tSysTag.setStore(jsonObject.getString("store"));
        tSysTag.setPrintUser(jsonObject.getString("userName"));
        tSysTag.setOriginalId(jsonObject.getString("original_Id"));
        tSysTag.setCreatTime(new Date());

        int printCount = 0;

        try{
            printCount = tSysTagMapper.selectCountByOriginalId(jsonObject.getString("original_Id"));
            tSysTag.setItems(tSysItemsMapper.selectByPrimaryKey(jsonObject.getString("itemId")).getItems());
            date.setTime(Long.parseLong(jsonObject.getString("printTime")));
            Date printTime=simpleDateFormat.parse(simpleDateFormat.format(date));
            tSysTag.setPrintTime(printTime);
            int insertSelective = tSysTagMapper.insertSelective(tSysTag);
            if (insertSelective>0){
                return AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue()).put("last",printCount).put("now",printCount+1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error("操作失败").put("last",printCount);
    }





    private boolean token(String number,String statusToken){
        if (StringUtils.isBlank(statusToken)) {
            return false;
        }
        String token = TokenCache.getKey(TokenCache.TOKRN_PREFIX + number);
        if (StringUtils.isBlank(token)) {
            return false;
        }
        if (!token.equals(statusToken)){
            return false;
        }
        return true;
    }

    private List searchList(String type,String itemId,String keyword){
        List list = new ArrayList();
        try {
            switch (type){
                case "Item"     :
                    list = tSysItemsMapper.selectItemList(keyword);
                    break;
                case "Stage"    :
                    list = tSysStageMapper.selectStageList(itemId,keyword);
                    break;
                case "Food"     :
                    list = tSysFoodMapper.selectFoodList(itemId,keyword);
                    break;
                case "Product"  :
                    list = tSysProductMapper.selectProductList(itemId,keyword);
                    break;
                case "Store"    :
                    list = tSysStoreMapper.selectStoreList(itemId,keyword);
                    break;
                case "Weight"    :
                    list = tSysWeightMapper.selectWeightList(itemId,keyword);
                    break;
                default:
                    list.add("参数错误");
                    break;
            }
        }catch (Exception e){
            System.out.println(e);
           list.add("SQL出错");
        }
        return list;
    }

    private AjaxResult isNull(List list){
            if (list.size() == 0){
                AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.noObject.getCode(),Const.CodeEnum.noObject.getValue());
                return ajaxResult;
            }
            AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            ajaxResult.put(AJAX_DATA,list);
            return ajaxResult;

    }

    private String fuzzyQuery(String str){
        str = new StringBuilder().append("%").append(str).append("%").toString();
        return str;
    }

}
