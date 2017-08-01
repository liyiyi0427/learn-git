/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.domain.hexin6;

import com.hexin.utils.DateUtils;
import com.hexin.utils.calculate.MoneyCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author liuzhishuo@hexindai.com
 */
@Component
public abstract class BidDocumentCal {

    private Logger logger = LoggerFactory.getLogger(BidDocumentCal.class);

    /**
     * 计算 理财包剩余本金
     * 
     * @author zhishuo
     * @param quota
     * @return
     */
    public BigDecimal calFinanceBidRemainPrin(BigDecimal quota) {
        logger.info("quota:{},remainPrincipal:{},money:{}",quota.toString(),this.getBid().getRemainPrincipal(),this.getBid().getMoney());
        return new MoneyCalculator(this.getBid().getRemainPrincipal()).multiply(quota).divide(new BigDecimal(100))
                .divide(this.getBid().getMoney()).toCeil();
    }

    /**
     * 计算标的剩余本金
     * 
     * @author zhishuo
     * @param quota
     * @return
     */
    public BigDecimal calAssetInBidRemainPrin(BigDecimal quota) {
        return new MoneyCalculator(this.getBid().getRemainPrincipal()).multiply(quota).divide(new BigDecimal(100))
                .divide(this.getBid().getMoney()).toResult();
    }

    /**
     * 计算标的剩余利息
     * 
     * @author zhishuo
     * @param quota
     * @return
     */
    public BigDecimal calAssetInBidRemainInterest(BigDecimal quota) {
        return new MoneyCalculator(this.getBid().getRemainInterest()).multiply(quota).divide(new BigDecimal(100))
                .divide(this.getBid().getMoney()).toResult();
    }

    /**
     * 计算 理财包剩余本金+转让人持有天数的应得利息
     *
     * @author tiejiuhzou
     * @param quota
     * @return
     */
    public BigDecimal calFinanceBidRemainPrinAndInterest(Repayplan repayPlan, Repayplan repayedPlan, BigDecimal quota) {
        Date lastDay = new Date();
        if (this.getBid().getIssueDone() == 0) {
            lastDay = this.getBid().getTransactTime();
        } else {
            lastDay = repayedPlan.getRepayday();
        }
        int holdDays = DateUtils.daysBetween(lastDay, DateUtils.getNow());
        int repyDay = DateUtils.daysBetween(lastDay, repayPlan.getRepayday());
        BigDecimal interestMoney = new MoneyCalculator(repayPlan.getInterest()).multiply(new BigDecimal(holdDays))
                .divide(new BigDecimal(repyDay)).getInstance();
        return (new MoneyCalculator(this.getBid().getRemainPrincipal()).add(interestMoney)).multiply(quota)
                .divide(new BigDecimal(100)).divide(this.getBid().getMoney()).toCeil();
    }

    public abstract BidDocument getBid();

}
