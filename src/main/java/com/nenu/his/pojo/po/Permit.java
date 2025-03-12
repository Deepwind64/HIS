package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;


/**
 * @author deepwind
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("permit")
@EqualsAndHashCode(callSuper = true)
public class Permit extends Base{
	/**
	 * 权限名称
	 */
	@TableField(value = "permit_name")
	private String permitName;

	/**
	 * 权限路径
	 */
	@TableField(value = "permit_path")
	private String permitPath;

	/**
	 * 权限图标
	 */
	@TableField(value = "permit_icon")
	private String permitIcon;

	/**
	 * 父权限ID
	 */
	@TableField(value = "permit_parent")
	private Integer permitParent;

	/**
	 * 子权限列表（不在数据库中存在）
	 */
	@TableField(exist = false)
	private List<Permit> permitList;
}
