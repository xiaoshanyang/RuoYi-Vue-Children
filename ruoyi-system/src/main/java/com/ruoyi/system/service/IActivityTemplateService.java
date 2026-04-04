package com.ruoyi.system.service;


import com.ruoyi.system.domain.ActivityTemplate;

import java.util.List;

/**
 * 班级信息（按届命名）Service接口
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public interface IActivityTemplateService {
    List<ActivityTemplate> selectActivityTemplateList(ActivityTemplate activityTemplate);
    ActivityTemplate selectActivityTemplateById(Long templateId);
    int insertActivityTemplate(ActivityTemplate activityTemplate);
    int updateActivityTemplate(ActivityTemplate activityTemplate);
    int deleteActivityTemplateById(Long templateId);
    int deleteActivityTemplateByIds(Long[] templateIds);
}
