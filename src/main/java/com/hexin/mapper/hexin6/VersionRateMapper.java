package com.hexin.mapper.hexin6;

import com.hexin.domain.hexin6.VersionRate;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


public interface VersionRateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VersionRate record);

    int insertSelective(VersionRate record);

    VersionRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VersionRate record);

    int updateByPrimaryKey(VersionRate record);

    /**
     * 根据版本号查询当前梯度规则信息
     * 
     * @param versionId
     * @return
     */
    BigDecimal getRateByVersionIdAndMonth(@Param("versionId") Integer versionId, @Param("month") Integer month);

    /**
     * 批量新增梯度利率
     *
     * @param versionRates
     */
    void batchInsertVersionRate(@Param("list") List<VersionRate> versionRates);

    /**
     * 版本号获取梯度利率
     * 
     * @param versionId
     * @return
     * @author panmeng
     */
    List<VersionRate> getVersionRatesByVersionId(@Param("versionId") Integer versionId);
}
