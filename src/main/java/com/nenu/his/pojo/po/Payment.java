package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("settle_category")
public class Payment extends Base{
	/**
	 * 类别编码
	 */
	@TableField(value = "settle_code")
	private String settleCode;

	/**
	 * 类别名称
	 */
	@TableField(value = "settle_name")
	private String settleName;

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
