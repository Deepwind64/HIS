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
@TableName("role")
public class Role extends Base {
    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "role_code")
    private String roleDesc;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "del_mark")
    private Integer delMark;
}
