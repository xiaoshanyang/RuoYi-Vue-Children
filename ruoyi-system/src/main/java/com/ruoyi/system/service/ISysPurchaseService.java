package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPurchase;
import com.ruoyi.system.domain.vo.PurchaseAuditDTO;

/**
 * 食材采购申请Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysPurchaseService 
{
    List<SysPurchase> selectPurchaseList(SysPurchase purchase);
    SysPurchase selectPurchaseById(Long purchaseId);
    int insertPurchase(SysPurchase purchase);
    int updatePurchase(SysPurchase purchase);
    int deletePurchaseByIds(Long[] ids);
    int auditPurchase(PurchaseAuditDTO dto);
}
