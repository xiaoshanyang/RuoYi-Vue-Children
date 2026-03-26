package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 食材采购申请对象 sys_purchase
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Data
public class SysPurchase extends BaseEntity
{
    private Long purchaseId;
    private String purchaseNo;
    private Long supplierId;
    private String supplierName;
    private BigDecimal totalAmount;
    private BigDecimal actualAmount;
    private String status;
    private String remark;
    private String auditBy;
    private Date auditTime;

    // 明细
    private List<SysPurchaseItem> items;
}
