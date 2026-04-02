package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Activity extends BaseEntity {
    private Long activityId;
    private Long classId;

    // 快照字段（新增时从班级带过来，永不修改）
    private String classCode;
    private String gradeName;
    private String classFullName;

    private String activityName;
    private Date activityTime;
    private String place;
    private String chargeBy;
    private String content;
    private String remark;
}
