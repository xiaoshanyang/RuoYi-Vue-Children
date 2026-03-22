package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysActivityMonitor;
import com.ruoyi.system.mapper.SysActivityMonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysActivityMapper;
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.service.ISysActivityService;

/**
 * 活动信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysActivityServiceImpl implements ISysActivityService 
{
    @Autowired
    private SysActivityMapper sysActivityMapper;

    @Autowired
    private SysActivityMonitorMapper sysActivityMonitorMapper;

    /**
     * 查询活动信息
     * 
     * @param id 活动信息主键
     * @return 活动信息
     */
    @Override
    public SysActivity selectSysActivityById(Long id)
    {
        return sysActivityMapper.selectSysActivityById(id);
    }

    /**
     * 查询活动信息列表
     * 
     * @param sysActivity 活动信息
     * @return 活动信息
     */
    @Override
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity)
    {
        return sysActivityMapper.selectSysActivityList(sysActivity);
    }

    /**
     * 新增活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    @Override
    public int insertSysActivity(SysActivity sysActivity)
    {
        sysActivity.setCreateTime(DateUtils.getNowDate());
        return sysActivityMapper.insertSysActivity(sysActivity);
    }

    /**
     * 修改活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    @Override
    public int updateSysActivity(SysActivity sysActivity)
    {
        sysActivity.setUpdateTime(DateUtils.getNowDate());
        return sysActivityMapper.updateSysActivity(sysActivity);
    }

    @Override
    public int startSysActivity(SysActivity sysActivity){
        sysActivity.setActivityStatus("监控中");
        int row = sysActivityMapper.updateSysActivity(sysActivity);

        SysActivityMonitor sysActivityMonitor = new SysActivityMonitor();
        sysActivityMonitor.setCreateTime(DateUtils.getNowDate());
        sysActivityMonitor.setActivityId(sysActivity.getId());
        sysActivityMonitor.setStartTime(DateUtils.getNowDate());
        sysActivityMonitor.setRemindTime(DateUtils.addMinutes(DateUtils.getNowDate(), sysActivity.getPresetDuration()));
        // 新建activity monitor
        return sysActivityMonitorMapper.insertSysActivityMonitor(sysActivityMonitor);
    }

    /**
     * 批量删除活动信息
     * 
     * @param ids 需要删除的活动信息主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityByIds(Long[] ids)
    {
        return sysActivityMapper.deleteSysActivityByIds(ids);
    }

    /**
     * 删除活动信息信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityById(Long id)
    {
        return sysActivityMapper.deleteSysActivityById(id);
    }
}
