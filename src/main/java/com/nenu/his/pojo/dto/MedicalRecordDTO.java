package com.nenu.his.pojo.dto;

import lombok.Data;

@Data
public class MedicalRecordDTO {
    private Integer id;
    /**
     * 关联挂号表id
     */
    private Integer registerId;

    /**
     * 主诉
     */
    private String readme;

    /**
     * 现病史
     */
    private String presentDisease;

    /**
     * 现病治疗情况
     */
    private String presentTreat;

    /**
     * 既往史
     */
    private String history;

    /**
     * 过敏史
     */
    private String allergy;

    /**
     * 体格检查
     */
    private String physique;

    /**
     * 检查/检验建议
     */
    private String proposal;

    /**
     * 注意事项
     */
    private String precaution;

    /**
     * 断诊结果
     */
    private String diagnosis;

    /**
     * 处理意见
     */
    private String cure;
}

