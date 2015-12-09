package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.BusinessTripAttach;
import com.oasys.service.BusinessTripAttachService;
import com.oasys.viewModel.Json;

@Controller
@RequestMapping("/businessTripAttach")
public class BusinessTripAttachController extends BaseController{
	
	@Autowired
	private BusinessTripAttachService businessTripAttachService;
	
	@ResponseBody
	@RequestMapping(value="/saveBusinessTripAttach",method=RequestMethod.POST)
	public String saveBusinessTripAttach(HttpServletResponse response,BusinessTripAttach businessTripAttach){
		boolean bl = businessTripAttachService.saveBusinessTripAttach(businessTripAttach);
    	if(bl){
			OutputJson2(response,new Json("提示","保存成功!",bl,businessTripAttach.getAppNo()));
		}else{
			OutputJson2(response,new Json("提示","出错了,保存失败!",bl));
		}
    	return null;
	}
}
