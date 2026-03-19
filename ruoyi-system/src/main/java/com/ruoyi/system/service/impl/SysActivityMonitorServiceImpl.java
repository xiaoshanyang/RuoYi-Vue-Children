package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysActivityMonitorMapper;
import com.ruoyi.system.domain.SysActivityMonitor;
import com.ruoyi.system.service.ISysActivityMonitorService;

/**
 * 活动监控记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysActivityMonitorServiceImpl implements ISysActivityMonitorService 
{
    @Autowired
    private SysActivityMonitorMapper sysActivityMonitorMapper;

    /**
     * 查询活动监控记录
     * 
     * @param id 活动监控记录主键
     * @return 活动监控记录
     */
    @Override
    public SysActivityMonitor selectSysActivityMonitorById(Long id)
    {
        return sysActivityMonitorMapper.selectSysActivityMonitorById(id);
    }

    /**
     * 查询活动监控记录列表
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 活动监控记录
     */
    @Override
    public List<SysActivityMonitor> selectSysActivityMonitorList(SysActivityMonitor sysActivityMonitor)
    {
        return sysActivityMonitorMapper.selectSysActivityMonitorList(sysActivityMonitor);
    }

    /**
     * 新增活动监控记录
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 结果
     */
    @Override
    public int insertSysActivityMonitor(SysActivityMonitor sysActivityMonitor)
    {
        sysActivityMonitor.setCreateTime(DateUtils.getNowDate());
        return sysActivityMonitorMapper.insertSysActivityMonitor(sysActivityMonitor);
    }

    /**
     * 修改活动监控记录
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 结果
     */
    @Override
    public int updateSysActivityMonitor(SysActivityMonitor sysActivityMonitor)
    {
        return sysActivityMonitorMapper.updateSysActivityMonitor(sysActivityMonitor);
    }

    /**
     * 批量删除活动监控记录
     * 
     * @param ids 需要删除的活动监控记录主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityMonitorByIds(Long[] ids)
    {
        return sysActivityMonitorMapper.deleteSysActivityMonitorByIds(ids);
    }

    /**
     * 删除活动监控记录信息
     * 
     * @param id 活动监控记录主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityMonitorById(Long id)
    {
        return sysActivityMonitorMapper.deleteSysActivityMonitorById(id);
    }
}
