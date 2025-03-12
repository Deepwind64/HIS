package com.nenu.his.common.enumeration;

import lombok.Getter;

public enum DrugState {
    /**
     * 状态(已开立、已缴费、已发药、已退药、已退费)
     */
    OPENED("已开立"),
    PAID("已缴费"),
    ISSUED("执行完成"),
    RETURNED("已出结果"),
    REFUNDED("已退费");

    @Getter
    private final String state;

    DrugState(String state) {
        this.state = state;
    }
}
