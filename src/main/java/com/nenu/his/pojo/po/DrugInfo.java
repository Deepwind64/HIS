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
@TableName("drug_info")
public class DrugInfo extends Base{
	/**
	 * 药品编码（国家药品编码本位码共14位“86”，代表在我国境内生产、销售的所有药品；国家药品编码本位码类别码为“9”，代表药品；国家药品编码本位码本体码的前5位为药品企业标识）
	 */
	@TableField(value = "drug_code")
	private String drugCode;

	/**
	 * 药品名称
	 */
	@TableField(value = "drug_name")
	private String drugName;

	/**
	 * 药品规格
	 */
	@TableField(value = "drug_format")
	private String drugFormat;

	/**
	 * 包装单位
	 */
	@TableField(value = "drug_unit")
	private String drugUnit;

	/**
	 * 生产厂家
	 */
	@TableField(value = "manufacturer")
	private String manufacturer;

	/**
	 * 药剂类型
	 */
	@TableField(value = "drug_dosage")
	private String drugDosage;

	/**
	 * 药品类型
	 */
	@TableField(value = "drug_type")
	private String drugType;

	/**
	 * 药品单价
	 */
	@TableField(value = "drug_price")
	private Double drugPrice;

	/**
	 * 拼音助记码
	 */
	@TableField(value = "mnemonic_code")
	private String mnemonicCode;

	@Override
	public String toString() {
		return "DrugInfo [id=" + id + ", drugCode=" + drugCode + ", drugName=" + drugName + ", drugFormat=" + drugFormat
				+ ", drugUnit=" + drugUnit + ", manufacturer=" + manufacturer + ", drugDosage=" + drugDosage
				+ ", drugType=" + drugType + ", drugPrice=" + drugPrice + ", mnemonicCode=" + mnemonicCode
				+ ", createDate=" + createTime + "]";
	}
	
}
