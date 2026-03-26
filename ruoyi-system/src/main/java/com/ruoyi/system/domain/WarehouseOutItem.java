package com.ruoyi.system.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WarehouseOutItem {
    private Long id;
    private Long outId;
    private Long foodId;
    private String foodName;
    private BigDecimal outQty;
    private Long batchId;
}
