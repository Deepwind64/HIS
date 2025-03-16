package com.nenu.his.pojo.vo;

import lombok.Data;

@Data
public class RegisterMedicalVO{
    private Integer id;
    private String realName;
    private String caseNumber;
    private String gender;
    private String age;
    private String cardNumber;
}
