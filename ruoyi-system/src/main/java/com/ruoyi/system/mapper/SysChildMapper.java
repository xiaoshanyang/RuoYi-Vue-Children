package com.ruoyi.system.mapper;

import java.util.List;

/**
 * 幼儿信息（无冗余班级名称）Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public interface SysChildMapper 
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
     * 删除幼儿信息（无冗余班级名称）
     * 
     * @param id 幼儿信息（无冗余班级名称）主键
     * @return 结果
     */
    public int deleteSysChildById(Long id);

    /**
     * 批量删除幼儿信息（无冗余班级名称）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysChildByIds(Long[] ids);
}
