package com.hexin.domain.hexin6;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hexin.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Date;


public class BidDocument extends BidDocumentCal {

    private Integer bidId;// 标ID

    private String bidName;// 借款项目名称

    private String userId;// 借款人ID

    private Integer userIsTrustee;// 是否是托管用户

    private Integer userCredit;// 发标人信誉等级

    private Integer bidType;// 标类型

    private BigDecimal money;// 借款金额

    private Integer duration;// 借款月数

    private Integer repaymentType;// 还款方式

    private BigDecimal rate;// 年利率

    private BigDecimal reward;// 奖励
    private BigDecimal plusRate;// 贴息

    private BigDecimal loanFeeRate;// 借款管理费率

    private BigDecimal prepaymentFeeRate;// 提前还款违约赔偿金费率

    private BigDecimal delaypaymentFeeRate;// 逾期还款罚息率

    private BigDecimal minimum;// 投标最小额度

    private BigDecimal maximum;// 投资最大额度

    private String password;// 投资密码

    private Integer autoMoney;// 自动投标比例

    private String bidDetail;// 借款详情

    private String repaymentDetail;// 还款详情

    private String guarantee;// 担保公司

    private Date transactTime;// 成交时间

    private Date endOfInvest;// 投标截止时间

    private Date effectTime;// 生效时间

    private Date expectEffectTime;// 期望生效时间

    private Integer validDays;// 投资有效期（天数），用于计算投资截止时间

    private BigDecimal completeMoney;// 完成比例

    private Integer state;// 标状态

    private Integer issueCount;// 总期数

    private Integer issueDone;// 已还期数

    private Integer issueInterest;// 每期应还利息

    private Byte publishBeforeAudit;// 是否在审核前发布

    private String creditCompanyId;// 仅适用于信用标；表示信用公司

    private Integer creditFee;// 仅适用于信用标；表示费用

    private Integer units;// 借款份数，用于流转标

    private BigDecimal remainPrincipal;// 剩余本金

    private BigDecimal remainInterest;// 剩余利息（按计划）

    private BigDecimal remainReward;// 剩余奖励
    private BigDecimal remainPlusRate;// 剩余贴息

    private BigDecimal interestSum;// 利息总额（按计划）

    private BigDecimal rewardSum;// 奖励总额（按计划）

    private String contractId;// 合同ID

    private Date applyTime;// 申请时间

    private String borrowUname;// 借款人姓名

    private Integer appPriority;// 客户端优先时间

    private Date failTime;// 失败时间

    private Integer bidTypeSecond;// 标二级类型

    private String bidBorrowUserIdentify;// 借款人身份证号

    private String bidMortgageUserId;// 他项权证用户ID

    private Integer parentId;// 父ID

    private Integer isNovice;// 是否为新手专享标 0否，1是

    private Date noviceInvalidTime;// 新手专享标的失效时间，之后就所有人都可以投了

    private String firstAuditUser; // 初审人ID

    private Date firstAuditTime; // 初审时间

    private String reviewAuditUser; // 复审人
    private Date reviewAuditTime; // 复审时间
    // private String reviewAuditUser; // 复审人

    private Date canelTime; // 取消时间

    // private Integer joinPackageType;// 加入理财包类型

    private Integer forPackage;

    private String deleteUserId;// 删除人

    private Date deleteTime;// 删除时间

    private String deleteReason;// 删除原因

    private String applyNum;// 申请编号

    private BigDecimal contractMoney;// 合同金额

    private Date loanTime;// 放款时间

    private BigDecimal realRate;

    private String fnContractId;// 理财包中信用标合同编号

    private Date createTime;// 创建时间

    private Integer joinIssue;// 实际借款期限

    private String reviewDetail;// 满标复审审核意见

    private Byte fnJoinState;// 状态：散标、匹配中、已匹配

    private Date publishTime;

    private String createUserName;// 录入人姓名

    private String userName;// 借款人用户名

    private Byte isRepurchase;// 是否需回购

    private Byte repurchaseState;// 回购状态0：待处理，1：已申请，2：已回购
    private Integer isPlusRate;// 是否贴息标 0否，1是

    private BigDecimal plusRateSum;

    private Byte isRenew;// 是否续借
    private Integer userIdNum;
    // add by liyiyi 冗余初审结果
    private Integer firstTrialState;
    // add by liyiyi 冗余复审结果
    private Integer reviewTrialState;
    // 标的来源
    private Integer bidFromType;
    // 咨询费
    private BigDecimal consultingFee;
    // 服务费
    private BigDecimal serviceFee;
    // 用户是否退出
    private Integer isQuit;
    private String loanCode;

    private Date quitTime;

    private String auditingCode;

    private Integer trusteeShipUserId;
    private Byte isRepay;

    // 可提现费
    private BigDecimal canWithdrawalAmount;

    private Integer specialRepaymentProcessToBankState;
    /**
     * 0--未提现 1--划扣成功 2--提现成功
     */
    private Integer isWithdrawServiceFeeSuccess;

    private BigDecimal withdrawServiceFeeLoanMoney;

    private Integer offlineBidType;
    private Integer isMortgageCar;

    private Integer isClaim;
    private Integer isFinishClaim;
    /**
     * 信用标等级
     */
    private String creditRating;
    /**
     * 获客来源
     */
    private Integer customerChannel;

    private BigDecimal totalInterest;

    public static enum OfflineBidType {
        CreditLoan(0, "信贷"), MortgageLoan(1, "抵押贷"), MortgageHouse(2, "房贷");
        int value;
        String text;

        private OfflineBidType(int value, String text) {
            this.text = text;
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public static OfflineBidType getEnumByValue(int value) {
            for (OfflineBidType item : values()) {
                if (item.value == value) {
                    return item;
                }
            }
            throw new BusinessException("当前状态下不允许的操作");
        }
    }

    public Integer getOfflineBidType() {
        return this.offlineBidType;
    }

    public void setOfflineBidType(Integer offlineBidType) {
        this.offlineBidType = offlineBidType;
    }

    public BigDecimal getCanWithdrawalAmount() {
        return this.canWithdrawalAmount;
    }

    public void setCanWithdrawalAmount(BigDecimal canWithdrawalAmount) {
        this.canWithdrawalAmount = canWithdrawalAmount;
    }

    public Byte getIsRepay() {
        return this.isRepay;
    }

    public void setIsRepay(Byte isRepay) {
        this.isRepay = isRepay;
    }

    public Integer getIsSubmitFullReview() {
        return this.isSubmitFullReview;
    }

    public void setIsSubmitFullReview(Integer isSubmitFullReview) {
        this.isSubmitFullReview = isSubmitFullReview;
    }

    // 是否提交银行满标复审
    private Integer isSubmitFullReview;

    public String getAuditingCode() {
        return this.auditingCode;
    }

    public void setAuditingCode(String auditingCode) {
        this.auditingCode = auditingCode;
    }

    public Integer getFirstTrialState() {
        return this.firstTrialState;
    }

    public void setFirstTrialState(Integer firstTrialState) {
        this.firstTrialState = firstTrialState;
    }

    public Integer getReviewTrialState() {
        return this.reviewTrialState;
    }

    public void setReviewTrialState(Integer reviewTrialState) {
        this.reviewTrialState = reviewTrialState;
    }

    public Integer getUserIdNum() {
        return this.userIdNum;
    }

    public void setUserIdNum(Integer userIdNum) {
        this.userIdNum = userIdNum;
    }

    /**
     * @param bidId
     * @param completeMoney
     */
    public BidDocument(Integer bidId, BigDecimal completeMoney) {
        this.bidId = bidId;
        this.completeMoney = completeMoney;
    }

    /**
     * 
     */
    public BidDocument() {}

    public String getCreateUserName() {
        return this.createUserName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Byte getFnJoinState() {
        return this.fnJoinState;
    }

    public void setFnJoinState(Byte fnJoinState) {
        this.fnJoinState = fnJoinState;
    }

    /**
     * @return the reviewDetail
     */
    public String getReviewDetail() {
        return this.reviewDetail;
    }

    /**
     * @param reviewDetail
     *            the reviewDetail to set
     */
    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    /**
     * @return the joinIssue
     */
    public Integer getJoinIssue() {
        return this.joinIssue;
    }

    /**
     * @param joinIssue
     *            the joinIssue to set
     */
    public void setJoinIssue(Integer joinIssue) {
        this.joinIssue = joinIssue;
    }

    /**
     * @return the realRate
     */
    public BigDecimal getRealRate() {
        return this.realRate;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the fnContractId
     */
    public String getFnContractId() {
        return this.fnContractId;
    }

    /**
     * @param fnContractId
     *            the fnContractId to set
     */
    public void setFnContractId(String fnContractId) {
        this.fnContractId = fnContractId;
    }

    /**
     * @param realRate
     *            the realRate to set
     */
    public void setRealRate(BigDecimal realRate) {
        this.realRate = realRate;
    }

    /**
     * @return the applyNum
     */
    public String getApplyNum() {
        return this.applyNum;
    }

    /**
     * @param applyNum
     *            the applyNum to set
     */
    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    /**
     * @return the contractMoney
     */
    public BigDecimal getContractMoney() {
        return this.contractMoney;
    }

    /**
     * @param contractMoney
     *            the contractMoney to set
     */
    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    /**
     * @return the loanTime
     */
    public Date getLoanTime() {
        return this.loanTime;
    }

    /**
     * @param loanTime
     *            the loanTime to set
     */
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    /**
     * @return the deleteUserId
     */
    public String getDeleteUserId() {
        return this.deleteUserId;
    }

    /**
     * @param deleteUserId
     *            the deleteUserId to set
     */
    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /**
     * @return the deleteTime
     */
    public Date getDeleteTime() {
        return this.deleteTime;
    }

    /**
     * @param deleteTime
     *            the deleteTime to set
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * @return the deleteReason
     */
    public String getDeleteReason() {
        return this.deleteReason;
    }

    /**
     * @param deleteReason
     *            the deleteReason to set
     */
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    // public Integer getJoinPackageType() {
    // return this.joinPackageType;
    // }
    //
    // public void setJoinPackageType(Integer joinPackageType) {
    // this.joinPackageType = joinPackageType;
    // }

    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public String getBidName() {
        return this.bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName == null ? null : bidName.trim();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getUserIsTrustee() {
        return this.userIsTrustee;
    }

    public void setUserIsTrustee(Integer userIsTrustee) {
        this.userIsTrustee = userIsTrustee;
    }

    public Integer getUserCredit() {
        return this.userCredit;
    }

    public void setUserCredit(Integer userCredit) {
        this.userCredit = userCredit;
    }

    public Integer getBidType() {
        return this.bidType;
    }

    public void setBidType(Integer bidType) {
        this.bidType = bidType;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRepaymentType() {
        return this.repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getReward() {
        return this.reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getLoanFeeRate() {
        return this.loanFeeRate;
    }

    public void setLoanFeeRate(BigDecimal loanFeeRate) {
        this.loanFeeRate = loanFeeRate;
    }

    public BigDecimal getPrepaymentFeeRate() {
        return this.prepaymentFeeRate;
    }

    public void setPrepaymentFeeRate(BigDecimal prepaymentFeeRate) {
        this.prepaymentFeeRate = prepaymentFeeRate;
    }

    public BigDecimal getDelaypaymentFeeRate() {
        return this.delaypaymentFeeRate;
    }

    public void setDelaypaymentFeeRate(BigDecimal delaypaymentFeeRate) {
        this.delaypaymentFeeRate = delaypaymentFeeRate;
    }

    public BigDecimal getMinimum() {
        return this.minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public BigDecimal getMaximum() {
        return this.maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAutoMoney() {
        return this.autoMoney;
    }

    public void setAutoMoney(Integer autoMoney) {
        this.autoMoney = autoMoney;
    }

    public String getBidDetail() {
        return this.bidDetail;
    }

    public void setBidDetail(String bidDetail) {
        this.bidDetail = bidDetail == null ? null : bidDetail.trim();
    }

    public String getRepaymentDetail() {
        return this.repaymentDetail;
    }

    public void setRepaymentDetail(String repaymentDetail) {
        this.repaymentDetail = repaymentDetail == null ? null : repaymentDetail.trim();
    }

    public String getGuarantee() {
        return this.guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee == null ? null : guarantee.trim();
    }

    public Date getTransactTime() {
        return this.transactTime;
    }

    /*
     * @return the forPackage
     */
    public Integer getForPackage() {
        return this.forPackage;
    }

    /**
     * @param forPackage
     *            the forPackage to set
     */
    public void setForPackage(Integer forPackage) {
        this.forPackage = forPackage;
    }

    public Integer getIsNovice() {
        return this.isNovice;
    }

    public void setIsNovice(Integer isNovice) {
        this.isNovice = isNovice;
    }

    public Date getNoviceInvalidTime() {
        return this.noviceInvalidTime;
    }

    public void setNoviceInvalidTime(Date noviceInvalidTime) {
        this.noviceInvalidTime = noviceInvalidTime;
    }

    /**
     * @return the publishTime
     */
    public Date getPublishTime() {
        return this.publishTime;
    }

    /**
     * @param publishTime
     *            the publishTime to set
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    public Date getEndOfInvest() {
        return this.endOfInvest;
    }

    public void setEndOfInvest(Date endOfInvest) {
        this.endOfInvest = endOfInvest;
    }

    public Date getEffectTime() {
        return this.effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getExpectEffectTime() {
        return this.expectEffectTime;
    }

    public void setExpectEffectTime(Date expectEffectTime) {
        this.expectEffectTime = expectEffectTime;
    }

    public Integer getValidDays() {
        return this.validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public BigDecimal getCompleteMoney() {
        return this.completeMoney;
    }

    public void setCompleteMoney(BigDecimal completeMoney) {
        this.completeMoney = completeMoney;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIssueCount() {
        return this.issueCount;
    }

    public void setIssueCount(Integer issueCount) {
        this.issueCount = issueCount;
    }

    public Integer getIssueDone() {
        return this.issueDone;
    }

    public void setIssueDone(Integer issueDone) {
        this.issueDone = issueDone;
    }

    public Integer getIssueInterest() {
        return this.issueInterest;
    }

    public void setIssueInterest(Integer issueInterest) {
        this.issueInterest = issueInterest;
    }

    public Byte getPublishBeforeAudit() {
        return this.publishBeforeAudit;
    }

    public void setPublishBeforeAudit(Byte publishBeforeAudit) {
        this.publishBeforeAudit = publishBeforeAudit;
    }

    public String getCreditCompanyId() {
        return this.creditCompanyId;
    }

    public void setCreditCompanyId(String creditCompanyId) {
        this.creditCompanyId = creditCompanyId == null ? null : creditCompanyId.trim();
    }

    public Integer getCreditFee() {
        return this.creditFee;
    }

    public void setCreditFee(Integer creditFee) {
        this.creditFee = creditFee;
    }

    public Integer getUnits() {
        return this.units;
    }

    public void setUnits(Integer units) {
        this.units = units;
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

    public BigDecimal getInterestSum() {
        return this.interestSum;
    }

    public void setInterestSum(BigDecimal interestSum) {
        this.interestSum = interestSum;
    }

    public BigDecimal getRewardSum() {
        return this.rewardSum;
    }

    public void setRewardSum(BigDecimal rewardSum) {
        this.rewardSum = rewardSum;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public Date getApplyTime() {
        return this.applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getBorrowUname() {
        return this.borrowUname;
    }

    public void setBorrowUname(String borrowUname) {
        this.borrowUname = borrowUname == null ? null : borrowUname.trim();
    }

    public Integer getAppPriority() {
        return this.appPriority;
    }

    public void setAppPriority(Integer appPriority) {
        this.appPriority = appPriority;
    }

    public Date getFailTime() {
        return this.failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    public Integer getBidTypeSecond() {
        return this.bidTypeSecond;
    }

    public void setBidTypeSecond(Integer bidTypeSecond) {
        this.bidTypeSecond = bidTypeSecond;
    }

    public String getBidBorrowUserIdentify() {
        return this.bidBorrowUserIdentify;
    }

    public void setBidBorrowUserIdentify(String bidBorrowUserIdentify) {
        this.bidBorrowUserIdentify = bidBorrowUserIdentify == null ? null : bidBorrowUserIdentify.trim();
    }

    public String getBidMortgageUserId() {
        return this.bidMortgageUserId;
    }

    public void setBidMortgageUserId(String bidMortgageUserId) {
        this.bidMortgageUserId = bidMortgageUserId == null ? null : bidMortgageUserId.trim();
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the firstAuditUser
     */
    public String getFirstAuditUser() {
        return this.firstAuditUser;
    }

    /**
     * @param firstAuditUser
     *            the firstAuditUser to set
     */
    public void setFirstAuditUser(String firstAuditUser) {
        this.firstAuditUser = firstAuditUser;
    }

    /**
     * @return the firstAuditTime
     */
    public Date getFirstAuditTime() {
        return this.firstAuditTime;
    }

    /**
     * @param firstAuditTime
     *            the firstAuditTime to set
     */
    public void setFirstAuditTime(Date firstAuditTime) {
        this.firstAuditTime = firstAuditTime;
    }

    /**
     * @return the reviewAuditUser
     */
    public String getReviewAuditUser() {
        return this.reviewAuditUser;
    }

    /**
     * @param reviewAuditUser
     *            the reviewAuditUser to set
     */
    public void setReviewAuditUser(String reviewAuditUser) {
        this.reviewAuditUser = reviewAuditUser;
    }

    public Date getReviewAuditTime() {
        return this.reviewAuditTime;
    }

    public void setReviewAuditTime(Date reviewAuditTime) {
        this.reviewAuditTime = reviewAuditTime;
    }

    /**
     * @return the canelTime
     */
    public Date getCanelTime() {
        return this.canelTime;
    }

    /**
     * @param canelTime
     *            the canelTime to set
     */
    public void setCanelTime(Date canelTime) {
        this.canelTime = canelTime;
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

    public BigDecimal getPlusRate() {
        return this.plusRate;
    }

    public void setPlusRate(BigDecimal plusRate) {
        this.plusRate = plusRate;
    }

    public BigDecimal getRemainPlusRate() {
        return this.remainPlusRate;
    }

    public void setRemainPlusRate(BigDecimal remainPlusRate) {
        this.remainPlusRate = remainPlusRate;
    }

    public Integer getIsPlusRate() {
        return this.isPlusRate;
    }

    public void setIsPlusRate(Integer isPlusRate) {
        this.isPlusRate = isPlusRate;
    }

    public BigDecimal getPlusRateSum() {
        return this.plusRateSum;
    }

    public void setPlusRateSum(BigDecimal plusRateSum) {
        this.plusRateSum = plusRateSum;
    }

    public Byte getIsRenew() {
        return this.isRenew;
    }

    public void setIsRenew(Byte isRenew) {
        this.isRenew = isRenew;
    }

    public Integer getBidFromType() {
        return this.bidFromType;
    }

    public void setBidFromType(Integer bidFromType) {
        this.bidFromType = bidFromType;
    }

    public BigDecimal getConsultingFee() {
        return this.consultingFee;
    }

    public void setConsultingFee(BigDecimal consultingFee) {
        this.consultingFee = consultingFee;
    }

    public BigDecimal getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getIsQuit() {
        return this.isQuit;
    }

    public void setIsQuit(Integer isQuit) {
        this.isQuit = isQuit;
    }

    public String getLoanCode() {
        return this.loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public Date getQuitTime() {
        return this.quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Integer getTrusteeShipUserId() {
        return this.trusteeShipUserId;
    }

    public void setTrusteeShipUserId(Integer trusteeShipUserId) {
        this.trusteeShipUserId = trusteeShipUserId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hexin.common.util.BidDocumentCal#getBid()
     */
    public Integer getSpecialRepaymentProcessToBankState() {
        return this.specialRepaymentProcessToBankState;
    }

    public void setSpecialRepaymentProcessToBankState(Integer specialRepaymentProcessToBankState) {
        this.specialRepaymentProcessToBankState = specialRepaymentProcessToBankState;
    }

    public Integer getIsWithdrawServiceFeeSuccess() {
        return this.isWithdrawServiceFeeSuccess;
    }

    public void setIsWithdrawServiceFeeSuccess(Integer isWithdrawServiceFeeSuccess) {
        this.isWithdrawServiceFeeSuccess = isWithdrawServiceFeeSuccess;
    }

    public BigDecimal getWithdrawServiceFeeLoanMoney() {
        return this.withdrawServiceFeeLoanMoney;
    }

    public void setWithdrawServiceFeeLoanMoney(BigDecimal withdrawServiceFeeLoanMoney) {
        this.withdrawServiceFeeLoanMoney = withdrawServiceFeeLoanMoney;
    }

    public Integer getIsMortgageCar() {
        return this.isMortgageCar;
    }

    public void setIsMortgageCar(Integer isMortgageCar) {
        this.isMortgageCar = isMortgageCar;
    }

    public String getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Integer getCustomerChannel() {
        return this.customerChannel;
    }

    public void setCustomerChannel(Integer customerChannel) {
        this.customerChannel = customerChannel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hexin.common.util.BidDocumentCal#getBid()
     */
    @Override
    @JsonIgnore
    public BidDocument getBid() {
        return this;
    }

    /**
     * @return the isClaim
     */
    public Integer getIsClaim() {
        return this.isClaim;
    }

    /**
     * @param isClaim the isClaim to set
     */
    public void setIsClaim(Integer isClaim) {
        this.isClaim = isClaim;
    }

    /**
     * @return the isFinishClaim
     */
    public Integer getIsFinishClaim() {
        return this.isFinishClaim;
    }

    /**
     * @param isFinishClaim the isFinishClaim to set
     */
    public void setIsFinishClaim(Integer isFinishClaim) {
        this.isFinishClaim = isFinishClaim;
    }

    public BigDecimal getTotalInterest() {
        return this.totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

}
