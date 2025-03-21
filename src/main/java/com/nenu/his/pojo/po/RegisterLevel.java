package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("regist_level")
public class RegisterLevel extends Base{
	/**
	 * 号别编码
	 */
	@TableField(value = "regist_code")
	private String registerLevelCode;

	/**
	 * 号别名称
	 */
	@TableField(value = "regist_name")
	private String registerLevelName;

	/**
	 * 挂号费
	 */
	@TableField(value = "regist_fee")
	private BigDecimal registerFee;

	/**
	 * 挂号限额
	 */
	@TableField(value = "regist_quota")
	private Integer registerQuota;

	/**
	 * 显示顺序号
	 */
	@TableField(value = "sequence_no")
	private Integer sequenceNumber;

	/**
	 * 删除标记
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(value = "del_mark")
	private Integer delMark;
}
