package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysFoodInfo;

/**
 * 食材基础信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public interface ISysFoodInfoService 
{
    /**
     * 查询食材基础信息
     * 
     * @param foodId 食材基础信息主键
     * @return 食材基础信息
     */
    public SysFoodInfo selectSysFoodInfoByFoodId(Long foodId);

    /**
     * 查询食材基础信息列表
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 食材基础信息集合
     */
    public List<SysFoodInfo> selectSysFoodInfoList(SysFoodInfo sysFoodInfo);

    /**
     * 新增食材基础信息
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 结果
     */
    public int insertSysFoodInfo(SysFoodInfo sysFoodInfo);

    /**
     * 修改食材基础信息
     * 
     * @param sysFoodInfo 食材基础信息
     * @return 结果
     */
    public int updateSysFoodInfo(SysFoodInfo sysFoodInfo);

    /**
     * 批量删除食材基础信息
     * 
     * @param foodIds 需要删除的食材基础信息主键集合
     * @return 结果
     */
    public int deleteSysFoodInfoByFoodIds(Long[] foodIds);

    /**
     * 删除食材基础信息信息
     * 
     * @param foodId 食材基础信息主键
     * @return 结果
     */
    public int deleteSysFoodInfoByFoodId(Long foodId);
}
