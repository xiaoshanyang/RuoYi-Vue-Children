package com.ruoyi.system.domain;

import lombok.Data;
import java.util.Date;

@Data
public class WarehouseOut {
    private Long outId;
    private Date outTime;
    private String createBy;
    private Integer abnormalStatus;
    private String abnormalNote;
    private String remark;
}
