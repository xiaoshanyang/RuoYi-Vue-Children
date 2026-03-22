package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级信息（按届命名）对象 sys_class
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public class SysClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID（唯一标识） */
    private Long id;

    /** 届数（如：2025、2026） */
    @Excel(name = "届数", readConverterExp = "如=：2025、2026")
    private String gradeYear;

    /** 班级编码（如：小一、中二、大一） */
    @Excel(name = "班级编码", readConverterExp = "如=：小一、中二、大一")
    private String classCode;

    /** 班级全称（如：2025级小一班） */
    @Excel(name = "班级全称", readConverterExp = "如=：2025级小一班")
    private String className;

    /** 班级类型：小班/中班/大班 */
    @Excel(name = "班级类型：小班/中班/大班")
    private String classType;

    /** 班主任ID（关联sys_user.id） */
    @Excel(name = "班主任ID", readConverterExp = "关=联sys_user.id")
    private Long teacherId;

    /** 班主任姓名 */
    @Excel(name = "班主任姓名")
    private String teacherName;

    /** 幼儿人数 */
    @Excel(name = "幼儿人数")
    private Long studentCount;

    /** 状态：0正常 1停用 */
    @Excel(name = "状态：0正常 1停用")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setGradeYear(String gradeYear) 
    {
        this.gradeYear = gradeYear;
    }

    public String getGradeYear() 
    {
        return gradeYear;
    }

    public void setClassCode(String classCode) 
    {
        this.classCode = classCode;
    }

    public String getClassCode() 
    {
        return classCode;
    }

    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }

    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }

    public void setStudentCount(Long studentCount) 
    {
        this.studentCount = studentCount;
    }

    public Long getStudentCount() 
    {
        return studentCount;
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
            .append("gradeYear", getGradeYear())
            .append("classCode", getClassCode())
            .append("className", getClassName())
            .append("classType", getClassType())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("studentCount", getStudentCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
