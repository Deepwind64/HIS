package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Base {
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    public Date createTime;
}
