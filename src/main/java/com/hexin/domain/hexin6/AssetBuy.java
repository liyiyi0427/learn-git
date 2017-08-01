package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class AssetBuy {
    private Integer actionSn;

    private Integer assetId;

    private BigDecimal investMoney;

    private BigDecimal dealMoney;

    private BigDecimal quota;

    private BigDecimal principal;

    private BigDecimal interest;

    private BigDecimal reward;

    private String saler;

    private Integer bidId;

    private String buyer;

    private Date tsSubmit;
    private Boolean fnIsPackage;

    private Integer currentLevel;

    private BigDecimal plusRate;

    private Integer buyerNum;

    private Integer salerNum;

    private String authCode;

    public AssetBuy() {

    }

    public AssetBuy(AssetBuyBuilder builder) {
        this.actionSn = builder.actionSn;
        this.assetId = builder.assetId;
        this.investMoney = builder.investMoney;
        this.dealMoney = builder.dealMoney;
        this.quota = builder.quota;
        this.principal = builder.principal;
        this.interest = builder.interest;
        this.reward = builder.reward;
        this.saler = builder.saler;
        this.bidId = builder.bidId;
    }

    public static class AssetBuyBuilder {
        private Integer actionSn;

        private Integer assetId;

        private BigDecimal investMoney;

        private BigDecimal dealMoney;

        private BigDecimal quota;

        private BigDecimal principal;

        private BigDecimal interest;

        private BigDecimal reward;

        private String saler;

        private Integer bidId;

        public AssetBuyBuilder() {

        }

        public AssetBuy build() {
            return new AssetBuy(this);
        }

        /**
         * @param actionSn the actionSn to set
         */
        public void setActionSn(Integer actionSn) {
            this.actionSn = actionSn;
        }

        /**
         * @param assetId the assetId to set
         */
        public void setAssetId(Integer assetId) {
            this.assetId = assetId;
        }

        /**
         * @param investMoney the investMoney to set
         */
        public void setInvestMoney(BigDecimal investMoney) {
            this.investMoney = investMoney;
        }

        /**
         * @param dealMoney the dealMoney to set
         */
        public void setDealMoney(BigDecimal dealMoney) {
            this.dealMoney = dealMoney;
        }

        /**
         * @param quota the quota to set
         */
        public void setQuota(BigDecimal quota) {
            this.quota = quota;
        }

        /**
         * @param principal the principal to set
         */
        public void setPrincipal(BigDecimal principal) {
            this.principal = principal;
        }

        /**
         * @param interest the interest to set
         */
        public void setInterest(BigDecimal interest) {
            this.interest = interest;
        }

        /**
         * @param reward the reward to set
         */
        public void setReward(BigDecimal reward) {
            this.reward = reward;
        }

        /**
         * @param saler the saler to set
         */
        public void setSaler(String saler) {
            this.saler = saler;
        }

        /**
         * @param bidId the bidId to set
         */
        public void setBidId(Integer bidId) {
            this.bidId = bidId;
        }
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

    public BigDecimal getInvestMoney() {
        return this.investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public BigDecimal getDealMoney() {
        return this.dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public BigDecimal getQuota() {
        return this.quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
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

    public BigDecimal getReward() {
        return this.reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public String getSaler() {
        return this.saler;
    }

    public void setSaler(String saler) {
        this.saler = saler == null ? null : saler.trim();
    }

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public String getBuyer() {
        return this.buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getTsSubmit() {
        return this.tsSubmit;
    }

    public void setTsSubmit(Date tsSubmit) {
        this.tsSubmit = tsSubmit;
    }

    public Integer getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * @return the fnIsPackage
     */
    public Boolean getFnIsPackage() {
        return this.fnIsPackage;
    }

    /**
     * @param fnIsPackage the fnIsPackage to set
     */
    public void setFnIsPackage(Boolean fnIsPackage) {
        this.fnIsPackage = fnIsPackage;
    }

    public BigDecimal getPlusRate() {
        return this.plusRate;
    }

    public void setPlusRate(BigDecimal plusRate) {
        this.plusRate = plusRate;
    }

    public Integer getBuyerNum() {
        return this.buyerNum;
    }

    public void setBuyerNum(Integer buyerNum) {
        this.buyerNum = buyerNum;
    }

    public Integer getSalerNum() {
        return this.salerNum;
    }

    public void setSalerNum(Integer salerNum) {
        this.salerNum = salerNum;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}