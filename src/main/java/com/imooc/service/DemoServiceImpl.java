package com.imooc.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

	@Override
	public void gretting(String name) {
		System.out.println("hello "+name);

	}

}
