package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动信息对象 sys_activity
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public class SysActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

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
    private Long presetDuration;

    /** 实际时长(分钟) */
    @Excel(name = "实际时长(分钟)")
    private Long actualDuration;

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

    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
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

    public void setPresetDuration(Long presetDuration) 
    {
        this.presetDuration = presetDuration;
    }

    public Long getPresetDuration() 
    {
        return presetDuration;
    }

    public void setActualDuration(Long actualDuration) 
    {
        this.actualDuration = actualDuration;
    }

    public Long getActualDuration() 
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
            .append("activityName", getActivityName())
            .append("activityType", getActivityType())
            .append("className", getClassName())
            .append("activityPlace", getActivityPlace())
            .append("presetDuration", getPresetDuration())
            .append("actualDuration", getActualDuration())
            .append("activityStatus", getActivityStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
