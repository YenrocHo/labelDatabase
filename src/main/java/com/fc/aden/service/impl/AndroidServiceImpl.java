package com.fc.aden.service.impl;

import com.fc.aden.common.base.Const;
import com.fc.aden.common.base.TokenCache;
import com.fc.aden.common.domain.AjaxResult;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.TsysUserMapper;
import com.fc.aden.mapper.auto.process.*;

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

    /**
     * 登录接口
     * @param username
     * @return
     */
    @Override
    public AjaxResult login(String username){
        int resultCount = tsysUserMapper.checkUserName(username);
        if (resultCount == 0) {
            AjaxResult ajaxResult = AjaxResult.error(Const.CodeEnum.noExistent.getCode(),Const.CodeEnum.noExistent.getValue());
            return ajaxResult;
        }
        TsysUser tsysUser = tsysUserMapper.selectLogin(username);
        if(tsysUser == null){
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        String statusToken = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKRN_PREFIX + username, statusToken);
        tsysUser.setStatusToken(statusToken);
        AjaxResult ajaxResult = AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
        ajaxResult.put("data",tsysUser);
        return ajaxResult;
    }

    @Override
    public AjaxResult AllUserList(String statusToken,String username){
        if (!token(username,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        List<TsysUser> tsysUserList;
        try {
            TsysUser tsysUser = tsysUserMapper.selectLogin(username);
            tsysUserList = tsysUserMapper.selectAllUser(tsysUser.getItemsCode());
        }catch (Exception e){
            return AjaxResult.error(Const.CodeEnum.badSQL.getCode(),Const.CodeEnum.badSQL.getValue());
        }
        AjaxResult tsysUserListResult = isNull(tsysUserList);
        return tsysUserListResult;
    }

    @Override
    public AjaxResult selectAllList(String itemsCode,String keyword,String statusToken,String username,String foodCode){
        if (!token(username,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<TSysStage> stageList;
        List<TSysFood> foodList;
        List<TSysProduct> productList;
        List<TSysStore> storeList;
        List<TSysWeight> weightList;
        try{
            stageList = tSysStageMapper.selectStageList(itemsCode,keyword);
            foodList = tSysFoodMapper.selectFoodList(itemsCode,keyword);
            productList = tSysProductMapper.selectProductList(itemsCode,keyword,foodCode);
            storeList = tSysStoreMapper.selectStoreList(itemsCode,keyword);
            weightList = tSysWeightMapper.selectWeightList(itemsCode,keyword);
        }catch (Exception e){
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
    public AjaxResult selectOneList(String keyword,String statusToken,String type,String foodCode,String username){
        if (!token(username,statusToken)){
            return AjaxResult.success(Const.CodeEnum.noToken.getCode(),Const.CodeEnum.noToken.getValue());
        }
        if (StringUtils.isNotBlank(keyword)) {
            keyword = fuzzyQuery(keyword);
        }
        AjaxResult listResult = new AjaxResult();
        AjaxResult ajaxResult = new AjaxResult();
        List<String> typeList = Convert.toListStrArray(type);
        for(String str:typeList){
            TsysUser tsysUser = tsysUserMapper.selectLogin(username);
            List searchResult = searchList(str, tsysUser.getItemsCode(), keyword,foodCode);
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
        tSysTag.setPrintUser(jsonObject.getString("username"));
        tSysTag.setCreatTime(new Date());
        tSysTag.setOriginalId(jsonObject.getString("original_Id"));
        tSysTag.setLabelId(jsonObject.getString("labelId"));
        try{
            tSysTag.setItems(tSysItemsMapper.selectByPrimaryKey(jsonObject.getString("itemsCode")).getItemsCode());
            date.setTime(Long.parseLong(jsonObject.getString("printTime")));
            Date printTime=simpleDateFormat.parse(simpleDateFormat.format(date));
            tSysTag.setPrintTime(printTime);
            int insertSelective = tSysTagMapper.insertSelective(tSysTag);
            if (insertSelective>0){
                return AjaxResult.success(Const.CodeEnum.success.getCode(),Const.CodeEnum.success.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error("操作失败");
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

    private List searchList(String type,String itemsCode,String keyword,String foodCode){
        List list = new ArrayList();
        try {
            switch (type){
                case "Item"     :
                    list = tSysItemsMapper.selectItemList(itemsCode,keyword);
                    break;
                case "Stage"    :
                    list = tSysStageMapper.selectStageList(itemsCode,keyword);
                    break;
                case "Food"     :
                    list = tSysFoodMapper.selectFoodList(itemsCode,keyword);
                    break;
                case "Product"  :
                    list = tSysProductMapper.selectProductList(itemsCode,keyword,foodCode);
                    break;
                case "Store"    :
                    list = tSysStoreMapper.selectStoreList(itemsCode,keyword);
                    break;
            /*    case "Weight"    :
                    list = tSysWeightMapper.selectWeightList(itemsCode,keyword);
                    break;*/
                default:
                    list.add("参数错误");
                    break;
            }
        }catch (Exception e){
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
