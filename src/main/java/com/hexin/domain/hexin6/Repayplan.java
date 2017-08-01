package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class Repayplan extends RepayplanKey {

    private Integer packageId;

    private BigDecimal principal;

    private BigDecimal interest;

    private Date repayday;

    private byte isPackage;

    private Boolean isReInvest;

    private Byte isRepurchase;// 是否需回购

    private Byte repurchaseState;// 回购状态0：待处理，1：已申请，2：已回购

    /**
     * 0——未还\\n1——提前还清\\n2——正常还清\\n3——无 4--理赔还款
     */
    private Byte state;
    private Byte processBankState;

    public Byte getProcessBankState() {
        return processBankState;
    }

    public void setProcessBankState(Byte processBankState) {
        this.processBankState = processBankState;
    }

    /**
     * @return the isPackage
     */
    public byte getIsPackage() {
        return this.isPackage;
    }

    /**
     * @param isPackage the isPackage to set
     */
    public void setIsPackage(byte isPackage) {
        this.isPackage = isPackage;
    }

    /**
     * 0--正常 10--逾期(标记逾期状态)
     */
    private Byte repayState;

    public Byte getRepayState() {
        return this.repayState;
    }

    public void setRepayState(Byte repayState) {
        this.repayState = repayState;
    }

    public BigDecimal getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getRepayday() {
        return this.repayday;
    }

    public void setRepayday(Date repayday) {
        this.repayday = repayday;
    }

    public Byte getState() {
        return this.state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * @return the isReInvest
     */
    public Boolean getIsReInvest() {
        return this.isReInvest;
    }

    /**
     * @param isReInvest the isReInvest to set
     */
    public void setIsReInvest(Boolean isReInvest) {
        this.isReInvest = isReInvest;
    }

    public Byte getIsRepurchase() {
        return this.isRepurchase;
    }

    public void setIsRepurchase(Byte isRepurchase) {
        this.isRepurchase = isRepurchase;
    }

    public Byte getRepurchaseState() {
        return this.repurchaseState;
    }

    public void setRepurchaseState(Byte repurchaseState) {
        this.repurchaseState = repurchaseState;
    }
}