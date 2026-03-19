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
import com.ruoyi.system.domain.SysPurchaseCheck;
import com.ruoyi.system.service.ISysPurchaseCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 食材采购验收Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/system/check")
public class SysPurchaseCheckController extends BaseController
{
    @Autowired
    private ISysPurchaseCheckService sysPurchaseCheckService;

    /**
     * 查询食材采购验收列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPurchaseCheck sysPurchaseCheck)
    {
        startPage();
        List<SysPurchaseCheck> list = sysPurchaseCheckService.selectSysPurchaseCheckList(sysPurchaseCheck);
        return getDataTable(list);
    }

    /**
     * 导出食材采购验收列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "食材采购验收", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPurchaseCheck sysPurchaseCheck)
    {
        List<SysPurchaseCheck> list = sysPurchaseCheckService.selectSysPurchaseCheckList(sysPurchaseCheck);
        ExcelUtil<SysPurchaseCheck> util = new ExcelUtil<SysPurchaseCheck>(SysPurchaseCheck.class);
        util.exportExcel(response, list, "食材采购验收数据");
    }

    /**
     * 获取食材采购验收详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysPurchaseCheckService.selectSysPurchaseCheckById(id));
    }

    /**
     * 新增食材采购验收
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "食材采购验收", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPurchaseCheck sysPurchaseCheck)
    {
        return toAjax(sysPurchaseCheckService.insertSysPurchaseCheck(sysPurchaseCheck));
    }

    /**
     * 修改食材采购验收
     */
    @PreAuthorize("@ss.hasPermi('system:check:edit')")
    @Log(title = "食材采购验收", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPurchaseCheck sysPurchaseCheck)
    {
        return toAjax(sysPurchaseCheckService.updateSysPurchaseCheck(sysPurchaseCheck));
    }

    /**
     * 删除食材采购验收
     */
    @PreAuthorize("@ss.hasPermi('system:check:remove')")
    @Log(title = "食材采购验收", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysPurchaseCheckService.deleteSysPurchaseCheckByIds(ids));
    }
}
