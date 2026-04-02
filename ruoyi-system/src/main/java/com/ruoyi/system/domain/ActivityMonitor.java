package com.ruoyi.system.domain;

import java.util.Date;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动监控记录对象 sys_activity_monitor
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Data
public class ActivityMonitor extends BaseEntity {
    private Long monitorId;
    private Long activityId;
    private Date monitorTime;

    private Integer monitorStatus; // 0正常 1异常
    private Integer orderStatus;    // 0好 1一般 2差
    private Integer safeHidden;     // 0无 1有
    private Integer childEmotion;   // 0好 1一般 2差

    private String abnormalDesc;    // 异常描述
    private String handleMethod;    // 处理措施
    private String handleResult;    // 处理结果

    private String monitorBy;       // 监控人
}
