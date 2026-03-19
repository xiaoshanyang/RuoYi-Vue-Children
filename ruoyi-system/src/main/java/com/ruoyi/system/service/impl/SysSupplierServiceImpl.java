package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSupplierMapper;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;

/**
 * 供应商信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysSupplierServiceImpl implements ISysSupplierService 
{
    @Autowired
    private SysSupplierMapper sysSupplierMapper;

    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息主键
     * @return 供应商信息
     */
    @Override
    public SysSupplier selectSysSupplierById(Long id)
    {
        return sysSupplierMapper.selectSysSupplierById(id);
    }

    /**
     * 查询供应商信息列表
     * 
     * @param sysSupplier 供应商信息
     * @return 供应商信息
     */
    @Override
    public List<SysSupplier> selectSysSupplierList(SysSupplier sysSupplier)
    {
        return sysSupplierMapper.selectSysSupplierList(sysSupplier);
    }

    /**
     * 新增供应商信息
     * 
     * @param sysSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int insertSysSupplier(SysSupplier sysSupplier)
    {
        sysSupplier.setCreateTime(DateUtils.getNowDate());
        return sysSupplierMapper.insertSysSupplier(sysSupplier);
    }

    /**
     * 修改供应商信息
     * 
     * @param sysSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int updateSysSupplier(SysSupplier sysSupplier)
    {
        sysSupplier.setUpdateTime(DateUtils.getNowDate());
        return sysSupplierMapper.updateSysSupplier(sysSupplier);
    }

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的供应商信息主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierByIds(Long[] ids)
    {
        return sysSupplierMapper.deleteSysSupplierByIds(ids);
    }

    /**
     * 删除供应商信息信息
     * 
     * @param id 供应商信息主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierById(Long id)
    {
        return sysSupplierMapper.deleteSysSupplierById(id);
    }
}
