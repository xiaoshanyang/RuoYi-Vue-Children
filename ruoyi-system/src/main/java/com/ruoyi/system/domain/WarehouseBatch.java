package com.ruoyi.system.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WarehouseBatch {
    private Long batchId;
    private Long foodId;
    private String foodName;
    private String foodType;
    private String unit;
    private Long purchaseId;
    private String purchaseNo;
    private String supplierName;
    private Long checkId;
    private BigDecimal inQty;
    private BigDecimal remainQty;
    private Date inTime;
    private String createBy;
}
