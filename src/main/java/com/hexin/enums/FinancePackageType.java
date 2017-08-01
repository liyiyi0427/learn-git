package com.hexin.enums;

public enum FinancePackageType {
	FP_COMMON(1), // 1——普通荷包
    FP_REWARD(2), // 2——月返荷包  奖励荷包
    FP_ACTIVITY(3),// 3——活动荷包
	FP_MONTHADDRATE(5),//5——月月生息荷包
    FP_MONTHRETURN(4);//4-新月返

    private int text;

    public int getText() {
        return this.text;
    }

    public void setText(int text) {
        this.text = text;
    }

    private FinancePackageType(int text) {
        this.text = text;
    }

    public static FinancePackageType getEnumByText(Integer text) {
        if (null == text) {
            return null;
        }
        for (FinancePackageType fpt : FinancePackageType.values()) {
            if (fpt.text == text) {
                return fpt;
            }
        }
        return null;
    }
}
