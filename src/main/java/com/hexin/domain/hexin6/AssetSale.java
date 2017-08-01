package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class AssetSale {
    private Integer actionSn;

    private Integer assetId;

    private BigDecimal price;

    private BigDecimal quota;

    private BigDecimal minMoney;

    private BigDecimal attornFeeRate;

    private BigDecimal attornFee;

    private Date startTime;

    private Date endTime;

    private Byte invalid;

    private Integer saledTimes;

    private BigDecimal remainPrincipal;

    private BigDecimal remainInterest;

    private BigDecimal remainReward;

    private Integer remainIssue;

    private BigDecimal saleMoney;

    private Date tsSubmit;

    private BigDecimal remainPlusRate;

    private Integer userIdNum;

    private String userId;

    private Integer state;

    private Integer bidId;

    // 转让人应收利息
    private BigDecimal receivableInterest;
    // 转让人应收利息的利息管理费
    private BigDecimal interestFee;
    private Date updateTime;// 转让中债权 利息 利息管理费 更新时间
    
    public AssetSale() {}

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getInterestFee() {
        return this.interestFee;
    }

    public void setInterestFee(BigDecimal interestFee) {
        this.interestFee = interestFee;
    }

    public BigDecimal getReceivableInterest() {
        return this.receivableInterest;
    }

    public void setReceivableInterest(BigDecimal receivableInterest) {
        this.receivableInterest = receivableInterest;
    }

    public Integer getActionSn() {
        return this.actionSn;
    }

    public void setActionSn(Integer actionSn) {
        this.actionSn = actionSn;
    }

    public Integer getAssetId() {
        return this.assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuota() {
        return this.quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getMinMoney() {
        return this.minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getAttornFeeRate() {
        return this.attornFeeRate;
    }

    public void setAttornFeeRate(BigDecimal attornFeeRate) {
        this.attornFeeRate = attornFeeRate;
    }

    public BigDecimal getAttornFee() {
        return this.attornFee;
    }

    public void setAttornFee(BigDecimal attornFee) {
        this.attornFee = attornFee;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getInvalid() {
        return this.invalid;
    }

    public void setInvalid(Byte invalid) {
        this.invalid = invalid;
    }

    public Integer getSaledTimes() {
        return this.saledTimes;
    }

    public void setSaledTimes(Integer saledTimes) {
        this.saledTimes = saledTimes;
    }

    public BigDecimal getRemainPrincipal() {
        return this.remainPrincipal;
    }

    public void setRemainPrincipal(BigDecimal remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public BigDecimal getRemainInterest() {
        return this.remainInterest;
    }

    public void setRemainInterest(BigDecimal remainInterest) {
        this.remainInterest = remainInterest;
    }

    public BigDecimal getRemainReward() {
        return this.remainReward;
    }

    public void setRemainReward(BigDecimal remainReward) {
        this.remainReward = remainReward;
    }

    public Integer getRemainIssue() {
        return this.remainIssue;
    }

    public void setRemainIssue(Integer remainIssue) {
        this.remainIssue = remainIssue;
    }

    public BigDecimal getSaleMoney() {
        return this.saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Date getTsSubmit() {
        return this.tsSubmit;
    }

    public void setTsSubmit(Date tsSubmit) {
        this.tsSubmit = tsSubmit;
    }

    public BigDecimal getRemainPlusRate() {
        return this.remainPlusRate;
    }

    public void setRemainPlusRate(BigDecimal remainPlusRate) {
        this.remainPlusRate = remainPlusRate;
    }

    public Integer getUserIdNum() {
        return this.userIdNum;
    }

    public void setUserIdNum(Integer userIdNum) {
        this.userIdNum = userIdNum;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }
}