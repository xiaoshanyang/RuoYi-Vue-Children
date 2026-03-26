package com.ruoyi.system.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SysPurchaseCheckItem {
    private Long itemId;
    private Long checkId;
    private Long foodId;
    private String foodName;
    private BigDecimal planQty;
    private BigDecimal realQty;
    private BigDecimal price;
    private String expireCheck;
    private String pesticideCheck;
    private String appearanceCheck;
    private String itemResult;
}