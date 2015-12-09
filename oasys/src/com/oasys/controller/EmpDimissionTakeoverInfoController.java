package com.oasys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.EmpDimissionTakeoverInfo;
import com.oasys.service.EmpDimissionTakeoverInfoService;
import com.oasys.service.UserService;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;

@Controller
@RequestMapping("/empDimissionTakeoverInfoController")
public class EmpDimissionTakeoverInfoController extends BaseController{
	@Autowired
	private EmpDimissionTakeoverInfoService empDimissionTakeoverInfoService;
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping(value="/saveEmpDimissionTakeoverInfo",method=RequestMethod.POST)
	public String saveEmpDimissionTakeoverInfo(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,EmpDimissionTakeoverInfo empDimissionTakeoverInfo) {
		try {
			empDimissionTakeoverInfoService.saveEmpDimissionTakeoverInfo(empDimissionTakeoverInfo);//新增或更新附加表信息
			OutputJson(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson(httpServletResponse, getMessage(false));
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllList")
	public String findAllList(HttpServletResponse response,HttpServletRequest request,String appNo) {
		GridModel gridModel = new GridModel();
		gridModel.setRows(empDimissionTakeoverInfoService.findEmpDimissionTakeoverList(appNo));
		OutputJson2(response,gridModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/createCombogrid",method=RequestMethod.POST)
	public String createCombogrid(HttpServletResponse httpServletResponse,String q){
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findUserList(q));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteByDaiIds")
	public String deleteByDaiIds(HttpServletRequest request,HttpServletResponse response){
		String daiIds = request.getParameter("daiIds");
		//判断id串是否为空
		if (StringUtils.isNotBlank(daiIds)){
			String[] ids = daiIds.split(",");
			//循环这个id数组
			for(String daiId:ids){
				//在删除该紧急联系人
				EmpDimissionTakeoverInfo takeoverInfo = new EmpDimissionTakeoverInfo();
				takeoverInfo.setDaiId(Integer.parseInt(daiId));
				empDimissionTakeoverInfoService.doDeleteById(takeoverInfo);
			}
			OutputJson2(response,new Json("提示","恭喜你删除成功!",true));
			return null;
		}
		OutputJson2(response,new Json("提示","出错了,删除失败!",false));
		return null;
	}
}
