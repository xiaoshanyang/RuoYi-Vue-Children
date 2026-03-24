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
import com.ruoyi.system.domain.SysFoodInfo;
import com.ruoyi.system.service.ISysFoodInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 食材基础信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
@RestController
@RequestMapping("/system/foodInfo")
public class SysFoodInfoController extends BaseController
{
    @Autowired
    private ISysFoodInfoService sysFoodInfoService;

    /**
     * 查询食材基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFoodInfo sysFoodInfo)
    {
        startPage();
        List<SysFoodInfo> list = sysFoodInfoService.selectSysFoodInfoList(sysFoodInfo);
        return getDataTable(list);
    }

    /**
     * 导出食材基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:export')")
    @Log(title = "食材基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFoodInfo sysFoodInfo)
    {
        List<SysFoodInfo> list = sysFoodInfoService.selectSysFoodInfoList(sysFoodInfo);
        ExcelUtil<SysFoodInfo> util = new ExcelUtil<SysFoodInfo>(SysFoodInfo.class);
        util.exportExcel(response, list, "食材基础信息数据");
    }

    /**
     * 获取食材基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:query')")
    @GetMapping(value = "/{foodId}")
    public AjaxResult getInfo(@PathVariable("foodId") Long foodId)
    {
        return success(sysFoodInfoService.selectSysFoodInfoByFoodId(foodId));
    }

    /**
     * 新增食材基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:add')")
    @Log(title = "食材基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFoodInfo sysFoodInfo)
    {
        return toAjax(sysFoodInfoService.insertSysFoodInfo(sysFoodInfo));
    }

    /**
     * 修改食材基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:edit')")
    @Log(title = "食材基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFoodInfo sysFoodInfo)
    {
        return toAjax(sysFoodInfoService.updateSysFoodInfo(sysFoodInfo));
    }

    /**
     * 删除食材基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:foodInfo:remove')")
    @Log(title = "食材基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{foodIds}")
    public AjaxResult remove(@PathVariable Long[] foodIds)
    {
        return toAjax(sysFoodInfoService.deleteSysFoodInfoByFoodIds(foodIds));
    }
}
