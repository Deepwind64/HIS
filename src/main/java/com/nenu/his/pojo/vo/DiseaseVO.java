package com.nenu.his.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DiseaseVO {
    private String diseaseCode;

    private String diseaseName;

    private String diseaseICD;

    private String diseaseCategory;
}
