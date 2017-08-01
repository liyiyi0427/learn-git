package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class Invest {
    private Integer actionSn;
    private Integer bidId;
    private BigDecimal investMoney;
    private Integer auto;
    private BigDecimal dealMoney;
    private BigDecimal expectInterest;
    private BigDecimal expectReward;
    private BigDecimal expectPlusRate;
    private BigDecimal moneyFromRecharge;
    private BigDecimal moneyFromPrincipal;
    private BigDecimal moneyFromIncome;
    private BigDecimal moneyFromRepayment;
    private BigDecimal moneyFromLoan;
    private BigDecimal moneyFromLoanNetvalue;
    private BigDecimal moneyFromDecimal;

    private Integer jobid;

    private String userId;

    private Integer userIsVip;

    private Date tsSubmit;

    private Integer financePackageId;

    private Integer isFinancePackage;

    private String fnUserId;

    private String fnUserName;

    private Date fnInvestTime;

    private Integer fnAccountId;

    private Integer type;

    private Integer currentLevel;

    private BigDecimal voucherMoney;

    private Integer userIdNum;
    private String authCode;

    // author wuxusen 2017年1月4日  代金券类型
    private Byte couponType;

    public Byte getCouponType() {
        return couponType;
    }

    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Integer getActionSn() {
        return this.actionSn;
    }

    public void setActionSn(Integer actionSn) {
        this.actionSn = actionSn;
    }

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public BigDecimal getInvestMoney() {
        return this.investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Integer getAuto() {
        return this.auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public BigDecimal getDealMoney() {
        return this.dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public BigDecimal getExpectInterest() {
        return this.expectInterest;
    }

    public void setExpectInterest(BigDecimal expectInterest) {
        this.expectInterest = expectInterest;
    }

    public BigDecimal getExpectReward() {
        return this.expectReward;
    }

    public void setExpectReward(BigDecimal expectReward) {
        this.expectReward = expectReward;
    }

    public BigDecimal getMoneyFromRecharge() {
        return this.moneyFromRecharge;
    }

    public void setMoneyFromRecharge(BigDecimal moneyFromRecharge) {
        this.moneyFromRecharge = moneyFromRecharge;
    }

    public BigDecimal getMoneyFromPrincipal() {
        return this.moneyFromPrincipal;
    }

    public void setMoneyFromPrincipal(BigDecimal moneyFromPrincipal) {
        this.moneyFromPrincipal = moneyFromPrincipal;
    }

    public BigDecimal getMoneyFromIncome() {
        return this.moneyFromIncome;
    }

    public void setMoneyFromIncome(BigDecimal moneyFromIncome) {
        this.moneyFromIncome = moneyFromIncome;
    }

    public BigDecimal getMoneyFromRepayment() {
        return this.moneyFromRepayment;
    }

    public void setMoneyFromRepayment(BigDecimal moneyFromRepayment) {
        this.moneyFromRepayment = moneyFromRepayment;
    }

    public BigDecimal getMoneyFromLoan() {
        return this.moneyFromLoan;
    }

    public void setMoneyFromLoan(BigDecimal moneyFromLoan) {
        this.moneyFromLoan = moneyFromLoan;
    }

    public BigDecimal getMoneyFromLoanNetvalue() {
        return this.moneyFromLoanNetvalue;
    }

    public void setMoneyFromLoanNetvalue(BigDecimal moneyFromLoanNetvalue) {
        this.moneyFromLoanNetvalue = moneyFromLoanNetvalue;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getUserIsVip() {
        return this.userIsVip;
    }

    public void setUserIsVip(Integer userIsVip) {
        this.userIsVip = userIsVip;
    }

    public Date getTsSubmit() {
        return this.tsSubmit;
    }

    public void setTsSubmit(Date tsSubmit) {
        this.tsSubmit = tsSubmit;
    }

    public Integer getFinancePackageId() {
        return this.financePackageId;
    }

    public void setFinancePackageId(Integer financePackageId) {
        this.financePackageId = financePackageId;
    }

    public Integer getIsFinancePackage() {
        return this.isFinancePackage;
    }

    public void setIsFinancePackage(Integer isFinancePackage) {
        this.isFinancePackage = isFinancePackage;
    }

    public String getFnUserId() {
        return this.fnUserId;
    }

    public void setFnUserId(String fnUserId) {
        this.fnUserId = fnUserId == null ? null : fnUserId.trim();
    }

    public String getFnUserName() {
        return this.fnUserName;
    }

    public void setFnUserName(String fnUserName) {
        this.fnUserName = fnUserName == null ? null : fnUserName.trim();
    }

    public Date getFnInvestTime() {
        return this.fnInvestTime;
    }

    public void setFnInvestTime(Date fnInvestTime) {
        this.fnInvestTime = fnInvestTime;
    }

    public Integer getFnAccountId() {
        return this.fnAccountId;
    }

    public void setFnAccountId(Integer fnAccountId) {
        this.fnAccountId = fnAccountId;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public BigDecimal getExpectPlusRate() {
        return this.expectPlusRate;
    }

    public void setExpectPlusRate(BigDecimal expectPlusRate) {
        this.expectPlusRate = expectPlusRate;
    }

    public BigDecimal getVoucherMoney() {
        return this.voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public Integer getUserIdNum() {
        return this.userIdNum;
    }

    public void setUserIdNum(Integer userIdNum) {
        this.userIdNum = userIdNum;
    }

    public BigDecimal getMoneyFromDecimal() {
        return this.moneyFromDecimal;
    }

    public void setMoneyFromDecimal(BigDecimal moneyFromDecimal) {
        this.moneyFromDecimal = moneyFromDecimal;
    }

}