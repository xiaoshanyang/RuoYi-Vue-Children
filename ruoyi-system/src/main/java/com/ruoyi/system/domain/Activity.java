package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Activity extends BaseEntity {
    private Long activityId;
    private Long classId;

    private Long templateId;     // 模板ID
    private String tempName;     // 快照：活动名称（来自模板）
    private String activityName;

    // 快照字段（新增时从班级带过来，永不修改）
    private String classCode;
    private String gradeName;
    private String classFullName;

    private Date beginTime;
    private Date endTime;

    private String place;
    private String chargeBy;
    private String chargeName;
    private String content;
    private String remark;

    private Integer isClosed; // 0正常 1已取消
    private String cancelReason; // 取消原因
    private Date cancelTime;     // 取消时间
}
