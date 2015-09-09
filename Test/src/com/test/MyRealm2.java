package com.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm{

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();//得到用户名
		String password = new String((char[])token.getCredentials());//得到密码
		if(!"zhang".equals(username)){
			throw new UnknownAccountException();//如果用户名错误
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException();//如果密码错误
		}
		//如果身份验证成功，返回一个AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username,password,getName());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "myRealm2";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		//仅支持UsernamePasswordToken类型的TOKEN
		return token instanceof UsernamePasswordToken;
	}
	
}
