package com.fc.aden.service;

import com.fc.aden.common.base.BaseService;
import com.fc.aden.common.support.Convert;
import com.fc.aden.mapper.auto.TSysItemsMapper;
import com.fc.aden.mapper.auto.process.TSysStageMapper;
import com.fc.aden.model.auto.TSysItems;
import com.fc.aden.model.custom.Tablepar;
import com.fc.aden.model.custom.process.TSysStage;
import com.fc.aden.model.custom.process.TSysStageExample;
import com.fc.aden.util.BeanCopierEx;
import com.fc.aden.util.SnowflakeIdWorker;
import com.fc.aden.vo.StageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysStageService implements BaseService<TSysStage, TSysStageExample> {
    @Autowired
    private TSysStageMapper tSysStageMapper;
    @Autowired
    private TSysItemsMapper tSysItemsMapper;
    @Override
    public  int deleteByPrimaryKey(String ids){
        List<String> list = Convert.toListStrArray(ids);
        TSysStageExample tSysStage = new TSysStageExample();
        tSysStage.createCriteria().andIdIn(list);
        return tSysStageMapper.deleteByExample(tSysStage);
    }

    /**
     * 新增制作阶段
     * @param record
     * @return
     */
    @Override
    public int insertSelective(TSysStage record){
        //添加主键id
        String id= SnowflakeIdWorker.getUUID().toString();
        record.setId(id);
        record.setCreateTime(new Date());//保存创建时间
        record.setUpdateTime(new Date());//保存更新时间
        return tSysStageMapper.insertSelective(record);
    }

    @Override
    public TSysStage selectByPrimaryKey(String id){
        return tSysStageMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(TSysStage record){
        record.setUpdateTime(new Date());//更新时间
        return tSysStageMapper.updateByPrimaryKeySelective(record);//修改数据
    }
    @Override
    public int updateByExampleSelective(@Param("record") TSysStage record, @Param("example") TSysStageExample example){
        return tSysStageMapper.updateByExampleSelective(record,example);
    }
    @Override
    public int updateByExample(@Param("record") TSysStage record, @Param("example") TSysStageExample example){
        return tSysStageMapper.updateByExample(record,example);
    }
    @Override
    public List<TSysStage> selectByExample(TSysStageExample example){
        return tSysStageMapper.selectByExample(example);
    }
    @Override
    public long countByExample(TSysStageExample example){
        return tSysStageMapper.countByExample(example);
    }
    @Override
    public int deleteByExample(TSysStageExample example){
       return tSysStageMapper.deleteByExample(example);
    }

    /**
     * 阶段管理列表
     * @param tablepar
     * @param stage
     * @return
     */
    public PageInfo<StageVO> sysStageList(Tablepar tablepar, String stage){
        TSysStageExample tSysStageExample = new TSysStageExample();
        tSysStageExample.setOrderByClause("id+0 desc");
        if(stage!=null&&!"".equals(stage)){
            tSysStageExample.createCriteria().andStageLike("%"+stage+"%");
        }
        if(tablepar.getPageNum() != 0 && tablepar.getPageSize() != 0) {
            PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        }
        List<TSysStage> list= selectByExample(tSysStageExample);
        List<StageVO> stageVOS = new ArrayList<>();
        for(TSysStage tSysStage:list){
            StageVO stageVO = new StageVO();
            TSysItems tSysItems = tSysItemsMapper.selectByPrimaryKey(tSysStage.getItemId());
            stageVO.setItem(tSysItems.getItemsCode());
            BeanCopierEx.copy(tSysStage, stageVO);
            stageVOS.add(stageVO);
        }
        PageInfo<StageVO> pageInfo = new PageInfo<StageVO>(stageVOS);
        return  pageInfo;
    }

    /**
     * 检查阶段名称是否重名
     * @param tSysStage
     * @return
     */
    public int checkStageUnique(TSysStage tSysStage){
        TSysStageExample example = new TSysStageExample();
        example.createCriteria().andStageEqualTo(tSysStage.getStage());
        List<TSysStage> list = selectByExample(example);
        return list.size();
    }
}
