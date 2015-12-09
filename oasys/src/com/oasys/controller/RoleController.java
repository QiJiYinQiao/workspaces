package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.Role;
import com.oasys.service.RoleService;
import com.oasys.viewModel.ComboBoxModel;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *职位
 */
@Controller
@RequestMapping("/Role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	//职位列表
	@ResponseBody
	@RequestMapping(value="/findRoleList",method=RequestMethod.POST)
	public String findRoleList(HttpServletResponse httpServletResponse){
		List<ComboBoxModel> roleList = roleService.findRoleList();
		OutputJson(httpServletResponse, roleList);
		return null;
	}
	
	//根据部门查询角色
	@ResponseBody
	@RequestMapping(value="/findRoleListByOrgID",method=RequestMethod.POST)
	public String findRoleListByOrgID(HttpServletResponse httpServletResponse,Integer orgID){
		List<ComboBoxModel> roleList = roleService.findRoleListByOrgID(orgID);
		OutputJson2(httpServletResponse, roleList);
		return null;
	}
	
}


