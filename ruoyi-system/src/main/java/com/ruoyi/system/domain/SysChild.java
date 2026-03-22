package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 幼儿信息（无冗余班级名称）对象 sys_child
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public class SysChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 幼儿姓名 */
    @Excel(name = "幼儿姓名")
    private String childName;

    /** 班级ID（唯一关联sys_class.id） */
    @Excel(name = "班级ID", readConverterExp = "唯=一关联sys_class.id")
    private Long classId;

    /** 性别：0男 1女 */
    @Excel(name = "性别：0男 1女")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 过敏信息（如：鸡蛋、牛奶） */
    @Excel(name = "过敏信息", readConverterExp = "如=：鸡蛋、牛奶")
    private String allergyInfo;

    /** 家长姓名 */
    @Excel(name = "家长姓名")
    private String parentName;

    /** 家长联系电话 */
    @Excel(name = "家长联系电话")
    private String parentPhone;

    /** 入园时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入园时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollTime;

    /** 状态：0正常 1请假 2退学 */
    @Excel(name = "状态：0正常 1请假 2退学")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setChildName(String childName) 
    {
        this.childName = childName;
    }

    public String getChildName() 
    {
        return childName;
    }

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }

    public void setAllergyInfo(String allergyInfo) 
    {
        this.allergyInfo = allergyInfo;
    }

    public String getAllergyInfo() 
    {
        return allergyInfo;
    }

    public void setParentName(String parentName) 
    {
        this.parentName = parentName;
    }

    public String getParentName() 
    {
        return parentName;
    }

    public void setParentPhone(String parentPhone) 
    {
        this.parentPhone = parentPhone;
    }

    public String getParentPhone() 
    {
        return parentPhone;
    }

    public void setEnrollTime(Date enrollTime) 
    {
        this.enrollTime = enrollTime;
    }

    public Date getEnrollTime() 
    {
        return enrollTime;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("childName", getChildName())
            .append("classId", getClassId())
            .append("gender", getGender())
            .append("birthday", getBirthday())
            .append("allergyInfo", getAllergyInfo())
            .append("parentName", getParentName())
            .append("parentPhone", getParentPhone())
            .append("enrollTime", getEnrollTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
