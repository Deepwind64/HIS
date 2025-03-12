package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 处方
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("prescription")
public class Prescription extends Base{
	/**
	 * 挂号id
	 */
	@TableField(value = "register_id")
	private Integer registerId;

	/**
	 * 药品id
	 */
	@TableField(value = "drug_id")
	private Integer drugId;

	/**
	 * 用法用量和频次
	 */
	@TableField(value = "drug_usage")
	private String drugUsage;

	/**
	 * 数量
	 */
	@TableField(value = "drug_number")
	private Integer drugNumber;

	/**
	 * 状态(已开立、已缴费、已发药、已退药、已退费)
	 */
	@TableField(value = "drug_state")
	private String drugState;
}

