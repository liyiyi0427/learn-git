/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.enums;

/**
 * 荷包持有状态
 *
 * @author panmeng@hexindai.com
 */
public enum OwnedState {
    OWNING((byte) 1)/* 持有中 */, QUIT((byte) 2)/* 申请退出 */, QUITING((byte) 3)/* 退出中 */, QUITED((byte) 4)/* 已退出/已结清 */, JOINING(
            (byte) 5)/* 加入中 */;

    private byte text;

    private OwnedState(byte text) {
        this.text = text;
    }

    public byte getText() {
        return this.text;
    }

    public void setText(byte text) {
        this.text = text;
    }
}
