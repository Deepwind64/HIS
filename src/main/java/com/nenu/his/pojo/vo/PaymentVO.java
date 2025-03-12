package com.nenu.his.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentVO {
    private String value;
    private String label;
}
