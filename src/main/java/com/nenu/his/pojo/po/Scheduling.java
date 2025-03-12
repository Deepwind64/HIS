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
@TableName("scheduling")
public class Scheduling extends Base {
    /**
     * 排班说明
     */
    @TableField(value = "rule_name")
    private String ruleName;

    /**
     * 生效标记
     */
    @TableField(value = "del_mark")
    @TableLogic(value = "0", delval = "1")
    private Integer delMark;

    /**
     * 班排规则14位01组成字符串，从星期日开始
     */
    @TableField(value = "week_rule")
    private String weekRule;
}
