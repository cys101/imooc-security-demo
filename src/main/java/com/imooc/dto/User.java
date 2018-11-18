package com.imooc.dto;



import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.Insert;
import com.imooc.validator.Myconstraint;
import com.imooc.validator.Update;

public class User {
	public interface UserSimpleView{};
	public interface UserDetialView extends UserSimpleView{};
	@ApiModelProperty(value="用户名")
	private String username;
	@Myconstraint(message="this is a test",groups={Insert.class})
	@ApiModelProperty(value="密码")
	private String password;
	@NotBlank(message="修改时ID不能为空",groups={Update.class})
	@Myconstraint(message="this is a test1",groups={Insert.class})
	@ApiModelProperty(value="用户ID")
	private String id;
	@Past(message="生日不能为过去的时间")
	@ApiModelProperty(value="用户生日")
	private Date birthday;
	public User(){
		
	}
	public User(String username,String password,String id){
		this.username=username;
		this.password=password;
		this.id=id;
	}
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonView(UserDetialView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	

}
