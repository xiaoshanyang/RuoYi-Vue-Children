package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysPurchaseCheckMapper;
import com.ruoyi.system.domain.SysPurchaseCheck;
import com.ruoyi.system.domain.SysPurchaseCheckItem;
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
              throw new ServiceException("该采购单已验收合格，不可重复验收！");
          }
          if ("2".equals(existCheck.getCheckResult())) {
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
