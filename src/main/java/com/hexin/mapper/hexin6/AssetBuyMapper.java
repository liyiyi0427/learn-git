package com.hexin.mapper.hexin6;

import java.math.BigDecimal;
import java.util.Map;

import com.hexin.domain.hexin6.AssetBuy;


public interface AssetBuyMapper {

    /**
     * 插入
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int insert(AssetBuy record);

    /**
     * 根据主键查询
     * 
     * @author zhishuo
     * @param actionSn
     * @return
     */
    AssetBuy selectByPrimaryKey(Integer actionSn);

    /**
     * 根据主键查询
     * 
     * @author renjingzhi
     * @param actionSn
     * @return
     */
    AssetBuy selectByAssetId(Integer assetId);

    /**
     * 根据主键动态更新
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AssetBuy record);

    /**
     * 根据主键更新全部
     * 
     * @author zhishuo
     * @param record
     * @return
     */
    int updateByPrimaryKey(AssetBuy record);

    /**
     * 查询一段时间内累计购买债权和
     * 
     * @param maps
     * @return
     */
    BigDecimal queryAssetBuySumByUserIdTsTime(Map<String, Object> maps);
}
