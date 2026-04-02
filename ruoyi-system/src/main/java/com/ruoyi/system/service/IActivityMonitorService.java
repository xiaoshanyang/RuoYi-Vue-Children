package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ActivityMonitor;

public interface IActivityMonitorService {
    List<ActivityMonitor> selectMonitorList(ActivityMonitor monitor);
    int insertActivityMonitor(ActivityMonitor monitor);
}