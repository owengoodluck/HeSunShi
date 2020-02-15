package com.xseagull.boxing.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultData defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			return ResultData.error(ResultCode.NOT_FOUND);
        }  else if (e instanceof  org.springframework.web.HttpRequestMethodNotSupportedException) {
        	return ResultData.error(ResultCode.NOT_SUPPORTED);
        } else {
        	return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
        }
	}
}
