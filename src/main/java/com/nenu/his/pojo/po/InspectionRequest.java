package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("inspection_request")
public class InspectionRequest extends Base{
	/**
	 * 挂号id
	 */
	@TableField(value = "register_id")
	private Integer registerId;

	/**
	 * 医疗技术id
	 */
	@TableField(value = "medical_technology_id")
	private Integer medicalTechnologyId;

	/**
	 * 目的要求
	 */
	@TableField(value = "inspection_info")
	private String inspectionInfo;

	/**
	 * 检查部位
	 */
	@TableField(value = "inspection_position")
	private String inspectionPosition;

	/**
	 * 检验人员id
	 */
	@TableField(value = "inspection_employee_id")
	private Integer inspectionEmployeeId;

	/**
	 * 结果录入人员id
	 */
	@TableField(value = "inputinspection_employee_id")
	private Integer inputinspectionEmployeeId;

	/**
	 * 检验时间
	 */
	@TableField(value = "inspection_time")
	private Date inspectionTime;

	/**
	 * 检验结果
	 */
	@TableField(value = "inspection_result")
	private String inspectionResult;

	/**
	 * 状态(已开立,已缴费,执行完成,已出结果,已退费,已作废)
	 */
	@TableField(value = "inspection_state")
	private String inspectionState;

	/**
	 * 备注
	 */
	@TableField(value = "inspection_remark")
	private String inspectionRemark;
}
