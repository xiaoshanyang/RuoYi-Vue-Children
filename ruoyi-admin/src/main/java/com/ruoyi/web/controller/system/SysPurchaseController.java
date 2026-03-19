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
import com.ruoyi.system.domain.SysPurchase;
import com.ruoyi.system.service.ISysPurchaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 食材采购申请Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/system/purchase")
public class SysPurchaseController extends BaseController
{
    @Autowired
    private ISysPurchaseService sysPurchaseService;

    /**
     * 查询食材采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPurchase sysPurchase)
    {
        startPage();
        List<SysPurchase> list = sysPurchaseService.selectSysPurchaseList(sysPurchase);
        return getDataTable(list);
    }

    /**
     * 导出食材采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:export')")
    @Log(title = "食材采购申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPurchase sysPurchase)
    {
        List<SysPurchase> list = sysPurchaseService.selectSysPurchaseList(sysPurchase);
        ExcelUtil<SysPurchase> util = new ExcelUtil<SysPurchase>(SysPurchase.class);
        util.exportExcel(response, list, "食材采购申请数据");
    }

    /**
     * 获取食材采购申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysPurchaseService.selectSysPurchaseById(id));
    }

    /**
     * 新增食材采购申请
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:add')")
    @Log(title = "食材采购申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPurchase sysPurchase)
    {
        return toAjax(sysPurchaseService.insertSysPurchase(sysPurchase));
    }

    /**
     * 修改食材采购申请
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:edit')")
    @Log(title = "食材采购申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPurchase sysPurchase)
    {
        return toAjax(sysPurchaseService.updateSysPurchase(sysPurchase));
    }

    /**
     * 删除食材采购申请
     */
    @PreAuthorize("@ss.hasPermi('system:purchase:remove')")
    @Log(title = "食材采购申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysPurchaseService.deleteSysPurchaseByIds(ids));
    }
}
