package com.shiro.test;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class LoginLogoutTest {

	@Test
	public void testHelloWorld() {
		//1、获取securityManager工厂，此处使用Ini配置文件实例化SecurityManager
		//Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		Factory<org.apache.shiro.mgt.SecurityManager> factorys = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2、得到securityManager实例，并绑定给SecurityUtils
		//org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		//SecurityUtils.setSecurityManager(securityManager);
		org.apache.shiro.mgt.SecurityManager securityManagers = factorys.getInstance();
		SecurityUtils.setSecurityManager(securityManagers);
		//3、得到subject及创建用户名/密码身份验证TOKEN
		//Subject subject = SecurityUtils.getSubject();
		//UsernamePasswordToken user = new UsernamePasswordToken("zhang1","1234");
		Subject subjects = SecurityUtils.getSubject();
		UsernamePasswordToken users = new UsernamePasswordToken("zhang","123");
		
		try {
			//subject.login(user);
			subjects.login(users);
		} catch (UnknownAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Assert.assertEquals(true, subject.isAuthenticated());
		Assert.assertEquals(true, subjects.isAuthenticated());
		
		//subject.logout();
		subjects.logout();
	}
	

}
