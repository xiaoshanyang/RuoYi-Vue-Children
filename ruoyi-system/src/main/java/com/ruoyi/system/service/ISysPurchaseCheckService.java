package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPurchaseCheck;

/**
 * 食材采购验收Service接口
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public interface ISysPurchaseCheckService 
{
    List<SysPurchaseCheck> selectPurchaseCheckList(SysPurchaseCheck check);
    SysPurchaseCheck selectPurchaseCheckById(Long checkId);
    int insertPurchaseCheck(SysPurchaseCheck check);
}
