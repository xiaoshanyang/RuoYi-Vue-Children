package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.WarehouseOutItem;

public interface WarehouseOutItemMapper {

    int insert(WarehouseOutItem item);

    List<WarehouseOutItem> selectByOutId(Long outId);
}
