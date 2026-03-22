package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysClass;

/**
 * 班级信息（按届命名）Service接口
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public interface ISysClassService 
{
    /**
     * 查询班级信息（按届命名）
     * 
     * @param id 班级信息（按届命名）主键
     * @return 班级信息（按届命名）
     */
    public SysClass selectSysClassById(Long id);

    /**
     * 查询班级信息（按届命名）列表
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 班级信息（按届命名）集合
     */
    public List<SysClass> selectSysClassList(SysClass sysClass);

    /**
     * 新增班级信息（按届命名）
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 结果
     */
    public int insertSysClass(SysClass sysClass);

    /**
     * 修改班级信息（按届命名）
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 结果
     */
    public int updateSysClass(SysClass sysClass);

    /**
     * 批量删除班级信息（按届命名）
     * 
     * @param ids 需要删除的班级信息（按届命名）主键集合
     * @return 结果
     */
    public int deleteSysClassByIds(Long[] ids);

    /**
     * 删除班级信息（按届命名）信息
     * 
     * @param id 班级信息（按届命名）主键
     * @return 结果
     */
    public int deleteSysClassById(Long id);
}
