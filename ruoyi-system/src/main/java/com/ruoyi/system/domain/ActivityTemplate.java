package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class ActivityTemplate extends BaseEntity {
    private Long templateId;
    private String tempName;
    private String tempContent;
    private String safeNotice;
    private String remark;
}
