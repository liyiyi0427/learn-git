package com.hexin.exception;

/**
 * Service层公用的Exception. 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * Created by tiejiuzhou on 2017/3/20.
 */
public class BusinessException extends RuntimeException{

    /* 错误描述 */
    private String messgae;
    private Object[] args;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... args) {
        this(message);
        this.args = args;
    }

    public String getMessgae() {
        return messgae;
    }

    public Object[] getArgs() {
        return args;
    }
}
