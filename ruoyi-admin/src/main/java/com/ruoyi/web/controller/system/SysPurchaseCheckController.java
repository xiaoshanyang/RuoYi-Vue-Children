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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 食材采购验收Controller
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@RestController
@RequestMapping("/system/purchaseCheck")
public class SysPurchaseCheckController extends BaseController
{
    @Autowired
    private ISysPurchaseCheckService sysPurchaseCheckService;

    @PreAuthorize("@ss.hasPermi('system:purchaseCheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPurchaseCheck check) {
        startPage();
        List<SysPurchaseCheck> list = sysPurchaseCheckService.selectPurchaseCheckList(check);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:purchaseCheck:query')")
    @GetMapping("/{checkId}")
    public AjaxResult getInfo(@PathVariable Long checkId) {
        return success(sysPurchaseCheckService.selectPurchaseCheckById(checkId));
    }

    @PreAuthorize("@ss.hasPermi('system:purchaseCheck:add')")
    @Log(title = "采购验收", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPurchaseCheck check) {
        return toAjax(sysPurchaseCheckService.insertPurchaseCheck(check));
    }
}
