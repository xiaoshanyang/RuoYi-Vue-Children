package com.ruoyi.system.service;

import java.util.List;

/**
 * 幼儿信息（无冗余班级名称）Service接口
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public interface ISysChildService 
{
    /**
     * 查询幼儿信息（无冗余班级名称）
     * 
     * @param id 幼儿信息（无冗余班级名称）主键
     * @return 幼儿信息（无冗余班级名称）
     */
    public SysChild selectSysChildById(Long id);

    /**
     * 查询幼儿信息（无冗余班级名称）列表
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 幼儿信息（无冗余班级名称）集合
     */
    public List<SysChild> selectSysChildList(SysChild sysChild);

    /**
     * 新增幼儿信息（无冗余班级名称）
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 结果
     */
    public int insertSysChild(SysChild sysChild);

    /**
     * 修改幼儿信息（无冗余班级名称）
     * 
     * @param sysChild 幼儿信息（无冗余班级名称）
     * @return 结果
     */
    public int updateSysChild(SysChild sysChild);

    /**
     * 批量删除幼儿信息（无冗余班级名称）
     * 
     * @param ids 需要删除的幼儿信息（无冗余班级名称）主键集合
     * @return 结果
     */
    public int deleteSysChildByIds(Long[] ids);

    /**
     * 删除幼儿信息（无冗余班级名称）信息
     * 
     * @param id 幼儿信息（无冗余班级名称）主键
     * @return 结果
     */
    public int deleteSysChildById(Long id);
}
