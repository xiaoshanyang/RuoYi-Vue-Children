package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ClassInfo;
import com.ruoyi.system.domain.ClassTeacher;
import com.ruoyi.system.mapper.ClassInfoMapper;
import com.ruoyi.system.mapper.ClassTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IClassInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 班级信息（按届命名）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@Service
public class ClassInfoServiceImpl implements IClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private ClassTeacherMapper classTeacherMapper;

    @Override
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo) {
        return classInfoMapper.selectClassInfoList(classInfo);
    }

    @Override
    public ClassInfo selectClassInfoById(Long classId) {
        return classInfoMapper.selectClassInfoById(classId);
    }

    @Override
    public int insertClassInfo(ClassInfo classInfo) {
        return classInfoMapper.insertClassInfo(classInfo);
    }

    @Override
    public int updateClassInfo(ClassInfo classInfo) {
        return classInfoMapper.updateClassInfo(classInfo);
    }

    @Override
    public int deleteClassInfoById(Long classId) {
        return classInfoMapper.deleteClassInfoById(classId);
    }

    @Transactional
    @Override
    public int deleteClassInfoByIds(Long[] classIds) {
        int id = classIds.length;
        for (Long classId : classIds) {
            classInfoMapper.deleteClassInfoById(classId);
        }
        return id;
    }

    @Transactional
    @Override
    public int upgradeGrade() {

        List<ClassInfo> classInfos = classInfoMapper.selectClassInfoList(new ClassInfo());
        for (ClassInfo classInfo : classInfos) {
            if (classInfo.getStatus() == 0) {
                upgradeGrade(classInfo.getClassId());
            }
        }
        return classInfos.size();

    }

    private void upgradeGrade(Long classId) {
        // 1. 查询旧班级
        ClassInfo oldClass = classInfoMapper.selectClassInfoById(classId);
        if (oldClass == null || oldClass.getStatus() == 1) {
            throw new RuntimeException("班级不可升班");
        }

        // 2. 旧班级标记为已升班
        oldClass.setStatus(1);
        classInfoMapper.updateClassInfo(oldClass);

        // 3. 生成新班级（年级+1）
        ClassInfo newClass = new ClassInfo();
        newClass.setClassName(oldClass.getClassName());
        newClass.setGrade(oldClass.getGrade() + 1);
        newClass.setSchoolYear("2026-2027"); // 可动态获取
        newClass.setStatus(0);
        classInfoMapper.insertClassInfo(newClass);

        // 4. 复制教师关联
        List<ClassTeacher> list = classTeacherMapper.selectByClassId(classId);
        for (ClassTeacher ct : list) {
            ClassTeacher newCt = new ClassTeacher();
            newCt.setClassId(newClass.getClassId());
            newCt.setUserId(ct.getUserId());
            newCt.setUserName(ct.getUserName());
            classTeacherMapper.insertClassTeacher(newCt);
        }
    }
}
