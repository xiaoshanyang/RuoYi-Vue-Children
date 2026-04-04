package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ActivityTemplate;

import java.util.List;

public interface ActivityTemplateMapper {
    List<ActivityTemplate> selectActivityTemplateList(ActivityTemplate activityTemplate);
    ActivityTemplate selectActivityTemplateById(Long templateId);
    int insertActivityTemplate(ActivityTemplate activityTemplate);
    int updateActivityTemplate(ActivityTemplate activityTemplate);
    int deleteActivityTemplateById(Long templateId);
}