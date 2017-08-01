package com.hexin.mapper.hexin6;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hexin.domain.hexin6.Repayplan;
import com.hexin.domain.hexin6.RepayplanKey;
import com.hexin.model.PageVo;




public interface RepayplanMapper {

    /**
     * 插入
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int insert(Repayplan record);

    /**
     * 根据主键查询
     * 
     * @author zhishuo
     * @param key
     * @return
     */
    Repayplan selectByPrimaryKey(RepayplanKey key);

    /**
     * 根据主键更新
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKey(Repayplan record);

    /**
     * 查询当天还款信息 根据C++视图而来
     * 
     * @author zhishuo
     * @param parameter
     * @return
     */
    Map<String, Object> selectRepayPlanDetail(Map<String, String> parameter);

    /**
     * 根据参数属性查询还款计划
     * 
     * @author zhishuo
     * @param parameter
     * @return
     */
    List<Repayplan> selectRepayPlan(Map<String, Object> parameter);

    /**
     * 查询大于某期的所有还款计划
     * 
     * @param parameter
     * @return
     * @author liuzhishuo@hexindai.com
     */
    List<Repayplan> selectRepayPlanAll(Map<String, String> parameter);

    /**
     * 更新还款状态
     * 
     * @author zhishuo
     * @param plan
     * @return
     */
    int updateStateByPrimaryKey(Repayplan plan);

    /**
     * 获取需要自动发放投资奖励的记录
     * 
     * @author tiejiuzhou
     * @return
     */
    List<Map<String, Object>> getAutoInvestRewardRecord();

    Repayplan getRepayPlanByBidIdAndIssue(@Param("bidId") int bidId, @Param("issue") int issue);

    int updateRepayStateByPrimaryKey(Repayplan plan);

    /**
     * 理标记财包id，条件（RepayPlan表中所有当日待还款&已还&标记复投）
     * 
     * @author yanshun
     * @return
     */
    public List<Repayplan> getRepayplansForReinvestWork();

    public List<Repayplan> getRepayplansByPackageIdForSysWork(Map<String, Object> param);

    /**
     * 动态更新
     * 
     * @author zhishuo
     * @param plan
     * @return
     */
    int updateByPrimaryKeySelective(Repayplan plan);

    /**
     * 查询理财包内待还款列表
     * 
     * @author zhishuo
     * @param pageVo
     * @param pageable
     * @return
     */
//    PageList getFinanceRepayList(@Param("pageVo") PageVo pageVo, Pageable pageable);

    /**
     * 理财包待还款统计
     * 
     * @author zhishuo
     * @param pageVo
     * @param pageable
     * @return
     */
    BigDecimal getFinanceRepayListCount(@Param("pageVo") PageVo pageVo, Pageable pageable);

    /**
     * @author zhishuo
     * @return
     */
    List<Repayplan> getPackageUnRepay();

    /**
     * 返回已经标记逾期次数 连续的
     * 
     * @author wangjiangtao
     * @param bidId
     * @param issue
     * @return
     */
    int statAlreadyDueIssue(@Param("bidId") int bidId, @Param("issue") int issue);

    /**
     * 更新回购还款状态
     * 
     * @author wangjiangtao
     * @param plan
     * @return
     */
    int updateRepurchaseState(Repayplan plan);

    /**
     * 统计距离当期还款日天数 类型 本息
     * 
     * @author wangjiangtao
     * @param bidId
     * @param issue
     * @return
     */
    Integer statDaysToRepayDay(@Param("bidId") int bidId, @Param("issue") int issue);

    /**
     * 
     * 查询所有标的待还款（无理财包无奖励）
     * 
     * @author wangjiangtao
     * @return
     */
    Map<String, Object> getAllBidRepay();

    /**
     * 查询所有理财包待还款
     * 
     * @author wangjiangtao
     * @return
     */
    Map<String, Object> getAllPackageRepay();

    /**
     * @param pageVo
     * @return
     */
    Map<String, Object> getTotalWaitingRepay(@Param("pageVo") PageVo pageVo);

    /**
     * 查询当日待还款（无奖励）
     * 
     * @return
     */
    List<Map<String, Object>> searchNowWaitRepayment(@Param("is_package") Integer is_package,
                                                     @Param("repayDay") String repayDay);

    List<Map<String, Object>> getRepurchaseListForPackage(@Param("repayDay") String repayDay);

    Repayplan unRepayFirstIssue(Integer bidId);

    Date getLastedRepayDate(Integer bidId);

    Integer getCountBetween2Day(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                @Param("bidId") int bidId);

    List<Map<String, Object>> getAllDebtBackAccountBalance(@Param("startDate") String startDate,
                                                           @Param("endDate") String endDate);

    Map<String, BigDecimal> queryOverdueRepayAmountByUserId(@Param("userId") String userId);

    List<Map<String, Object>> queryOverdueListByUserId(@Param("userId") String userId);

    List<Map<String, Object>> queryAheadRepayListByUserId(@Param("userId") String userId);

    Map<String, BigDecimal> queryUnRepayAmountByUserId(@Param("userId") String userId);

    List<Map<String, Object>> getRepayListForRepair();

    List<Map<String, Object>> getRepayListForRepairAll();

}
