package com.nenu.his.common.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Noon {
    MORNING("上午", 0),
    AFTERNOON("下午", 1);

    private String value;
    private Integer number;

    Noon(String value, Integer number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    static Map<Integer, Noon> noonMap = new HashMap<>();

    static {
        for (Noon noon : Noon.values()) {
            noonMap.put(noon.getNumber(), noon);
        }
    }

    private Integer getNumber() {
        return number;
    }

    public static Noon getNoonByNumber(Integer number){
        return noonMap.get(number);
    }
}
