package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysPurchaseCheck;
import com.ruoyi.system.domain.SysPurchaseCheckItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 食材采购审核Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
@Mapper
public interface SysPurchaseCheckMapper {
    List<SysPurchaseCheck> selectPurchaseCheckList(SysPurchaseCheck check);
    SysPurchaseCheck selectPurchaseCheckById(Long checkId);
    SysPurchaseCheck selectCheckByPurchaseId(Long purchaseId);
    int insertPurchaseCheck(SysPurchaseCheck check);
    int updatePurchaseCheck(SysPurchaseCheck check);

    void insertPurchaseCheckItems(List<SysPurchaseCheckItem> list);
    List<SysPurchaseCheckItem> selectItemsByCheckId(Long checkId);
    void deletePurchaseCheckById(Long checkId);
    void deleteItemsByCheckId(Long checkId);
}