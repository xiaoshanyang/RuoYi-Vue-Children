package com.ruoyi.system.service;

import com.ruoyi.system.domain.vo.WarehouseOutVO;

/**
 * 仓库服务接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface IWarehouseService 
{

    /**
     * 新增仓库出库记录
     * 
     * @param WarehouseOutVO 仓库出库信息
     * @return 结果
     */
    public int createWarehouseOut(WarehouseOutVO vo);

}
