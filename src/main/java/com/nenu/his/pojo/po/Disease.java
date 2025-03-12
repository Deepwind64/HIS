package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("disease")
public class Disease {
    /**
     * 疾病助记编码
     */
    @TableField(value = "disease_code")
    private String diseaseCode;

    /**
     * 疾病名称
     */
    @TableField(value = "disease_name")
    private String diseaseName;

    /**
     * 国际ICD编码
     */
    @TableField(value = "disease_icd")
    private String diseaseICD;

    /**
     * 疾病类别
     */
    @TableField(value = "disease_category")
    private String diseaseCategory;
}
