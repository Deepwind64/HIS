package com.nenu.his.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeVO {
    private Integer id;
    private String realName;
}
