package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPurchase;
import com.ruoyi.system.domain.SysPurchaseItem;

/**
 * 食材采购申请Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface SysPurchaseMapper 
{
   List<SysPurchase> selectPurchaseList(SysPurchase purchase);
    SysPurchase selectPurchaseById(Long purchaseId);
    int insertPurchase(SysPurchase purchase);
    int updatePurchase(SysPurchase purchase);
    int deletePurchaseByIds(Long[] ids);

    // 明细
    void insertPurchaseItems(List<SysPurchaseItem> list);
    List<SysPurchaseItem> selectItemsByPurchaseId(Long purchaseId);
    void deleteItemsByPurchaseId(Long purchaseId);

    // 更新实际金额
    void updateActualAmount(Long purchaseId, java.math.BigDecimal actualAmount);
}
