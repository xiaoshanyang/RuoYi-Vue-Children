package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPurchase;

/**
 * 食材采购申请Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysPurchaseService 
{
    /**
     * 查询食材采购申请
     * 
     * @param id 食材采购申请主键
     * @return 食材采购申请
     */
    public SysPurchase selectSysPurchaseById(Long id);

    /**
     * 查询食材采购申请列表
     * 
     * @param sysPurchase 食材采购申请
     * @return 食材采购申请集合
     */
    public List<SysPurchase> selectSysPurchaseList(SysPurchase sysPurchase);

    /**
     * 新增食材采购申请
     * 
     * @param sysPurchase 食材采购申请
     * @return 结果
     */
    public int insertSysPurchase(SysPurchase sysPurchase);

    /**
     * 修改食材采购申请
     * 
     * @param sysPurchase 食材采购申请
     * @return 结果
     */
    public int updateSysPurchase(SysPurchase sysPurchase);

    /**
     * 批量删除食材采购申请
     * 
     * @param ids 需要删除的食材采购申请主键集合
     * @return 结果
     */
    public int deleteSysPurchaseByIds(Long[] ids);

    /**
     * 删除食材采购申请信息
     * 
     * @param id 食材采购申请主键
     * @return 结果
     */
    public int deleteSysPurchaseById(Long id);
}
