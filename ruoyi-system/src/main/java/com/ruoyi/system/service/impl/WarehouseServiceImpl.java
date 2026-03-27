package com.ruoyi.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.WarehouseBatch;
import com.ruoyi.system.domain.WarehouseOut;
import com.ruoyi.system.domain.WarehouseOutItem;
import com.ruoyi.system.domain.vo.WarehouseOutVO;
import com.ruoyi.system.domain.vo.WarehouseOutItemVo;
import com.ruoyi.system.domain.vo.FoodStockVo;
import com.ruoyi.system.mapper.WarehouseBatchMapper;
import com.ruoyi.system.mapper.WarehouseOutItemMapper;
import com.ruoyi.system.mapper.WarehouseOutMapper;
import com.ruoyi.system.service.IWarehouseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired  
    private WarehouseBatchMapper batchMapper;
    @Autowired
    private WarehouseOutMapper outMapper;
    @Autowired
    private WarehouseOutItemMapper outItemMapper;

    @Transactional
    public int createWarehouseOut(WarehouseOutVO vo) {
        // 1. 新建领用单
        WarehouseOut out = new WarehouseOut();
        out.setOutTime(vo.getOutTime());
        out.setAbnormalStatus(vo.getAbnormalStatus());
        out.setAbnormalNote(vo.getAbnormalNote());
        out.setCreateBy(SecurityUtils.getUsername());
        int result = outMapper.insert(out);

        // 2. 遍历食材，FIFO 扣减
        for (WarehouseOutItemVo item : vo.getItemList()) {
            BigDecimal need = item.getOutQty();
            Long foodId = item.getFoodId();

            List<WarehouseBatch> batches = batchMapper.selectAvailableBatch(foodId);

            for (WarehouseBatch batch : batches) {
                if (need.compareTo(BigDecimal.ZERO) <= 0) break;

                BigDecimal remain = batch.getRemainQty();

                if (remain.compareTo(need) >= 0) {
                    batch.setRemainQty(remain.subtract(need));
                    saveItem(out.getOutId(), item, need, batch.getBatchId());
                    need = BigDecimal.ZERO;
                } else {
                    batch.setRemainQty(BigDecimal.ZERO);
                    saveItem(out.getOutId(), item, remain, batch.getBatchId());
                    need = need.subtract(remain);
                }
                batchMapper.updateById(batch);
            }

            if (need.compareTo(BigDecimal.ZERO) > 0) {
                throw new ServiceException(item.getFoodName() + " 库存不足");
            }
        }
        return result;
    }

    @Override
    public List<WarehouseBatch> selectBatchList(Long foodId, String foodName) {
      List<WarehouseBatch> list = batchMapper.selectBatchList(foodId, foodName);
        return batchMapper.selectBatchList(foodId, foodName);
    }

    @Override
    public List<FoodStockVo> selectFoodStockList(FoodStockVo foodStockVo) {
        return batchMapper.selectFoodStockList(foodStockVo);
    }

    @Override
    public WarehouseOut selectWarehouseOutById(Long outId) {
        WarehouseOut out = outMapper.selectWarehouseOutById(outId);
        if (out != null) {
            List<WarehouseOutItem> items = outItemMapper.selectByOutId(outId);
            out.setItems(items);
        }
        return out;
    }


    @Override
    public List<Map<String, Object>> selectOutList(Integer abnormalStatus, String startTime, String endTime) {
        return outMapper.selectOutList(abnormalStatus, startTime, endTime);
    }

    // 保存领用明细
    private void saveItem(Long outId, WarehouseOutItemVo item, BigDecimal qty, Long batchId) {
        WarehouseOutItem record = new WarehouseOutItem();
        record.setOutId(outId);
        record.setFoodId(item.getFoodId());
        record.setFoodName(item.getFoodName());
        record.setOutQty(qty);
        record.setBatchId(batchId);
        outItemMapper.insert(record);
    }
}
