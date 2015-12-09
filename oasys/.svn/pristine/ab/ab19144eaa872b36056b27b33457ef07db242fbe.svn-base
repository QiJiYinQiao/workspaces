package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.MonthlyWorkPlan;
import com.oasys.model.VO.MonthlyWorkPlanModel;
import com.oasys.service.MonthlyWorkPlanAttachService;
import com.oasys.service.MonthlyWorkPlanService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
/**
 * 管理中心月度工作计划表Controller
 * @ClassName: monthlyWorkPlanController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:18:55
 */
@Controller
@RequestMapping("/monthlyWorkPlanController")
public class MonthlyWorkPlanController extends BaseController{

	@Autowired
	private MonthlyWorkPlanService monthlyWorkPlanService;
	@Autowired
	private MonthlyWorkPlanAttachService monthlyWorkPlanAttachService;
	
	/**
	 * 新增
	 * @Title: saveMonthlyWorkPlan 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param expensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午1:24:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveMonthlyWorkPlan",method=RequestMethod.POST)
	public String saveMonthlyWorkPlan(HttpServletResponse httpServletResponse,MonthlyWorkPlan monthlyWorkPlan){
		boolean bl = monthlyWorkPlanService.saveMonthlyWorkPlan(monthlyWorkPlan);
		if(bl){
			OutputJson2(httpServletResponse,new Json("提示","保存成功!",bl,monthlyWorkPlan));
		}else{
			OutputJson2(httpServletResponse,new Json("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * 查询列表
	 * @Title: findMonthlyWorkPlanList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午3:19:01
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findMonthlyWorkPlanList",method=RequestMethod.POST)
	public String findMonthlyWorkPlanList(HttpServletResponse httpServletResponse,Integer page,Integer rows,MonthlyWorkPlanModel monthlyWorkPlanModel){
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(monthlyWorkPlanService.findMonthlyWorkPlanList(pageUtil,monthlyWorkPlanModel));
		gridModel.setTotal(monthlyWorkPlanService.countFindMonthlyWorkPlanList());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 删除
	 * @Title: delMonthlyWorkPlanbyMwpId 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午4:39:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delMonthlyWorkPlanbyMwpId",method=RequestMethod.POST)
	public String delMonthlyWorkPlanbyMwpId(HttpServletResponse httpServletResponse,String ids){
		//先删除子数据
		monthlyWorkPlanAttachService.delMonthlyWorkPlanAttachByMwpId(ids);
		boolean bl = monthlyWorkPlanService.delMonthlyWorkPlanbyMwpId(ids);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","删除成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,删除失败!",bl));
		}
		return null;
	}
}
