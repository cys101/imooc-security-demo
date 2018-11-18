package com.imooc.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetialView;
import com.imooc.dto.User.UserSimpleView;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;
import com.imooc.validator.Insert;
import com.imooc.validator.Update;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils; 
	
	@GetMapping
	@JsonView(UserSimpleView.class)
	public List<User> query(UserQueryCondition condition,Pageable pageable){
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		List<User> list = new ArrayList<User>();
		list.add(new User("Tom","123","1"));
		list.add(new User("dam","123","2"));
		list.add(new User("try","123","4"));
		return list;
	}
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetialView.class)
	@ApiOperation(value="用户查询服务")
	public User getInfo(@ApiParam(value="用户ID") @PathVariable("id")String id){
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		/*if(true){
			throw new RuntimeException("user not exist");
		}*/
		return user;
	}
	@PostMapping
	@JsonView(UserSimpleView.class)
	public User create(@Valid @RequestBody User user/*,BindingResult errors*/){
		/*if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}*/
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getId());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	@JsonView(UserSimpleView.class)
	public User update(/*@Valid*/ @Validated(value={Insert.class}) @RequestBody User user,BindingResult errors){
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error -> {
			FieldError fieldError=(FieldError)error;
			String message=fieldError.getField()+error.getDefaultMessage();
			System.out.println(message);
			});
		}
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getId());
		user.setId("1");
		return user;
	}
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable("id")String id){
		System.out.println(id);
	}
    
	
	@PostMapping("/register")
	public void register(User user,HttpServletRequest request){
		//注册逻辑
		String username = user.getUsername();
		providerSignInUtils.doPostSignUp(username, new ServletWebRequest(request));
	}
}
