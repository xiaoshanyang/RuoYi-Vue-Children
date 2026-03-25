package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.vo.PurchaseAuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysPurchaseMapper;
import com.ruoyi.system.domain.SysPurchase;
import com.ruoyi.system.domain.SysPurchaseItem;
import com.ruoyi.system.service.ISysPurchaseService;

/**
 * 食材采购申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Service
public class SysPurchaseServiceImpl implements ISysPurchaseService 
{
    @Autowired
    private SysPurchaseMapper purchaseMapper;

     @Override
    public List<SysPurchase> selectPurchaseList(SysPurchase purchase) {
        List<SysPurchase> list = purchaseMapper.selectPurchaseList(purchase);
        // 给每个单子设置明细
        for (SysPurchase p : list) {
            p.setItems(purchaseMapper.selectItemsByPurchaseId(p.getPurchaseId()));
        }
        return list;
    }

    @Override
    public SysPurchase selectPurchaseById(Long purchaseId) {
        SysPurchase purchase = purchaseMapper.selectPurchaseById(purchaseId);
        purchase.setItems(purchaseMapper.selectItemsByPurchaseId(purchaseId));
        return purchase;
    }

    @Override
    @Transactional
    public int insertPurchase(SysPurchase purchase) {

      // 若未指定状态，默认草稿（0）
      if (purchase.getStatus() == null) {
          purchase.setStatus("0");
      }
        //purchase.setStatus("1"); // 待审核
        purchase.setCreateBy(SecurityUtils.getUsername());
        purchase.setCreateTime(DateUtils.getNowDate());
        int rows = purchaseMapper.insertPurchase(purchase);
        if (purchase.getItems() != null && !purchase.getItems().isEmpty()) {
            for (SysPurchaseItem item : purchase.getItems()) {
                item.setPurchaseId(purchase.getPurchaseId());
            }
            purchaseMapper.insertPurchaseItems(purchase.getItems());
        }
        return rows;
    }

    @Override
    @Transactional
    public int updatePurchase(SysPurchase purchase) {
        purchaseMapper.deleteItemsByPurchaseId(purchase.getPurchaseId());
        if (purchase.getItems() != null && !purchase.getItems().isEmpty()) {
            purchaseMapper.insertPurchaseItems(purchase.getItems());
        }
        purchase.setUpdateBy(SecurityUtils.getUsername());
        purchase.setUpdateTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }

    @Override
    @Transactional
    public int deletePurchaseByIds(Long[] ids) {
        for (Long id : ids) {
            purchaseMapper.deleteItemsByPurchaseId(id);
        }
        return purchaseMapper.deletePurchaseByIds(ids);
    }

    @Override
    @Transactional
    public int auditPurchase(PurchaseAuditDTO dto) {
        SysPurchase purchase = purchaseMapper.selectPurchaseById(dto.getPurchaseId());
        if (purchase == null) throw new ServiceException("采购单不存在");
        if (!"1".equals(purchase.getStatus())) throw new ServiceException("当前状态不可审核");
        //0:待提交 1:待审核 2:审核通过 3:审核驳回
        //dto: 2:审核通过 3:审核驳回
        purchase.setStatus(dto.getResult());
        purchase.setRemark(dto.getRemark());
        purchase.setAuditBy(SecurityUtils.getUsername());
        purchase.setAuditTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }
}
