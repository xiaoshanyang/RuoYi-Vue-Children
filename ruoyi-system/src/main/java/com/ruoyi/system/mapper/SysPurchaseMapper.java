package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPurchase;

/**
 * 食材采购申请Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface SysPurchaseMapper 
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
     * 删除食材采购申请
     * 
     * @param id 食材采购申请主键
     * @return 结果
     */
    public int deleteSysPurchaseById(Long id);

    /**
     * 批量删除食材采购申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPurchaseByIds(Long[] ids);
}
