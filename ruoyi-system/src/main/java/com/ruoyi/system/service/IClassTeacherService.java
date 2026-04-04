package com.ruoyi.system.service;

import com.ruoyi.system.domain.ClassTeacher;

import java.util.List;

public interface IClassTeacherService {
    List<ClassTeacher> getTeachersByClassId(Long classId);
    void saveClassTeachers(Long classId, List<Long> userIds, Long leaderUserId);
    ClassTeacher getMyClass(Long classId);
}
