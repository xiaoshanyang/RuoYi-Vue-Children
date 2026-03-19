package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 食材采购验收对象 sys_purchase_check
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public class SysPurchaseCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 采购ID */
    @Excel(name = "采购ID")
    private Long purchaseId;

    /** 验收状态：合格/不合格 */
    @Excel(name = "验收状态：合格/不合格")
    private String checkStatus;

    /** 验收结果 */
    @Excel(name = "验收结果")
    private String checkResult;

    /** 验收凭证路径 */
    @Excel(name = "验收凭证路径")
    private String checkVoucher;

    /** 是否创建异常：0否 1是 */
    @Excel(name = "是否创建异常：0否 1是")
    private String createAbnormal;

    /** 验收人 */
    @Excel(name = "验收人")
    private String checkBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }

    public void setCheckStatus(String checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public String getCheckStatus() 
    {
        return checkStatus;
    }

    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }

    public void setCheckVoucher(String checkVoucher) 
    {
        this.checkVoucher = checkVoucher;
    }

    public String getCheckVoucher() 
    {
        return checkVoucher;
    }

    public void setCreateAbnormal(String createAbnormal) 
    {
        this.createAbnormal = createAbnormal;
    }

    public String getCreateAbnormal() 
    {
        return createAbnormal;
    }

    public void setCheckBy(String checkBy) 
    {
        this.checkBy = checkBy;
    }

    public String getCheckBy() 
    {
        return checkBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseId", getPurchaseId())
            .append("checkStatus", getCheckStatus())
            .append("checkResult", getCheckResult())
            .append("checkVoucher", getCheckVoucher())
            .append("createAbnormal", getCreateAbnormal())
            .append("checkBy", getCheckBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
