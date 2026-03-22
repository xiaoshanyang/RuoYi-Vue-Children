package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.service.ISysClassService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息（按届命名）Controller
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@RestController
@RequestMapping("/base/class")
public class SysClassController extends BaseController
{
    @Autowired
    private ISysClassService sysClassService;

    /**
     * 查询班级信息（按届命名）列表
     */
    @PreAuthorize("@ss.hasPermi('base:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysClass sysClass)
    {
        startPage();
        List<SysClass> list = sysClassService.selectSysClassList(sysClass);
        return getDataTable(list);
    }

    /**
     * 导出班级信息（按届命名）列表
     */
    @PreAuthorize("@ss.hasPermi('base:class:export')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysClass sysClass)
    {
        List<SysClass> list = sysClassService.selectSysClassList(sysClass);
        ExcelUtil<SysClass> util = new ExcelUtil<SysClass>(SysClass.class);
        util.exportExcel(response, list, "班级信息（按届命名）数据");
    }

    /**
     * 获取班级信息（按届命名）详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:class:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysClassService.selectSysClassById(id));
    }

    /**
     * 新增班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('base:class:add')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysClass sysClass)
    {
        return toAjax(sysClassService.insertSysClass(sysClass));
    }

    /**
     * 修改班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('base:class:edit')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysClass sysClass)
    {
        return toAjax(sysClassService.updateSysClass(sysClass));
    }

    /**
     * 删除班级信息（按届命名）
     */
    @PreAuthorize("@ss.hasPermi('base:class:remove')")
    @Log(title = "班级信息（按届命名）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysClassService.deleteSysClassByIds(ids));
    }
}
