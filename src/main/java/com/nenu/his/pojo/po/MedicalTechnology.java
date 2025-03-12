package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 医疗项目
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("medical_technology")
public class MedicalTechnology extends Base {
    /**
     * 项目编码
     */
    @TableField(value = "tech_code")
    private String techCode;

    /**
     * 项目名称
     */
    @TableField(value = "tech_name")
    private String techName;

    /**
     * 规格
     */
    @TableField(value = "tech_format")
    private String techFormat;

    /**
     * 价格
     */
    @TableField(value = "tech_price")
    private BigDecimal techPrice;

    /**
     * 项目类型
     */
    @TableField(value = "tech_type")
    private String techType;

    /**
     * 价格类型
     */
    @TableField(value = "price_type")
    private String priceType;

    /**
     * 科室ID
     */
    @TableField(value = "department_id")
    private Integer departmentId;

    /**
     * 检查请求（不在数据库中存在）
     */
    @TableField(exist = false)
    private CheckRequest checkRequest;
}
