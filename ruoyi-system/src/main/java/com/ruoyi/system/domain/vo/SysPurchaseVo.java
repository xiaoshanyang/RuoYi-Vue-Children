package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 食材采购申请对象 sys_purchase
 * 
 * @author ruoyi
 * @date 2026-03-19
 */
public class SysPurchaseVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 食材名称 */
    @Excel(name = "食材名称")
    private String foodName;

    /** 食材规格 */
    @Excel(name = "食材规格")
    private String specification;

    /** 采购数量 */
    @Excel(name = "采购数量")
    private Long purchaseQuantity;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 采购预算(元) */
    @Excel(name = "采购预算(元)")
    private BigDecimal purchaseBudget;

    /** 采购状态：待审核/审核失败/已审核/验收合格/验收不合格 */
    @Excel(name = "采购状态：待审核/审核失败/已审核/验收合格/验收不合格")
    private String purchaseStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setFoodName(String foodName) 
    {
        this.foodName = foodName;
    }

    public String getFoodName() 
    {
        return foodName;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setPurchaseQuantity(Long purchaseQuantity) 
    {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Long getPurchaseQuantity() 
    {
        return purchaseQuantity;
    }

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }

    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }

    public void setPurchaseBudget(BigDecimal purchaseBudget) 
    {
        this.purchaseBudget = purchaseBudget;
    }

    public BigDecimal getPurchaseBudget() 
    {
        return purchaseBudget;
    }

    public void setPurchaseStatus(String purchaseStatus) 
    {
        this.purchaseStatus = purchaseStatus;
    }

    public String getPurchaseStatus() 
    {
        return purchaseStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("foodName", getFoodName())
            .append("specification", getSpecification())
            .append("purchaseQuantity", getPurchaseQuantity())
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("purchaseBudget", getPurchaseBudget())
            .append("purchaseStatus", getPurchaseStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
