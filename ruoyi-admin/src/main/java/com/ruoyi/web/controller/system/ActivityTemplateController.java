package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ActivityTemplate;
import com.ruoyi.system.service.IActivityTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 活动模板Controller
 *
 * @author ruoyi
 * @date 2026-04-03
 */
@RestController
@RequestMapping("/system/activity/template")
public class ActivityTemplateController extends BaseController {

    @Autowired
    private IActivityTemplateService activityTemplateService;

    /**
     * 查询活动模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityTemplate activityTemplate) {
        startPage();
        List<ActivityTemplate> list = activityTemplateService.selectActivityTemplateList(activityTemplate);
        return getDataTable(list);
    }

    /**
     * 导出活动模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:export')")
    @Log(title = "活动模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityTemplate activityTemplate) {
        List<ActivityTemplate> list = activityTemplateService.selectActivityTemplateList(activityTemplate);
        ExcelUtil<ActivityTemplate> util = new ExcelUtil<ActivityTemplate>(ActivityTemplate.class);
        util.exportExcel(response, list, "活动模板数据");
    }

    /**
     * 获取活动模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId) {
        return success(activityTemplateService.selectActivityTemplateById(templateId));
    }

    /**
     * 新增活动模板
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:add')")
    @Log(title = "活动模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivityTemplate activityTemplate) {
        return toAjax(activityTemplateService.insertActivityTemplate(activityTemplate));
    }

    /**
     * 修改活动模板
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:edit')")
    @Log(title = "活动模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActivityTemplate activityTemplate) {
        return toAjax(activityTemplateService.updateActivityTemplate(activityTemplate));
    }

    /**
     * 删除活动模板
     */
    @PreAuthorize("@ss.hasPermi('system:activityTemplate:remove')")
    @Log(title = "活动模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds) {
        return toAjax(activityTemplateService.deleteActivityTemplateByIds(templateIds));
    }
}

