package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysClassMapper;
import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.service.ISysClassService;

/**
 * 班级信息（按届命名）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@Service
public class SysClassServiceImpl implements ISysClassService 
{
    @Autowired
    private SysClassMapper sysClassMapper;

    /**
     * 查询班级信息（按届命名）
     * 
     * @param id 班级信息（按届命名）主键
     * @return 班级信息（按届命名）
     */
    @Override
    public SysClass selectSysClassById(Long id)
    {
        return sysClassMapper.selectSysClassById(id);
    }

    /**
     * 查询班级信息（按届命名）列表
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 班级信息（按届命名）
     */
    @Override
    public List<SysClass> selectSysClassList(SysClass sysClass)
    {
        return sysClassMapper.selectSysClassList(sysClass);
    }

    /**
     * 新增班级信息（按届命名）
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 结果
     */
    @Override
    public int insertSysClass(SysClass sysClass)
    {
        sysClass.setCreateTime(DateUtils.getNowDate());
        return sysClassMapper.insertSysClass(sysClass);
    }

    /**
     * 修改班级信息（按届命名）
     * 
     * @param sysClass 班级信息（按届命名）
     * @return 结果
     */
    @Override
    public int updateSysClass(SysClass sysClass)
    {
        sysClass.setUpdateTime(DateUtils.getNowDate());
        return sysClassMapper.updateSysClass(sysClass);
    }

    /**
     * 批量删除班级信息（按届命名）
     * 
     * @param ids 需要删除的班级信息（按届命名）主键
     * @return 结果
     */
    @Override
    public int deleteSysClassByIds(Long[] ids)
    {
        return sysClassMapper.deleteSysClassByIds(ids);
    }

    /**
     * 删除班级信息（按届命名）信息
     * 
     * @param id 班级信息（按届命名）主键
     * @return 结果
     */
    @Override
    public int deleteSysClassById(Long id)
    {
        return sysClassMapper.deleteSysClassById(id);
    }
}
