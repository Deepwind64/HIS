package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("department")
public class Department extends Base{
	/**
	 * 科室编码
	 */
	@TableField(value = "dept_code")
	private String deptCode;

	/**
	 * 科室名称
	 */
	@TableField(value = "dept_name")
	private String deptName;

	/**
	 * 科室类型（门诊、检查、检验、处置）
	 */
	@TableField(value = "dept_type")
	private String deptType;

	/**
	 * 生效标志（1：未删除，0：已删除）
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(value = "del_mark")
	private Integer delMark;
}
