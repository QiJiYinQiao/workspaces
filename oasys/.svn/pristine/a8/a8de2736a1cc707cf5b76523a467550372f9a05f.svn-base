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
import com.oasys.service.PpeBorrowRegService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.UsersModel;
/**
 * 固定资产借用登记
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PpeBorrowReg")
public class PpeBorrowRegController extends BaseController{
	
	@Autowired
	private  PpeBorrowRegService  ppeBorrowRegService;
	@Autowired
	private UserService userService;
	
	
	//查询借用登记列表
	@ResponseBody
	@RequestMapping(value="/findPpeBorrowRegList",method=RequestMethod.POST)
	public String findPpeBorrowRegList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("ppeBorrowReg") PpeBorrowReg ppeBorrowReg,  BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<PpeBorrowReg> ppeBorrowRegList = ppeBorrowRegService.findPpeBorrowRegList(ppeBorrowReg, pageUtil);
		OutputJson(httpServletResponse, new GridModel(ppeBorrowRegList,ppeBorrowRegService.getTatol(ppeBorrowReg)));
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
	@RequestMapping(value="/savePpeBorrowReg",method=RequestMethod.POST)
	public String savePpeBorrowReg(HttpServletResponse httpServletResponse,@ModelAttribute("ppeBorrowReg") PpeBorrowReg ppeBorrowReg){
		boolean flag = ppeBorrowRegService.saveOrUpdPpeBorrowReg(ppeBorrowReg);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	
	//批量删除
	@ResponseBody
	@RequestMapping(value="/delPpeBorrowRegList",method=RequestMethod.POST)
	public String delPpeBorrowRegList(HttpServletResponse httpServletResponse,String ids){
		boolean flag = ppeBorrowRegService.deletePpeBorrowRegList(ids);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	
	
	
	
}


