package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClassInfo extends BaseEntity {
    private Long classId;
    private String classCode;
    private String className;
    private Integer grade;
    private Integer entryYear;
    private String schoolYear;
    private Integer status;
    private String remark;

    private String teacherName;
    private Long teacherId;
}