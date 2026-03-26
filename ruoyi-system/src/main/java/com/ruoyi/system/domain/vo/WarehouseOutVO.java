package com.ruoyi.system.domain.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class WarehouseOutVO {
    private Date outTime;
    private Integer abnormalStatus;
    private String abnormalNote;
    private List<WarehouseOutItemVo> itemList;
}
