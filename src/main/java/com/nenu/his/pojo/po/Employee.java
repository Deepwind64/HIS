package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee")
@EqualsAndHashCode(callSuper = true)
public class Employee extends Base{
	/**
	 * 真实姓名
	 */
	@TableField(value = "realname")
	private String realName;

	/**
	 * 密码
	 */
	@TableField(value = "password")
	private String password;

	/**
	 * 所在科室ID
	 */
	@TableField(value = "department_id")
	private Integer departmentId;

	/**
	 * 挂号级别ID
	 */
	@TableField(value = "register_level_id")
	private Integer registerLevelId;

	/**
	 * 排班ID
	 */
	@TableField(value = "scheduling_id")
	private Integer schedulingId;

	/**
	 * 生效标记
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(value = "del_mark")
	private Integer delMark;

	/**
	 * 角色
	 */
	@TableField(value = "role")
	private Integer role;

	/**
	 * 权限列表
	 */
	@TableField(exist = false)
	private List<Permit> permitList;
}
