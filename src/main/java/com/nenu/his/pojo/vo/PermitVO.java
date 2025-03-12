package com.nenu.his.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PermitVO {
    private String permitName;
    private String permitPath;
    private String permitIcon;
    private List<PermitVO> permitList;
}
