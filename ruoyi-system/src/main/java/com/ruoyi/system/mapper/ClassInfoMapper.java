package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ClassInfo;

import java.util.List;

public interface ClassInfoMapper {
    List<ClassInfo> selectClassInfoList(ClassInfo classInfo);
    ClassInfo selectClassInfoById(Long classId);
    int insertClassInfo(ClassInfo classInfo);
    int updateClassInfo(ClassInfo classInfo);
    int deleteClassInfoById(Long classId);
    int upgradeGrade(); // 一键升班
}