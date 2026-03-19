package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动监控记录对象 sys_activity_monitor
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public class SysActivityMonitor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long activityId;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 超时提醒时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "超时提醒时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date remindTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setRemindTime(Date remindTime) 
    {
        this.remindTime = remindTime;
    }

    public Date getRemindTime() 
    {
        return remindTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("remindTime", getRemindTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
