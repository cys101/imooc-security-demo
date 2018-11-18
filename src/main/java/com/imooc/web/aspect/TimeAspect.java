package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*@Aspect
@Component*/
public class TimeAspect {
	
	
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("time aspect start");
		Object[] args = pjp.getArgs();
		for(Object arg:args){
			System.out.println(arg);
		}
		Long start=new Date().getTime();
		Object proceed = pjp.proceed();
		System.out.println("time aspect end");
		System.out.println("time aspect paste: "+(new Date().getTime()-start));
		return null;
	}

}
