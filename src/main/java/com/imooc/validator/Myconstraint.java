package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MyConstraintValidator.class)
public @interface Myconstraint {
	
	String message();

	Class<?>[] groups() default { };  //分组校验,不同的controller针对同一个字段可以有不同的校验方式

	Class<? extends Payload>[] payload() default { };

}
