package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ActivityTemplate;
import com.ruoyi.system.mapper.ActivityTemplateMapper;
import com.ruoyi.system.service.IActivityTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动模板Service业务层处理
 *
 * @author ruoyi
 * @date 2026-03-22
 */
@Service
public class ActivityTemplateServiceImpl implements IActivityTemplateService {

    @Autowired
    private ActivityTemplateMapper activityTemplateMapper;

    @Override
    public List<ActivityTemplate> selectActivityTemplateList(ActivityTemplate activityTemplate) {
        return activityTemplateMapper.selectActivityTemplateList(activityTemplate);
    }

    @Override
    public ActivityTemplate selectActivityTemplateById(Long templateId) {
        return activityTemplateMapper.selectActivityTemplateById(templateId);
    }

    @Override
    public int insertActivityTemplate(ActivityTemplate activityTemplate) {
        return activityTemplateMapper.insertActivityTemplate(activityTemplate);
    }

    @Override
    public int updateActivityTemplate(ActivityTemplate activityTemplate) {
        return activityTemplateMapper.updateActivityTemplate(activityTemplate);
    }

    @Override
    public int deleteActivityTemplateById(Long templateId) {
        return activityTemplateMapper.deleteActivityTemplateById(templateId);
    }

    @Transactional
    @Override
    public int deleteActivityTemplateByIds(Long[] templateIds) {
        int rows = templateIds.length;
        for (Long templateId : templateIds) {
            activityTemplateMapper.deleteActivityTemplateById(templateId);
        }
        return rows;
    }
}

