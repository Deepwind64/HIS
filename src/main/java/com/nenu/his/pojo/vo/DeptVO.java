package com.nenu.his.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeptVO {
    private Integer id;
    private String deptName;
}
