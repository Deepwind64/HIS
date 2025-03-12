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
@TableName("medical_record")
public class MedicalRecord extends Base{
	/**
	 * 关联挂号表id
	 */
	@TableField(value = "register_id")
	private Integer registerId;

	/**
	 * 主诉
	 */
	@TableField(value = "readme")
	private String readme;

	/**
	 * 现病史
	 */
	@TableField(value = "present")
	private String presentDisease;

	/**
	 * 现病治疗情况
	 */
	@TableField(value = "present_treat")
	private String presentTreat;

	/**
	 * 既往史
	 */
	@TableField(value = "history")
	private String history;

	/**
	 * 过敏史
	 */
	@TableField(value = "allergy")
	private String allergy;

	/**
	 * 体格检查
	 */
	@TableField(value = "physique")
	private String physique;

	/**
	 * 检查/检验建议
	 */
	@TableField(value = "proposal")
	private String proposal;

	/**
	 * 注意事项
	 */
	@TableField(value = "precaution")
	private String precaution;

	/**
	 * 断诊结果
	 */
	@TableField(value = "diagnosis")
	private String diagnosis;

	/**
	 * 处理意见
	 */
	@TableField(value = "cure")
	private String cure;
}
