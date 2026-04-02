package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.service.IActivityService;
import com.ruoyi.system.service.IClassInfoService;
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
import com.ruoyi.system.domain.ClassInfo;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/system/activity/activityInfo")
public class ActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IClassInfoService classInfoService;

    @PreAuthorize("@ss.hasPermi('system:activityInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Activity activity) {
        startPage();
        List<Activity> list = activityService.selectActivityList(activity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:activityInfo:query')")
    @GetMapping("/{activityId}")
    public AjaxResult getInfo(@PathVariable Long activityId) {
        return success(activityService.selectActivityById(activityId));
    }

    @PreAuthorize("@ss.hasPermi('system:activityInfo:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Activity activity) {
        return toAjax(activityService.insertActivity(activity));
    }

    @PreAuthorize("@ss.hasPermi('system:activityInfo:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Activity activity) {
        activity.setUpdateTime(DateUtils.getNowDate());
        activity.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return toAjax(activityService.updateActivity(activity));
    }

    @PreAuthorize("@ss.hasPermi('system:activityInfo:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds) {
        return toAjax(activityService.deleteActivityByIds(activityIds));
    }

    // 下拉班级列表
    @PreAuthorize("@ss.hasPermi('system:classInfo:list')")
    @GetMapping("/classList")
    public AjaxResult classList() {
        ClassInfo info = new ClassInfo();
        info.setStatus("0");
        return success(classInfoService.selectClassInfoList(info));
    }
}
