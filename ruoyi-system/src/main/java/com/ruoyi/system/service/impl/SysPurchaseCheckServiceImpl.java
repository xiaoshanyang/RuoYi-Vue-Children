package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPurchaseCheckMapper;
import com.ruoyi.system.domain.SysPurchaseCheck;
import com.ruoyi.system.service.ISysPurchaseCheckService;

/**
 * 食材采购验收Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysPurchaseCheckServiceImpl implements ISysPurchaseCheckService 
{
    @Autowired
    private SysPurchaseCheckMapper sysPurchaseCheckMapper;

    /**
     * 查询食材采购验收
     * 
     * @param id 食材采购验收主键
     * @return 食材采购验收
     */
    @Override
    public SysPurchaseCheck selectSysPurchaseCheckById(Long id)
    {
        return sysPurchaseCheckMapper.selectSysPurchaseCheckById(id);
    }

    /**
     * 查询食材采购验收列表
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 食材采购验收
     */
    @Override
    public List<SysPurchaseCheck> selectSysPurchaseCheckList(SysPurchaseCheck sysPurchaseCheck)
    {
        return sysPurchaseCheckMapper.selectSysPurchaseCheckList(sysPurchaseCheck);
    }

    /**
     * 新增食材采购验收
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 结果
     */
    @Override
    public int insertSysPurchaseCheck(SysPurchaseCheck sysPurchaseCheck)
    {
        sysPurchaseCheck.setCreateTime(DateUtils.getNowDate());
        return sysPurchaseCheckMapper.insertSysPurchaseCheck(sysPurchaseCheck);
    }

    /**
     * 修改食材采购验收
     * 
     * @param sysPurchaseCheck 食材采购验收
     * @return 结果
     */
    @Override
    public int updateSysPurchaseCheck(SysPurchaseCheck sysPurchaseCheck)
    {
        return sysPurchaseCheckMapper.updateSysPurchaseCheck(sysPurchaseCheck);
    }

    /**
     * 批量删除食材采购验收
     * 
     * @param ids 需要删除的食材采购验收主键
     * @return 结果
     */
    @Override
    public int deleteSysPurchaseCheckByIds(Long[] ids)
    {
        return sysPurchaseCheckMapper.deleteSysPurchaseCheckByIds(ids);
    }

    /**
     * 删除食材采购验收信息
     * 
     * @param id 食材采购验收主键
     * @return 结果
     */
    @Override
    public int deleteSysPurchaseCheckById(Long id)
    {
        return sysPurchaseCheckMapper.deleteSysPurchaseCheckById(id);
    }
}
