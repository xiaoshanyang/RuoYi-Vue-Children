package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.SysActivityMonitorVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysActivityMonitor;
import com.ruoyi.system.service.ISysActivityMonitorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动监控记录Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/activity/activityMonitor")
public class SysActivityMonitorController extends BaseController
{
    @Autowired
    private ISysActivityMonitorService sysActivityMonitorService;

    /**
     * 查询活动监控记录列表
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysActivityMonitor sysActivityMonitor)
    {
        startPage();
        List<SysActivityMonitorVo> list = sysActivityMonitorService.selectSysActivityMonitorList(sysActivityMonitor);
        return getDataTable(list);
    }

    /**
     * 导出活动监控记录列表
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:export')")
    @Log(title = "活动监控记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysActivityMonitor sysActivityMonitor)
    {
        List<SysActivityMonitorVo> list = sysActivityMonitorService.selectSysActivityMonitorList(sysActivityMonitor);
        ExcelUtil<SysActivityMonitorVo> util = new ExcelUtil<SysActivityMonitorVo>(SysActivityMonitorVo.class);
        util.exportExcel(response, list, "活动监控记录数据");
    }

    /**
     * 获取活动监控记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysActivityMonitorService.selectSysActivityMonitorById(id));
    }

    /**
     * 新增活动监控记录
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:add')")
    @Log(title = "活动监控记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivityMonitor sysActivityMonitor)
    {
        return toAjax(sysActivityMonitorService.insertSysActivityMonitor(sysActivityMonitor));
    }

    /**
     * 修改活动监控记录
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:edit')")
    @Log(title = "活动监控记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivityMonitor sysActivityMonitor)
    {
        return toAjax(sysActivityMonitorService.updateSysActivityMonitor(sysActivityMonitor));
    }

    /**
     * 删除活动监控记录
     */
    @PreAuthorize("@ss.hasPermi('activity:monitor:remove')")
    @Log(title = "活动监控记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysActivityMonitorService.deleteSysActivityMonitorByIds(ids));
    }
}
