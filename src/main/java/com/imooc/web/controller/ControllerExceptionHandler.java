package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;








import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.exception.UserNotExistException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", ex.getId());
		map.put("message", ex.getMessage());
		return map;
	}

}
