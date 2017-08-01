package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class Asset {
    private Integer assetId;

    private Integer investId;

    private Integer bidId;

    private String userId;

    private Integer type;

    private BigDecimal quota;

    private BigDecimal sale;

    private Date creatTime;

    private BigDecimal interestFeeRate;

    private Integer state;

    private Integer fnIsPackage;

    private Integer fnPackageId;
    private Integer fnBackEndId;

    private Integer userIdNum;

    private String platFormSn;

    private BigDecimal preTradeAmount;

    private BigDecimal payInterest = BigDecimal.ZERO;

    private BigDecimal remainPrincipal = BigDecimal.ZERO;
    private BigDecimal receivableInterest = BigDecimal.ZERO;
    private Date updateTime;

    public BigDecimal getPayInterest() {
        return this.payInterest;
    }

    public void setPayInterest(BigDecimal payInterest) {
        this.payInterest = payInterest;
    }

    public Integer getUserIdNum() {
        return this.userIdNum;
    }

    public void setUserIdNum(Integer userIdNum) {
        this.userIdNum = userIdNum;
    }

    public Integer getAssetId() {
        return this.assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getInvestId() {
        return this.investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getQuota() {
        return this.quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getSale() {
        return this.sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public Date getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getInterestFeeRate() {
        return this.interestFeeRate;
    }

    public void setInterestFeeRate(BigDecimal interestFeeRate) {
        this.interestFeeRate = interestFeeRate;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getFnIsPackage() {
        return this.fnIsPackage;
    }

    public void setFnIsPackage(Integer fnIsPackage) {
        this.fnIsPackage = fnIsPackage;
    }

    public Integer getFnPackageId() {
        return this.fnPackageId;
    }

    public void setFnPackageId(Integer fnPackageId) {
        this.fnPackageId = fnPackageId;
    }

    /**
     * @return the fnBackEndId
     */
    public Integer getFnBackEndId() {
        return this.fnBackEndId;
    }

    /**
     * @param fnBackEndId the fnBackEndId to set
     */
    public void setFnBackEndId(Integer fnBackEndId) {
        this.fnBackEndId = fnBackEndId;
    }

    public String getPlatFormSn() {
        return this.platFormSn;
    }

    public void setPlatFormSn(String platFormSn) {
        this.platFormSn = platFormSn;
    }

    public BigDecimal getPreTradeAmount() {
        return this.preTradeAmount;
    }

    public void setPreTradeAmount(BigDecimal preTradeAmount) {
        this.preTradeAmount = preTradeAmount;
    }

    public BigDecimal getRemainPrincipal() {
        return this.remainPrincipal;
    }

    public void setRemainPrincipal(BigDecimal remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public BigDecimal getReceivableInterest() {
        return this.receivableInterest;
    }

    public void setReceivableInterest(BigDecimal receivableInterest) {
        this.receivableInterest = receivableInterest;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}