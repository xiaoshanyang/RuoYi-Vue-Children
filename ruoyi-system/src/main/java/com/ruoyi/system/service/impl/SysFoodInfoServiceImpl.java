package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFoodInfoMapper;
import com.ruoyi.system.domain.SysFoodInfo;
import com.ruoyi.system.service.ISysFoodInfoService;

/**
 * 食材基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
@Service
public class SysFoodInfoServiceImpl implements ISysFoodInfoService 
{
    @Autowired
    private SysFoodInfoMapper sysFoodInfoMapper;

    /**
     * 查询食材基础信息
     * 
     * @param foodId 食材基础信息主键
     * @return 食材基础信息
     */
    @Override
    public SysFoodInfo selectSysFoodInfoByFoodId(Long foodId)
    {
        return sysFoodInfoMapper.selectSysFoodInfoByFoodId(foodId);
    }

    /**
     * 查询食材基础信息列表
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 食材基础信息
     */
    @Override
    public List<SysFoodInfo> selectSysFoodInfoList(SysFoodInfo sysFoodInfo)
    {
        return sysFoodInfoMapper.selectSysFoodInfoList(sysFoodInfo);
    }

    /**
     * 新增食材基础信息
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 结果
     */
    @Override
    public int insertSysFoodInfo(SysFoodInfo sysFoodInfo)
    {
        sysFoodInfo.setCreateTime(DateUtils.getNowDate());
        return sysFoodInfoMapper.insertSysFoodInfo(sysFoodInfo);
    }

    /**
     * 修改食材基础信息
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 结果
     */
    @Override
    public int updateSysFoodInfo(SysFoodInfo sysFoodInfo)
    {
        sysFoodInfo.setUpdateTime(DateUtils.getNowDate());
        return sysFoodInfoMapper.updateSysFoodInfo(sysFoodInfo);
    }

    /**
     * 批量删除食材基础信息
     * 
     * @param foodIds 需要删除的食材基础信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFoodInfoByFoodIds(Long[] foodIds)
    {
        return sysFoodInfoMapper.deleteSysFoodInfoByFoodIds(foodIds);
    }

    /**
     * 删除食材基础信息信息
     * 
     * @param foodId 食材基础信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFoodInfoByFoodId(Long foodId)
    {
        return sysFoodInfoMapper.deleteSysFoodInfoByFoodId(foodId);
    }
}
