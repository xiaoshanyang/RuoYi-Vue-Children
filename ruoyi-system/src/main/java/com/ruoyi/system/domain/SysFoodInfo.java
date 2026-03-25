package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 食材基础信息对象 sys_food_info
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public class SysFoodInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 食材ID */
    private Long foodId;

    /** 食材名称 */
    @Excel(name = "食材名称")
    private String foodName;

    /** 食材编码 */
    @Excel(name = "食材编码")
    private String foodCode;

    /** 食材类型（字典：food_type） */
    @Excel(name = "食材类别", readConverterExp = "字=典：food_type")
    private String foodType;

    /** 规格（如：一级、无公害、有机） */
    @Excel(name = "规格", readConverterExp = "如=：一级、无公害、有机")
    private String spec;

    /** 计量单位（斤/公斤/箱/袋） */
    @Excel(name = "计量单位", readConverterExp = "斤=/公斤/箱/袋")
    private String unit;

    /** 价格 */
    @Excel(name = "价格")
    private String price;

    /** 保质期(天) */
    @Excel(name = "保质期(天)")
    private Long expireDays;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setFoodId(Long foodId) 
    {
        this.foodId = foodId;
    }

    public Long getFoodId() 
    {
        return foodId;
    }

    public void setFoodName(String foodName) 
    {
        this.foodName = foodName;
    }

    public String getFoodName() 
    {
        return foodName;
    }

    public void setFoodCode(String foodCode) 
    {
        this.foodCode = foodCode;
    }

    public String getFoodCode() 
    {
        return foodCode;
    }

    public void setFoodType(String foodType) 
    {
        this.foodType = foodType;
    }

    public String getFoodType() 
    {
        return foodType;
    }

    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setExpireDays(Long expireDays) 
    {
        this.expireDays = expireDays;
    }

    public Long getExpireDays() 
    {
        return expireDays;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("foodId", getFoodId())
            .append("foodName", getFoodName())
            .append("foodCode", getFoodCode())
            .append("foodType", getFoodType())
            .append("spec", getSpec())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("expireDays", getExpireDays())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
