/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.mapper.hexin6;

import com.hexin.domain.hexin6.Account;
import com.hexin.model.PageVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Administrator@hexindai.com
 */
public interface UserAccountMapper {
	/**
	 * 查询用户账户信息
	 * @param userIdNum 用户id
	 * @return
	 */
	public Account searchUserAccount(long userIdNum);
	
	/**
	 * 查询用户理财包待收本金和待收利息
	 * @param userIdNum 用户id
	 * @return
	 */
	public Map<String,BigDecimal> searchUserFinancePackage(long userIdNum);
	
	/**
	 * 查询用户散标待收本金和待收利息
	 * @param userIdNum 用户id
	 * @return 
	 */
	public Map<String,BigDecimal> searchUserBidAccount(long userIdNum);
	
	/**
	 * 查询用户已赚取收益
	 * 
	 */
	public Map<String,BigDecimal> searchDeliveredIncome(long userIdNum);
	
	/**
	 * 转让中的资产
	 * @param userIdNum
	 * @return
	 */
	public Map<String,BigDecimal> searchForSale(long userIdNum);

	// 查询冻结资金
	public Map<String, Object> searchFreezeMoney(@Param("pageVo") PageVo pageVo);

	//查询转让资金
	public Map<String, Object> searchTransferSale(@Param("pageVo") PageVo pageVo);

	public Map<String, Object> receivedReward(@Param("pageVo") PageVo pageVo);

	public Map<String, Object> earnIncome(@Param("pageVo") PageVo pageVo);

	public Map<String, Object> sameMoneyIncome(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> addUpIncome(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> collectBidMoney(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> collectPackageMoney(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> incomeExpenses(@Param("pageVo") PageVo pageVo);

	public Map<String, Object> searchUserBoundCard(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> searchUserInvestBidRePayIngList(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> searchUserAssetBuyBidRePayIngList(@Param("pageVo") PageVo pageVo);

	public Map<String, Object> incomeExpendTotal(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> searchWaitRepayment(Integer bidId);

	public List<Map<String, Object>> searchUserInvestAtInvestingList(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> searchAssetSale(@Param("pageVo") PageVo pageVo);

	public List<Map<String, Object>> searchUserRepayOk(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchBidWaitGathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchBidWaitReward(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> getWaitingForRepayFnPackageLogs(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinanceReceivedReward(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinrewardwaitgathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchBidAlreadyGathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinAlreadyGathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinMonthAlreadyGathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryInvestOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryAssetbuyOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryAssetSaleOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryRechargeOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryWithdrawCashOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryFinanceQuitOrder(@Param("pageVo") PageVo pageVo);

	List<Map<String, Object>> searchFinanceMMHInvest(@Param("pageVo") PageVo pageVo);

	/**
	 * 判断该退出荷包类型是紧急退出还是锁定期后正常退出
	 *
	 * @param packageId
	 * @author panmeng
	 * @return
	 */
	boolean checkQuitBeforeOrAfterLockDate(@Param("packageId") Integer packageId);

	public List<Map<String,Object>> getWaitingforRepaymonthFnpackage(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchAssetList(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinanceInvest(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinanceJoin(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryFinanceMMQuitOrder(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinanceAborting(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> searchFinanceExited(@Param("pageVo") PageVo pageVo);

	public Map<String,Object> bidReceivable(long userIdNum);

	public Map<String,Object> queryAssetRemainMoney(@Param("pageVo") PageVo pageVo);

	public Map<String,Object> rewardRecord(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> queryBidAlreayGathering(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> financeMonthReturnInterest(@Param("pageVo") PageVo pageVo);

	public List<Map<String,Object>> collectMonthPackageMoney(@Param("pageVo") PageVo pageVo);






}
