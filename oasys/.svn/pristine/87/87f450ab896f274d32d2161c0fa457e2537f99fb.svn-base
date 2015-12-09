package com.oasys.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.PpeTurnoverAttach;
import com.oasys.service.PpeTurnoverAppAttachService;
import com.oasys.service.UserService;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;

/**   
 * @Title: PpeTurnoverAppAttachController.java 
 * @Package com.oasys.controller 
 * @Description: TODO
 * @author PANCHUANHE  
 * @date 2015年9月24日 上午11:03:11 
 * @version V1.0   
 */
@Controller
@RequestMapping("/ppeTurnoverAppAttachController")
public class PpeTurnoverAppAttachController extends BaseController{
	@Autowired
	private PpeTurnoverAppAttachService ppeTurnoverAppAttachService;
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping(value="/saveTurnoverAppAttach")
	public String saveTurnoverAppAttach(HttpServletResponse httpServletResponse,PpeTurnoverAttach ppeTurnoverAttach){
		//更新主数据的总金额
//		purchaseAppService.updatePurchaseAppTotalAmt(purchaseAppAttach.getAppNo(), count);
		
		boolean bl = ppeTurnoverAppAttachService.savePurchaseAppAttach(ppeTurnoverAttach);
		if(bl){
			OutputJson2(httpServletResponse,new Json("提示","保存成功!",bl,ppeTurnoverAttach.getPtaId()));
		}else{
			OutputJson2(httpServletResponse,new Json("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/deleteByPtaIds")
	public String deleteByPtaIds(HttpServletRequest request,HttpServletResponse response,String appNo){
		String ptaIds = request.getParameter("ptaIds");
		//判断id串是否为空
		if (StringUtils.isNotBlank(ptaIds)){
			String[] ids = ptaIds.split(",");
			//循环这个id数组
			for(String ptaId:ids){
				//在删除该紧急联系人
				PpeTurnoverAttach turnoverAttach = new PpeTurnoverAttach();
				turnoverAttach.setPtaId(Integer.parseInt(ptaId));
				ppeTurnoverAppAttachService.doDeleteById(turnoverAttach);
			}
			ppeTurnoverAppAttachService.totalCount(appNo);
			OutputJson2(response,new Json("提示","恭喜你删除成功!",true));
			return null;
		}
		OutputJson2(response,new Json("提示","出错了,删除失败!",false));
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteByAppNos")
	public String deleteByAppNos(HttpServletRequest request,HttpServletResponse response){
		String appNos = request.getParameter("appNos");
		//判断id串是否为空
		if (StringUtils.isNotBlank(appNos)){
			String[] nos = appNos.split(",");
			//循环这个id数组
			for(String appNo:nos){
				//在删除该紧急联系人
				PpeTurnoverAttach turnoverAttach = new PpeTurnoverAttach();
				turnoverAttach.setAppNo(appNo);
				ppeTurnoverAppAttachService.doDeleteByAppNo(turnoverAttach);
			}
			OutputJson2(response,new Json("提示","恭喜你删除成功!",true));
			return null;
		}
		OutputJson2(response,new Json("提示","出错了,删除失败!",false));
		return null;
	}
	
	/**
	 * 查询列表
	 */
	@ResponseBody
	@RequestMapping(value="/findAllList")
	public String findAllList(HttpServletResponse response,HttpServletRequest request) {
		String appNo = request.getParameter("appNo");
		GridModel gridModel = new GridModel();
		gridModel.setRows(ppeTurnoverAppAttachService.findListByAppNo(appNo));
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
}


