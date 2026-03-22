package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.service.ISysActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/activity/activityInfo")
public class SysActivityController extends BaseController
{
    @Autowired
    private ISysActivityService sysActivityService;

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysActivity sysActivity)
    {
        startPage();
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysActivity sysActivity)
    {
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        ExcelUtil<SysActivity> util = new ExcelUtil<SysActivity>(SysActivity.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysActivityService.selectSysActivityById(id));
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivity sysActivity)
    {
        sysActivity.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        return toAjax(sysActivityService.insertSysActivity(sysActivity));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivity sysActivity)
    {
        sysActivity.setUpdateTime(DateUtils.getNowDate());
        sysActivity.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return toAjax(sysActivityService.updateSysActivity(sysActivity));
    }

    /**
     * 删除活动信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysActivityService.deleteSysActivityByIds(ids));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activity:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PostMapping("/start")
    public AjaxResult startActivity(@RequestBody SysActivity sysActivity)
    {
        sysActivity.setUpdateTime(DateUtils.getNowDate());
        sysActivity.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        int row = sysActivityService.startSysActivity(sysActivity);
        return toAjax(row);
    }
}
