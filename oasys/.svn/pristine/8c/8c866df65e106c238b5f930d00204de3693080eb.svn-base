package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.service.CommonService;

/**
 * 公用Controller
 * @ClassName: CommonController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月18日 下午5:15:08
 */
@Controller
@RequestMapping("/commonController")
public class CommonController extends BaseController {

	@Autowired
	private CommonService commonService;
	
	
	/**
	 * 根据字典codeMyid获取下拉框选项，渲染下拉框
	 * @Title: findDicList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月18日 下午5:28:54
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findDicList",method=RequestMethod.POST)
	public String findDicList(HttpServletResponse httpServletResponse,String codeMyid){
		OutputJson(httpServletResponse,this.commonService.findDicList(codeMyid));
		return null;
	}
}
