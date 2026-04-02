package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ActivityMonitorMapper;
import com.ruoyi.system.domain.ActivityMonitor;
import com.ruoyi.system.service.IActivityMonitorService;

/**
 * 活动监控记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class ActivityMonitorServiceImpl implements IActivityMonitorService
{
    @Autowired
    private ActivityMonitorMapper activityMonitorMapper;

    @Override
    public List<ActivityMonitor> selectMonitorList(ActivityMonitor monitor) {
        return activityMonitorMapper.selectMonitorList(monitor);
    }

    @Override
    public int insertActivityMonitor(ActivityMonitor monitor) {
        return activityMonitorMapper.insertActivityMonitor(monitor);
    }
}
