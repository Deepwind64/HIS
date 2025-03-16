package com.nenu.his.common.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum RegisterState {
    /**
     * （1-已挂号 2-医生接诊 3-看诊结束 4-已退号）
     */
    REGISTER_STATE_UNKNOWN("未知状态", 0),
    REGISTER_STATE_REGISTERED("已挂号", 1),
    REGISTER_STATE_DIAGNOSIS("医生接诊", 2),
    REGISTER_STATE_END("看诊结束", 3),
    REGISTER_STATE_CANCEL("已退号", 4);

    private String value;
    private Integer number;

    static Map<Integer, RegisterState> registerStateMap = new HashMap<>();

    static {
        for (RegisterState registerState : RegisterState.values()) {
            registerStateMap.put(registerState.getNumber(), registerState);
        }
    }

    RegisterState(String value, Integer number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public static RegisterState getRegisterStateByNumber(Integer number) {
        return registerStateMap.get(number);
    }
}
