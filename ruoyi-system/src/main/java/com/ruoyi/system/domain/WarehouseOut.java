package com.ruoyi.system.domain;

import lombok.Data;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
public class WarehouseOut extends BaseEntity {
    private Long outId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outTime;
    private String createBy;
    private Integer abnormalStatus;
    private String abnormalNote;
    private String remark;

    private List<WarehouseOutItem> items;
}
