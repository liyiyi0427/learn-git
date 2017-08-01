package com.hexin.annotation;

import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {
    /**
     * 用于绑定的请求参数名字
     */
    String value() default "";
}
