package com.nenu.his.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RegisterDTO {
    // 病历号
    private String caseNumber;
    // 姓名
    private String realName;
    // 性别
    private String gender;
    // 身份证号
    private String cardNumber;
    // 出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;
    // 年龄
    private Integer age;
    // 年龄类型（岁、天）
    private String ageType;
    // 地址
    private String homeAddress;
    // 看诊日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime visitDate;
    // 午别（0-上午、1-下午）
    private Integer noon;
    // 挂号科室的id
    private Integer departmentId;
    // 看诊医生的id
    private Integer employeeId;
    // 号别的id
    private Integer registerLevelId;
    // 结算类型的id（自费、市医保）
    private Integer settleCategoryId;
    // 是否有病历本（true、false）
    private Boolean isBook;
    // 收费方式
    private String registerMethod;
    // 应收金额
    private String registerMoney;
}

