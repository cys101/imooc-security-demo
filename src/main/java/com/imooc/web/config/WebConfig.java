package com.imooc.web.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.filter.TimeFilter;
import com.imooc.interceptor.TimeInterceptor;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	/*@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Bean
	public FilterRegistrationBean timeFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);
        List<String> list=new ArrayList<String>();
        list.add("/*");
        filterRegistrationBean.setUrlPatterns(list);
		return filterRegistrationBean;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}*/
	
}
