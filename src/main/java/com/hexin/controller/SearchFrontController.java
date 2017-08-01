package com.hexin.controller;

import com.hexin.common.ReturnInfo;
import com.hexin.model.PageVo;
import com.hexin.service.CouponHandlerService;
import com.hexin.service.UserAccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyy on 2017/4/26.
 */
@RestController
@RequestMapping("/search/front/v1")
public class SearchFrontController {

    @Autowired
    private UserAccountSearchService userAccountSearchService;
    @Autowired
    private CouponHandlerService couponHandlerService;

    /**
     *查询呢用户资产（
     资产总额： totalAsset
     可用金额： availableCash
     待收总额： remainTotal
     待收本金： remainPrincipal
     待收利息： remainInterest
     累计收益： deliveredIncome）
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_account")
    public ReturnInfo searchAccount(@RequestBody PageVo pageVo) {
        long userIdNum = Long.valueOf(pageVo.getParameters().get("user_id").toString());
        return new ReturnInfo(this.userAccountSearchService.searchAccount(userIdNum));
    }

    /**
     * 冻结资金查询
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_freeze_money")
    public ReturnInfo searchFreezeMoney(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.userAccountSearchService.searchFreezeMoney(pageVo));
    }

    /**
     * 债转中的金额
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_transfer_sale")
    public Object searchTransferSale(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.userAccountSearchService.searchTransferSale(pageVo));
    }

    /**
     * 获取可用代金券
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_CouponUseInfo")
    public ReturnInfo searchCouponUseInfo(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.couponHandlerService.searchCouponUseInfo(pageVo));

    }

    /**
     *已收奖励总和
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/received_reward")
    public ReturnInfo receivedReward(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.userAccountSearchService.receivedReward(pageVo));
    }

    /**
     * 近三月收益
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/earn_Income")
    public ReturnInfo earnIncome(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.userAccountSearchService.earnIncome(pageVo));
    }


    /**
     * 当月收益
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/samemoney_Income")
    public ReturnInfo samemoneyIncome(@RequestBody PageVo pageVo) {
        return new ReturnInfo(this.userAccountSearchService.sameMoneyIncome(pageVo));
    }

    /**
     * 收益趋势待收-散标待收
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/duein_bid_money")
    public ReturnInfo dueInBidMoney(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.dueInBidMoney(pageVo));
    }

    /**
     * 收益趋势待收-荷包待收
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/duein_package_money")
    public ReturnInfo dueInPackageMoney(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.dueInPackageMoney(pageVo));
    }

    /**
     * 账户收支明细
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/income_expenses")
    public Object incomeExpenses(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.incomeExpenses(pageVo);
    }

    /**
     * 累计收益趋势图
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/addup_income")
    public ReturnInfo addUpIncome(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.addUpIncome(pageVo));
    }

    /**
     * 查询绑卡信息
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/searchuser_boundcard")
    public ReturnInfo searchUserBoundCard(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchUserBoundCard(pageVo));
    }

    /**
     * 累计收益
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/total_income")
    public ReturnInfo totalIncome(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.totalIncome(pageVo));
    }

    /**
     * 持有中（出借）
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/searchuser_investbid_repayinglist")
    public Object searchUserInvestBidRePayIngList(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchUserInvestBidRePayIngList(pageVo);
    }

    /**
     * 账户收支明细累计收入支出统计
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/incomeexpend_total")
    public ReturnInfo incomeExpendTotal(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.incomeExpendTotal(pageVo));
    }


    /**
     * 持有中（承接）
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/searchuser_assetbuybid_repayinglist")
    public Object searchUserAssetBuyBidRePayIngList(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchUserAssetBuyBidRePayIngList(pageVo);
    }

    /**
     * 发起债转计算利息管理费和
     * @param pageVo
     * @return
     */
    @PostMapping(value="/assignment_of_debt")
    public ReturnInfo assignmentOfDebt(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.assignmentOfDebt(pageVo));
    }

    /**
     * 查询招标中
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_userinvestatinvesting_list")
    public Object searchUserInvestAtInvestingList(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchUserInvestAtInvestingList(pageVo);
    }

    /**
     * 已结束
     * @param pageVo
     * @return
     */

    @PostMapping(value ="/search_userrepayok")
    public Object searchUserRepayOk(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchUserRepayOk(pageVo);
    }

    /**
     * 债转中
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_assetsale")
    public Object searchAssetSale(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchAssetSale(pageVo);
    }


    /**
     * 回款日历- 散标三个月待收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_waitgathering")
    public ReturnInfo searchWaitGathering(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchWaitBidGathering(pageVo));
    }


    /**
     * 回款日历-荷包三个月待收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_waitingfor_repayfnpackagelogs")
    public ReturnInfo getWaitingForRepayFnPackageLogs(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.getWaitingForRepayFnPackageLogs(pageVo));
    }


    /**
     * 回款日历-新月返荷包三个月待收0 代表未收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_waitingfor_repaymonthfnpackage")
    public ReturnInfo getWaitingforRepaymonthFnpackage(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.getWaitingforRepaymonthFnpackage(pageVo));
    }

    /**
     * 回款日历-散标三个月已收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_bidalreadygathering")
    public ReturnInfo searchBidAlreadyGathering(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchBidAlreadyGathering(pageVo));
    }

    /**
     * 回款日历-荷包三个月已收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_finalreadygathering")
    public ReturnInfo searchFinAlreadyGathering(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchFinAlreadyGathering(pageVo));
    }

    /**
     * 回款日历-奖励荷包已收奖励
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_finrewardalreadygathering")
    public ReturnInfo searchFinRewardAlreadyGathering(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchFinRewardAlreadyGathering(pageVo));
    }

    /**
     * 回款日历-奖励荷包待收
     * @param pageVo
     * @return
     */
    @PostMapping(value ="/search_finrewardwaitgathering")
    public ReturnInfo searchFinrewardwaitgathering(@RequestBody PageVo pageVo){
        return new ReturnInfo(this.userAccountSearchService.searchFinrewardwaitgathering(pageVo));
    }

    /**
     * 功能 ： 我的代金券
     * @author wuxusen
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_coupon_record")
    public Object searchVoucherRecord(@RequestBody PageVo pageVo) {
        return this.couponHandlerService.selectCouponRecods(pageVo);
    }

    /**
     * 我的交易单-出借订单
     * @param pageVo
     * @return
     */

    @PostMapping(value = "/query_invest_order")
    public Object queryOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryInvestOrder(pageVo);
    }

    /**
     * 我的交易单-债权承接订单
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_assetbuy_order")
    public Object queryAssetbuyOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryAssetbuyOrder(pageVo);
    }

    /**
     * 我的交易单-债权转让订单
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_assetsale_order")
    public Object queryAssetSaleOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryAssetSaleOrder(pageVo);
    }

    /**
     * 我的交易单-充值订单
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_recharge_order")
    public Object queryRechargeOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryRechargeOrder(pageVo);
    }

    /**
     * 我的交易单-提现订单
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_withdrawcash_order")
    public Object queryWithdrawCashOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryWithdrawCashOrder(pageVo);
    }

    /**
     * 我的交易单-荷包退出记录
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_financequit_order")
    public Object queryFinanceQuitOrder(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.queryFinanceQuitOrder(pageVo);
    }

    /**
     * 我的月月生息-持有中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_financemmh_hold")
    public Object searchFinancemmhHold(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.searchFinancemmhHold(pageVo);
    }

    /**
     * 我的月月生息-加入中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_financemmh_joinin")
    public Object searchFinancemmhJoinin(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.searchFinancemmhJoinin(pageVo);
    }

    /**
     * 我的月月生息-退出中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_financemmh_aborting")
    public Object searchFinancemmhAborting(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.searchFinancemmhAborting(pageVo);
    }

    /**
     * 我的月月生息-已退出
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_financemmh_exited")
    public Object searchFinancemmhExited(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.searchFinancemmhExited(pageVo);
    }

    /**
     * 我的月月生息-债权列表
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_asset_list")
    public Object searchAssetList(@RequestBody PageVo pageVo) {
        return this.userAccountSearchService.searchAssetList(pageVo);
    }


    /**
     * 我的荷包-持有中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_finance_invest")
    public Object searchFinanceInvest(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchFinanceInvest(pageVo);
    }

    /**
     * 我的荷包-加入中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_finance_join")
    public Object searchFinanceJoin(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchFinanceJoin(pageVo);
    }

    /**
     * 我的荷包-退出中
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_finance_aborting")
    public Object searchFinanceAborting(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchFinanceAborting(pageVo);
    }

    /**
     * 我的交易单-月月生息退出记录
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_financeMM_quitOrder")
    public Object queryFinanceMMQuitOrder(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.queryFinanceMMQuitOrder(pageVo);
    }

    /**
     * 我的荷包-已退出
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/search_finance_exited")
    public Object searchFinanceExited(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.searchFinanceExited(pageVo);
    }


    /**
     * app 查询投资已收计划
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/query_bid_alreayGathering")
    public Object queryBidAlreayGathering(@RequestBody PageVo pageVo){
        return this.userAccountSearchService.queryBidAlreayGathering(pageVo);
    }
}
