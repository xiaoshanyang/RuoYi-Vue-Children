package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.ClassTeacher;
import com.ruoyi.system.mapper.ClassTeacherMapper;
import com.ruoyi.system.service.IClassTeacherService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassTeacherServiceImpl implements IClassTeacherService {

    @Autowired
    private ClassTeacherMapper classTeacherMapper;

    @Autowired
    private ISysUserService userService;

    @Override
    public List<ClassTeacher> getTeachersByClassId(Long classId) {
        return classTeacherMapper.selectByClassId(classId);
    }

    @Override
    @Transactional
    public void saveClassTeachers(Long classId, List<Long> userIds, Long leaderUserId) {
        // 1. 本班原有老师全部标记为调离
        classTeacherMapper.updateStatusToLeaveByClassId(classId);

        // 2. 批量保存/恢复教师
        for (Long userId : userIds) {
            SysUser user = userService.selectUserById(userId);
            if (user == null) continue;

            ClassTeacher old = classTeacherMapper.selectByClassAndUser(classId, userId);
            if (old != null) {
                old.setStatus("0");
                old.setIsLeader("0");
                classTeacherMapper.updateClassTeacher(old);
            } else {
                ClassTeacher ct = new ClassTeacher();
                ct.setClassId(classId);
                ct.setUserId(userId);
                ct.setUserName(user.getUserName());
                ct.setStatus("0");
                ct.setIsLeader("0");
                classTeacherMapper.insertClassTeacher(ct);
            }
        }

        // 3. 设置唯一班主任
        if (leaderUserId != null) {
            classTeacherMapper.clearLeader(classId);
            classTeacherMapper.setLeader(classId, leaderUserId);
        }
    }
}
