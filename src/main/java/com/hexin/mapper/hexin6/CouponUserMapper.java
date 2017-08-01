package com.hexin.mapper.hexin6;

import com.hexin.model.PageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/27.
 */
public interface CouponUserMapper {

    Map<String,Object> searchCouponUseInfo(@Param("pageVo") PageVo pageVo);

    List<Map<String,Object>> selectCouponrRecods(@Param("pageVo")PageVo pageVo);

}
