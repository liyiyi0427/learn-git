package com.hexin.mapper.hexin6;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hexin.domain.hexin6.Asset;
import com.hexin.model.PageVo;



public interface AssetMapper {

    /**
     * 插入
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int insert(Asset record);

    /**
     * 根据主键查询
     * 
     * @author zhishuo
     * @param assetId
     * @return
     */
    Asset selectByPrimaryKey(Integer assetId);

    /**
     * 根据标ID查询
     * 
     * @author zhishuo
     * @param assetId
     * @return
     */
    List<Asset> selectByBid(Integer bidId);

    List<Asset> selectEffectAssetByBid(Integer bidId);

    /**
     * 动态更新，传入对象，有值就会更新，没值就不会更新，where条件是id
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Asset record);

    /**
     * 根据主键更新，会set对象所有值
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKey(Asset record);

    /**
     * 根据资产ID与状态停止售卖
     * 
     * @param record
     * @return
     */
    int stopAssetSale(Asset record);

    /**
     * 根据invest_id 和 state 查询 asset
     * 
     * @param state
     */
    int selectByAssetIAndState(@Param("invest_id") Integer investId, @Param("state") int state);

    /**
     * 根据原值更新
     * 
     * @param param
     * @return
     * @author liuzhishuo@hexindai.com
     */
    int updateAssetByOld(Map<String, Asset> param);

    /**
     * 根据用户id，查询投资次数
     */
    int selectByUserId(String userId);

    /*
     * 根据userId获取待收资产
     * 
     * @param userId
     * 
     * @return
     */
    Map<String, Object> fetchIncomeAsset(String userId);

    /*
     * 根据userId、bidId、Sn获取资产
     * 
     * @param userId
     * 
     * @param bidId
     * 
     * @param actionSn
     * 
     * @return
     */
    Asset getAssetByBidIdUserIdInvestId(@Param("userId") String userId, @Param("bidId") int bidId,
                                        @Param("investId") int investId);

    List<Integer> queryPackageAsset(@Param("packageId") int packageId);

    /**
     * 获取理财包下用户持有的未转让资产
     * 
     * @author yanshun
     * @return
     */
    List<Asset> getAssetByUserIdPackageId(@Param("userId") String userId, @Param("packageId") int packageId);

    /**
     * 获取超级用户债权
     *
     * @author zhishuo
     * @param pageVo
     * @param pageable
     * @return
     */
//    PageList getSuperAsset(@Param("pageVo") PageVo pageVo, Pageable pageable);

    List<Map<String, Object>> getUnTransferAssetsOfPackage(@Param("packageId") int packageId);

    /**
     * 获取理财包内，正在转让中的资产
     * 
     * @author zhishuo
     * @return
     */
    List<Asset> getInTheTransferAsset();

    /**
     * @param packageId
     * @param fnAccountIds
     * @return
     */
    int getUnsaledAssetsOfPackage(@Param("packageId") int packageId, @Param("fnAccountIds") List<Integer> fnAccountIds);

    /**
     * 根据后台帐户查询 资产
     * 
     * @author zhishuo
     * @param id
     * @return
     */
    List<Asset> getAssetByBackendId(@Param("id") int id, @Param("bid") int bid);

    /**
     * @author zhishuo
     * @param bid
     * @param packageId
     * @return
     */
    List<Asset> getAssetByBidAndPackageId(@Param("bid") int bid, @Param("package_id") int packageId);

    int getAssetOnSaleCount();

    String whetherUserHasAsset(@Param("userId") String userId, @Param("today") String today);

    /**
     * @param userId
     */
    Map<String, Object> getTotalWatingGarthing(@Param("userId") String userId);

    List<Asset> selectByBidGroupByUser(Integer bidId);

    public BigDecimal getAllInvestByUserIdAndBidId(@Param("userId") String userId, @Param("bidId") int bidId,
                                                   @Param("assetId") int assetId);

    public BigDecimal getAllAssetBuyByUserIdAndBidId(@Param("userId") String userId, @Param("bidId") int bidId,
                                                     @Param("assetId") int assetId);

    public int getUnFinishCountByBidAndUserId(@Param("userId") String userId, @Param("bidId") int bidId);

    public List<String> getAllUserIdByBidId(@Param("bidId") int bidId);

    /**
     * @param packageId
     * @return
     */
    List<Map<String, Object>> isHaveAsset(int packageId);

    /**
     * @param investIds
     * @return
     */
    List<Map<String, Object>> searchAssign(@Param("investIds") String investIds);

    /**
     * 是否是新手用户
     * 
     * @param userid
     * @return
     *
     * @author renjingzhi@hexindai.com
     */
    public boolean isNewer(@Param("userid") String userid);

    List<Asset> getAssetByFinanceAccountIdAndState(@Param("financeAccountId") Integer financeAccountId);

    List<Asset> getBidByFinanceCompletTime();

    List<Asset> getAssetByBidId(Integer bidId);

    List<Asset> selectEffectByBid(Integer bidId);

    /**
     * 查询用户未完成的债权
     * 
     * @author renjingzhi@hexindai.com
     * @param userIdNum
     * @return
     */
    int getAssetCount(@Param("userIdNum") int userIdNum);

    /**
     * 查询当前理财包下未转让的债权
     * 
     * @param packageId
     * @return
     */
    List<Asset> getUnTransferAssetsByPackageId(int packageId);

    List<Asset> selectEffectByUpdateTime(@Param("update_time") Date updateTime);

    int batchUpdate(@Param("list") List<Asset> list);

    int batchUpdateToEnd(@Param("list") List<Asset> list);

    List<Asset> selectByAssetIds(@Param("list") Set<String> list);

    Map<String, Object> getTodayLoanAmountByUserId(@Param("userId") String userId);

    List<Asset> getOwnedAssetsByUserId(@Param("userId") String userId);

    List<Map<String, Object>> getFullAssetByFinanceAccountIdAndState(@Param("pageVo") PageVo pageVo, Pageable pageable);

    List<Map<String, Object>> getEveryAssetDealMoney(@Param("financeAccountId") int financeAccountId);

    List<Asset> getFinishedTransferAssets(@Param("financeAccountIds") List<Integer> appendFinanceAccountIds);

    List<Map<String, Object>> getUnTransferAssetsByDate(@Param("completeTime") Date completeTime);

    List<Map<String, Object>> getAssetByBidAndUserId(@Param("bidId") Integer bidId);

    List<Asset> getValidAssetByBid(@Param("bidId") Integer bidId);

    BigDecimal getTotalVaildQuota(@Param("bidId") Integer bidId);
}
