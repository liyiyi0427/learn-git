/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.dto;

import java.math.BigDecimal;

/**
 * LevelDTO
 *
 * @author dongzhijie@hexindai.com
 */
public class LevelDTO {

    private Integer userId;
    private Boolean isVip;
    private Integer level;
    private BigDecimal totalAsset;
    private Long totalGrowth;
    private Long totalGift;

    /**
     * @return the isVip
     */
    public Boolean getIsVip() {
        return this.isVip;
    }

    /**
     * @param isVip the isVip to set
     */
    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * @return the totalGift
     */
    public Long getTotalGift() {
        return this.totalGift;
    }

    /**
     * @param totalGift the totalGift to set
     */
    public void setTotalGift(Long totalGift) {
        this.totalGift = totalGift;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the isVip
     */
    public Boolean isVip() {
        return this.isVip;
    }

    /**
     * @param isVip the isVip to set
     */
    public void setVip(Boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return this.level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the totalAsset
     */
    public BigDecimal getTotalAsset() {
        return this.totalAsset;
    }

    /**
     * @param totalAsset the totalAsset to set
     */
    public void setTotalAsset(BigDecimal totalAsset) {
        this.totalAsset = totalAsset;
    }

    /**
     * @return the totalGrowth
     */
    public Long getTotalGrowth() {
        return this.totalGrowth;
    }

    /**
     * @param totalGrowth the totalGrowth to set
     */
    public void setTotalGrowth(Long totalGrowth) {
        this.totalGrowth = totalGrowth;
    }

}
