package com.nenu.his.common.enumeration;

import lombok.Getter;

/**
 * 状态(已开立,已缴费,执行完成,已出结果,已退费,已作废)
 */
public enum CommonState {

    OPENED("已开立"),
    PAID("已缴费"),
    EXECUTED("执行完成"),
    RESULTED("已出结果"),
    REFUNDED("已退费"),
    CANCELLED("已作废");

    @Getter
    private final String state;

    CommonState(String state) {
        this.state = state;
    }
}
