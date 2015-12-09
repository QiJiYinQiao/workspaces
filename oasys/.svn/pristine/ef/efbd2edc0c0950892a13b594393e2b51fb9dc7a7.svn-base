package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oasys.service.LoginService;
import com.oasys.shiro.CaptchaUsernamePasswordToken;
import com.oasys.shiro.IncorrectCaptchaException;
import com.oasys.util.Constants;
import com.oasys.viewModel.Json;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	private static Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String manager1(HttpServletResponse response,String userName,String password,String captcha){
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		Json json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			System.out.println("sessionTimeout===>"
					+ subject.getSession().getTimeout());
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			logger.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException e) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		OutputJson(response,json, Constants.TEXT_TYPE_PLAIN);
		// token.clear();
		return null; 
	}
	
	@ResponseBody
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout(){
		SecurityUtils.getSubject().logout();
		Json json = new Json();
		json.setStatus(true);
		return JSON.toJSONString(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/findMenuList",method=RequestMethod.POST)
	public String findMenuList(HttpServletResponse httpServletResponse){
		OutputJson(httpServletResponse,loginService.findMenuList());
		return null;
	}
	
}


