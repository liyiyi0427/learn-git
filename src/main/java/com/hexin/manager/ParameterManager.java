/**
 * Copyright(c) 2011-2015 by HeXin Inc.
 * All Rights Reserved
 */
package com.hexin.manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hexin.domain.hexin6.Parameter;
import com.hexin.mapper.hexin6.ParameterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @author yanshun
 */
@Component
@Transactional( readOnly = true)
public class ParameterManager {
    private static Logger logger = LoggerFactory.getLogger(ParameterManager.class);
    @Autowired
    private ParameterMapper parameterDao;

    // 设置缓存最大个数为100，缓存过期时间为10分
    LoadingCache<String, Parameter> parameterCache = CacheBuilder.newBuilder().maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES).build(new CacheLoader<String, Parameter>() {
                @Override
                public Parameter load(String key) throws Exception {
                    logger.info("fetch from database");
                    return ParameterManager.this.parameterDao.selectByPrimaryKey(key);
                }
            });

    /*
     * @author yanshun
     * 
     * 查询Parameter
     */
    public Parameter findValue(String value) {
        try {
            return this.parameterCache.get(value);
        } catch (ExecutionException e) {
            logger.error("key:{}", value, e);
        }
        return null;
    }

    public List<Parameter> findAllValue() {

        return this.parameterDao.selectAll();

    }

    /**
     * 获取提现所需要的常量参数
     * 
     * @author tiejiuzhou
     * @return
     */
    public Map<String, String> findValueForCash() {
        return this.parameterDao.findValueForCash();
    }

    // 添加Parameter
    @Transactional( readOnly = false)
    public boolean modValue(Parameter parameter) {
        boolean tag = false;
        // 查询要修改记录是否已存在
        Parameter target = this.parameterDao.selectByPrimaryKey(parameter.getName());
        if (target != null) {
            this.parameterDao.updateByPrimaryKeySelective(parameter);
            // 刷新缓存
            this.parameterCache.refresh(parameter.getName());
            tag = true;
        }
        return tag;
    }

    /**
     * 获取vip等级范围
     * 
     * @author dongzhijie
     * @return
     */
    public Map<Integer, Map<String, Long>> findValueForVip(int valueId) {
        List<Parameter> parameterList = this.parameterDao.findValueForVip(valueId);
        Map<Integer, Map<String, Long>> map = new HashMap<Integer, Map<String, Long>>();
        for (Parameter parameter : parameterList) {
            String name = parameter.getName();
            String[] names = name.split("_");
            int vipLevel = Integer.valueOf(names[0].substring(3));
            String type = names[1];
            if (map.containsKey(vipLevel)) {
                map.get(vipLevel).put(type, Long.valueOf(parameter.getValue()));
            } else {
                Map<String, Long> map1 = new HashMap<String, Long>();
                map1.put(type, Long.valueOf(parameter.getValue()));
                map.put(vipLevel, map1);
            }
        }
        return map;
    }

    /**
     * 从库里查询parameter 非缓存
     * 
     * @author wangjiangtao
     * @param name
     * @return
     */
    public Parameter getParameter(String name) {
        return this.parameterDao.selectByPrimaryKey(name);
    }
}
