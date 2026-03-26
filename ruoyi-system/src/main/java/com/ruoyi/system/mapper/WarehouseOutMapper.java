package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.WarehouseOut;

public interface WarehouseOutMapper {

    int insert(WarehouseOut out);
    List<Map<String, Object>> selectAbnormalReport(
        @Param("abnormalStatus") Integer abnormalStatus,
        @Param("startTime") String startTime,
        @Param("endTime") String endTime);
}
