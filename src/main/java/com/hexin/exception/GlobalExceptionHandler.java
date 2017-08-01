package com.hexin.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hexin.common.ReturnInfo;
import com.hexin.enums.ReturnState;


/**
 * Created by tiejiuzhou on 2017/3/20.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(value = Exception.class)
    public ReturnInfo jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception{
    	logger.info("服务器异常{}",e.getMessage(),e);
    	if(e instanceof BusinessException){
	        ReturnInfo returnInfo = new ReturnInfo();
	        returnInfo.setMessage(e.getMessage());
	        returnInfo.setStatus(ReturnState.FAILED);
	        returnInfo.setReturnData("something went wrong");
	        returnInfo.setUrl(request.getRequestURL().toString());
	        return returnInfo;
    	}
    	return new ReturnInfo(ReturnState.FAILED,"error","服务器异常");
    }
}
