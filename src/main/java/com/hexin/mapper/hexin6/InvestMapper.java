package com.hexin.mapper.hexin6;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hexin.domain.hexin6.Invest;
import com.hexin.dto.InvestAssetDTO;
import com.hexin.dto.InvestReviewDTO;



public interface InvestMapper {
    int insert(Invest record);

    int insertSelective(Invest record);

    int updateInvest(@Param("expectInterest") BigDecimal expectInterest, @Param("actionSn") Integer actionSn);

    List<InvestReviewDTO> queryInvestListByBidId(Integer bidId);

    // add by yanshun
    List<InvestAssetDTO> getInvestsAndAsset(@Param("bidId") Integer bidId);

    /**
     * 为流标查询相关信息
     * 
     * @param bidId
     * @return
     */
    List<Map<String, Object>> queryInvestListByBidIdForStopBid(Integer bidId);

    /**
     * 查询一段时间内累计投资
     * 
     * @param maps
     * @return
     */
    BigDecimal queryInvestSumByUserIdTsTime(Map<String, Object> maps);

    /**
     * 查询一段时间内累计投资
     * 
     * @param maps
     * @return
     */
    BigDecimal getInvestSumByUserIdTsTime(Map<String, Object> maps);

    /**
     * 查询一段时间内累计投资(不包括代金券!!!)
     *
     * @param maps
     * @return
     */
    BigDecimal getInvestSumByUserIdTsTimeWithoutVoucher(Map<String, Object> maps);

    /**
     * 查询出借人投标数
     * 
     * @param maps
     * @return
     */
    List<Map<String, Object>> queryInvestCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    public Invest getInvestByActionSn(@Param("invest_id") int actionSn);

    /**
     * 根据用户查询投资和
     * 
     * @param maps
     * @return
     */
    BigDecimal getInvestSumByUserId(String userId);

    /**
     * 根据用户查询投资和
     * 
     * @return
     */
    BigDecimal getInvestSum(Map<String, Object> maps);

    /**
     * 查询某标大于上一次投资时间的投资记录
     * 
     * @author wangjiangtao
     * @param maps
     * @return
     */
    List<Map<String, String>> getInvestAfterLastTime(Map<String, Object> maps);

    public List<Invest> selectInvestListByBidId(int bidBid);

    /**
     * 查询截止到今日凌晨所有用户Invest投资额
     * 
     * @author wangjiangtao
     * @return
     */
    public Map<String, Object> getAllInvestMoney(@Param("endDate") Date endDate);

    /**
     * 查询昨日凌晨截止到今日凌晨所有用户Invest投资额
     * 
     * @author wangjiangtao
     * @param startDate
     * @param endDate
     * @return
     */
    public Map<String, Object> getLastDayInvestMoney(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    public Map<String, Object> getLastDayFinanceInvestMoney(@Param("startDate") Date startDate,
                                                            @Param("endDate") Date endDate);

    /**
     * 获取满足条件的投资信息
     * 
     * @author dongzhijie
     * @param maps
     * @return
     */
    List<Map<String, Object>> selectInvestForCollectMonkeySortMoneyListByBidId(int bidBid);

    List<Map<String, Object>> selectInvestForCollectMonkeySortTimeListByBidId(int bidBid);

    public List<Invest> sumInvestListByBidId(int bidBid);

    /**
     * 查询用户针对某一标的投资总合
     * 
     * @author wangjiangtao
     * @param bidId
     * @param userId
     * @return
     */
    public BigDecimal getAllInvestByUserIdAndBid(@Param("bidId") int bidId, @Param("userId") String userId);

    public Invest selectInvestByAssetId(int assetId);

    Integer selectInvestedUserCount(@Param("bidId") int bidId);

    Map<String, Object> selectInvestedUserCountAndMoney(@Param("bidId") int bidId);

    Map<String, Object> getAllBidDealMoney(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Map<String, Object> getAllFinanceDealMoney(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询一段时间内累计投资(包括代金券,不包含新手标和新手包)
     *
     * @param maps
     * @return
     */
    BigDecimal getInvestSumByUserIdToDay(Map<String, Object> maps);
}
