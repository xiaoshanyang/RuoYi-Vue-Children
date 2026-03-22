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
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/purchase/supplier")
public class SysSupplierController extends BaseController
{
    @Autowired
    private ISysSupplierService sysSupplierService;

    /**
     * 查询供应商信息列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSupplier sysSupplier)
    {
        startPage();
        List<SysSupplier> list = sysSupplierService.selectSysSupplierList(sysSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商信息列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:export')")
    @Log(title = "供应商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSupplier sysSupplier)
    {
        List<SysSupplier> list = sysSupplierService.selectSysSupplierList(sysSupplier);
        ExcelUtil<SysSupplier> util = new ExcelUtil<SysSupplier>(SysSupplier.class);
        util.exportExcel(response, list, "供应商信息数据");
    }

    /**
     * 获取供应商信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSupplierService.selectSysSupplierById(id));
    }

    /**
     * 新增供应商信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:add')")
    @Log(title = "供应商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSupplier sysSupplier)
    {
        return toAjax(sysSupplierService.insertSysSupplier(sysSupplier));
    }

    /**
     * 修改供应商信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:edit')")
    @Log(title = "供应商信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSupplier sysSupplier)
    {
        return toAjax(sysSupplierService.updateSysSupplier(sysSupplier));
    }

    /**
     * 删除供应商信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:remove')")
    @Log(title = "供应商信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSupplierService.deleteSysSupplierByIds(ids));
    }
}
