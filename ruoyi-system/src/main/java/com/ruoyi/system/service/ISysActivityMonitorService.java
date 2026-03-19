package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysActivityMonitor;

/**
 * 活动监控记录Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysActivityMonitorService 
{
    /**
     * 查询活动监控记录
     * 
     * @param id 活动监控记录主键
     * @return 活动监控记录
     */
    public SysActivityMonitor selectSysActivityMonitorById(Long id);

    /**
     * 查询活动监控记录列表
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 活动监控记录集合
     */
    public List<SysActivityMonitor> selectSysActivityMonitorList(SysActivityMonitor sysActivityMonitor);

    /**
     * 新增活动监控记录
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 结果
     */
    public int insertSysActivityMonitor(SysActivityMonitor sysActivityMonitor);

    /**
     * 修改活动监控记录
     * 
     * @param sysActivityMonitor 活动监控记录
     * @return 结果
     */
    public int updateSysActivityMonitor(SysActivityMonitor sysActivityMonitor);

    /**
     * 批量删除活动监控记录
     * 
     * @param ids 需要删除的活动监控记录主键集合
     * @return 结果
     */
    public int deleteSysActivityMonitorByIds(Long[] ids);

    /**
     * 删除活动监控记录信息
     * 
     * @param id 活动监控记录主键
     * @return 结果
     */
    public int deleteSysActivityMonitorById(Long id);
}
