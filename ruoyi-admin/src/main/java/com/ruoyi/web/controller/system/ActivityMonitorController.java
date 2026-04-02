package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ActivityMonitor;
import com.ruoyi.system.service.IActivityMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动监控记录Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/system/activity/monitor")
public class ActivityMonitorController extends BaseController {

    @Autowired
    private IActivityMonitorService monitorService;

    // 监控记录列表
    @PreAuthorize("@ss.hasPermi('system:activityMonitor:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityMonitor monitor) {
        startPage();
        List<ActivityMonitor> list = monitorService.selectMonitorList(monitor);
        return getDataTable(list);
    }

    // 新增监控
    @PreAuthorize("@ss.hasPermi('system:activityMonitor:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivityMonitor monitor) {
        return toAjax(monitorService.insertActivityMonitor(monitor));
    }
}
