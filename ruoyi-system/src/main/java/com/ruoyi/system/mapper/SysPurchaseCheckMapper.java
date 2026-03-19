package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPurchaseCheck;

/**
 * 食材采购验收Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface SysPurchaseCheckMapper 
{
    /**
     * 查询食材采购验收
     * 
     * @param id 食材采购验收主键
     * @return 食材采购验收
     */
    public SysPurchaseCheck selectSysPurchaseCheckById(Long id);

    /**
     * 查询食材采购验收列表
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 食材采购验收集合
     */
    public List<SysPurchaseCheck> selectSysPurchaseCheckList(SysPurchaseCheck sysPurchaseCheck);

    /**
     * 新增食材采购验收
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 结果
     */
    public int insertSysPurchaseCheck(SysPurchaseCheck sysPurchaseCheck);

    /**
     * 修改食材采购验收
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 结果
     */
    public int updateSysPurchaseCheck(SysPurchaseCheck sysPurchaseCheck);

    /**
     * 删除食材采购验收
     * 
     * @param id 食材采购验收主键
     * @return 结果
     */
    public int deleteSysPurchaseCheckById(Long id);

    /**
     * 批量删除食材采购验收
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPurchaseCheckByIds(Long[] ids);
}
