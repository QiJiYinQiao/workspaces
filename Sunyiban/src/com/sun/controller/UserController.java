package com.sun.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.entity.UserEntity;
import com.sun.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userServiceImpl;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(){
		return "/welcome";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.POST)
	public String registPost(Model model, String username, String password){
		System.out.println(username);
		model.addAttribute("username",username);
		return "/hello";
	}
	
	@RequestMapping(value="/findUser",method=RequestMethod.GET)
	public String findUser(Model model){
		UserEntity user = userServiceImpl.selectByPrimaryKey("b8354347507d11e5916dfcaa144c9f1d");
		model.addAttribute("user",user);
		return "user/user";
	}
	
	public static void main(String[] args) {
		int i = 0;
		int j = 1;
		int k = 0;
		
		for(int l = 0 ; l < 42; l++) {
			k = i + j;
			i = j;
			j = k;
			System.out.print(k+",");
			
		}
	}
}
