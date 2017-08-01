package com.hexin.mapper.hexin6;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hexin.domain.hexin6.BidDocument;
import com.hexin.dto.BidDto;
import com.hexin.model.PageVo;




/**
 * @author zhoumin@hexindai.com
 */
public interface BidDocumentMapper {

    /**
     * 插入基本信息
     * 
     * @author jiangtao
     * @param record
     * @return
     */
    int insert(BidDocument record);

    /**
     * 根据标ID查询基本信息
     * 
     * @author jiangtao
     * @param bidId
     * @return
     */
    BidDocument selectByPrimaryKey(Integer bidId);

    /**
     * 更新基本信息
     * 
     * @author jiangtao
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BidDocument record);

    /**
     * 更新投标相关的标信息
     * 
     * @param param
     * @return
     */
    int updateBidDocument(Map<String, BidDocument> param);

    int updateFullReviewStateForLendPay(BidDocument bidDocument);

    /**
     * 更新投标相关的标信息
     * 
     * @param param
     * @return
     */
    int updateBidDocumentByInvest(@Param("bidId") Integer bidId, @Param("dealMoney") BigDecimal dealMoney);

    /**
     * 删除准备中标的
     * 
     * @param param
     * @return
     */
    int updateBidDocumentByDel(Map<String, BidDocument> param);

    /**
     * 更新还款的相关信息
     * 
     * @author zhishuo
     * @param param
     * @return
     */
    int updateBidDocumentByRepay(Map<String, BidDocument> param);

    /**
     * 查询要流标的相关信息
     * 
     * @author jiangtao
     * @return
     */
    List<BidDocument> selectBidListByStopBid();

    /**
     * @param param
     * @return
     */
    int updateBidDocumentForRepayReward(Map<String, BidDocument> param);

    BidDocument selectByActionSn(int actionSn);

    /**
     * 获取理财包内 散标
     * 
     * @return
     */
    public List<Map<String, Object>> getPackageBid(@Param("param") List param);

    public List<Map<String, Object>> getPackageBidJob();

    /**
     * 获取理财包内标和债权
     * 
     * @return
     */
    public List<Map<String, Integer>> getAllPackageBid();

    /**
     * 获取所有有效信用标的（理财包）
     * 
     * @return
     */
    public List<BidDocument> selectValidPackageBid();

    /**
     * 获取理财包内标以及债权相关信息
     * 
     * @param packageId
     * @param fnAccountIds
     * @return
     */
    public List<Map<String, String>> getPackageBidAsset(@Param("packageId") long packageId,
                                                        @Param("fnAccountIds") List<Integer> fnAccountIds);

    /**
     * 获取散标时间最久的一个
     * 
     * @author yanshun
     * @return
     */
    public BidDocument getUnJoinedBidsForPackage();

    /**
     * @author yanshun 动态条件查询理财包项目标的列表
     * @param pageVo
     * @return
     */
//    public PageList<Map<String, Object>> getBidsForFnPackage(@Param("pageVo") PageVo pageVo, Pageable pageable);

    /**
     * @author yanshun
     * @param pageVo
     * @return
     */
    BidDocument selectByBidId(@Param("pageVo") PageVo pageVo);

    /**
     * @author renjingzhi
     * @param pageVo
     * @return
     */
    List<BidDocument> selectByUserId(@Param("userId") String userId);

    /**
     * 查询复投对接标的
     * 
     * @author yanshun
     * @param reinvestLogId
     * @return
     */
    List<BidDocument> selectBidsWaitingForReinvestByLogId(@Param("reinvestLogId") Integer reinvestLogId);

    /**
     * @author yanshun
     * @param bidDto
     * @return
     */
    int updateBidWithDto(@Param("bidDto") BidDto bidDto);

    /**
     * 
     */
    List<Map<String, String>> getUserWithRechargeAmountAndCardInfo();

    /**
     * 根据时间区间获取前一天满标复审成功的bid
     * 
     * @author tiejiuzhou
     * 
     * @param dateStart
     * @param dateEnd
     * @return
     */
    List<BidDocument> getAudit2OkBidDocumentsWithTime();

    /**
     * @param userIdStr
     * @return
     */
    int getValidBidocumentByUserId(@Param("user_id") String userIdStr);

    List<BidDocument> getValidBidocumentByUserIdForCash(@Param("user_id") String userIdStr);

    /**
     * 获取线下待放款列表
     * 
     * @author renjingzhi@hexindai.com
     * @return
     */
    List<Map<String, Object>> listPendingLoan(Map params);

    /**
     * 获取线下待放款列表数量
     * 
     * @author renjingzhi@hexindai.com
     * @return
     */
    Integer countPendingLoan(Map params);

    BidDocument selectBidByLoanCode(@Param("loan_code") String loanCode);

    BidDocument getLastValidLoanRecordByUserId(@Param("user_id") String userId);

    List<BidDocument> getBidInfoForNetEye(@Param("sendTime") String sendTime);

    int updateBidIsRepayYes(List<Integer> bidIds);

    int updateBidIsRepayNo(List<Integer> bidIds);

    List<BidDocument> getBidDocumentForIsRepay();

    BigDecimal getCanWithdrawalAmount(@Param("user_id") String userId);

    List<BidDocument> getBidWithSpRepayAuditing();

    int updateIsWithDrawServiceFeeToSuccess(@Param("param") Map<String, Object> param);

    int updateIsWithDrawServiceFeeToWaitDraw(@Param("loanCode") String loanCode);

    List<Integer> superAccountBid();

    List<Map<String, String>> getPackageQuitBidAsset(@Param("financeBackendIds") List<Integer> appendFinanceAccountIds);

    List<BidDocument> getBidByList(@Param("list") List<Integer> bidList);

    List<BidDocument> getPendingBid();

    BidDocument searchBidByAuditingCode(@Param("auditing_code") String auditingCode);
}
