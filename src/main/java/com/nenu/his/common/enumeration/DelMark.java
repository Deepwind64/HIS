package com.nenu.his.common.enumeration;

public enum DelMark {
    /**
     * 生效标志（1：未删除，0：已删除）
     */
    NORMAL(1),
    DELETED(0);

    private Integer mark;

    DelMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
