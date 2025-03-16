package com.nenu.his.pojo.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchRegisterDTO extends Page {
    private Integer employeeId;
    private String realName;
    private String caseNumber;
}
