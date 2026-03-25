package com.ruoyi.system.domain;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 采购申请明细对象 sys_purchase_item
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Data
public class SysPurchaseItem {
   private Long itemId;
    private Long purchaseId;
    private Long foodId;
    private String foodName;
    private String foodType;
    private String unit;
    private BigDecimal price;
    private BigDecimal qty;
    private BigDecimal subTotal;
}
