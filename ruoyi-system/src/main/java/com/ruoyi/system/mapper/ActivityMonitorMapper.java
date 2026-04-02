package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ActivityMonitor;

/**
 * 活动监控记录Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ActivityMonitorMapper
{
    List<ActivityMonitor> selectMonitorList(ActivityMonitor monitor);
    int insertActivityMonitor(ActivityMonitor monitor);
}
