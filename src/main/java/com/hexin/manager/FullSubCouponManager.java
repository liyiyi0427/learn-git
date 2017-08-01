package com.hexin.manager;

import com.hexin.mapper.hexin6.CouponUserMapper;
import com.hexin.model.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/27.
 */
@Component
@Transactional( readOnly = true)
public class FullSubCouponManager {

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Transactional(readOnly = true)
    public Map<String,Object> searchCouponUseInfo(PageVo  pageVo) {
        Map<String,Object> map = this.couponUserMapper.searchCouponUseInfo(pageVo);
        return map;
    }

    @Transactional( readOnly = true)
    public List<Map<String,Object>> selectCouponRecods(PageVo pageVo) {
        return this.couponUserMapper.selectCouponrRecods(pageVo);
    }
}
