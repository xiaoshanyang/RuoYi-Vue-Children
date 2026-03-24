package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class SupplierApplyDTO {
    /** 供应商ID */
    private Long supplierId;
    /** 申请原因 */
    private String reason;
}