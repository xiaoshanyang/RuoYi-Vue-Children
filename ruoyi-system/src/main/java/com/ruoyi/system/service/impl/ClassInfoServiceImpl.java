package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ClassInfo;
import com.ruoyi.system.mapper.ClassInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IClassInfoService;

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

    @Override
    public int upgradeGrade() {
        return classInfoMapper.upgradeGrade();
    }
}
