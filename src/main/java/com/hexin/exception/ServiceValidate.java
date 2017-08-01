package com.hexin.exception;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 *  所有的错误异常统一封装为 BusinessException
 * Created by tiejiuzhou on 2017/3/21.
 */
public class ServiceValidate {
    private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "服务器参数校验异常--判断条件是false";
    private static final String DEFAULT_IS_NULL_EX_MESSAGE = "服务器参数校验异常--对象为null";
    private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "服务器参数校验异常--数组为空";
    private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "服务器参数校验异常--集合为空";
    private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "服务器参数校验异常--参数为空";
    private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "服务器参数校验异常--参数为空";

    /**
     * Constructor. This class should not normally be instantiated.
     */
    public ServiceValidate() {
        super();
    }

    public static void isTrue(boolean expression,String message){
        if (expression == false) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean expression, String message, Object... args){
        if (expression == false) {
            throw new BusinessException(message, args);
        }
    }

    public static void isTrue(boolean expression) {
        if (expression == false) {
            throw new BusinessException(DEFAULT_IS_TRUE_EX_MESSAGE);
        }
    }

    public static <T> T notNull(T object) {
        return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
    }

    public static <T> T notNull(T object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
        return object;
    }

    public static <T> T[] notEmpty(T[] array, String message) {
        if (array == null) {
            throw new BusinessException(message);
        }
        if (array.length == 0) {
            throw new BusinessException(message);
        }
        return array;
    }

    public static <T> T[] notEmpty(T[] array) {
        return notEmpty(array, DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE);
    }

    public static <T extends Collection<?>> T notEmpty(T collection, String message) {
        if (collection == null) {
            throw new BusinessException(message);
        }
        if (collection.isEmpty()) {
            throw new BusinessException(message);
        }
        return collection;
    }

    public static <T extends Collection<?>> T notEmpty(T collection) {
        return notEmpty(collection, DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE);
    }

    public static <T extends Map<?, ?>> T notEmpty(T map, String message) {
        if (map == null) {
            throw new BusinessException(message);
        }
        if (map.isEmpty()) {
            throw new BusinessException(message);
        }
        return map;
    }

    public static <T extends Map<?, ?>> T notEmpty(T map) {
        return notEmpty(map, DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE);
    }

    public static <T extends CharSequence> T notEmpty(T chars, String message) {
        if (chars == null) {
            throw new BusinessException(message);
        }
        if (chars.length() == 0) {
            throw new BusinessException(message);
        }
        return chars;
    }

    public static <T extends CharSequence> T notBlank(T chars, String message) {
        if (chars == null) {
            throw new BusinessException(message);
        }
        if (StringUtils.isBlank(chars)) {
            throw new BusinessException(message);
        }
        return chars;
    }

    public static <T extends CharSequence> T notBlank(T chars, String message, Object... args) {
        if (chars == null) {
            throw new BusinessException(message, args);
        }
        if (StringUtils.isBlank(chars)) {
            throw new BusinessException(message, args);
        }
        return chars;
    }

    public static <T extends CharSequence> T notBlank(T chars) {
        return notBlank(chars, DEFAULT_NOT_BLANK_EX_MESSAGE);
    }
}
