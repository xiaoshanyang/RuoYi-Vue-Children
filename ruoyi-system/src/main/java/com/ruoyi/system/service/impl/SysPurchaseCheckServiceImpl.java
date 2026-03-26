package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.WarehouseBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysPurchaseCheckMapper;
import com.ruoyi.system.mapper.SysPurchaseMapper;
import com.ruoyi.system.service.ISysPurchaseCheckService;

/**
 * 食材采购验收Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysPurchaseCheckServiceImpl implements ISysPurchaseCheckService 
{
    @Autowired
    private SysPurchaseCheckMapper sysPurchaseCheckMapper;
    @Autowired
    private SysPurchaseMapper sysPurchaseMapper;
    @Autowired
    private WarehouseBatchMapper batchMapper;

    
    @Override
    public List<SysPurchaseCheck> selectPurchaseCheckList(SysPurchaseCheck check) {
        return sysPurchaseCheckMapper.selectPurchaseCheckList(check);
    }

    @Override
    public SysPurchaseCheck selectPurchaseCheckById(Long checkId) {
        SysPurchaseCheck check = sysPurchaseCheckMapper.selectPurchaseCheckById(checkId);
        check.setItems(sysPurchaseCheckMapper.selectItemsByCheckId(checkId));
        return check;
    }

    @Override
    @Transactional
    public int insertPurchaseCheck(SysPurchaseCheck check) {

      // 1. 校验该采购单是否已验收
      SysPurchaseCheck existCheck = sysPurchaseCheckMapper.selectCheckByPurchaseId(check.getPurchaseId());
      if (existCheck != null) {
          if ("1".equals(existCheck.getCheckResult())) {
              throw new ServiceException("该采购单已验收完成，不可重复验收！");
          }else{
              // 不合格单：删除旧验收记录，允许重新验收
              sysPurchaseCheckMapper.deleteItemsByCheckId(existCheck.getCheckId());
              sysPurchaseCheckMapper.deletePurchaseCheckById(existCheck.getCheckId());
          }
      }

        check.setCheckBy(SecurityUtils.getUsername());
        check.setCheckTime(DateUtils.getNowDate());
        int rows = sysPurchaseCheckMapper.insertPurchaseCheck(check);

        List<SysPurchaseCheckItem> items = check.getItems();
        if (items != null && !items.isEmpty()) {
            for (SysPurchaseCheckItem item : items) {
                item.setCheckId(check.getCheckId());
                autoCalcItemResult(item);
            }
            sysPurchaseCheckMapper.insertPurchaseCheckItems(items);
        }

        // 验收完成
        if("1".equals(check.getCheckResult())) {
          // 3. 计算实际应付金额（只算合格食材）
          BigDecimal actualAmount = BigDecimal.ZERO;

          // 2. 验收完成，自动入库
          // ==============================
          // 关键：整单完成后 → 统一入库
          // ==============================
          for (SysPurchaseCheckItem item : items) {
            if ("1".equals(item.getItemResult())) { // 只入库合格食材
              // 生成库存批次
              WarehouseBatch batch = new WarehouseBatch();
              batch.setFoodId(item.getFoodId());
              batch.setFoodName(item.getFoodName());
              batch.setPurchaseId(check.getPurchaseId());
              batch.setCheckId(check.getCheckId());
              batch.setInQty(item.getRealQty());
              batch.setRemainQty(item.getRealQty());
              batch.setInTime(DateUtils.getNowDate());
              batchMapper.insert(batch);

              // 获取采购单详情，取得采购价格
                SysPurchaseItem purchaseItem = sysPurchaseMapper.selectPurchaseItemByPurchaseId(check.getPurchaseId(), item.getFoodId());
              
              actualAmount = actualAmount.add(item.getRealQty().multiply(purchaseItem.getPrice()));
            }
          }
            // 4. 更新采购单实际金额
            sysPurchaseMapper.updateActualAmount(check.getPurchaseId(), actualAmount);

        }
        

        return rows;
    }

    // 自动计算明细结果：任意一项不合格则明细不合格
    private void autoCalcItemResult(SysPurchaseCheckItem item) {
        if ("0".equals(item.getExpireCheck())
            || "0".equals(item.getPesticideCheck())
            || "0".equals(item.getAppearanceCheck())) {
            item.setItemResult("0");
        } else {
            item.setItemResult("1");
        }
    }
}
