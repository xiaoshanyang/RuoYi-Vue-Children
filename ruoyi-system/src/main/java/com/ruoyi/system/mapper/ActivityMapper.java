package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Activity;

import java.util.List;

public interface ActivityMapper {
    List<Activity> selectActivityList(Activity activity);
    Activity selectActivityById(Long activityId);
    int insertActivity(Activity activity);
    int updateActivity(Activity activity);
    int deleteActivityById(Long activityId);
}