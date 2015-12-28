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
import com.oasys.model.PenaltyNoticeSubmitAppAttach;
import com.oasys.service.EmpDimissionTakeoverInfoService;
import com.oasys.service.PenaltyNoticeSubmitAppAttachService;
import com.oasys.service.UserService;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;

@Controller
@RequestMapping("/penaltyNoticeSubmitAppAttachController")
public class PenaltyNoticeSubmitAppAttachController extends BaseController{
	@Autowired
	private PenaltyNoticeSubmitAppAttachService penaltyNoticeSubmitAppAttachService;
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping(value="/savePenaltyNoticeSubmitAppAttach",method=RequestMethod.POST)
	public String savePenaltyNoticeSubmitAppAttach(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach) {
		try {
			penaltyNoticeSubmitAppAttachService.savePenaltyNoticeSubmitAppAttach(penaltyNoticeSubmitAppAttach);//新增或更新附加表信息
			OutputJson2(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson2(httpServletResponse, getMessage(false));
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllList")
	public String findAllList(HttpServletResponse response,HttpServletRequest request,String appNo) {
		GridModel gridModel = new GridModel();
		gridModel.setRows(penaltyNoticeSubmitAppAttachService.findPenaltyNoticeSubmitAppAttachList2(appNo));
		OutputJson2(response,gridModel);
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
				PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach = new PenaltyNoticeSubmitAppAttach();
				penaltyNoticeSubmitAppAttach.setSaaId(Integer.parseInt(daiId));
				penaltyNoticeSubmitAppAttachService.doDeleteById(penaltyNoticeSubmitAppAttach);
			}
			OutputJson2(response,new Json("提示","恭喜你删除成功!",true));
			return null;
		}
		OutputJson2(response,new Json("提示","出错了,删除失败!",false));
		return null;
	}
}
