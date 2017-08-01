package com.hexin.service;

import com.hexin.annotation.Page;
import com.hexin.manager.FullSubCouponManager;
import com.hexin.model.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class CouponHandlerService {

    @Autowired
    private FullSubCouponManager fullSubCouponManager;
    /**
     * 前台查看使用代金券情况
     */
    public Map<String, Object> searchCouponUseInfo(PageVo pageVo) {
        Map<String,Object> map = pageVo.getParameters();
        map.put("user_id",new StringBuffer("p_").append(map.get("user_id").toString()).toString());
        Map<String,Object> result = this.fullSubCouponManager.searchCouponUseInfo(pageVo);
        return result;
    }

    @Page
    public Object selectCouponRecods(PageVo pageVo) {
        Map<String,Object> map = pageVo.getParameters();
        map.put("user_id",new StringBuffer("p_").append(map.get("user_id").toString()).toString());
        return this.fullSubCouponManager.selectCouponRecods(pageVo);
    }
}
