package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class PurchaseAuditDTO {
    private Long purchaseId;
    private String result;   // 1通过 2驳回
    private String remark;
}
