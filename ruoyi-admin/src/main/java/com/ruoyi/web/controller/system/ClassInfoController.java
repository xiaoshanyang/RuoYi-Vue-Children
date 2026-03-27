package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.ClassInfo;
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
import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.service.IClassInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息（按届命名）Controller
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@RestController
@RequestMapping("/activity/classInfo")
public class ClassInfoController extends BaseController
{
    @Autowired
    private IClassInfoService classInfoService;

    /**
     * 查询班级信息（按届命名）列表
     */
    @PreAuthorize("@ss.hasPermi('system:classInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClassInfo classInfo) {
        startPage();
        List<ClassInfo> list = classInfoService.selectClassInfoList(classInfo);
        return getDataTable(list);
    }

    /**
     * 获取班级信息（按届命名）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:classInfo:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId) {
        return success(classInfoService.selectClassInfoById(classId));
    }

    /**
     * 新增班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('system:classInfo:add')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClassInfo classInfo) {
        return toAjax(classInfoService.insertClassInfo(classInfo));
    }

    /**
     * 修改班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('system:classInfo:edit')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClassInfo classInfo) {
        return toAjax(classInfoService.updateClassInfo(classInfo));
    }

    /**
     * 删除班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('system:classInfo:remove')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] classIds) {
        return toAjax(classInfoService.deleteClassInfoByIds(classIds));
    }

    // 一键升班接口
    @PreAuthorize("@ss.hasPermi('system:classInfo:edit')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.UPDATE)
    @PostMapping("/upgrade")
    public AjaxResult upgrade() {
        return toAjax(classInfoService.upgradeGrade());
    }
}
