package com.sun.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.entity.UserEntity;
import com.sun.service.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"WEB-INF/springMVC-servlet.xml"})
public class Tests {

	@Autowired
	private IUserService userServiceImpl;
	
	@Test
	public void test() {
		UserEntity user = userServiceImpl.selectByPrimaryKey("b8354347507d11e5916dfcaa144c9f1d");
		System.out.println(user);
	}
	
}
