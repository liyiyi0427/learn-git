/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.dto;

import java.math.BigDecimal;

/**
 * 
 *
 * @author yanshun@hexindai.com
 */
public class InvestAssetDTO {
    private Byte investType;
    private Integer fnPackageId;
    private BigDecimal quota;
    private String userId;

    /**
     * @return the investType
     */
    public Byte getInvestType() {
        return this.investType;
    }

    /**
     * @param investType the investType to set
     */
    public void setInvestType(Byte investType) {
        this.investType = investType;
    }

    /**
     * @return the fnPackageId
     */
    public Integer getFnPackageId() {
        return this.fnPackageId;
    }

    /**
     * @param fnPackageId the fnPackageId to set
     */
    public void setFnPackageId(Integer fnPackageId) {
        this.fnPackageId = fnPackageId;
    }

    /**
     * @return the quota
     */
    public BigDecimal getQuota() {
        return this.quota;
    }

    /**
     * @param quota the quota to set
     */
    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
