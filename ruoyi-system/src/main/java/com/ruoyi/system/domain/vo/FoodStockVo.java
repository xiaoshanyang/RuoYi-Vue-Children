package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FoodStockVo {
  private Long foodId;
  private String foodName;
  private String unit;
  private String foodType;
  private Long expireDays;
  private BigDecimal totalStock; // 总库存
}
