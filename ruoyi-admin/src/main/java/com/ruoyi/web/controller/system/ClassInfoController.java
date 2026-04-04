package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.ClassInfo;
import com.ruoyi.system.domain.ClassTeacher;
import com.ruoyi.system.service.IClassTeacherService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.system.service.IClassInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息（按届命名）Controller
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@RestController
@RequestMapping("/system/activity/classInfo")
public class ClassInfoController extends BaseController
{
    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IClassTeacherService classTeacherService;

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

    // 获取班级教师
    @PreAuthorize("@ss.hasPermi('system:classInfo:edit')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.UPDATE)
    @GetMapping("/teachers/{classId}")
    public AjaxResult teachers(@PathVariable Long classId) {
        return success(classTeacherService.getTeachersByClassId(classId));
    }

    @PreAuthorize("@ss.hasPermi('system:classInfo:query')")
    @GetMapping("/getMyClass")
    public AjaxResult getMyClass() {
        Long userId = SecurityUtils.getUserId(); // 获取当前登录人ID
        ClassTeacher ct = classTeacherService.getMyClass(userId);
        return success(ct);
    }

    // 保存班级教师（含班主任）
    @PreAuthorize("@ss.hasPermi('system:classInfo:edit')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.UPDATE)
    @PostMapping("/saveTeachers")
    public AjaxResult saveTeachers(@RequestBody Map<String, Object> params) {
        Long classId = Long.valueOf(params.get("classId").toString());
        List<Long> userIds = (List<Long>) params.get("userIds");
        Long leaderUserId = params.get("leaderUserId") != null
                ? Long.valueOf(params.get("leaderUserId").toString())
                : null;

        classTeacherService.saveClassTeachers(classId, userIds, leaderUserId);
        return success();
    }

    // 获取教师下拉（角色：teacher）
    @GetMapping("/teacherList")
    public AjaxResult teacherList() {
        List<SysUser> list = sysUserService.selectUserByRoleKey("teacher");
        return success(list);
    }
}
