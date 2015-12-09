package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.PpeBorrowReg;
import com.oasys.model.PpeReg;
import com.oasys.model.UseReg;
import com.oasys.service.PpeRegService;
import com.oasys.service.UseRegService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.UsersModel;
/**
 * 固定资产领用表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/UseReg")
public class UseRegController extends BaseController{
	
	@Autowired
	private UseRegService useRegService;
	@Autowired
	private UserService userService;
	
	
	//查询固定资产领用表
	@ResponseBody
	@RequestMapping(value="/findUseRegList",method=RequestMethod.POST)
	public String findUseRegList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("useReg") UseReg useReg,  BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<UseReg> useRegList = useRegService.findUseRegList(useReg, pageUtil);
		OutputJson(httpServletResponse, new GridModel(useRegList,useRegService.getTotal(useReg)));
		return null;
	}
	
	
	//根据部门id查询用户列
	@ResponseBody
	@RequestMapping(value="/findOrgUserList",method=RequestMethod.POST)
	public String findOrgUserList(HttpServletResponse httpServletResponse,String organizeId){
		List<ComboBoxModel> userList = userService.findOrgUserList(organizeId);
		OutputJson(httpServletResponse, userList);
		return null;
	}
	
	
	//保存或更新固定资产借用登记
	@ResponseBody
	@RequestMapping(value="/saveUseReg",method=RequestMethod.POST)
	public String saveUseReg(HttpServletResponse httpServletResponse,@ModelAttribute("useReg") UseReg useReg){
		boolean flag = useRegService.saveOrUpdUseReg(useReg);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	
	//批量删除
	@ResponseBody
	@RequestMapping(value="/delUseRegList",method=RequestMethod.POST)
	public String delPpeBorrowRegList(HttpServletResponse httpServletResponse,String ids){
		boolean flag = useRegService.deleteUseRegList(ids);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	
	
	
	
}


