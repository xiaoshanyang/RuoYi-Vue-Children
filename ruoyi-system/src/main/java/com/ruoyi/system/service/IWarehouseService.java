package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.vo.FoodStockVo;
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
      
    /**
      * 查询食材库存列表
      * 
      * @param foodStockVo 食材库存查询条件
      * @return 食材库存列表
      */
    public List<FoodStockVo> selectFoodStockList(FoodStockVo foodStockVo);

}
