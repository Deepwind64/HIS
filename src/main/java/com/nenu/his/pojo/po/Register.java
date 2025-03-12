package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("register")
public class Register extends Base{
	/**
	 * 病历号
	 */
	@TableField(value = "case_number")
	private String caseNumber;

	/**
	 * 姓名
	 */
	@TableField(value = "real_name")
	private String realName;

	/**
	 * 性别（男、女）
	 */
	@TableField(value = "gender")
	private String gender;

	/**
	 * 身份证号
	 */
	@TableField(value = "card_number")
	private String cardNumber;

	/**
	 * 出生日期
	 */
	@TableField(value = "birthday")
	private Date birthday;

	/**
	 * 年龄
	 */
	@TableField(value = "age")
	private Integer age;

	/**
	 * 年龄类型（年、天）
	 */
	@TableField(value = "age_type")
	private String ageType;

	/**
	 * 家庭住址
	 */
	@TableField(value = "home_address")
	private String homeAddress;

	/**
	 * 午别（上午、下午）
	 */
	@TableField(value = "noon")
	private String noon;

	/**
	 * 本次挂号科室id
	 */
	@TableField(value = "department_id")
	private Integer departmentId;

	/**
	 * 本次挂号医生id
	 */
	@TableField(value = "employee_id")
	private Integer employeeId;

	/**
	 * 本次挂号级别id
	 */
	@TableField(value = "regist_level_id")
	private Integer registerLevelId;

	/**
	 * 结算类别id
	 */
	@TableField(value = "settle_category_id")
	private Integer settleCategoryId;

	/**
	 * 病历本要否（要、否）
	 */
	@TableField(value = "is_book")
	private Boolean isBooked;

	/**
	 * 收费方式（现金、银行卡、微信、医保卡、支付宝）
	 */
	@TableField(value = "regist_method")
	private String registerMethod;

	/**
	 * 本次看诊状态（1-已挂号 2-医生接诊 3-看诊结束 4-已退号）
	 */
	@TableField(value = "visit_state")
	private Integer visitState;

	/**
	 * 挂号金额
	 */
	@TableField(value = "regist_money")
	private BigDecimal registerMoney;

	/**
	 * 检查请求列表（不在数据库中存在）
	 */
	@TableField(exist = false)
	private List<CheckRequest> checkRequestList = new ArrayList<>();

	@Override
	public String toString() {
		return "Register [id=" + id + ", caseNumber=" + caseNumber + ", realName=" + realName + ", gender=" + gender
				+ ", cardNumber=" + cardNumber + ", birthday=" + birthday + ", age=" + age + ", ageType=" + ageType
				+ ", homeAddress=" + homeAddress + ", visitDate=" + createTime + ", noon=" + noon + ", departmentId="
				+ departmentId + ", employeeId=" + employeeId + ", registerLevelId=" + registerLevelId + ", settleCategoryId="
				+ settleCategoryId + ", isBook=" + isBooked + ", registerMethod=" + registerMethod + ", visitState="
				+ visitState + ", registerMoney=" + registerMoney + "]";
	}
}
