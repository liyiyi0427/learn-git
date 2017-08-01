/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.service;

import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.hexin.annotation.Page;
import com.hexin.domain.hexin6.*;
import com.hexin.enums.DateEnum;
import com.hexin.enums.FinancePackageType;
import com.hexin.enums.OwnedState;
import com.hexin.manager.AssetSaleManager;
import com.hexin.manager.ParameterManager;
import com.hexin.manager.VersionRateManager;
import com.hexin.mapper.hexin6.*;
import com.hexin.model.PageVo;
import com.hexin.utils.DateUtils;
import com.hexin.utils.calculate.MoneyCalculator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

import static com.hexin.utils.DateUtils.dateToDateTime;
import static com.hexin.utils.DateUtils.toDate;


/**
 *
 *
 * @author Administrator@hexindai.com
 */
@Service
public class UserAccountSearchService {
    private static Logger logger = LoggerFactory.getLogger(UserAccountSearchService.class);

    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private ParameterManager paramManager;
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private BidDocumentMapper bidDocumentMapper;
    @Autowired
    private AssetSaleManager assetSaleManager;
    @Autowired
    private InvestMapper investMapper;
    @Autowired
    private AssetBuyMapper assetBuyMapper;
    @Autowired
    private AssetSaleMapper assetSaleMapper;

    @Autowired
    private VersionRateManager versionRateManager;

    public Map<String, String> searchAccount(long userIdNum) {

        Map<String, String> returnMap = new HashMap<String, String>();
        // 可用金额计算
        Account account = this.userAccountMapper.searchUserAccount(userIdNum);
        BigDecimal totleMoney = BigDecimal.ZERO;
        BigDecimal freezaCash = BigDecimal.ZERO;
        BigDecimal freezeInvest = BigDecimal.ZERO;
        BigDecimal decimalMoney = BigDecimal.ZERO;
        BigDecimal availableCash = BigDecimal.ZERO;
        if (account != null) {
            decimalMoney = new MoneyCalculator(account.getDecimalMoney()).downToResult().getInstance();
            totleMoney = new MoneyCalculator(account.getTotalMoney()).subtract(decimalMoney).getInstance();
            freezaCash = account.getFreezeForCash();
            freezeInvest = account.getFreezeForInvest();
            availableCash = new MoneyCalculator(totleMoney).subtract(freezaCash).subtract(freezeInvest).getInstance();
        }
        returnMap.put("availableCash", new MoneyCalculator(availableCash).downToResultToString());
        returnMap.put("freezeInvest", new MoneyCalculator(freezeInvest).downToResultToString());
        returnMap.put("freezaCash", new MoneyCalculator(freezaCash).downToResultToString());
        returnMap.put("freezaTotal", new MoneyCalculator(freezaCash).add(freezeInvest).downToResultToString());

        Map<String, BigDecimal> forSaleAsset = this.userAccountMapper.searchForSale(userIdNum);
        BigDecimal forSaleAmount = BigDecimal.ZERO;
        if (forSaleAsset != null) {
            forSaleAmount = forSaleAsset.get("price_sum");
        }

        // 标的待收
        Map<String, BigDecimal> bidRemainMoney = this.userAccountMapper.searchUserBidAccount(userIdNum);

        BigDecimal bidPrincipal = BigDecimal.ZERO;
        BigDecimal bidInterest = BigDecimal.ZERO;
        if (bidRemainMoney != null) {
            bidPrincipal = bidRemainMoney.get("remainPrincipal");
            bidInterest = bidRemainMoney.get("remainInterest");
            returnMap.put("bidPrincipal",bidPrincipal.toString());
            returnMap.put("bidInterest", bidInterest.toString());
        }else{
            returnMap.put("bidPrincipal","0");
            returnMap.put("bidInterest", "0");
        }
        // 荷包待收

        Map<String, BigDecimal> packageRemainMoney = this.userAccountMapper.searchUserFinancePackage(userIdNum);
        BigDecimal pcgPrincipal = BigDecimal.ZERO;
        BigDecimal pcgInterest = BigDecimal.ZERO;
        if (packageRemainMoney != null) {
            pcgPrincipal = packageRemainMoney.get("packagePrincipal");
            pcgInterest = packageRemainMoney.get("packageInterest");
        }
        BigDecimal remainPrincipal = new MoneyCalculator(bidPrincipal).add(pcgPrincipal).getInstance();
        BigDecimal remainInterest = new MoneyCalculator(bidInterest).add(pcgInterest).getInstance();
        BigDecimal remainTotal = new MoneyCalculator(remainPrincipal).add(remainInterest).toResult();
        returnMap.put("remainPrincipal", new MoneyCalculator(remainPrincipal).downToResultToString());
        returnMap.put("remainInterest", new MoneyCalculator(remainInterest).downToResultToString());
        returnMap.put("remainTotal", remainTotal.toString());



        BigDecimal deliveredIncome = BigDecimal.ZERO;
        Map<String, BigDecimal> income = this.userAccountMapper.searchDeliveredIncome(userIdNum);
        if (income != null) {
            deliveredIncome = income.get("deliveredIncome");
        }
        returnMap.put("deliveredIncome", new MoneyCalculator(deliveredIncome).downToResultToString());

        BigDecimal totleAsset = new MoneyCalculator(totleMoney).add(forSaleAmount).add(remainTotal).toResult();
        returnMap.put("totalAsset", totleAsset.toString());
        returnMap.put("saleAsset",new MoneyCalculator(forSaleAmount).downToResultToString());


        Map<String, Object> bidReceivable = this.userAccountMapper.bidReceivable(userIdNum);
        if(bidReceivable!=null){
            if(bidReceivable.get("remainPrincipal")!=null){
                returnMap.put("bidReceivablePrincipal",bidReceivable.get("remainPrincipal").toString());
            }
            if(bidReceivable.get("remainInterest")!=null){
                returnMap.put("bidReceivableInterest",bidReceivable.get("remainInterest").toString());
            }
        }else{
            returnMap.put("bidReceivablePrincipal","0");
            returnMap.put("bidReceivableInterest","0");
        }
        return returnMap;
    }

    public Map<String,Object> searchFreezeMoney(PageVo pageVo) {
        Map<String, Object> result = this.userAccountMapper.searchFreezeMoney(pageVo);
        BigDecimal totalFreezeMoney=BigDecimal.ZERO;
        if (!StringUtils.isEmpty(result)){
            BigDecimal freezeForCash = new BigDecimal(result.get("freeze_for_cash").toString());
            BigDecimal freezeForInvest = new BigDecimal(result.get("freeze_for_invest").toString());
            totalFreezeMoney = new MoneyCalculator(freezeForCash).add(freezeForInvest).getInstance();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("totalFreezeMoney",totalFreezeMoney);
        return map;
    }

    public Map<String,Object> searchTransferSale(PageVo pageVo) {
        return this.userAccountMapper.searchTransferSale(pageVo);
    }

    public Map<String,Object> receivedReward(PageVo pageVo) {
        Map<String,Object> param = pageVo.getParameters();
        param.put("user_id",new StringBuffer("p_").append(param.get("user_id")).toString());
        return this.userAccountMapper.receivedReward(pageVo);
    }

    public Map<String,Object> earnIncome(PageVo pageVo) {
        Date endDate = new Date();
        String str = DateUtils.dateToStr(endDate, DateEnum.DATE_SIMPLE) + " 23:59:59";
        endDate = toDate(str, DateEnum.DATE_FORMAT);
        Date startDate = DateUtils.beforDayOfMonth(endDate,3);
        Map<String,Object> map = pageVo.getParameters();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        Map<String,Object> result = this.userAccountMapper.earnIncome(pageVo);
        String nearThreeMonthMoney = result.get("nearThreeMonthMoney").toString();
        if (StringUtils.isEmpty(nearThreeMonthMoney)){
            nearThreeMonthMoney="0";
        }
        nearThreeMonthMoney = new MoneyCalculator(nearThreeMonthMoney).downToResultToString();
        result.put("nearThreeMonthMoney",nearThreeMonthMoney);
        return  result;
    }

    public Map<String,Object> sameMoneyIncome(PageVo pageVo) {
        Map<String,Object> map = this.userAccountMapper.sameMoneyIncome(pageVo);
        String sameMonthMoney = map.get("sameMonthMoney").toString();
        if(StringUtils.isEmpty(sameMonthMoney)){
            sameMonthMoney="0";
        }
        sameMonthMoney = new MoneyCalculator(sameMonthMoney).downToResultToString();
        map.put("sameMonthMoney",sameMonthMoney);
        return map;
    }

    public List<Map<String,Object>> addUpIncome(PageVo pageVo){
        return this.userAccountMapper.addUpIncome(pageVo);
    }

    public List<Map<String,Object>> dueInBidMoney(PageVo pageVo){
        return this.userAccountMapper.collectBidMoney(pageVo);
    }

    public List<Map<String,Object>> dueInPackageMoney(PageVo pageVo){
        //非月返荷包本金和利息待收
        List<Map<String, Object>> list = this.userAccountMapper.collectPackageMoney(pageVo);
        List<Map<String,Object>> newList = new ArrayList<>();
        Map<String,Map<String,Object>> newMap = Maps.newHashMap();
        for (int i = 0;i<list.size();i++) {
            //取出对象 算出每个荷包锁定期限的待收利息
            Map<String, Object> temp = list.get(i);
            String packageId = temp.get("id").toString();
            String rate = temp.get("rate").toString();
            String issue = temp.get("issue").toString();
            String lock_issue ="";
            String packagePrincipal=temp.get("packagePrincipal").toString();
            BigDecimal packageInterest= BigDecimal.ZERO;
            if(temp.get("lock_issue")!=null){
                lock_issue =temp.get("lock_issue").toString();
                packageInterest =this.calculateInterest(packagePrincipal,lock_issue,rate);
            }else{
                BigDecimal nowRate=BigDecimal.ZERO;
                if(temp.get("version_id")!=null ){
                    if(!temp.get("version_id").toString().equals("1")){
                        nowRate = this.versionRateManager.findValue(temp.get("version_id").toString() + ":"
                                + (Integer.valueOf(issue)));
                    }else{
                        nowRate = new BigDecimal(rate);
                    }
                }else{
                    nowRate = new BigDecimal(rate);
                }
                packageInterest=this.calculateInterest(packagePrincipal,issue,nowRate.toString());
            }
            temp.put("packageInterest",packageInterest);
            if(newMap.containsKey(temp.get("month").toString())){
                Map<String,Object> map = newMap.get(temp.get("month").toString());
                //取出原有值 做累加
                BigDecimal oldPrincipal=new BigDecimal(map.get("packagePrincipal").toString());
                BigDecimal oldInterest = new BigDecimal(map.get("packageInterest").toString());

                BigDecimal newPrincipal=new BigDecimal(temp.get("packagePrincipal").toString());
                BigDecimal newInterest = new BigDecimal(temp.get("packageInterest").toString());

                map.put("packagePrincipal",new MoneyCalculator(oldPrincipal).add(newPrincipal).downToResult().getInstance());
                map.put("packageInterest",new MoneyCalculator(oldInterest).add(newInterest).downToResult().getInstance());
                newMap.put(temp.get("month").toString(),map);
            }else{
                newMap.put(temp.get("month").toString(),temp);
            }
        }
        //月返荷包本金和利息待收
        List<Map<String,Object>> monthList= this.userAccountMapper.financeMonthReturnInterest(pageVo);

        if(monthList.size()>0) {
              for (int i = 0;i<monthList.size();i++) {
                  Map<String, Object> temp = monthList.get(i);
                  if(newMap.containsKey(temp.get("month").toString())){
                      Map<String,Object> map = newMap.get(temp.get("month").toString());
                      //取出原有值 做累加
                      BigDecimal oldPrincipal=new BigDecimal(map.get("packagePrincipal").toString());
                      BigDecimal oldInterest = new BigDecimal(map.get("packageInterest").toString());

                      BigDecimal newPrincipal=new BigDecimal(temp.get("packagePrincipal").toString());
                      BigDecimal newInterest = new BigDecimal(temp.get("packageInterest").toString());

                      map.put("packagePrincipal",new MoneyCalculator(oldPrincipal).add(newPrincipal).downToResult().getInstance());
                      map.put("packageInterest",new MoneyCalculator(oldInterest).add(newInterest).downToResult().getInstance());
                  }else{
                      newMap.put(temp.get("month").toString(),temp);
                  }
                }

            }
        Set<String> setIt = newMap.keySet();
        Iterator<String> it = setIt.iterator();
        while(it.hasNext()){
            newList.add(newMap.get(it.next()));
        }
        return newList;
    }

    @Page
    public Object incomeExpenses(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("user_id",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        return this.userAccountMapper.incomeExpenses(pageVo);
    }

    public Map<String,Object> searchUserBoundCard(PageVo pageVo){
        return this.userAccountMapper.searchUserBoundCard(pageVo);
    }

    public Map<String,Object> totalIncome(PageVo pageVo){
        long userIdNum = Long.valueOf(pageVo.getParameters().get("user_id").toString());
        Map<String,BigDecimal> income =this.userAccountMapper.searchDeliveredIncome(userIdNum);
        BigDecimal deliveredIncome = BigDecimal.ZERO;
        if (income != null) {
            deliveredIncome = income.get("deliveredIncome");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("deliveredIncome", new MoneyCalculator(deliveredIncome).downToResultToString());
        return result;
    }

    @Page
    public Object searchUserInvestBidRePayIngList(PageVo pageVo){
       List<Map<String,Object>> list =  this.userAccountMapper.searchUserInvestBidRePayIngList(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            String isVip = temp.get("isVip") == null ? "0" : temp.get("isVip").toString();
            String feeRate = null;
            if ("1".equals(isVip)) {
                int level = Integer.valueOf(temp.get("vipLevel").toString());
                feeRate = this.paramManager.findValue("vip" + level + "_manage_fee").getValue();
            } else {
                feeRate = this.paramManager.findValue("interest_fee_not_vip").getValue();
            }
            BigDecimal expectInterest = new MoneyCalculator(temp.get("expectInterest").toString()).toCeil();
            BigDecimal fee = new MoneyCalculator(expectInterest).multiply(new BigDecimal(feeRate)).downToResult()
                    .getInstance();
            temp.put("investFee", fee);
        }
        return list;
    }

    public Map<String,Object> incomeExpendTotal(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("user_id",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        Map<String,Object> result = new HashMap<>();
        if(param.get("io")==null){
            param.put("io","0");
            Map<String,Object> temp = this.userAccountMapper.incomeExpendTotal(pageVo);
            result.put("incomeTotal",temp.get("totalMoney"));

            param.put("io","1");
            Map<String,Object> temp1 = this.userAccountMapper.incomeExpendTotal(pageVo);
            result.put("expendTotal",temp1.get("totalMoney"));
        }else{
            String io = param.get("io").toString();
            if(io.equals("0")){
                Map<String,Object> temp = this.userAccountMapper.incomeExpendTotal(pageVo);
                result.put("incomeTotal",temp.get("totalMoney"));
                result.put("expendTotal",0);
            }else{
                Map<String,Object> temp1 = this.userAccountMapper.incomeExpendTotal(pageVo);
                result.put("expendTotal",temp1.get("totalMoney"));
                result.put("incomeTotal",0);
            }
        }
        return result;
    }

    @Page
    public Object searchUserAssetBuyBidRePayIngList(PageVo pageVo){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("state", "10");
        pageVo.getParameters().putAll(paramMap);
        List<Map<String,Object>> list =  this.userAccountMapper.searchUserAssetBuyBidRePayIngList(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            String feeRate = null;
            if (!StringUtils.isEmpty(temp.get("vipLevel").toString())&&!("0".equals(temp.get("vipLevel").toString()))) {
                int level = Integer.valueOf(temp.get("vipLevel").toString());
                feeRate = this.paramManager.findValue("vip" + level + "_manage_fee").getValue();
            } else {
                feeRate = this.paramManager.findValue("interest_fee_not_vip").getValue();
            }
            BigDecimal expectInterest = new MoneyCalculator(temp.get("expectInterest").toString()).toCeil();
            BigDecimal fee = new MoneyCalculator(expectInterest).multiply(new BigDecimal(feeRate)).downToResult()
                    .getInstance();
            temp.put("investFee", fee);
        }
        return list;

    }


    public Map<String,Object> assignmentOfDebt(PageVo pageVo){
        String bid_id = pageVo.getParameters().get("bid_id").toString();
        Integer bidId = Ints.tryParse(bid_id);
        String asset_id = pageVo.getParameters().get("asset_id").toString();
        Integer assetId = Ints.tryParse(asset_id);
        List<Map<String,Object>> list  = this.userAccountMapper.searchWaitRepayment(bidId);
        logger.info("======债权id:{}校验参数start======",assetId);
        Map<String,Object> result = validAsset(list);

        if(!result.isEmpty()){
            return result;
        }
        logger.info("======债权id:{}校验参数end======",assetId);
        result = this.getReceivableInterest(assetId);
        Map<String,Object> assetMap = this.queryAssetRemainMoney(bid_id,asset_id);
        if(assetMap !=null){
            result.put("remain_principal",assetMap.get("remain_principal").toString());
            result.put("name",assetMap.get("bidName").toString());
        }
        result.put("error",null);
        return result;
    }

    public Map<String,Object> validAsset(List<Map<String,Object>> list){
        Parameter parameter = this.paramManager.findValue("asset_day");
        String assetDay = parameter.getValue();
        Map<String,Object> reslut = new HashMap<>();

        ok: for (Map<String, Object> map : list) {
            String overdue =  map.get("days_overdue").toString();
            int overday = Integer.valueOf(overdue);
            if(overday>0){
                logger.info("该笔债权在bidId:{}存在逾期",map.get("bid_id").toString());
                reslut.put("error","E00003");
                return reslut;
            }
        }
        //判断是否持有债权30日以上 离还款日是否3天以上
        if(list.size()>0){

            Map<String,Object> map = list.get(0);
            String transactTime = map.get("start_time").toString();
            logger.info("transactTime=" + transactTime + "assetDay=" + assetDay+"holdTime"+ DateUtils.plusDay(toDate(transactTime,DateEnum.DATE_FORMAT),Ints.tryParse(assetDay)));
            //小于-1，等于0，大于1 满标复审时间+债权最少持有天数 和当天时间做对比  超过当天 不允许债转
            int flag = DateUtils.compareDateToNow(dateToDateTime(DateUtils.plusDay(toDate(transactTime,DateEnum.DATE_FORMAT),Ints.tryParse(assetDay))));

            if(flag==1){
                logger.info("该笔债权持有天数不足30天,transactTime:{},assetDay:{},holdTime{}",transactTime,assetDay,DateUtils.plusDay(toDate(transactTime,DateEnum.DATE_FORMAT),Ints.tryParse(assetDay)));
                reslut.put("error","E00002");
                return reslut;
            }

            String repayDay = map.get("ts_repay_due").toString();
            int day = DateUtils.daysBetween(DateUtils.getNow(),toDate(repayDay, DateEnum.DATE_FORMAT));
            if(day<3){
                logger.info("该笔债权距离还款日不足三天,还款日期:{}",repayDay);
                reslut.put("error","E00001");
                return reslut;
            }
        }
        return reslut;
    }


    public Map<String,Object> getReceivableInterest(Integer assetId) {

        logger.info("获取利息的 assetId=" + assetId);
        Asset asset = this.assetMapper.selectByPrimaryKey(assetId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != asset && asset.getState() == 0) {// 发起债转时，
            BidDocument bid = this.bidDocumentMapper.selectByPrimaryKey(asset.getBidId());
            BigDecimal getReceivableInterest = this.assetSaleManager.getReceivableInterest(bid, asset);
            Invest invest = this.investMapper.getInvestByActionSn(asset.getInvestId());
            int level = 0;
            if (null != invest) {
                level = invest.getCurrentLevel();
            } else {
                AssetBuy ab = this.assetBuyMapper.selectByPrimaryKey(asset.getInvestId());
                level = ab.getCurrentLevel();
            }
            Parameter par = this.paramManager.findValue("interest_fee_not_vip");// 非vip
            if (level == 99) {
                par.setValue("0");
            } else if (level != 0) {
                String parameterValue = String.format("vip%s_manage_fee", level);
                par = this.paramManager.findValue(parameterValue);
            }

            // 计算利息管理费
            BigDecimal getInterestFee = new MoneyCalculator(new BigDecimal(par.getValue()))
                    .multiply(getReceivableInterest).downToResult().getInstance();// 利息管理费(舍位)
            map.put("receivableInterest", getReceivableInterest);
            map.put("interestFee", getInterestFee);
        }
        logger.info("assetId=" + assetId + "receivableInterest,interestFee=" + map);
        return map;
    }


    /**
     * 用户投资的招标中的散标
     *
     * @param pageVo
     * @return
     */
    @Page
    public Object searchUserInvestAtInvestingList(PageVo pageVo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("state", "4,7,9");
        pageVo.getParameters().putAll(paramMap);
        List<Map<String,Object>> list =  this.userAccountMapper.searchUserInvestAtInvestingList(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            String isVip = temp.get("isVip") == null ? "0" : temp.get("isVip").toString();
            String feeRate = null;
            if ("1".equals(isVip)) {
                    int level = Integer.valueOf(temp.get("vipLevel").toString());
                    feeRate = this.paramManager.findValue("vip" + level + "_manage_fee").getValue();
                } else {
                    feeRate = this.paramManager.findValue("interest_fee_not_vip").getValue();
            }
            BigDecimal expectInterest = new MoneyCalculator(temp.get("expectInterest").toString()).toCeil();
            String fee = new MoneyCalculator(expectInterest).multiply(
                    new BigDecimal(feeRate)).downToResultToString();
            temp.put("investFee", fee);
        }
        return list;
    }


    /**
     * 用户投资的已还清的散标
     *
     * @param pageVo
     * @return
     */
    @Page
    public Object searchUserRepayOk(PageVo pageVo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("state", "15");
        pageVo.getParameters().putAll(paramMap);
        List<Map<String,Object>> list = this.userAccountMapper
                .searchUserRepayOk(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            if ("0".equals(temp.get("vipLevel").toString())||"".equals(temp.get("vipLevel").toString())) {
                String feeRate = this.paramManager.findValue("interest_fee_not_vip").getValue();
                String fee = new MoneyCalculator(temp.get("expectInterest").toString()).multiply(
                        new BigDecimal(feeRate)).downToResultToString();
                temp.put("investFee", fee);
            } else {
                int level = Integer.valueOf(temp.get("vipLevel").toString());
                String feeRate = this.paramManager.findValue("vip" + level + "_manage_fee").getValue();
                String fee = new MoneyCalculator(temp.get("expectInterest").toString()).multiply(
                        new BigDecimal(feeRate)).downToResultToString();
                temp.put("investFee", fee);
            }
        }
        return list;
    }


    @Page
    public Object searchAssetSale(PageVo pageVo) {
        List<Map<String,Object>> list = this.userAccountMapper
                .searchAssetSale(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            BigDecimal price = new BigDecimal(temp.get("price").toString());
            BigDecimal attornFeeRate = new BigDecimal(temp.get("attorn_fee_rate").toString());


            BigDecimal attornFee = new MoneyCalculator(price).multiply(attornFeeRate).downToResult()
                    .getInstance();
            // 取出系统配置参数
            Parameter assetFeeMinPar = this.paramManager.findValue("asset_fee_min");
            Parameter assetFeeMaxPar = this.paramManager.findValue("asset_fee_max");
            Parameter notVipFee = this.paramManager.findValue("interest_fee_not_vip");
            double assetFeeMin = Double.valueOf(assetFeeMinPar.getValue());
            double assetFeeMax = Double.valueOf(assetFeeMaxPar.getValue());
            // 判断是否超过最大值，和小于最小值，并且更改为对应的金额
            if (attornFee.doubleValue() < assetFeeMin) {
                attornFee = new BigDecimal(assetFeeMin);
            } else if (attornFee.doubleValue() > assetFeeMax) {
                attornFee = new BigDecimal(assetFeeMax);
            }
            temp.put("attornFee", attornFee);
        }
        return list;
    }


    public List<Map<String,Object>> searchWaitBidGathering(PageVo pageVo){

        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
          Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
          Date endDate = DateUtils.behineMonthEndDate(startDate,3);
          param.put("startDate",startDate);
          param.put("endDate",endDate);
        }

        List<Map<String,Object>> waitPrincipalInterest = this.userAccountMapper.searchBidWaitGathering(pageVo);
        List<Map<String,Object>> waitReward = this.userAccountMapper.searchBidWaitReward(pageVo);

        if(waitPrincipalInterest.size()==0){
            for(int i=0;i<waitReward.size();i++){
                Map<String,Object> reward =waitReward.get(i);
                reward.put("income_principal","0");
                reward.put("income_interest","0");
            }
            return waitReward;
        }else{
            for(int i=0;i<waitPrincipalInterest.size();i++){
                Map<String,Object> temp = waitPrincipalInterest.get(i);
                if(waitReward.size()==0){
                    temp.put("reward","0");
                }

                for(int j=0;j<waitReward.size();j++){
                    Map<String,Object> reward =waitReward.get(j);
                    String userId= temp.get("user_id").toString();
                    String issue = temp.get("income_issue").toString();
                    String bidId = temp.get("bid_id").toString();
                    String assetId=temp.get("asset_id").toString();
                    String investId=temp.get("invest_id").toString();

                    String rUserId= reward.get("user_id").toString();
                    String rIssue = reward.get("income_issue").toString();
                    String rBidId = reward.get("bid_id").toString();
                    String rAssetId=reward.get("asset_id").toString();
                    String rInvestId=reward.get("invest_id").toString();
                    if(userId.equals(rUserId)&&issue.equals(issue)&&bidId.equals(rBidId)&&assetId.equals(rAssetId)&&investId.equals(rInvestId)){
                        temp.put("reward",reward.get("income_reward").toString());
                    }else{
                        temp.put("reward","0");
                    }
                }
            }

        }

        return waitPrincipalInterest;

    }

    public List<Map<String,Object>> getWaitingForRepayFnPackageLogs(PageVo pageVo){

        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        String userId = new StringBuffer("p_").append(pageVo.getParameters().get("user_id").toString()).toString();
        param.put("userId",userId);
        pageVo.setParameters(param);
        List<Map<String,Object>> waitPrincipalInterest = this.userAccountMapper.getWaitingForRepayFnPackageLogs(pageVo);
        for(int i = 0; i < waitPrincipalInterest.size(); i++) {
            Map<String,Object> map = waitPrincipalInterest.get(i);
            //普通荷包和活动荷包在回款日历里边显示为一笔记录 （加入金额*锁定期利率/12）*荷包锁定期限
            String packageType = map.get("packageType").toString();
            if(String.valueOf(FinancePackageType.FP_COMMON.getText()).equals(packageType) ){
                String dealMoney = map.get("amount").toString();
                String rate = map.get("rate").toString();
                String issue ="";
                if(map.get("lock_issue")!=null){
                    issue=map.get("lock_issue").toString();
                }else{
                    issue=map.get("totalIssue").toString();
                }
                BigDecimal interest = this.calculateInterest(packageType,dealMoney,rate,issue);
                map.put("expect_interest",interest);
            }

            if(String.valueOf(FinancePackageType.FP_ACTIVITY.getText()).equals(packageType)){
                String dealMoney = map.get("amount").toString();
                String rate = map.get("rate").toString();
                String issue ="";
                if(map.get("lock_issue")!=null){
                    issue=map.get("lock_issue").toString();
                }else{
                    issue=map.get("totalIssue").toString();
                }
                BigDecimal interest = BigDecimal.ZERO;
                if(!StringUtils.isEmpty(map.get("add_rate"))){
                    interest = this.calculateInterest(packageType,dealMoney,rate,issue);
                }else{
                    interest = this.calculateInterest(packageType,dealMoney,rate,issue);
                }
                map.put("expect_interest",interest);
            }

            if(String.valueOf(FinancePackageType.FP_MONTHADDRATE.getText()).equals(packageType)){
                String dealMoney = map.get("amount").toString();
                String rate = map.get("rate").toString();
                String issue ="";
                if(StringUtils.isEmpty(map.get("lock_issue"))){
                    issue=map.get("totalIssue").toString();
                }else{
                    issue=map.get("lock_issue").toString();
                }
                BigDecimal interest = this.calculateInterest(packageType,dealMoney,rate,issue);
                map.put("expect_interest",interest);
            }
        }

       return waitPrincipalInterest;

    }

    public List<Map<String,Object>> getWaitingforRepaymonthFnpackage(PageVo pageVo){

        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        List<Map<String,Object>> waitPrincipalInterest = this.userAccountMapper.getWaitingforRepaymonthFnpackage(pageVo);

        return waitPrincipalInterest;

    }


    public List<Map<String,Object>> searchBidAlreadyGathering(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }

        return this.userAccountMapper.searchBidAlreadyGathering(pageVo);

    }

    public List<Map<String,Object>> searchFinAlreadyGathering(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        List<Map<String,Object>> finPriInte =  this.userAccountMapper.searchFinAlreadyGathering(pageVo);
        List<Map<String,Object>> finMonthPriInte =  this.userAccountMapper.searchFinMonthAlreadyGathering(pageVo);

        for(int i=0;i<finMonthPriInte.size();i++){
            Map<String,Object> temp =finMonthPriInte.get(i);
            finPriInte.add(temp);
        }

        return finPriInte;
    }

    public List<Map<String,Object>> searchFinRewardAlreadyGathering(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        List<Map<String,Object>> receivedReward =  this.userAccountMapper.searchFinanceReceivedReward(pageVo);
        return receivedReward;
    }


    public List<Map<String,Object>> searchFinrewardwaitgathering(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        String start = param.get("startDate").toString();
        if(!StringUtils.isEmpty(start)){
            Date startDate =  DateUtils.toDate(start,DateEnum.DATE_FORMAT);
            Date endDate = DateUtils.behineMonthEndDate(startDate,3);
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        List<Map<String,Object>> waitReward =  this.userAccountMapper.searchFinrewardwaitgathering(pageVo);

        return waitReward;
    }

    @Page
    public Object queryInvestOrder(PageVo pageVo) {
        return this.userAccountMapper.queryInvestOrder(pageVo);
    }

    @Page
    public Object queryAssetbuyOrder(PageVo pageVo) {
        Map<String,Object> map = pageVo.getParameters();
        map.put("user_id",new StringBuffer("p_").append(map.get("user_id").toString()).toString());
        return this.userAccountMapper.queryAssetbuyOrder(pageVo);
    }

    @Page
    public Object queryAssetSaleOrder(PageVo pageVo) {
        List<Map<String,Object>> list = this.userAccountMapper.queryAssetSaleOrder(pageVo);
        for (int i = 0, len = list.size(); i < len; i++) {
            Map<String, Object> temp = list.get(i);
            BigDecimal price = new BigDecimal(temp.get("price").toString());
            BigDecimal interestFee = new BigDecimal(temp.get("interestFee").toString());
            BigDecimal remainInterest= new BigDecimal(temp.get("remain_interest").toString());
            BigDecimal receivableInterest= new BigDecimal(temp.get("receivable_interest").toString());
            BigDecimal attornFeeRate = new BigDecimal(temp.get("attorn_fee_rate").toString());


            BigDecimal attornFee = new MoneyCalculator(price).multiply(attornFeeRate).downToResult()
                    .getInstance();
            // 取出系统配置参数
            Parameter assetFeeMinPar = this.paramManager.findValue("asset_fee_min");
            Parameter assetFeeMaxPar = this.paramManager.findValue("asset_fee_max");
            Parameter notVipFee = this.paramManager.findValue("interest_fee_not_vip");
            double assetFeeMin = Double.valueOf(assetFeeMinPar.getValue());
            double assetFeeMax = Double.valueOf(assetFeeMaxPar.getValue());
            // 判断是否超过最大值，和小于最小值，并且更改为对应的金额
            if (attornFee.doubleValue() < assetFeeMin) {
                attornFee = new BigDecimal(assetFeeMin);
            } else if (attornFee.doubleValue() > assetFeeMax) {
                attornFee = new BigDecimal(assetFeeMax);
            }
            temp.put("attornFee", attornFee);
            BigDecimal totalMoney = new MoneyCalculator(price).add(receivableInterest).add(remainInterest).subtract(attornFee).subtract(interestFee).downToResult().getInstance();
            temp.put("totalMoney",totalMoney);
        }
        return list;
    }

    @Page
    public Object queryRechargeOrder(PageVo pageVo){
            return this.userAccountMapper.queryRechargeOrder(pageVo);
    }

    @Page
    public Object queryWithdrawCashOrder(PageVo pageVo){
        return this.userAccountMapper.queryWithdrawCashOrder(pageVo);
    }

    @Page
    public Object queryFinanceQuitOrder(PageVo pageVo){
        Map<String,Object> map = pageVo.getParameters();
        map.put("user_id",new StringBuffer("p_").append(map.get("user_id").toString()).toString());
        return this.userAccountMapper.queryFinanceQuitOrder(pageVo);
    }

    @Page
    public Object  searchFinancemmhHold(PageVo pageVo){
        logger.info("查询已加入的月月升息理财包...");
        //owned_state 1 持有中 5 加入中 3退出中 4已退出
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        param.put("owned_state",1);
        List<Map<String, Object>> financePackageInvests = this.searchFinanceMMHInvest(pageVo);
        if (CollectionUtils.isNotEmpty(financePackageInvests)) {
            for (Map<String, Object> invest : financePackageInvests) {
                Date expectEndTime = DateUtils.toDate(invest.get("expect_end_time").toString(), DateEnum.DATE_SIMPLE);
                // 持有天数算头不算尾
                String ownedDays = null;
                BigDecimal interest = BigDecimal.ZERO;
                if (invest.get("quit_state") == null || Ints.tryParse(invest.get("quit_state").toString()).equals(0)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, new Date());
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 0);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(1)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(new Date(), 1));
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 1);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(3)
                        || Ints.tryParse(invest.get("quit_state").toString()).equals(4)) {
                    ownedDays = invest.get("owned_days").toString();
                    interest = new BigDecimal(invest.get("quit_interest").toString());
                }

                invest.put("interest", interest);
                // 计算实时利率
                BigDecimal nowRate = BigDecimal.ZERO;
                int monthsBetween = DateUtils.monthsBetween(expectEndTime, new Date());
                int daysBetween = DateUtils.daysBetween(expectEndTime, new Date());
                int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
                if (monthsBetween > 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                if (daysBetween - marginDays > 0) {
                    monthsBetween = monthsBetween + 1 > Ints.tryParse(invest.get("issue").toString()) ? Ints
                            .tryParse(invest.get("issue").toString()) : monthsBetween + 1;// 2个月10天取3个月对应的利率
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                // 解决当天为荷包进入锁定日期,实时利率为0的问题
                if (monthsBetween == 0 && (daysBetween - marginDays) <= 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween + 1));
                }
                invest.put("now_rate", nowRate);
                // 判断是正常退出还是到期退出 begin_quit_time
                Boolean isQuit = Ints.tryParse(invest.get("owned_state").toString()).byteValue() == OwnedState.QUITED
                        .getText();
                if (isQuit) {
                    // 正常退出
                    if (invest.get("begin_quit_time") != null) {
                        invest.put("isUsual", "0"); // 正常退出
                        ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(
                                DateUtils.strToDate(invest.get("begin_assign_time").toString(), DateEnum.DATE_SIMPLE),
                                1));// 债转日当日算收益
                        invest.put("begin_quit_time1", invest.get("begin_assign_time").toString());
                        invest.put("interest", new BigDecimal(invest.get("quit_interest").toString())
                                .subtract(new BigDecimal(invest.get("quit_charge").toString())));
                    } else {
                        invest.put("isUsual", "1"); // 到期退出
                        ownedDays = invest.get("issue").toString() + "个月";
                        invest.put("begin_quit_time1", invest.get("complete_time1").toString());
                    }
                }
                invest.put("owned_days", ownedDays);

                //判断该笔债权是否持有一个月
                String expect_end_time = invest.get("expect_end_time").toString();
                String complete_time = invest.get("complete_time1").toString();
                Date holdDate = DateUtils.plusMonth(DateUtils.toDate(expect_end_time,DateEnum.DATE_SIMPLE),1);
                int holdNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(holdDate));
                //锁定日期+一个月和当前日期进行对比 如果早于今天就不可退出 如果晚于或者当天可以进行退出
                if(holdNum!=1){
                    invest.put("hold","0");
                }else{
                    invest.put("hold","1");
                }

                //判断是否是锁定期到期的前五天
                if(!StringUtils.isEmpty(invest.get("lock_issue"))){
                    Date lockEndDate = DateUtils.minusDay(DateUtils.toDate(expect_end_time,DateEnum.DATE_SIMPLE),Integer.valueOf(invest.get("lock_issue").toString()));
                    lockEndDate = DateUtils.minusDay(lockEndDate,5);
                    int lockNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(lockEndDate));
                    if(lockNum!=-1){
                        invest.put("lock","0");
                    }else{
                        invest.put("lock","1");
                    }
                    Date completeDate = DateUtils.minusDay(DateUtils.toDate(complete_time,DateEnum.DATE_SIMPLE),Integer.valueOf(invest.get("lock_issue").toString()));
                    completeDate= DateUtils.minusDay(completeDate,5);
                    int comNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(completeDate));
                    if(comNum!=-1){
                        invest.put("com","0");
                    }else{
                        invest.put("com","1");
                    }
                }else{
                    Date completeDate = DateUtils.minusDay(DateUtils.toDate(complete_time,DateEnum.DATE_SIMPLE),Integer.valueOf(invest.get("lock_issue").toString()));
                    completeDate= DateUtils.minusDay(completeDate,5);
                    int comNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(completeDate));
                    if(comNum!=-1){
                        invest.put("com","0");
                    }else{
                        invest.put("com","1");
                    }
                }
            }
        }
        return financePackageInvests;
    }


    public List<Map<String, Object>> searchFinanceMMHInvest(PageVo pageVo) {
        return this.userAccountMapper.searchFinanceMMHInvest(pageVo);
    }

    public BigDecimal calInterest(Date expectEndTime, BigDecimal investMoney, Integer versionId, Integer issue,
                                  int addDay) {
        int monthsBetween = DateUtils.monthsBetween(expectEndTime, DateUtils.plusDay(new Date(), addDay));
        int daysBetween = DateUtils.daysBetween(expectEndTime, DateUtils.plusDay(new Date(), addDay));
        int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
        int marginNextMonthDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween + 1);
        int notEnoughThisMonthTotalDays = (daysBetween - marginDays) + (marginNextMonthDays - daysBetween);
        // 取月份相对应梯度利率
        BigDecimal monthInterest = BigDecimal.ZERO;
        // 解决荷包已经退出利息仍然增长的问题
        boolean hasExpire = false;
        if (monthsBetween >= issue) {
            monthsBetween = issue;
            hasExpire = true;
        }
        // 计算整月利息
        for (int m = 1; m <= monthsBetween; m++) {
            BigDecimal rate = this.versionRateManager.findValue(versionId + ":" + (m));
            monthInterest = monthInterest.add(new MoneyCalculator(investMoney).multiply(rate)
                    .divide(new BigDecimal(100)).divide(new BigDecimal(12)).getInstance());
        }
        // 整月的利息四舍五入
        if (daysBetween - marginDays == 0) {
            monthInterest = new MoneyCalculator(monthInterest).toResult();
        }
        // 计算不足月份天数利息
        if (daysBetween - marginDays > 0 && !hasExpire) {
            monthsBetween = monthsBetween + 1 > issue ? issue : monthsBetween + 1;// 2个月10天取3个月对应的利率
            BigDecimal rate = this.versionRateManager.findValue(versionId + ":" + (monthsBetween));
            BigDecimal notEnoughDaysInterest = new MoneyCalculator(investMoney).multiply(rate)
                    .divide(new BigDecimal(100)).divide(new BigDecimal(12))
                    .multiply(new BigDecimal(daysBetween - marginDays))
                    .divide(new BigDecimal(notEnoughThisMonthTotalDays)).getInstance();
            monthInterest = new MoneyCalculator(monthInterest).add(notEnoughDaysInterest).toResult();
        }
        return monthInterest;
    }



    @Page
    public Object  searchFinancemmhJoinin(PageVo pageVo){
        logger.info("查询已加入的月月升息理财包...");
        //owned_state 1 持有中 5 加入中 3退出中 4已退出
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        param.put("owned_state",5);
        List<Map<String, Object>> financePackageInvests = this.searchFinanceMMHInvest(pageVo);
        if (CollectionUtils.isNotEmpty(financePackageInvests)) {
            for (Map<String, Object> invest : financePackageInvests) {
                Date expectEndTime = DateUtils.toDate(invest.get("expect_end_time").toString(), DateEnum.DATE_SIMPLE);
                // 持有天数算头不算尾
                String ownedDays = null;
                BigDecimal interest = BigDecimal.ZERO;
                if (invest.get("quit_state") == null || Ints.tryParse(invest.get("quit_state").toString()).equals(0)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, new Date());
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 0);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(1)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(new Date(), 1));
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 1);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(3)
                        || Ints.tryParse(invest.get("quit_state").toString()).equals(4)) {
                    ownedDays = invest.get("owned_days").toString();
                    interest = new BigDecimal(invest.get("quit_interest").toString());
                }

                invest.put("interest", interest);
                // 计算实时利率
                BigDecimal nowRate = BigDecimal.ZERO;
                int monthsBetween = DateUtils.monthsBetween(expectEndTime, new Date());
                int daysBetween = DateUtils.daysBetween(expectEndTime, new Date());
                int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
                if (monthsBetween > 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                if (daysBetween - marginDays > 0) {
                    monthsBetween = monthsBetween + 1 > Ints.tryParse(invest.get("issue").toString()) ? Ints
                            .tryParse(invest.get("issue").toString()) : monthsBetween + 1;// 2个月10天取3个月对应的利率
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                // 解决当天为荷包进入锁定日期,实时利率为0的问题
                if (monthsBetween == 0 && (daysBetween - marginDays) <= 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween + 1));
                }
                invest.put("now_rate", nowRate);
                // 判断是正常退出还是到期退出 begin_quit_time
                Boolean isQuit = Ints.tryParse(invest.get("owned_state").toString()).byteValue() == OwnedState.QUITED
                        .getText();
                if (isQuit) {
                    // 正常退出
                    if (invest.get("begin_quit_time") != null) {
                        invest.put("isUsual", "0"); // 正常退出
                        ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(
                                DateUtils.strToDate(invest.get("begin_assign_time").toString(), DateEnum.DATE_SIMPLE),
                                1));// 债转日当日算收益
                        invest.put("begin_quit_time1", invest.get("begin_assign_time").toString());
                        invest.put("interest", new BigDecimal(invest.get("quit_interest").toString())
                                .subtract(new BigDecimal(invest.get("quit_charge").toString())));
                    } else {
                        invest.put("isUsual", "1"); // 到期退出
                        ownedDays = invest.get("issue").toString() + "个月";
                        invest.put("begin_quit_time1", invest.get("complete_time1").toString());
                    }
                }
                invest.put("owned_days", ownedDays);


            }
        }
        return financePackageInvests;
    }

    @Page
    public Object  searchFinancemmhAborting(PageVo pageVo){
        logger.info("查询已加入的月月升息理财包...");
        //owned_state 1 持有中 5 加入中 3退出中 4已退出
        Map<String,Object> param = pageVo.getParameters();
        param.put("owned_state",3);
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        List<Map<String, Object>> financePackageInvests = this.searchFinanceMMHInvest(pageVo);
        if (CollectionUtils.isNotEmpty(financePackageInvests)) {
            for (Map<String, Object> invest : financePackageInvests) {
                Date expectEndTime = DateUtils.toDate(invest.get("expect_end_time").toString(), DateEnum.DATE_SIMPLE);
                // 持有天数算头不算尾
                String ownedDays = null;
                BigDecimal interest = BigDecimal.ZERO;
                if (invest.get("quit_state") == null || Ints.tryParse(invest.get("quit_state").toString()).equals(0)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, new Date());
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 0);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(1)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(new Date(), 1));
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 1);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(3)
                        || Ints.tryParse(invest.get("quit_state").toString()).equals(4)) {
                    ownedDays = invest.get("owned_days").toString();
                    interest = new BigDecimal(invest.get("quit_interest").toString());
                }

                invest.put("interest", interest);
                // 计算实时利率
                BigDecimal nowRate = BigDecimal.ZERO;
                int monthsBetween = DateUtils.monthsBetween(expectEndTime, new Date());
                int daysBetween = DateUtils.daysBetween(expectEndTime, new Date());
                int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
                if (monthsBetween > 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                if (daysBetween - marginDays > 0) {
                    monthsBetween = monthsBetween + 1 > Ints.tryParse(invest.get("issue").toString()) ? Ints
                            .tryParse(invest.get("issue").toString()) : monthsBetween + 1;// 2个月10天取3个月对应的利率
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                // 解决当天为荷包进入锁定日期,实时利率为0的问题
                if (monthsBetween == 0 && (daysBetween - marginDays) <= 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween + 1));
                }
                invest.put("now_rate", nowRate);
                // 判断是正常退出还是到期退出 begin_quit_time
                Boolean isQuit = Ints.tryParse(invest.get("owned_state").toString()).byteValue() == OwnedState.QUITED
                        .getText();
                if (isQuit) {
                    // 正常退出
                    if (invest.get("begin_quit_time") != null) {
                        invest.put("isUsual", "0"); // 正常退出
                        ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(
                                DateUtils.strToDate(invest.get("begin_assign_time").toString(), DateEnum.DATE_SIMPLE),
                                1));// 债转日当日算收益
                        invest.put("begin_quit_time1", invest.get("begin_assign_time").toString());
                        invest.put("interest", new BigDecimal(invest.get("quit_interest").toString())
                                .subtract(new BigDecimal(invest.get("quit_charge").toString())));
                    } else {
                        invest.put("isUsual", "1"); // 到期退出
                        ownedDays = invest.get("issue").toString() + "个月";
                        invest.put("begin_quit_time1", invest.get("complete_time1").toString());
                    }
                }
                invest.put("owned_days", ownedDays);
                BigDecimal cashed_money = new MoneyCalculator(invest.get("deal_amount").toString()).add(interest).
                        subtract(new BigDecimal(invest.get("quit_charge").toString())).upToResult().getInstance();
                invest.put("cashed_money",cashed_money );
            }
        }
        return financePackageInvests;
    }

    @Page
    public Object  searchFinancemmhExited(PageVo pageVo){
        logger.info("查询已加入的月月升息理财包...");
        //owned_state 1 持有中 5 加入中 3退出中 4已退出
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        param.put("owned_state",4);
        List<Map<String, Object>> financePackageInvests = this.searchFinanceMMHInvest(pageVo);
        if (CollectionUtils.isNotEmpty(financePackageInvests)) {
            for (Map<String, Object> invest : financePackageInvests) {
                Date expectEndTime = DateUtils.toDate(invest.get("expect_end_time").toString(), DateEnum.DATE_SIMPLE);
                // 持有天数算头不算尾
                String ownedDays = null;
                BigDecimal interest = BigDecimal.ZERO;
                if (invest.get("quit_state") == null || Ints.tryParse(invest.get("quit_state").toString()).equals(0)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, new Date());
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 0);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(1)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(new Date(), 1));
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(invest.get("deal_amount").toString()),
                            Ints.tryParse(invest.get("version_id").toString()),
                            Ints.tryParse(invest.get("issue").toString()), 1);
                } else if (Ints.tryParse(invest.get("quit_state").toString()).equals(3)
                        || Ints.tryParse(invest.get("quit_state").toString()).equals(4)) {
                    ownedDays = invest.get("owned_days").toString();
                    interest = new BigDecimal(invest.get("quit_interest").toString());
                }

                invest.put("interest", interest);
                // 计算实时利率
                BigDecimal nowRate = BigDecimal.ZERO;
                int monthsBetween = DateUtils.monthsBetween(expectEndTime, new Date());
                int daysBetween = DateUtils.daysBetween(expectEndTime, new Date());
                int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
                if (monthsBetween > 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                if (daysBetween - marginDays > 0) {
                    monthsBetween = monthsBetween + 1 > Ints.tryParse(invest.get("issue").toString()) ? Ints
                            .tryParse(invest.get("issue").toString()) : monthsBetween + 1;// 2个月10天取3个月对应的利率
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                // 解决当天为荷包进入锁定日期,实时利率为0的问题
                if (monthsBetween == 0 && (daysBetween - marginDays) <= 0) {
                    nowRate = this.versionRateManager.findValue(invest.get("version_id").toString() + ":"
                            + (monthsBetween + 1));
                }
                invest.put("now_rate", nowRate);
                // 判断是正常退出还是到期退出 begin_quit_time
                Boolean isQuit = Ints.tryParse(invest.get("owned_state").toString()).byteValue() == OwnedState.QUITED
                        .getText();
                if (isQuit) {
                    // 正常退出
                    if (invest.get("begin_quit_time") != null) {
                        invest.put("isUsual", "0"); // 正常退出
                        ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(
                                DateUtils.strToDate(invest.get("begin_assign_time").toString(), DateEnum.DATE_SIMPLE),
                                1));// 债转日当日算收益
                        invest.put("begin_quit_time1", invest.get("begin_assign_time").toString());
                        invest.put("interest", new BigDecimal(invest.get("quit_interest").toString())
                                .subtract(new BigDecimal(invest.get("quit_charge").toString())));
                    } else {
                        invest.put("isUsual", "1"); // 到期退出
                        ownedDays = invest.get("issue").toString() + "个月";
                        invest.put("begin_quit_time1", invest.get("complete_time1").toString());
                    }
                }
                invest.put("owned_days", ownedDays);
            }
        }
        return financePackageInvests;
    }

    public BigDecimal calculateInterest(String packageType,String dealMoney,String rate,String issue){
        BigDecimal interest = BigDecimal.ZERO;
        if(String.valueOf(FinancePackageType.FP_COMMON.getText()).equals(packageType)||String.valueOf(FinancePackageType.FP_ACTIVITY.getText()).equals(packageType)){
            //加入金额*锁定期利率/12）*荷包锁定期限
            interest = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).
                    multiply(new BigDecimal(issue)).upToResult().getInstance();
        }

        //（加入金额*锁定期利率/12）*荷包锁定期限
        if(String.valueOf(FinancePackageType.FP_MONTHADDRATE.getText()).equals(packageType)){
            interest = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).
                    multiply(new BigDecimal(issue)).upToResult().getInstance();
        }

        //新月返 加入金额*固定利率/12；
        if(String.valueOf(FinancePackageType.FP_MONTHRETURN.getText()).equals(packageType)){
            interest = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).upToResult().getInstance();
        }
        return interest;
    }


    @Page
    public Object  searchAssetList(PageVo pageVo){
        return userAccountMapper.searchAssetList(pageVo);
    }

    @Page
    public Object searchFinanceInvest(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        List<Map<String,Object>> list =userAccountMapper.searchFinanceInvest(pageVo);
        for(int i =0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            //判断锁定期是否为空  如果锁定期不为空 利息为区间 锁定期利息~到期利息 （加入金额*锁定期利率/12）*荷包锁定期限
            BigDecimal interest = BigDecimal.ZERO;
            BigDecimal lockInterest=BigDecimal.ZERO;
            BigDecimal gradRate = BigDecimal.ZERO;
            String rate = map.get("rate").toString();
            String issue = map.get("issue").toString();
            String dealMoney = map.get("deal_amount").toString();
            BigDecimal rewardMoney = BigDecimal.ZERO;
            String rewardRate = map.get("reward_rate").toString();

            //计算利息区间
            if(!StringUtils.isEmpty(map.get("lock_issue"))){
                String lockIssue = map.get("lock_issue").toString();
                //新手荷包加息按照利息计算
                lockInterest = this.calculateInterest(dealMoney,rate,lockIssue);
                map.put("lockInterest",lockInterest);
                // 获取当前梯度利率
                int package_type =Integer.valueOf( map.get("package_type").toString());
                //月返没有梯度利率
                if(package_type!=(FinancePackageType.FP_MONTHRETURN.getText())){
                    String versionId = map.get("version_id").toString();
                    gradRate= this.versionRateManager.findValue(versionId + ":" + issue);
                    rate = String.valueOf(gradRate);
                }
                //普通奖励荷包-计算锁定期奖励
                if(!rewardRate.equals("0")){
                    rewardMoney =  this.calculateReward(dealMoney,rewardRate,lockIssue);
                }

                //新手荷包贴息按照 最长持有期限-锁定期
                if(!StringUtils.isEmpty(map.get("add_rate"))){
                    BigDecimal totalIsuue = new BigDecimal(issue);
                    BigDecimal remainIssue = totalIsuue.subtract(new BigDecimal(lockIssue));
                    interest = this.calculateInterest(dealMoney,rate,String.valueOf(remainIssue));
                    interest=interest.add(lockInterest);
                }else{
                    interest = this.calculateInterest(dealMoney,rate,issue);
                }
                map.put("interest",interest);
                map.put("expect_reward",rewardMoney);
            }else{
                interest = this.calculateInterest(dealMoney,rate,issue);
                map.put("interest",interest);
                map.put("expect_reward",rewardMoney);
                map.put("lockInterest",lockInterest);
            }

            //判断该笔债权是否持有一个月
            String expect_end_time = map.get("expect_end_time1").toString();
            String complete_time = map.get("complete_time1").toString();
            Date holdDate = DateUtils.plusMonth(DateUtils.toDate(expect_end_time,DateEnum.DATE_SIMPLE),1);
            int holdNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(holdDate));
            //锁定日期+一个月和当前日期进行对比 如果早于今天就不可退出 如果晚于或者当天可以进行退出
            if(holdNum!=1){
                map.put("hold","0");
            }else{
                map.put("hold","1");
            }

            //判断是否是锁定期到期的前五天
            if(!StringUtils.isEmpty(map.get("lock_issue"))){
                Date lockEndDate = DateUtils.plusMonth(DateUtils.toDate(expect_end_time,DateEnum.DATE_SIMPLE),Integer.valueOf(map.get("lock_issue").toString()));
                lockEndDate = DateUtils.minusDay(lockEndDate,5);
                int lockNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(lockEndDate));
                if(lockNum!=-1){
                    map.put("lock","0");
                }else{
                    map.put("lock","1");
                }
                Date completeDate = DateUtils.toDate(complete_time,DateEnum.DATE_SIMPLE);
                completeDate= DateUtils.minusDay(completeDate,5);
                int comNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(completeDate));
                if(comNum!=-1){
                    map.put("com","0");
                }else{
                    map.put("com","1");
                }
            }else{
                Date completeDate = DateUtils.toDate(complete_time,DateEnum.DATE_SIMPLE);
                completeDate= DateUtils.minusDay(completeDate,5);
                int comNum =DateUtils.compareDateToNow(DateUtils.dateToDateTime(completeDate));
                if(comNum!=-1){
                    map.put("com","0");
                }else{
                    map.put("com","1");
                }
            }

        }
        return list;
    }


    /**
     * 计算没有锁定期的待收利息
     * @param dealMoney
     * @param rate
     * @param issue
     * @return
     */
    public BigDecimal calculateInterest(String dealMoney,String rate,String issue){
        BigDecimal interest = BigDecimal.ZERO;
        //加入金额*荷包利率/12）*荷包总期数
        interest = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).
                    multiply(new BigDecimal(issue)).upToResult().getInstance();
        return interest;
    }


    public BigDecimal calculateReward(String dealMoney,String rewardRate,String lockIssue){
        BigDecimal reward = BigDecimal.ZERO;
        reward = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rewardRate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).
                multiply(new BigDecimal(lockIssue)).upToResult().getInstance();
        return reward;
    }


    @Page
    public Object searchFinanceJoin(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        List<Map<String,Object>> list =userAccountMapper.searchFinanceJoin(pageVo);
        for(int i =0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            BigDecimal interest = BigDecimal.ZERO;
            BigDecimal lockInterest=BigDecimal.ZERO;
            BigDecimal gradRate = BigDecimal.ZERO;
            //判断锁定期是否为空  如果锁定期不为空 利息为区间 锁定期利息~到期利息 （加入金额*锁定期利率/12）*荷包锁定期限
            String rate = map.get("rate").toString();
            String issue = map.get("issue").toString();
            String dealMoney = map.get("deal_amount").toString();
            BigDecimal rewardMoney = BigDecimal.ZERO;
            String rewardRate = map.get("reward_rate").toString();
            BigDecimal baseRate = BigDecimal.ZERO;

            //计算利息区间
            if(!StringUtils.isEmpty(map.get("lock_issue"))){
                String lockIssue = map.get("lock_issue").toString();
                //新手荷包加息按照利息计算
                lockInterest = this.calculateInterest(dealMoney,rate,lockIssue);
                map.put("lockInterest",lockInterest);


                // 获取当前梯度利率
                int package_type =Integer.valueOf( map.get("package_type").toString());
                //月返没有梯度利率
                if(package_type!=(FinancePackageType.FP_MONTHRETURN.getText())){
                    String versionId = map.get("version_id").toString();
                    gradRate= this.versionRateManager.findValue(versionId + ":" + issue);
                    rate = String.valueOf(gradRate);
                }
                //普通奖励荷包-计算锁定期奖励
                if(!rewardRate.equals("0")){
                    rewardMoney =  this.calculateReward(dealMoney,rewardRate,lockIssue);
                }

                //新手荷包贴息按照 最长持有期限-锁定期
                if(!StringUtils.isEmpty(map.get("add_rate"))){
                    BigDecimal totalIsuue = new BigDecimal(issue);
                    BigDecimal remainIssue = totalIsuue.subtract(new BigDecimal(lockIssue));
                    interest = this.calculateInterest(dealMoney,rate,String.valueOf(remainIssue));
                    interest=interest.add(lockInterest);
                }else{
                    interest = this.calculateInterest(dealMoney,rate,issue);
                }
                map.put("interest",interest);
                map.put("expect_reward",rewardMoney);
            }else{
                interest = this.calculateInterest(dealMoney,rate,issue);
                map.put("interest",interest);
                map.put("expect_reward",rewardMoney);
                map.put("lockInterest",lockInterest);
            }


        }
        return list;
    }


    @Page
    public Object searchFinanceAborting(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("user_id",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        return this.userAccountMapper.searchFinanceAborting(pageVo);
    }

    @Page
    public Object queryFinanceMMQuitOrder(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("user_id",new StringBuffer("p_").append(param.get("user_id").toString()).toString());

         List<Map<String,Object>> financePackageQuit = this.userAccountMapper.queryFinanceMMQuitOrder(pageVo);
        if (CollectionUtils.isNotEmpty(financePackageQuit)) {
            for (Map<String, Object> quit : financePackageQuit) {
                Date expectEndTime = DateUtils.toDate(quit.get("expect_end_time").toString(), DateEnum.DATE_SIMPLE);
                // 持有天数算头不算尾
                String ownedDays = null;
                BigDecimal interest = BigDecimal.ZERO;
                if (quit.get("quit_state") == null || Ints.tryParse(quit.get("quit_state").toString()).equals(0)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, new Date());
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(quit.get("joinMoney").toString()),
                            Ints.tryParse(quit.get("version_id").toString()),
                            Ints.tryParse(quit.get("issue").toString()), 0);
                } else if (Ints.tryParse(quit.get("quit_state").toString()).equals(1)) {
                    ownedDays = DateUtils.calculateBetweenDateOfDays(expectEndTime, DateUtils.plusDay(new Date(), 1));
                    interest = this.calInterest(expectEndTime,
                            new BigDecimal(quit.get("joinMoney").toString()),
                            Ints.tryParse(quit.get("version_id").toString()),
                            Ints.tryParse(quit.get("issue").toString()), 1);
                } else if (Ints.tryParse(quit.get("quit_state").toString()).equals(3)
                        || Ints.tryParse(quit.get("quit_state").toString()).equals(4)) {
                    ownedDays = quit.get("ownedDays").toString();
                    interest = new BigDecimal(quit.get("quit_interest").toString());
                }

                quit.put("quitInterest", interest);
                // 计算实时利率
                BigDecimal nowRate = BigDecimal.ZERO;
                int monthsBetween = DateUtils.monthsBetween(expectEndTime, new Date());
                int daysBetween = DateUtils.daysBetween(expectEndTime, new Date());
                int marginDays = DateUtils.daysAfterMonths(expectEndTime, monthsBetween);
                if (monthsBetween > 0) {
                    nowRate = this.versionRateManager.findValue(quit.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                if (daysBetween - marginDays > 0) {
                    monthsBetween = monthsBetween + 1 > Ints.tryParse(quit.get("issue").toString()) ? Ints
                            .tryParse(quit.get("issue").toString()) : monthsBetween + 1;// 2个月10天取3个月对应的利率
                    nowRate = this.versionRateManager.findValue(quit.get("version_id").toString() + ":"
                            + (monthsBetween));
                }
                // 解决当天为荷包进入锁定日期,实时利率为0的问题
                if (monthsBetween == 0 && (daysBetween - marginDays) <= 0) {
                    nowRate = this.versionRateManager.findValue(quit.get("version_id").toString() + ":"
                            + (monthsBetween + 1));
                }
                quit.put("now_rate", nowRate);
                quit.put("ownedDays", ownedDays);

                BigDecimal expectMoney = new MoneyCalculator(quit.get("joinMoney").toString())
                        .subtract(new BigDecimal(quit.get("quitCharge").toString())).add(interest).upToResult()
                        .getInstance();
                quit.put("expectMoney",expectMoney);
            }
        }
        return financePackageQuit;
    }

    @Page
    public Object searchFinanceExited(PageVo pageVo){
        Map<String,Object> param = pageVo.getParameters();
        param.put("userId",new StringBuffer("p_").append(param.get("user_id").toString()).toString());
        List<Map<String,Object>> list = this.userAccountMapper.searchFinanceExited(pageVo);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> quit : list) {
                String  package_id = quit.get("package_id").toString();
                String user_id = param.get("user_id").toString();
                Long pId = Long.valueOf(package_id);
                Integer uId =  Integer.valueOf(user_id);
                param.put("user_id",uId);
                param.put("package_id",pId);
                Map<String,Object> result = userAccountMapper.rewardRecord(pageVo);
                if(result!=null){
                    quit.put("expect_reward",result.get("reward").toString());
                }else{
                    quit.put("expect_reward","0");
                }
            }
        }
        return list;
    }

    public Map<String,Object> queryAssetRemainMoney(String bidId,String assetId){
        PageVo pageVo = new PageVo();
        Map<String,Object> map = new HashMap<>();
        map.put("bid_id",bidId);
        map.put("asset_id",assetId);
        pageVo.setParameters(map);
        Map<String,Object> result =  this.userAccountMapper.queryAssetRemainMoney(pageVo);
        return result;
    }

    /**
     * 计算奖励
     * @param dealMoney
     * @param rate
     * @param issue
     * @return
     */
    public BigDecimal calculateRewardInterest(String dealMoney,String rate,String issue){
        BigDecimal interest = BigDecimal.ZERO;
        //加入金额*荷包利率/12）*荷包总期数
        interest = new MoneyCalculator(dealMoney).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).divide(new BigDecimal(12)).
                multiply(new BigDecimal(issue)).downToResult().getInstance();
        return interest;
    }


    @Page
    public Object queryBidAlreayGathering(PageVo pageVo){
        return this.userAccountMapper.queryBidAlreayGathering(pageVo);
    }



}
