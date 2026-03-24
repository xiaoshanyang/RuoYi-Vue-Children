package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商信息对象 sys_supplier
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public class SysSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 营业执照号 */
    @Excel(name = "营业执照号")
    private String businessLicense;

    /** 食品经营许可证 */
    @Excel(name = "食品经营许可证")
    private String foodLicense;

    /** 审核类型：add-新增审核 disable-停用审核 enable-启用审核 */
    @Excel(name = "审核类型：add-新增审核 disable-停用审核 enable-启用审核")
    private String auditType;

    /** 操作描述（后勤填写：如“新增XX食材供应商”“申请停用违规供应商”） */
    @Excel(name = "操作描述", readConverterExp = "后=勤填写：如“新增XX食材供应商”“申请停用违规供应商”")
    private String operateDesc;

    /** 状态 0 = 待审核、1 = 正常、2 = 停用、3 = 驳回、4 = 待停用、5 = 待启用 */
    @Excel(name = "状态 0 = 待审核、1 = 正常、2 = 停用、3 = 驳回、4 = 待停用、5 = 待启用")
    private String status;

    /** 审核意见/驳回原因/停用原因 */
    @Excel(name = "审核意见/驳回原因/停用原因")
    private String reason;

    /** 审核人（园长） */
    @Excel(name = "审核人", readConverterExp = "园=长")
    private String auditBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

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

    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }

    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setBusinessLicense(String businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() 
    {
        return businessLicense;
    }

    public void setFoodLicense(String foodLicense) 
    {
        this.foodLicense = foodLicense;
    }

    public String getFoodLicense() 
    {
        return foodLicense;
    }

    public void setAuditType(String auditType) 
    {
        this.auditType = auditType;
    }

    public String getAuditType() 
    {
        return auditType;
    }

    public void setOperateDesc(String operateDesc) 
    {
        this.operateDesc = operateDesc;
    }

    public String getOperateDesc() 
    {
        return operateDesc;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public void setAuditBy(String auditBy) 
    {
        this.auditBy = auditBy;
    }

    public String getAuditBy() 
    {
        return auditBy;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("supplierCode", getSupplierCode())
            .append("contact", getContact())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("businessLicense", getBusinessLicense())
            .append("foodLicense", getFoodLicense())
            .append("auditType", getAuditType())
            .append("operateDesc", getOperateDesc())
            .append("status", getStatus())
            .append("reason", getReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("auditBy", getAuditBy())
            .append("auditTime", getAuditTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
