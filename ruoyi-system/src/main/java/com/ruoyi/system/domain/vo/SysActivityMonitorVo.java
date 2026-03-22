package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 活动监控记录对象 sys_activity_monitor
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public class SysActivityMonitorVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long activityId;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 超时提醒时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "超时提醒时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date remindTime;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 活动类型：课堂活动/户外活动/午睡活动 */
    @Excel(name = "活动类型：课堂活动/户外活动/午睡活动")
    private String activityType;

    /** 参与班级 */
    @Excel(name = "参与班级")
    private String className;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String activityPlace;

    /** 预设时长(分钟) */
    @Excel(name = "预设时长(分钟)")
    private int presetDuration;

    /** 实际时长(分钟) */
    @Excel(name = "实际时长(分钟)")
    private int actualDuration;

    /** 活动状态：未开始/监控中/已完成/超时完成 */
    @Excel(name = "活动状态：未开始/监控中/已完成/超时完成")
    private String activityStatus;

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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityType(String activityType)
    {
        this.activityType = activityType;
    }

    public String getActivityType()
    {
        return activityType;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getClassName()
    {
        return className;
    }

    public void setActivityPlace(String activityPlace)
    {
        this.activityPlace = activityPlace;
    }

    public String getActivityPlace()
    {
        return activityPlace;
    }

    public void setPresetDuration(int presetDuration)
    {
        this.presetDuration = presetDuration;
    }

    public int getPresetDuration()
    {
        return presetDuration;
    }

    public void setActualDuration(int actualDuration)
    {
        this.actualDuration = actualDuration;
    }

    public int getActualDuration()
    {
        return actualDuration;
    }

    public void setActivityStatus(String activityStatus)
    {
        this.activityStatus = activityStatus;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("activityName", getActivityName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("remindTime", getRemindTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
