package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;


@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService{
    Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.info("登录用户名： "+username);
		return buildUser(username);
	}
	@Override
	public SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException {
		logger.info("登录用户ID： "+userId);
		return buildUser(userId);
	}
	
	private SocialUserDetails buildUser(String userId){
		String passowrd=passwordEncoder.encode("123456");
		logger.info("密码： "+passowrd);
		 SocialUser socialUser = new SocialUser(userId, passowrd,true, true,true,true,AuthorityUtils.createAuthorityList("admin"));
		 return socialUser;
	}

}
