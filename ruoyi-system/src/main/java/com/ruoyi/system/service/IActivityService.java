package com.ruoyi.system.service;

import com.ruoyi.system.domain.Activity;

import java.util.List;

/**
 * 活动信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface IActivityService
{
    List<Activity> selectActivityList(Activity activity);
    Activity selectActivityById(Long activityId);
    int insertActivity(Activity activity);
    int updateActivity(Activity activity);
    int deleteActivityById(Long activityId);
    int deleteActivityByIds(Long[] activityIds);
}
