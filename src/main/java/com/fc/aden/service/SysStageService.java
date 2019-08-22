package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.TSysStageMapper;
import com.fc.aden.mapper.auto.process.TSysStoreMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.auto.TsysUser;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.ImportStageDTO;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStageExample;
import com.fc.aden.model.custom.process.TSysStore;
import com.fc.aden.shiro.util.ShiroUtils;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.util.StringUtils;
import com.fc.aden.vo.ImportTSysStageDTO;
import com.fc.aden.vo.StageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.stage.Stage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SysStageService implements BaseService<TSysStage, TSysStageExample> {
    @Autowired
    private TSysStageMapper tSysStageMapper;
    @Autowired
    private TSysItemsMapper tSysItemsMapper;

    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> list = Convert.toListStrArray(ids);
        TSysStageExample tSysStage = new TSysStageExample();
        tSysStage.createCriteria().andIdIn(list);
        return tSysStageMapper.deleteByExample(tSysStage);
    }

    /**
     * 新增制作阶段
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(TSysStage record) {
        //添加主键id
        String id = SnowflakeIdWorker.getUUID().toString();
        record.setId(id);
        record.setName(record.getStage());
        record.setCreateTime(new Date());//保存创建时间
        record.setUpdateTime(new Date());//保存更新时间
        return tSysStageMapper.insertSelective(record);
    }

    @Override
    public TSysStage selectByPrimaryKey(String id) {
        return tSysStageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TSysStage record) {
        record.setUpdateTime(new Date());//更新时间
        return tSysStageMapper.updateByPrimaryKeySelective(record);//修改数据
    }

    @Override
    public int updateByExampleSelective(@Param("record") TSysStage record, @Param("example") TSysStageExample example) {
        return tSysStageMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") TSysStage record, @Param("example") TSysStageExample example) {
        return tSysStageMapper.updateByExample(record, example);
    }

    @Override
    public List<TSysStage> selectByExample(TSysStageExample example) {
        return tSysStageMapper.selectByExample(example);
    }

    @Override
    public long countByExample(TSysStageExample example) {
        return tSysStageMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TSysStageExample example) {
        return tSysStageMapper.deleteByExample(example);
    }

    /**
     * 阶段管理列表
     *
     * @param tablepar
     * @param stage
     * @return
     */
    public PageInfo<TSysStage> sysStageList(Tablepar tablepar, String stage, String itemsCode) {
        TsysUser tsysUser = ShiroUtils.getUser();
        List<TSysStage> sysStages = null;
        if (stage != null && itemsCode != null) {
            if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
                //根据项目点编号搜索
                sysStages = tSysStageMapper.findByStageOrItems(stage, tsysUser.getItemsCode());
            } else {
                //管理员全部搜索
                sysStages = tSysStageMapper.selectStage(itemsCode, stage);
            }
        } else {
            if ("2" != tsysUser.getRoles() && !"2".equals(tsysUser.getRoles())) {
                sysStages = tSysStageMapper.findByStageOrItems(stage, tsysUser.getItemsCode());
            } else {
                sysStages = tSysStageMapper.selectStage(itemsCode, stage);
            }
        }
        if (tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        PageInfo<TSysStage> pageInfo = new PageInfo<TSysStage>(sysStages);
        return pageInfo;
    }

    /**
     * 根据项目点编号检查阶段名称是否重名
     *
     * @param stage  itemsCode
     * @return
     */
    public int checkStageUnique(String stage,String itemsCode) {
        List<TSysStage> list = tSysStageMapper.findByStage(stage,itemsCode);
         return list.size();
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 验证文件，数据导入到DTO
     *
     * @param dataList
     * @return
     */
    public ImportStageDTO importValid(List<Map<String, String>> dataList) {
        List<String> projectNames = new ArrayList<>();
        ImportStageDTO importUserDTO = new ImportStageDTO();
        List<ImportTSysStageDTO> importTSysStageDTOS = new ArrayList<>();

        int errNumber = 0;
        int successNumber = 0;
//        for (Map<String, String> row : dataList) {
        for (int i = 1; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            String items = row.get(ImportStageDTO.ITEMS_CODE);
            String stageEnglish = row.get(ImportStageDTO.STAGE_ENGLISH);
            String stageName = row.get(ImportStageDTO.STAGE_NAME);

            StringBuffer errorMessage = new StringBuffer();
            boolean pass = true;
            ImportTSysStageDTO importTSysStageDTO = new ImportTSysStageDTO();
            importTSysStageDTO.setCreateTime(df.format(new Date()));
            importTSysStageDTO.setUpdateTime(df.format(new Date()));
            importTSysStageDTO.setId(UUID.randomUUID().toString());
            importTSysStageDTO.setStatus(1);

            List<TSysStage> stages = tSysStageMapper.findByStage(stageName,items);
            if (StringUtils.isEmpty(stageName)) {
                errorMessage.append("阶段名称名称不能为空；");
                pass = false;
            } else if (projectNames.contains(stageName) || stages != null && stages.size() > 0) {
                importTSysStageDTO.setStage(stageName);
                errorMessage.append("阶段名称名称不能重复；");
                pass = false;
            } else {
                importTSysStageDTO.setName(stageName);
                importTSysStageDTO.setStage(stageName);
            }
            List<TSysItems> tSysItemsList = tSysItemsMapper.selectByItems(items);
            if (StringUtils.isEmpty(items)) {
                errorMessage.append("项目点编号不为空;");
                pass = false;
            } else if (tSysItemsList == null || tSysItemsList.size() == 0) {
                importTSysStageDTO.setItemsCode(items);
                errorMessage.append("项目点不存在;");
                pass = false;
            } else {
                importTSysStageDTO.setItemsCode(items);
            }
            if (pass) {
                errorMessage.append("成功！");
                successNumber++;
            } else {
                errNumber++;
            }

            importTSysStageDTO.setEnglishName(stageEnglish);
            importTSysStageDTO.setPass(pass);
            importTSysStageDTO.setMessages(errorMessage.toString());
            importTSysStageDTOS.add(importTSysStageDTO);
        }
        importUserDTO.setErrorNumber(errNumber);
        importUserDTO.setSuccessNumber(successNumber);
        importUserDTO.settSysStageDTOS(importTSysStageDTOS);
        return importUserDTO;
    }

    /**
     * 导入文件到数据库
     *
     * @param stageVOS
     */
    public void saveStage(List<StageVO> stageVOS) {
        for (StageVO stageVO : stageVOS) {
            TSysStage tSysStage = new TSysStage();
            tSysStage.setUpdateTime(new Date());
            tSysStage.setCreateTime(new Date());
            BeanCopierEx.copy(stageVO, tSysStage);
            tSysStageMapper.insertSelective(tSysStage);
        }
    }

    public List<StageVO> getSuccessTSysItems(List<ImportTSysStageDTO> importTSysStageDTOS) {
        if (importTSysStageDTOS == null) return new ArrayList<>();
        List<StageVO> stageVOS = new ArrayList<>();
        for (ImportTSysStageDTO importTSysUserDTO : importTSysStageDTOS) {
            if (importTSysUserDTO.getPass()) {
                stageVOS.add(loadByDTO(importTSysUserDTO));
            }
        }
        return stageVOS;
    }

    /**
     * 确认导入数据
     *
     * @param dto
     * @return
     */
    private StageVO loadByDTO(ImportTSysStageDTO dto) {
        StageVO stageVO = new StageVO();
        BeanCopierEx.copy(dto, stageVO);
        return stageVO;
    }
}
