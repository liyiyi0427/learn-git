package com.hexin.domain.hexin6;

import java.math.BigDecimal;
import java.util.Date;

public class CouponUser {
    private Integer id;
    
    private Byte couponType;
    
    private String couponNumber;
    
    private Byte isHandauto;

    private Integer couponTaskId;

    private Integer couponTypeId;

    private String userId;

    private String userName;

    private Byte useStatus;

    private Date beginTime;

    private Date endTime;

    private Date acquireTime;

    private Integer bidId;

    private Integer packageId;

    private Byte platform;

    private Integer investId;

    private Integer financePackageId;

    private BigDecimal investMoney;

    private BigDecimal couponMoeny;

    private Byte isHonour;

    private Integer version;

    private Date createTime;

    private Date updateTime;
    
    private Date useTime;
    
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Byte getCouponType() {
		return couponType;
	}

	public void setCouponType(Byte couponType) {
		this.couponType = couponType;
	}

	public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber == null ? null : couponNumber.trim();
    }

    public Byte getIsHandauto() {
        return isHandauto;
    }

    public void setIsHandauto(Byte isHandauto) {
        this.isHandauto = isHandauto;
    }

    public Integer getCouponTaskId() {
        return couponTaskId;
    }

    public void setCouponTaskId(Integer couponTaskId) {
        this.couponTaskId = couponTaskId;
    }

    public Integer getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(Integer couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public Integer getFinancePackageId() {
        return financePackageId;
    }

    public void setFinancePackageId(Integer financePackageId) {
        this.financePackageId = financePackageId;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public BigDecimal getCouponMoeny() {
        return couponMoeny;
    }

    public void setCouponMoeny(BigDecimal couponMoeny) {
        this.couponMoeny = couponMoeny;
    }

    public Byte getIsHonour() {
        return isHonour;
    }

    public void setIsHonour(Byte isHonour) {
        this.isHonour = isHonour;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}