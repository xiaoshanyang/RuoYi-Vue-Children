package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.WarehouseBatch;

import java.util.List;

public interface WarehouseBatchMapper {
    List<WarehouseBatch> selectAvailableBatch(@Param("foodId") Long foodId);
    int updateById(WarehouseBatch batch);
    int insert(WarehouseBatch batch);
}
