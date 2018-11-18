package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.DemoService;

public class MyConstraintValidator implements ConstraintValidator<Myconstraint, Object> {
	@Autowired
	public DemoService demoService;

	@Override
	public void initialize(Myconstraint constraintAnnotation) {
		System.out.println("my ConstraintValidator init");
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println(value);
		demoService.gretting((String)value);
		return false;
	}

}
