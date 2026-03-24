package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSupplierMapper;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.domain.vo.SupplierApplyDTO;
import com.ruoyi.system.domain.vo.SupplierAuditDTO;
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
     * @param supplierId 供应商信息主键
     * @return 供应商信息
     */
    @Override
    public SysSupplier selectSysSupplierBySupplierId(Long supplierId)
    {
        return sysSupplierMapper.selectSysSupplierBySupplierId(supplierId);
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
        sysSupplier.setStatus("0");               // 待审核
        sysSupplier.setAuditType("add");          // 新增审核
        sysSupplier.setOperateDesc("新增供应商申请");
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
     * @param supplierIds 需要删除的供应商信息主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierBySupplierIds(Long[] supplierIds)
    {
        return sysSupplierMapper.deleteSysSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息信息
     * 
     * @param supplierId 供应商信息主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierBySupplierId(Long supplierId)
    {
        return sysSupplierMapper.deleteSysSupplierBySupplierId(supplierId);
    }


    @Override
    public int applyDisableSupplier(SupplierApplyDTO dto) {
        SysSupplier supplier = sysSupplierMapper.selectSysSupplierBySupplierId(dto.getSupplierId());
        supplier.setStatus("4");               // 待停用
        supplier.setAuditType("disable");      // 停用审核
        supplier.setOperateDesc(dto.getReason());
        return sysSupplierMapper.updateSysSupplier(supplier);
    }

    @Override
    public int applyEnableSupplier(SupplierApplyDTO dto) {
        SysSupplier supplier = sysSupplierMapper.selectSysSupplierBySupplierId(dto.getSupplierId());
        supplier.setStatus("5");               // 待启用
        supplier.setAuditType("enable");       // 启用审核
        supplier.setOperateDesc(dto.getReason());
        return sysSupplierMapper.updateSysSupplier(supplier);
    }

    @Override
    public int auditSupplier(SupplierAuditDTO dto) {
        SysSupplier supplier = sysSupplierMapper.selectSysSupplierBySupplierId(dto.getSupplierId());
        if (supplier == null) {
            throw new ServiceException("供应商不存在");
        }

        String auditType = supplier.getAuditType();
        String auditResult = dto.getAuditResult(); // 1=通过 0=驳回
        String remark = dto.getAuditRemark();

        // 审核通过
        if ("1".equals(auditResult)) {
            if ("add".equals(auditType)) {
                supplier.setStatus("1");      // 正常
            } else if ("disable".equals(auditType)) {
                supplier.setStatus("2");      // 停用
            } else if ("enable".equals(auditType)) {
                supplier.setStatus("1");      // 启用
            }
        }
        // 审核驳回
        else {
            if ("add".equals(auditType)) {
                supplier.setStatus("3");      // 驳回
            } else if ("disable".equals(auditType)) {
                supplier.setStatus("1");      // 恢复正常
            } else if ("enable".equals(auditType)) {
                supplier.setStatus("2");      // 保持停用
            }
        }

        supplier.setReason(remark);
        supplier.setAuditBy(SecurityUtils.getUsername());
        supplier.setAuditTime(DateUtils.getNowDate());
        return sysSupplierMapper.updateSysSupplier(supplier);
    }

}
