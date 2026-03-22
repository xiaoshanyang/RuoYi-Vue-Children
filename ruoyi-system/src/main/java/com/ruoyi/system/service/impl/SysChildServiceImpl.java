package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysChildMapper;
import com.ruoyi.system.domain.SysChild;
import com.ruoyi.system.service.ISysChildService;

/**
 * 幼儿信息（无冗余班级名称）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@Service
public class SysChildServiceImpl implements ISysChildService 
{
    @Autowired
    private SysChildMapper sysChildMapper;

    /**
     * 查询幼儿信息（无冗余班级名称）
     * 
     * @param id 幼儿信息（无冗余班级名称）主键
     * @return 幼儿信息（无冗余班级名称）
     */
    @Override
    public SysChild selectSysChildById(Long id)
    {
        return sysChildMapper.selectSysChildById(id);
    }

    /**
     * 查询幼儿信息（无冗余班级名称）列表
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 幼儿信息（无冗余班级名称）
     */
    @Override
    public List<SysChild> selectSysChildList(SysChild sysChild)
    {
        return sysChildMapper.selectSysChildList(sysChild);
    }

    /**
     * 新增幼儿信息（无冗余班级名称）
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 结果
     */
    @Override
    public int insertSysChild(SysChild sysChild)
    {
        sysChild.setCreateTime(DateUtils.getNowDate());
        return sysChildMapper.insertSysChild(sysChild);
    }

    /**
     * 修改幼儿信息（无冗余班级名称）
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 结果
     */
    @Override
    public int updateSysChild(SysChild sysChild)
    {
        sysChild.setUpdateTime(DateUtils.getNowDate());
        return sysChildMapper.updateSysChild(sysChild);
    }

    /**
     * 批量删除幼儿信息（无冗余班级名称）
     * 
     * @param ids 需要删除的幼儿信息（无冗余班级名称）主键
     * @return 结果
     */
    @Override
    public int deleteSysChildByIds(Long[] ids)
    {
        return sysChildMapper.deleteSysChildByIds(ids);
    }

    /**
     * 删除幼儿信息（无冗余班级名称）信息
     * 
     * @param id 幼儿信息（无冗余班级名称）主键
     * @return 结果
     */
    @Override
    public int deleteSysChildById(Long id)
    {
        return sysChildMapper.deleteSysChildById(id);
    }
}
