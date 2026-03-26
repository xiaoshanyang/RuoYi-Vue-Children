package com.ruoyi.system.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WarehouseBatch {
    private Long batchId;
    private Long foodId;
    private String foodName;
    private Long purchaseId;
    private Long checkId;
    private BigDecimal inQty;
    private BigDecimal remainQty;
    private Date inTime;
    private String createBy;
}
