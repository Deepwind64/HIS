package com.nenu.his.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginVO {
    private Integer id;
    private String realName;
    private String token;
    private List<PermitVO> permitList;
}
