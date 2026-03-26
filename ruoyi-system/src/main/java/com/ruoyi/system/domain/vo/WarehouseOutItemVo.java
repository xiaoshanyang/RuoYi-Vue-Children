package com.ruoyi.system.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WarehouseOutItemVo {
    private Long foodId;
    private String foodName;
    private BigDecimal outQty;
}