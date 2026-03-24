package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class SupplierAuditDTO {
    /** 供应商ID */
    private Long supplierId;
    /** 审核结果：1=通过 0=驳回 */
    private String auditResult;
    /** 审核意见 */
    private String auditRemark;
}
