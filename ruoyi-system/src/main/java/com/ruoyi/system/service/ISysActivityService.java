package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysActivity;

/**
 * 活动信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysActivityService 
{
    /**
     * 查询活动信息
     * 
     * @param id 活动信息主键
     * @return 活动信息
     */
    public SysActivity selectSysActivityById(Long id);

    /**
     * 查询活动信息列表
     * 
     * @param sysActivity 活动信息
     * @return 活动信息集合
     */
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity);

    /**
     * 新增活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    public int insertSysActivity(SysActivity sysActivity);

    /**
     * 修改活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    public int updateSysActivity(SysActivity sysActivity);

    /**
     * 开始活动
     * @param sysActivity
     * @return
     */
    public int startSysActivity(SysActivity sysActivity);

    /**
     * 批量删除活动信息
     * 
     * @param ids 需要删除的活动信息主键集合
     * @return 结果
     */
    public int deleteSysActivityByIds(Long[] ids);

    /**
     * 删除活动信息信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    public int deleteSysActivityById(Long id);
}
