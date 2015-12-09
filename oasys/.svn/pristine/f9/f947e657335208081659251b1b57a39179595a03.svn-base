package com.oasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.SystemCode;
import com.oasys.service.SystemCodeService;
/**
 * 
 * @ClassName: SystemCodeController
 * @Description: TODO
 * @Author lida
 * @Version 1.0
 * @Date 2015年10月26日 下午3:32:06
 *
 */
@Controller
@RequestMapping("/systemCodeController")
public class SystemCodeController extends BaseController{
	
	/** 自动注入service. */
	@Autowired
	private SystemCodeService systemCodeService;
	
	
	/**
	 * @title findSysDictListByCode
	 * @author lida 
	 * @descraption 根据字典code获取字典List 
	 * @param request
	 * @param resp
	 * @param code
	 * @date 2015/10/16
	 */
	@ResponseBody
	@RequestMapping(value="/findSysDictListByCode",method=RequestMethod.POST)
	public void findSysDictListByCode(HttpServletRequest request, HttpServletResponse resp,@RequestParam(value="code",required = true) String code) {
		List<SystemCode> sysCodeList = systemCodeService.findSystemCodeByType(code);
		OutputJson(resp,sysCodeList);
	}
	
}


