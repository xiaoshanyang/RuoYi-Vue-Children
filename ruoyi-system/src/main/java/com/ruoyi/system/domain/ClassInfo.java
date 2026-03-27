package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class ClassInfo extends BaseEntity {
    private Long classId;
    private String classCode;      // 固定编号 C202401
    private String className;      // 当前名称 小一班
    private Integer grade;         // 1小班 2中班 3大班
    private Integer entryYear;     // 入园年份
    private String teacher;        // 班主任
    private String status;         // 0正常 1毕业
    private String remark;
}