package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.vo.SysPurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPurchaseMapper;
import com.ruoyi.system.domain.SysPurchase;
import com.ruoyi.system.service.ISysPurchaseService;

/**
 * 食材采购申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysPurchaseServiceImpl implements ISysPurchaseService 
{
    @Autowired
    private SysPurchaseMapper sysPurchaseMapper;

    /**
     * 查询食材采购申请
     * 
     * @param id 食材采购申请主键
     * @return 食材采购申请
     */
    @Override
    public SysPurchaseVo selectSysPurchaseById(Long id)
    {
        return sysPurchaseMapper.selectSysPurchaseById(id);
    }

    /**
     * 查询食材采购申请列表
     * 
     * @param sysPurchase 食材采购申请
     * @return 食材采购申请
     */
    @Override
    public List<SysPurchaseVo> selectSysPurchaseList(SysPurchase sysPurchase)
    {
        return sysPurchaseMapper.selectSysPurchaseList(sysPurchase);
    }

    /**
     * 新增食材采购申请
     * 
     * @param sysPurchase 食材采购申请
     * @return 结果
     */
    @Override
    public int insertSysPurchase(SysPurchase sysPurchase)
    {
        sysPurchase.setCreateTime(DateUtils.getNowDate());
        return sysPurchaseMapper.insertSysPurchase(sysPurchase);
    }

    /**
     * 修改食材采购申请
     * 
     * @param sysPurchase 食材采购申请
     * @return 结果
     */
    @Override
    public int updateSysPurchase(SysPurchase sysPurchase)
    {
        sysPurchase.setUpdateTime(DateUtils.getNowDate());
        return sysPurchaseMapper.updateSysPurchase(sysPurchase);
    }

    /**
     * 批量删除食材采购申请
     * 
     * @param ids 需要删除的食材采购申请主键
     * @return 结果
     */
    @Override
    public int deleteSysPurchaseByIds(Long[] ids)
    {
        return sysPurchaseMapper.deleteSysPurchaseByIds(ids);
    }

    /**
     * 删除食材采购申请信息
     * 
     * @param id 食材采购申请主键
     * @return 结果
     */
    @Override
    public int deleteSysPurchaseById(Long id)
    {
        return sysPurchaseMapper.deleteSysPurchaseById(id);
    }
}
