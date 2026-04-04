package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ClassTeacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassTeacherMapper {
    // 查询班级教师列表
    List<ClassTeacher> selectByClassId(Long classId);

    // 查询单个教师记录
    ClassTeacher selectByClassAndUser(@Param("classId") Long classId,
                                      @Param("userId") Long userId);

    // 插入
    int insertClassTeacher(ClassTeacher ct);

    // 更新
    int updateClassTeacher(ClassTeacher ct);

    // 该班级所有教师置为 调离
    int updateStatusToLeaveByClassId(@Param("classId") Long classId);

    // 清空该班班主任标记
    void clearLeader(Long classId);

    // 设置某人为主班
    int setLeader(@Param("classId") Long classId,
                  @Param("userId") Long userId);

    /**
     * 根据当前登录用户ID 查询他的班级
     */
    ClassTeacher selectClassByUserId(Long userId);
}
