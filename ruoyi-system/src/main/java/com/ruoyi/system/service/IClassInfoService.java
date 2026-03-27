package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.ClassInfo;
import com.ruoyi.system.domain.SysClass;

/**
 * 班级信息（按届命名）Service接口
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public interface IClassInfoService {
    List<ClassInfo> selectClassInfoList(ClassInfo classInfo);
    ClassInfo selectClassInfoById(Long classId);
    int insertClassInfo(ClassInfo classInfo);
    int updateClassInfo(ClassInfo classInfo);
    int deleteClassInfoById(Long classId);
    int upgradeGrade();
}
