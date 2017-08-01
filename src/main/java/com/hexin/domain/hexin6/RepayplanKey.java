package com.hexin.domain.hexin6;

public class RepayplanKey {
    private Integer bidId;

    private Integer issue;

    /**
     * 1为奖励0为正常
     */
    private Integer repayType;

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Integer getIssue() {
        return this.issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public Integer getRepayType() {
        return this.repayType;
    }

    public void setRepayType(Integer repayType) {
        this.repayType = repayType;
    }
}