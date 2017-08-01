/**
 * Copyright(c) 2011-2015 by HeXin Inc.
 * All Rights Reserved
 */
package com.hexin.enums;

/**
 * 还款类型
 * 
 * @author zhishuo
 */
public enum RepaymentType {
    RP_INTEREST_FIRST(1)/* 每月还息到期还本 */, RP_ONCE(2)/* 一次性还款 */, RP_AVERAGE(3)/* 按月还款等额本息 */;

    private int text;

    private RepaymentType(int text) {
        this.text = text;
    }

    public int getText() {
        return this.text;
    }

    public void setText(int text) {
        this.text = text;
    }
}
