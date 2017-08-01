package com.hexin.aspect;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.hexin.common.ReturnInfo;
import com.hexin.enums.ReturnState;
import com.hexin.model.PageVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口请求参数处理切面
 *
 * @author zzn
 * @create 2017-04-14 17:03
 **/
@Aspect
@Component
@Order(1)
public class PageAspect {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.hexin.annotation.Page)")
    public void page(){}

    @Around("page()")
    public Object processPage(ProceedingJoinPoint jp) throws java.lang.Throwable {

        // 获取目标方法原始的调用参数
        Object[] args = jp.getArgs();
        if(args != null && args.length > 0 && args[0] instanceof PageVo) {
            // 修改目标方法的第一个参数
            PageVo pageVo =  (PageVo)args[0];
            logger.info("当前页为：{},每页{}条数据",pageVo.getPageNum(),pageVo.getPageSize());
            logger.info("查询条件为：{}",pageVo.getParameters());
            PageHelper.startPage(Integer.parseInt(pageVo.getPageNum()),Integer.parseInt(pageVo.getPageSize()));
        }
        // 以改变后的参数去执行目标方法，并保存目标方法执行后的返回值
        Object result = jp.proceed(args);
        // 如果result的类型是List,并且参数类型为pageVo，将result初始化到分页中
        if(result != null && result instanceof List && args[0] instanceof PageVo) {
            ArrayList resultList = (ArrayList) result;
            PageInfo<Object> pageInfo = new PageInfo<Object>(resultList);
            //将pageInfo中多余的参数去除掉
            ReturnInfo info  = new ReturnInfo();
            info.setStatus(ReturnState.SUCCESS);
            info.setMessage("");
            Map<String,Object> page = Maps.newHashMap();
            page.put("totalCount",pageInfo.getTotal());
            page.put("pageSize",pageInfo.getPageSize());
            page.put("currentPage",pageInfo.getPageNum());
            page.put("totalPage",pageInfo.getPages());
            info.setReturnData(pageInfo.getList());
            info.setPageInfo(page);
            return info;
        }
        return result;
    }
}
