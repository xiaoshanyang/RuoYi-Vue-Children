package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IActivityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 活动信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> selectActivityList(Activity activity) {
        return activityMapper.selectActivityList(activity);
    }

    @Override
    public Activity selectActivityById(Long activityId) {
        return activityMapper.selectActivityById(activityId);
    }

    @Override
    public int insertActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public int deleteActivityById(Long activityId) {
        return activityMapper.deleteActivityById(activityId);
    }

    @Transactional
    @Override
    public int deleteActivityByIds(Long[] activityIds) {
        int rows = activityIds.length;
        for(Long activityId : activityIds){
            activityMapper.deleteActivityById(activityId);
        }
        return rows;
    }


}
