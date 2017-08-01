/**
 * Copyright(c) 2011-2015 by HeXin Inc.
 * All Rights Reserved
 */
package com.hexin.manager;

import com.hexin.domain.hexin6.Asset;
import com.hexin.domain.hexin6.BidDocument;
import com.hexin.domain.hexin6.Repayplan;
import com.hexin.enums.DateEnum;
import com.hexin.enums.RepaymentType;
import com.hexin.mapper.hexin6.AssetMapper;
import com.hexin.mapper.hexin6.BidDocumentMapper;
import com.hexin.utils.DateUtils;
import com.hexin.utils.calculate.MoneyCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author zhishuo
 */
@Component
@Transactional(readOnly = true)
public class AssetSaleManager {

    private static Logger logger = LoggerFactory.getLogger(AssetSaleManager.class);


    @Autowired
    private RepayPlanManager repayPlanManager;
    @Autowired
    private BidDocumentMapper bidDocumentMapper;
    @Autowired
    private AssetMapper assetMapper;

    /**
     * 根据还款类型计算 按日计息
     *
     * @param bid
     * @param asset
     * @return
     */
    public BigDecimal getReceivableInterest(BidDocument bid, Asset asset) {
        BigDecimal receivalbleInterest = new BigDecimal(0.00);
        List<Repayplan> repayplan = this.repayPlanManager.getRepayPlanByBid(bid.getBidId());
        if (CollectionUtils.isEmpty(repayplan)) {
            return receivalbleInterest;
        }
        if (bid.getRepaymentType() == RepaymentType.RP_INTEREST_FIRST.getText()) {// 每月还息到期还本

            Map<String, Object> lastRepayPlanDetail = this.repayPlanManager.getRepayplanDetail(bid.getBidId(),
                    bid.getIssueDone());
            String lastRepay = "";
            Date lastRepayDay = DateUtils.getNow();
            if (StringUtils.isEmpty(lastRepayPlanDetail)) {
                if (bid.getForPackage() == 1) {
                    lastRepayDay = bid.getTransactTime();
                } else {
                    lastRepayDay = asset.getCreatTime();
                }
            } else {
                lastRepay = lastRepayPlanDetail.get("ts_repay").toString();
                lastRepayDay = DateUtils.toDate(lastRepay, DateEnum.DATE_SPLIT_SEQ);
            }
            Map<String, Object> nextRepayPlanDetail = this.repayPlanManager.getRepayplanDetail(bid.getBidId(),
                    bid.getIssueDone() + 1);
            String nextRepay = nextRepayPlanDetail.get("ts_repay").toString();
            Date nextRepayDay = DateUtils.toDate(nextRepay, DateEnum.DATE_SPLIT_SEQ);
            // 转让人持有天数 当前日期-上一回款日
            Integer receivalbleDays = DateUtils.daysBetween(lastRepayDay, DateUtils.getNow());
            receivalbleInterest = new MoneyCalculator(bid.getMoney())
                    .multiply(bid.getRate())
                    .divide(new BigDecimal(12))
                    .multiply(
                            new MoneyCalculator(receivalbleDays).divide(
                                    new BigDecimal(DateUtils.daysBetween(lastRepayDay, nextRepayDay))).getInstance())
                    .multiply(
                            new MoneyCalculator(asset.getQuota()).divide(
                                    new MoneyCalculator(bid.getMoney()).multiply(new BigDecimal(100)).getInstance())
                                    .getInstance()).toResult();

        } else if (bid.getRepaymentType() == RepaymentType.RP_AVERAGE.getText()) {// 按月还款等额本息
            Map<String, Object> currentRepayPlan = this.repayPlanManager.getRepayplanDetail(bid.getBidId(),
                    bid.getIssueDone() + 1);

            BigDecimal interest = new BigDecimal(currentRepayPlan.get("interest_to_repay").toString()); // 标的本期应还利息
            Date currentRepayday = DateUtils.toDate(currentRepayPlan.get("ts_repay").toString(),
                    DateEnum.DATE_SPLIT_SEQ);// 本期还款日期

            Map<String, Object> preRepayPlan = this.repayPlanManager.getRepayplanDetail(bid.getBidId(),
                    bid.getIssueDone());
            Date preRepayday = new Date();
            if (null == preRepayPlan) {// 没有上一期还款日，获取满标审核时间 asset: creat_time
                if (bid.getForPackage() == 1) {
                    preRepayday = bid.getTransactTime();
                } else {
                    preRepayday = asset.getCreatTime();
                }
            } else {
                preRepayday = DateUtils.toDate(preRepayPlan.get("ts_repay").toString(), DateEnum.DATE_SPLIT_SEQ);//
            }
            Integer receivalbleDays = DateUtils.daysBetween(preRepayday, DateUtils.getNow()); // 发起人应收未收利息天数
            logger.info("receivalbleDays=" + receivalbleDays + "PreRepayday=" + preRepayday + "DateUtils.getNow()="
                    + DateUtils.getNow());
            // 上一期还款日期
            // Date PreRepayday = DateUtils.toDate("2016-09-13 00:00:00.0", DateEnum.DATE_SPLIT_SEQ);// 上一期还款日期
            Integer days = DateUtils.daysBetween(preRepayday, currentRepayday);// 本期总天数=本期还款日-上一个还款日；
            receivalbleInterest = new MoneyCalculator(interest).multiply(new BigDecimal(receivalbleDays))
                    .divide(new BigDecimal(days)).multiply(asset.getQuota())
                    .divide(bid.getMoney().multiply(new BigDecimal(100))).toResult();// 本期应收未收利息
        }
        return receivalbleInterest;
    }

}
