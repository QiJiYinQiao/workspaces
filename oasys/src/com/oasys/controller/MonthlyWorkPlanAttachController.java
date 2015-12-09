package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.MonthlyWorkPlanAttach;
import com.oasys.service.MonthlyWorkPlanAttachService;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
/**
 * @ClassName: MonthlyWorkPlanAttachController 
 * @Description: TODO 物料表Controller
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:15:12
 */
@Controller
@RequestMapping("/monthlyWorkPlanAttachController")
public class MonthlyWorkPlanAttachController extends BaseController{
	
	@Autowired
	private MonthlyWorkPlanAttachService monthlyWorkPlanAttachService;
	/**
	 * @Title: saveMonthlyWorkPlanAttach 
	 * @Description: TODO 
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月16日 下午3:16:28
	 * @throws
	 */
	@RequestMapping(value="/saveMonthlyWorkPlanAttach")
	public String saveMonthlyWorkPlanAttach(HttpServletResponse httpServletResponse,MonthlyWorkPlanAttach monthlyWorkPlanAttach){
		//新增物料明细
		boolean bl = monthlyWorkPlanAttachService.saveMonthlyWorkPlanAttach(monthlyWorkPlanAttach);
		if(bl){
			OutputJson(httpServletResponse,new Json("提示","保存成功!",bl));
		}else{
			OutputJson(httpServletResponse,new Json("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * @Title: delMonthlyWorkPlanAttach 
	 * @Description: TODO 删除
	 * @param @param httpServletResponse
	 * @param @param monthlyWorkPlanAttach
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月18日 上午11:43:19
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="delMonthlyWorkPlanAttach",method=RequestMethod.POST)
	public String delMonthlyWorkPlanAttach(HttpServletResponse httpServletResponse,String ids){
		//删除该条子数据
		boolean bl = monthlyWorkPlanAttachService.delMonthlyWorkPlanAttachByMwpaIds(ids);
		if(bl){
			OutputJson(httpServletResponse,new Json("提示","删除成功!",bl));
		}else{
			OutputJson(httpServletResponse,new Json("提示","出错了,删除失败!",bl));
		}
		return null;
	}
	/**
	 * 物料列表（新增、修改页面grid数据）
	 * @Title: findAllMonthlyWorkPlanAttachList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月24日 下午4:43:22
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findAllMonthlyWorkPlanAttachList",method=RequestMethod.POST)
	public String findAllMonthlyWorkPlanAttachList(HttpServletResponse httpServletResponse,Integer mwpId){
		GridModel gridModel = new GridModel();
		gridModel.setRows(monthlyWorkPlanAttachService.findMonthlyWorkPlanAttachList(mwpId));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
}
