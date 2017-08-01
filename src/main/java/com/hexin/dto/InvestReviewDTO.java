/**
 * Copyright(c) 2011-2015 by HeXin Inc.
 * All Rights Reserved
 */
package com.hexin.dto;

import java.math.BigDecimal;

/**
 * 满标复审使用
 * 
 * @author zhoumin@hexindai.com
 */
public class InvestReviewDTO {

    private BigDecimal dealMoney;
    private Integer investId;
    private BigDecimal moneyFromRecharge;
    private BigDecimal moneyFromPrincipal;
    private BigDecimal moneyFromIncome;
    private BigDecimal moneyFromRepayment;
    private BigDecimal moneyFromLoan;
    private BigDecimal moneyFromLoanNetvalue;
    private BigDecimal moneyFromDecimal;
    private String investUserId;
    private Byte investUserIsVip;
    private Boolean isFinancePacakge;
    private Integer financePackageId;
    private Integer fnAccountId;

    // add wuxusen 代金券面值
    private BigDecimal voucherMoney;

    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public Integer getInvestId() {
        return this.investId;
    }
    public void setInvestId(Integer investId) {
        this.investId = investId;
    }
    public String getInvestUserId() {
        return this.investUserId;
    }
    public void setInvestUserId(String investUserId) {
        this.investUserId = investUserId;
    }
    public Byte getInvestUserIsVip() {
        return this.investUserIsVip;
    }
    public void setInvestUserIsVip(Byte investUserIsVip) {
        this.investUserIsVip = investUserIsVip;
    }

    public Boolean getIsFinancePacakge() {
        return this.isFinancePacakge;
    }

    public void setIsFinancePacakge(Boolean isFinancePacakge) {
        this.isFinancePacakge = isFinancePacakge;
    }

    public Integer getFinancePackageId() {
        return this.financePackageId;
    }

    public void setFinancePackageId(Integer financePackageId) {
        this.financePackageId = financePackageId;
    }

    /**
     * @return the fnAccountId
     */
    public Integer getFnAccountId() {
        return this.fnAccountId;
    }
    /**
     * @param fnAccountId the fnAccountId to set
     */
    public void setFnAccountId(Integer fnAccountId) {
        this.fnAccountId = fnAccountId;
    }
    /**
     * @return the dealMoney
     */
    public BigDecimal getDealMoney() {
        return this.dealMoney;
    }
    /**
     * @param dealMoney the dealMoney to set
     */
    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }
    /**
     * @return the moneyFromRecharge
     */
    public BigDecimal getMoneyFromRecharge() {
        return this.moneyFromRecharge;
    }
    /**
     * @param moneyFromRecharge the moneyFromRecharge to set
     */
    public void setMoneyFromRecharge(BigDecimal moneyFromRecharge) {
        this.moneyFromRecharge = moneyFromRecharge;
    }
    /**
     * @return the moneyFromPrincipal
     */
    public BigDecimal getMoneyFromPrincipal() {
        return this.moneyFromPrincipal;
    }
    /**
     * @param moneyFromPrincipal the moneyFromPrincipal to set
     */
    public void setMoneyFromPrincipal(BigDecimal moneyFromPrincipal) {
        this.moneyFromPrincipal = moneyFromPrincipal;
    }
    /**
     * @return the moneyFromIncome
     */
    public BigDecimal getMoneyFromIncome() {
        return this.moneyFromIncome;
    }
    /**
     * @param moneyFromIncome the moneyFromIncome to set
     */
    public void setMoneyFromIncome(BigDecimal moneyFromIncome) {
        this.moneyFromIncome = moneyFromIncome;
    }
    /**
     * @return the moneyFromRepayment
     */
    public BigDecimal getMoneyFromRepayment() {
        return this.moneyFromRepayment;
    }
    /**
     * @param moneyFromRepayment the moneyFromRepayment to set
     */
    public void setMoneyFromRepayment(BigDecimal moneyFromRepayment) {
        this.moneyFromRepayment = moneyFromRepayment;
    }
    /**
     * @return the moneyFromLoan
     */
    public BigDecimal getMoneyFromLoan() {
        return this.moneyFromLoan;
    }
    /**
     * @param moneyFromLoan the moneyFromLoan to set
     */
    public void setMoneyFromLoan(BigDecimal moneyFromLoan) {
        this.moneyFromLoan = moneyFromLoan;
    }
    /**
     * @return the moneyFromLoanNetvalue
     */
    public BigDecimal getMoneyFromLoanNetvalue() {
        return this.moneyFromLoanNetvalue;
    }
    /**
     * @param moneyFromLoanNetvalue the moneyFromLoanNetvalue to set
     */
    public void setMoneyFromLoanNetvalue(BigDecimal moneyFromLoanNetvalue) {
        this.moneyFromLoanNetvalue = moneyFromLoanNetvalue;
    }

    public BigDecimal getMoneyFromDecimal() {
        return moneyFromDecimal;
    }

    public void setMoneyFromDecimal(BigDecimal moneyFromDecimal) {
        this.moneyFromDecimal = moneyFromDecimal;
    }

}
