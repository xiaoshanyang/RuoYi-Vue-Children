package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSupplier;

/**
 * 供应商信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public interface SysSupplierMapper 
{
    /**
     * 查询供应商信息
     * 
     * @param supplierId 供应商信息主键
     * @return 供应商信息
     */
    public SysSupplier selectSysSupplierBySupplierId(Long supplierId);

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
     * 删除供应商信息
     * 
     * @param supplierId 供应商信息主键
     * @return 结果
     */
    public int deleteSysSupplierBySupplierId(Long supplierId);

    /**
     * 批量删除供应商信息
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSupplierBySupplierIds(Long[] supplierIds);
}
