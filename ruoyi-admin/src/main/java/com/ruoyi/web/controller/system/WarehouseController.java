package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.vo.WarehouseOutVO;
import com.ruoyi.system.service.IWarehouseService;
import com.ruoyi.system.domain.SysPurchaseCheck;
import com.ruoyi.system.domain.WarehouseBatch;
import com.ruoyi.system.domain.vo.FoodStockVo;

@RestController
@RequestMapping("/system/warehouse")
public class WarehouseController extends BaseController {

  @Autowired
  private IWarehouseService warehouseService;

  @PreAuthorize("@ss.hasPermi('system:warehouseOut:add')")
  @Log(title = "仓库管理", businessType = BusinessType.INSERT)
  @PostMapping("/createOut")
  public AjaxResult createOut(@RequestBody WarehouseOutVO vo) {
      warehouseService.createWarehouseOut(vo);
      return AjaxResult.success("领用成功");
  }

  @PreAuthorize("@ss.hasPermi('system:warehouse:list')")
  @GetMapping("/stock/list")
  public TableDataInfo list(FoodStockVo foodStockVo) {
     startPage();
        List<FoodStockVo> list = warehouseService.selectFoodStockList(foodStockVo);
        return getDataTable(list);
  }

  @PreAuthorize("@ss.hasPermi('system:warehouseBatch:list')")
  @GetMapping("/batch/list")
  public TableDataInfo batchList(Long foodId, String foodName) {
      startPage();
      List<WarehouseBatch> list = warehouseService.selectBatchList(foodId, foodName);
      return getDataTable(list);
  }

  @PreAuthorize("@ss.hasPermi('system:warehouseOut:list')")
  @GetMapping("/out/list")
  public TableDataInfo list(Integer abnormalStatus, String startTime, String endTime) {
      startPage();
      List<Map<String, Object>> list = warehouseService.selectOutList(abnormalStatus, startTime, endTime);
      return getDataTable(list);
  }

  @PreAuthorize("@ss.hasPermi('system:warehouseOut:query')")
  @GetMapping("/out/{outId}")
  public AjaxResult getInfo(@PathVariable Long outId) {
      return success(warehouseService.selectWarehouseOutById(outId));
  }
}
