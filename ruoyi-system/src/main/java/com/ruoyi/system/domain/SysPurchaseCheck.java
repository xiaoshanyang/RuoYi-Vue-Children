package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysPurchaseCheck extends BaseEntity {
    private Long checkId;
    private Long purchaseId;
    private String purchaseNo;
    private Long supplierId;
    private String supplierName;
    private String checkResult;
    private String checkRemark;
    private String checkBy;
    private Date checkTime;

    // 明细
    private List<SysPurchaseCheckItem> items;
}