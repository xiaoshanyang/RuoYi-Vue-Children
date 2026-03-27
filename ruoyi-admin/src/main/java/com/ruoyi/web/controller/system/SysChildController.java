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
import com.ruoyi.system.service.ISysChildService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 幼儿信息（无冗余班级名称）Controller
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@RestController
@RequestMapping("/base/child")
public class SysChildController extends BaseController
{
    @Autowired
    private ISysChildService sysChildService;

    /**
     * 查询幼儿信息（无冗余班级名称）列表
     */
    @PreAuthorize("@ss.hasPermi('base:child:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysChild sysChild)
    {
        startPage();
        List<SysChild> list = sysChildService.selectSysChildList(sysChild);
        return getDataTable(list);
    }

    /**
     * 导出幼儿信息（无冗余班级名称）列表
     */
    @PreAuthorize("@ss.hasPermi('base:child:export')")
    @Log(title = "幼儿信息（无冗余班级名称）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysChild sysChild)
    {
        List<SysChild> list = sysChildService.selectSysChildList(sysChild);
        ExcelUtil<SysChild> util = new ExcelUtil<SysChild>(SysChild.class);
        util.exportExcel(response, list, "幼儿信息（无冗余班级名称）数据");
    }

    /**
     * 获取幼儿信息（无冗余班级名称）详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:child:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysChildService.selectSysChildById(id));
    }

    /**
     * 新增幼儿信息（无冗余班级名称）
     */
    @PreAuthorize("@ss.hasPermi('base:child:add')")
    @Log(title = "幼儿信息（无冗余班级名称）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysChild sysChild)
    {
        return toAjax(sysChildService.insertSysChild(sysChild));
    }

    /**
     * 修改幼儿信息（无冗余班级名称）
     */
    @PreAuthorize("@ss.hasPermi('base:child:edit')")
    @Log(title = "幼儿信息（无冗余班级名称）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysChild sysChild)
    {
        return toAjax(sysChildService.updateSysChild(sysChild));
    }

    /**
     * 删除幼儿信息（无冗余班级名称）
     */
    @PreAuthorize("@ss.hasPermi('base:child:remove')")
    @Log(title = "幼儿信息（无冗余班级名称）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysChildService.deleteSysChildByIds(ids));
    }
}
