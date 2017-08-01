package com.hexin.mapper.hexin6;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hexin.domain.hexin6.AssetSale;


public interface AssetSaleMapper {

    /**
     * 插入
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int insert(AssetSale record);

    /**
     * 根据主键查询
     * 
     * @author zhishuo
     * @param actionSn
     * @return
     */
    AssetSale selectByPrimaryKey(Integer actionSn);

    /**
     * 根据主键动态更新
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AssetSale record);

    /**
     * 根据原值更新
     * 
     * @param param
     * @return
     * @author liuzhishuo@hexindai.com
     */
    int updateByOldValue(Map<String, AssetSale> param);

    /**
     * 根据主键更新全部
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKey(AssetSale record);

    /**
     * 根据ID查询
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    AssetSale selectByActionAndInvalid(Integer record);

    List<AssetSale> fetchAssetSaling(Map<String, Date> maps);

    /**
     * 根据UserId获取转让中的资产
     * 
     * @param userId
     * @return
     */
    Map<String, Object> fetchAssetSale(String userId);

    /*
     * 查询属于理财包的，正在转让的债权
     * 
     * @return
     */
    List<Map<String, String>> selectAssetSaling();

    /**
     * 获取未被锁定的理财包债权转让
     * 
     * @return
     */
    List<Map<String, Integer>> getPackageUnLockSaling();

    /**
     * 查询成交转让交易总金额
     * 
     * @author wangjiangtao
     * @param endDate
     * @return
     */
    Map<String, Object> getAllAssetSaleMoney(@Param("endDate") Date endDate);

    Map<String, Object> getNewAssetSaleMoney(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询昨日成交转让交易总金额
     * 
     * @author wangjiangtao
     * @param startDate
     * @param endDate
     * @return
     */
    Map<String, Object> getLastDaySaleMoney(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
