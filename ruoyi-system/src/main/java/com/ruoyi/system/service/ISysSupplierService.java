package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysSupplier;

/**
 * 供应商信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysSupplierService 
{
    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息主键
     * @return 供应商信息
     */
    public SysSupplier selectSysSupplierById(Long id);

    /**
     * 查询供应商信息列表
     * 
     * @param sysSupplier 供应商信息
     * @return 供应商信息集合
     */
    public List<SysSupplier> selectSysSupplierList(SysSupplier sysSupplier);

    /**
     * 新增供应商信息
     * 
     * @param sysSupplier 供应商信息
     * @return 结果
     */
    public int insertSysSupplier(SysSupplier sysSupplier);

    /**
     * 修改供应商信息
     * 
     * @param sysSupplier 供应商信息
     * @return 结果
     */
    public int updateSysSupplier(SysSupplier sysSupplier);

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的供应商信息主键集合
     * @return 结果
     */
    public int deleteSysSupplierByIds(Long[] ids);

    /**
     * 删除供应商信息信息
     * 
     * @param id 供应商信息主键
     * @return 结果
     */
    public int deleteSysSupplierById(Long id);
}
