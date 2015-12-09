package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.service.OrganizationService;
import com.oasys.viewModel.TreeModel;

@Controller
@RequestMapping("/Organization")
public class OrganizationController extends BaseController{
	
	@Autowired
	private OrganizationService organization;
	
	
	/**
	 * 部门列表
	 * @Title: findMenuList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月9日 上午11:50:20
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/organizationList",method=RequestMethod.POST)
	public String findMenuList(HttpServletResponse httpServletResponse){
		
		List<TreeModel> organizationList = organization.findOrganizationList();
		OutputJson(httpServletResponse,organizationList);
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getOrganizationByUserId")
	public String findOrganizationByUserId(HttpServletResponse response,Integer userId){
		List<TreeModel> list = organization.findOrganizationModelByUserId(userId);
		OutputJson(response,list);
		return null;
	}
}


