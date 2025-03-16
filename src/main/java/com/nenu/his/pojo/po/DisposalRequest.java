package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("disposal_request")
public class DisposalRequest extends Base{
	/**
	 * 挂号ID
	 */
	@TableField(value = "register_id")
	private Integer registerId;

	/**
	 * 医疗技术ID
	 */
	@TableField(value = "medical_technology_id")
	private Integer medicalTechnologyId;

	/**
	 * 处置信息
	 */
	@TableField(value = "disposal_info")
	private String disposalInfo;

	/**
	 * 处置位置
	 */
	@TableField(value = "disposal_position")
	private String disposalPosition;

	/**
	 * 处置员工ID
	 */
	@TableField(value = "disposal_employee_id")
	private Integer disposalEmployeeId;

	/**
	 * 录入处置员工ID
	 */
	@TableField(value = "input_disposal_employee_id")
	private Integer inputDisposalEmployeeId;

	/**
	 * 处置时间
	 */
	@TableField(value = "disposal_time")
	private Date disposalTime;

	/**
	 * 处置结果
	 */
	@TableField(value = "disposal_result")
	private String disposalResult;

	/**
	 * 处置状态
	 */
	@TableField(value = "disposal_state")
	private String disposalState;

	/**
	 * 处置备注
	 */
	@TableField(value = "disposal_remark")
	private String disposalRemark;
}
