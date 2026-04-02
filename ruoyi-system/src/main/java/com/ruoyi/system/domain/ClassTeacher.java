package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClassTeacher extends BaseEntity {
    private Long id;
    private Long classId;
    private Long userId;
    private String userName;

    private String isLeader;    // 1班主任 0普通
    private String status;      // 0在职 1调离

    private Date createTime;
    private String remark;
}
