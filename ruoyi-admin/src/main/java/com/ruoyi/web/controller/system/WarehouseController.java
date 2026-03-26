package com.ruoyi.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.vo.WarehouseOutVO;
import com.ruoyi.system.service.IWarehouseService;

@RestController
@RequestMapping("/system/warehouse")
public class WarehouseController extends BaseController {

  @Autowired
  private IWarehouseService warehouseService;

  @PreAuthorize("@ss.hasPermi('system:warehouse:add')")
  @Log(title = "仓库管理", businessType = BusinessType.INSERT)
  @PostMapping("/createOut")
  public AjaxResult createOut(@RequestBody WarehouseOutVO vo) {
      warehouseService.createWarehouseOut(vo);
      return AjaxResult.success("领用成功");
  }

}
