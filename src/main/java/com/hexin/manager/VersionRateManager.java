/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.hexin.domain.hexin6.Version;
import com.hexin.domain.hexin6.VersionRate;
import com.hexin.mapper.hexin6.VersionMapper;
import com.hexin.mapper.hexin6.VersionRateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 
 *
 * @author panmeng@hexindai.com
 */
@Component
@Transactional(readOnly = true)
public class VersionRateManager {
    private static Logger logger = LoggerFactory.getLogger(VersionRateManager.class);
    @Autowired
    private VersionRateMapper versionRateDao;

    @Autowired
    private VersionMapper versionDao;
    // 设置缓存最大个数为100，缓存过期时间为10分
    LoadingCache<String, BigDecimal> versionRateCache = CacheBuilder.newBuilder().maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES).build(new CacheLoader<String, BigDecimal>() {
                @Override
                public BigDecimal load(String key) throws Exception {
                    String[] split = key.split(":");
                    return VersionRateManager.this.versionRateDao.getRateByVersionIdAndMonth(
                            Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                }
            });

    /*
     * @author panmeng
     * 
     * 查询Parameter
     */
    public BigDecimal findValue(String value) {
        try {
            return this.versionRateCache.get(value);
        } catch (Exception e) {
            logger.error("key:{}", value, e);
        }
        return null;
    }

    /**
     * 新增利率梯度表
     * 
     * @param rateArray
     * @param versionName
     * @param packageType
     * @param userName
     * @param userId
     */
    @Transactional( readOnly = false)
    public void handleVersionAndInsertVersions(String[] rateArray, byte packageType, String versionName, String userId,
            String userName) {
        List<VersionRate> versionRates = Lists.newArrayList();
        Integer versionId = this.handleVersion(packageType, versionName, userId, userName);
        // 1#8,2#9,3#9,4#10,5#11,6#12,7#12,8#13,9#13,10#13,11#13,12#14,18#15,24#16,36#17
        for (String rate : rateArray) {
            String[] monthMappingRate = rate.split("@");
            VersionRate vr = new VersionRate();
            vr.setMonth((byte) (Ints.tryParse(monthMappingRate[0]).intValue()));// 对应利息
            vr.setRate(new BigDecimal(monthMappingRate[1]));// 对应利率
            vr.setVersionId(versionId);
            versionRates.add(vr);
        }
        this.versionRateDao.batchInsertVersionRate(versionRates);
    }

    /**
     * 停用老版本 新增新版本
     * 
     * @param versionName
     * @param packageType
     * @param userName
     * @param userId
     * 
     * @return
     */
    @Transactional( readOnly = false)
    private Integer handleVersion(byte packageType, String versionName, String userId, String userName) {
        Version oldVersion = this.versionDao.getLastVersion(packageType);
        int count = 0;
        if (oldVersion != null) {
            oldVersion.setState((byte) 0);// 停用
            count = this.versionDao.updateByPrimaryKeySelective(oldVersion);
//            ServiceValidate.isTrue(count == 1, ErrorCode.SERVER_EXCEPTION);
        }
        Version version = new Version();
        version.setName(versionName);
        version.setPackageType(packageType);
        version.setCreateTime(new Date());
        version.setType((byte) (oldVersion == null ? 0 : oldVersion.getType() + 1));
        version.setState((byte) 1);
        StringBuilder builder = new StringBuilder("p_");
        builder.append(userId);
        version.setUserId(builder.toString());
        version.setUserName(userName);
        count = this.versionDao.insertSelective(version);
//        ServiceValidate.isTrue(count == 1, ErrorCode.SERVER_EXCEPTION);
        Version newVersion = this.versionDao.getLastVersion(packageType);
        return newVersion.getId();
    }

    /**
     * 梯度列表
     * 
     * @return
     * @author panmeng
     * @param pageVo
     */
//    @Transactional( readOnly = true)
//    public List<Version> getVersionList(PageVo pageVo) {
//        return this.versionDao.getAllVersions(pageVo, pageVo.getPageable());
//    }

    /**
     * 主键查找
     * 
     * @param versionId
     * @return
     * @author panmeng
     */
    @Transactional(readOnly = true)
    public Version selectByPrimaryKey(Integer versionId) {
        return this.versionDao.selectByPrimaryKey(versionId);
    }

    /**
     * 通过版本号获取梯度利率
     * 
     * @param versionId
     * @return
     * @author panmeng
     */
    public List<VersionRate> getVersionRatesByVersionId(Integer versionId) {
        return this.versionRateDao.getVersionRatesByVersionId(versionId);
    }
}
