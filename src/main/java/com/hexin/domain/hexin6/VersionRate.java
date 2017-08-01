package com.hexin.domain.hexin6;

import java.math.BigDecimal;

public class VersionRate {
    private Integer id;

    private Byte month;

    private BigDecimal rate;

    private Integer versionId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getMonth() {
        return this.month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return this.rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }
}