package com.nenu.his.common.enumeration;

public enum DeptType {
    /**
     * 科室类型（门诊、检查、检验、处置）
     */
    OUTPATIENT("门诊"),
    CHECK("检查"),
    INSPECTION("检验"),
    DISPOSAL("处置");

    private String type;

    DeptType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
