/**
 * Copyright(c) 2011-2015 by HeXin Inc.
 * All Rights Reserved
 */
package com.hexin.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hexin.domain.hexin6.Asset;
import com.hexin.domain.hexin6.BidDocument;
import com.hexin.domain.hexin6.Repayplan;
import com.hexin.domain.hexin6.RepayplanKey;
import com.hexin.mapper.hexin6.RepayplanMapper;
import com.hexin.model.PageVo;
import com.hexin.utils.DateUtils;
import com.hexin.utils.calculate.MoneyCalculator;


/**
 * 还款计划
 * 
 * @author zhishuo
 */
@Component
@Transactional(readOnly = true)
public class RepayPlanManager {

    private static Logger logger = LoggerFactory.getLogger(RepayPlanManager.class);
    @Autowired
    private RepayplanMapper repayplanDao;

    private final static int DUE_ISSUE_REPURCHASE = 3; // 逾期期数 for 回购

    /**
     * 根据标的查询还款计划
     * 
     * @param bid
     * @return
     */
    public List<Repayplan> getRepayPlanByBid(int bid) {
        Map<String, Object> parameter = Maps.newHashMap();
        parameter.put("bidId", bid + "");
        return this.repayplanDao.selectRepayPlan(parameter);
    }

    /**
     * @author zhishuo
     * @return
     */
    @Transactional(readOnly = true)
    public List<Repayplan> getPackageUnRepay() {
        return this.repayplanDao.getPackageUnRepay();
    }




    /**
     * 插入
     * 
     * @author zhishuo
     * @param repayplan
     */
    @Transactional( readOnly = false)
    public void saveRepayPlan(Repayplan repayplan) {
        this.repayplanDao.insert(repayplan);
    }

    /**
     * 更新还款状态
     * 
     * @author zhishuo
     * @param bidId
     * @param issue
     * @param repayType
     * @return
     */
    @Transactional( readOnly = false)
    public int updateRepayplan(int bidId, int issue, int repayType, int state) {
        Repayplan plan = new Repayplan();
        plan.setBidId(bidId);
        plan.setIssue(issue);
        plan.setRepayType(repayType);
        plan.setState((byte) state);
        return this.repayplanDao.updateStateByPrimaryKey(plan);
    }

    /**
     * 根据标ID，期数，还款类型查询还款计划
     * 
     * @author zhishuo
     * @param bidId
     * @param issue
     * @param repayType
     * @return
     */
    public List<Repayplan> getRepayplan(int bidId, int issue, int repayType) {
        Map<String, Object> parameter = Maps.newHashMap();
        parameter.put("bidId", bidId + "");
        parameter.put("issue", issue + "");
        parameter.put("repayType", repayType + "");
        return this.repayplanDao.selectRepayPlan(parameter);
    }

    /**
     * 根据标ID，期数， 查询还款计划
     * 
     * @author zhishuo
     * @param bidId
     * @param issue
     * @return
     */
    public List<Repayplan> getRepayplan(int bidId, int issue) {
        Map<String, String> parameter = Maps.newHashMap();
        parameter.put("bidId", bidId + "");
        parameter.put("issue", issue + "");
        return this.repayplanDao.selectRepayPlanAll(parameter);
    }

    /**
     * 获取一条还款记录的详细信息，不是从RepayPlan一张表中
     * 
     * @author zhishuo
     * @param bid
     * @param issue
     * @return
     */
    public Map<String, Object> getRepayplanDetail(int bid, double issue) {
        Map<String, String> parameter = Maps.newHashMap();
        parameter.put("bid", bid + "");
        parameter.put("issue", issue + "");
        return this.repayplanDao.selectRepayPlanDetail(parameter);
    }

    /**
     * 获取需要自动发放投资奖励的记录
     * 
     * @author tiejiuzhou
     * @return
     */
    public List<Map<String, Object>> getAutoInvestRewardRecord() {
        return this.repayplanDao.getAutoInvestRewardRecord();
    }

    /**
     * 获取需要自动发放投资奖励的记录
     * 
     * @author tiejiuzhou
     * @return
     */
    public Repayplan getRepayPlanByBidIdAndIssue(int bidId, int issue) {
        return this.repayplanDao.getRepayPlanByBidIdAndIssue(bidId, issue);
    }



    /**
     * 获取所有还款日为当日的Repayplan
     * 
     * @author yanshun
     * @return
     */
    @Transactional(readOnly = true)
    public List<Repayplan> getRepayplansForReinvestWork() {

        return this.repayplanDao.getRepayplansForReinvestWork();
    }

    /**
     * 查询理财包下当日还款标的是否都已还清
     * 
     * @author yanshun
     * @return
     */
    @Transactional(readOnly = true)
    public List<Repayplan> getRepayplansByPackageId(int packageId) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("packageId", packageId);
        return this.repayplanDao.getRepayplansByPackageIdForSysWork(param);
    }


    /**
     * 验证是否符合回款规则
     * 
     * @author wangjiangtao
     * @param bidId
     * @param issue
     * @param repayState
     * @return
     */
    private boolean checkIssueIsRepurchse(int bidId, int issue, int repayState) {
        boolean result = Boolean.FALSE;
        if (issue >= DUE_ISSUE_REPURCHASE && repayState == 10) {
            int issueCount = this.repayplanDao.statAlreadyDueIssue(bidId, issue);
            if (issueCount == DUE_ISSUE_REPURCHASE) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }



    /**
     *
     * 查询所有标的待还款（无理财包无奖励）
     * 
     * @author wangjiangtao
     * @return
     */
    public BigDecimal getAllBidRepay() {
        BigDecimal allBidRepay = null;
        Map<String, Object> map = this.repayplanDao.getAllBidRepay();
        if (!CollectionUtils.isEmpty(map) && map.get("principal_to_repay") != null
                && map.get("interest_to_repay") != null) {
            allBidRepay = new BigDecimal(map.get("principal_to_repay").toString()).add(
                    new BigDecimal(map.get("interest_to_repay").toString()));

        }
        return allBidRepay;
    }

    /**
     * 查询所有理财包待还款
     * 
     * @author wangjiangtao
     * @return
     */
    public BigDecimal getAllPackageRepay() {
        BigDecimal allPackageReapay = null;
        Map<String, Object> map = this.repayplanDao.getAllPackageRepay();
        if (!CollectionUtils.isEmpty(map) && map.get("finance_to_repay") != null) {
            allPackageReapay = new BigDecimal(map.get("finance_to_repay").toString());
        }
        return allPackageReapay;
    }

    /**
     * 标的待还款总额统计
     * 
     * @param pageVo
     */
    public Map<String, Object> getTotalWaitingRepay(PageVo pageVo) {
        return this.repayplanDao.getTotalWaitingRepay(pageVo);
    }

    /**
     * 查询当日待还款标的（线上）
     * 
     * @param 0 散标 1荷包
     * 
     * @param isPackage
     * @return
     */
    public List<Map<String, Object>> searchNowWaitRepayment(Integer isPackage, String repayDay) {
        return this.repayplanDao.searchNowWaitRepayment(isPackage, repayDay);
    }

    public Repayplan selectByPrimaryKey(RepayplanKey key) {
        return this.repayplanDao.selectByPrimaryKey(key);
    }

    /**
     * 查询待回购理财包标的
     *
     * author wangjiangtao
     * 
     * @return
     */
    public List<Map<String, Object>> getRepurchaseListForPackage(String repayDay) {
        List<Map<String, Object>> repurchaseList = Lists.newArrayList();
        repurchaseList.addAll(this.repayplanDao.getRepurchaseListForPackage(repayDay));
        return repurchaseList;
    }

    /**
     * 根据标的id和
     * 
     * @param bidId
     * @param now
     * @return
     */
    /**
     * public Repayplan getRepayPlanByBidAndRepaDay(Integer bidId, Date now) {
     * 
     * }
     **/

    @Transactional( readOnly = false)
    public int updateBankState(Repayplan repayplan) {

        return this.repayplanDao.updateByPrimaryKeySelective(repayplan);
    }

    public Repayplan unRepayFirstIssue(Integer bidId) {
        Repayplan repayPlan = this.repayplanDao.unRepayFirstIssue(bidId);
        return repayPlan;
    }

    public Date getLastedRepayDate(Integer bidId) {
        return this.repayplanDao.getLastedRepayDate(bidId);
    }

    public Integer getCountBetween2Day(Date startDate, Date endDate, int bidId) {
        return this.repayplanDao.getCountBetween2Day(startDate, endDate, bidId);
    }

    @Transactional( readOnly = true)
    public List<Map<String, Object>> getAllDebtBackAccountBalance(String startDate, String endDate) {
        return this.repayplanDao.getAllDebtBackAccountBalance(startDate, endDate);
    }

    /**
     * 借款人预期总金额
     * 
     * @author panmeng
     * @param userId
     * @return
     *
     */
    @Transactional( readOnly = true)
    public Map<String, BigDecimal> queryOverdueRepayAmountByUserId(String userId) {
        return this.repayplanDao.queryOverdueRepayAmountByUserId(userId);
    }

    /**
     * 借款人待还金额
     * 
     * @author panmeng
     * @param userId
     * @return
     *
     */
    @Transactional( readOnly = true)
    public Map<String, BigDecimal> queryUnRepayAmountByUserId(String userId) {
        return this.repayplanDao.queryUnRepayAmountByUserId(userId);
    }

    /**
     * 借款人预期总笔数
     * 
     * @author panmeng
     * @param userId
     * @return
     *
     */
    @Transactional( readOnly = true)
    public List<Map<String, Object>> queryOverdueListByUserId(String userId) {
        return this.repayplanDao.queryOverdueListByUserId(userId);
    }

    /**
     * 借款人提前还款总笔数
     * 
     * @author panmeng
     * @param userId
     * @return
     *
     */
    @Transactional( readOnly = true)
    public List<Map<String, Object>> queryAheadRepayListByUserId(String userId) {
        return this.repayplanDao.queryAheadRepayListByUserId(userId);
    }

    @Transactional( readOnly = true)
    public List<Map<String, Object>> getRepayListForRepair() {
        return this.repayplanDao.getRepayListForRepair();
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getRepayListForRepairAll() {
        return this.repayplanDao.getRepayListForRepairAll();
    }
}
