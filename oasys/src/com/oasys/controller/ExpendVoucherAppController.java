package com.oasys.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.BusinessTripAttach;
import com.oasys.model.ExpendVoucherApp;
import com.oasys.model.SystemCode;
import com.oasys.model.TravelExpensesApp;
import com.oasys.model.TravelExpensesOther;
import com.oasys.service.ExpendVoucherAppService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 差率报销申请
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/ExpendVoucherApp")
public class ExpendVoucherAppController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private SystemCodeService SystemCodeService;
	@Autowired
	private ExpendVoucherAppService expendVoucherAppService;
	
	
	/**
	 * 查询支出凭单申请列表
	 * @Title: findTravelExpensesAppList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param travelExpensesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月20日 上午10:02:49
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findExpendList",method=RequestMethod.POST)
	public String findExpendList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("expendVoucherApp") ExpendVoucherApp expendVoucherApp,  BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<ExpendVoucherApp> appList = expendVoucherAppService.findExpendVoucherAppList(expendVoucherApp, pageUtil);
		Long count = expendVoucherAppService.findExpendCount(expendVoucherApp);
		OutputJson2(httpServletResponse,new GridModel(appList, count));
		return null;
	}
	/**
	 * 查询申请人能够选择的借款申请
	 * @Title: findUserLoanList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月26日 上午10:53:58
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUserLoanList",method=RequestMethod.POST)
	public String findUserLoanList(HttpServletResponse httpServletResponse){
		List<ComboBoxModel> list = expendVoucherAppService.findLoanByUserId();
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/**
	 * 查询申请人所选的借款谁去哪个
	 * @Title: findSelectUserLoan 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月26日 上午10:54:28
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findSelectUserLoan",method=RequestMethod.POST)
	public String findSelectUserLoan(HttpServletResponse httpServletResponse,String appNo){
		List<ComboBoxModel> list = expendVoucherAppService.findUserLoan(appNo);
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/**
	 * 保存或更新支出凭单申请
	 * @Title: saveExpendVoucherApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param expendVoucherApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月20日 上午11:43:32
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveExpendVoucherApp",method=RequestMethod.POST)
	public String saveExpendVoucherApp(HttpServletResponse httpServletResponse,@ModelAttribute("expendVoucherApp") ExpendVoucherApp expendVoucherApp,  BindingResult bindingResult){
		ExpendVoucherApp saveOrUpdExpend = expendVoucherAppService.saveOrUpdExpend(expendVoucherApp);
		OutputJson2(httpServletResponse, saveOrUpdExpend);
		return null;
	}
	
	/**
	 * 删除支出凭单申请
	 * @Title: deleteExpendVoucherApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月20日 下午12:01:15
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deleteExpendVoucherApp",method=RequestMethod.POST)
	public String deleteExpendVoucherApp(HttpServletResponse httpServletResponse,String appNo){
		boolean flag = expendVoucherAppService.deleteExpendVoucherApp(appNo);
		if(flag){
			OutputJson(httpServletResponse, new Json("提示","支出凭单删除成功！",flag));
		}else{
			OutputJson(httpServletResponse, new Json("提示","支出凭单删除失败！",flag));
		}
		return null;
	}
	/**
	 * 查询流程图
	 * @Title: 
 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param evaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月23日 上午11:22:28
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showBadgeProcess",method=RequestMethod.GET)
	public String showBadgeProcess(HttpServletResponse httpServletResponse,Integer evaId){
		expendVoucherAppService.getDiagramResourceByPaId(httpServletResponse, evaId);
		return null;
	}
	
	
	//-------------------------------------------------流程---------------------------------------------------------------
	
	/**
	 * 提交申请
	 * @Title: addExpendAppTask 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param evaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月23日 上午10:28:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addExpendAppTask",method=RequestMethod.POST)
	public String addExpendAppTask(HttpServletResponse httpServletResponse,Integer evaId){
		String resultStr = "";
		Json json=null;
		resultStr =expendVoucherAppService.addExpendTask(evaId);
		if(StringUtils.isBlank(resultStr)){
			json=getMessage(false);
		}else{
			json=new Json("提示", resultStr, true);
		}
		OutputJson(httpServletResponse,json);
		return null;	
	}
	
	/**
	 * 查询任务列表信息
	 * @Title: findExpendTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param expendVoucherApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月23日 上午10:43:19
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findExpendTaskList",method=RequestMethod.POST)
	public String findExpendTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("expendVoucherApp")ExpendVoucherApp expendVoucherApp,BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<ExpendVoucherApp> appTasKList = expendVoucherAppService.findExpendAppTasKList(expendVoucherApp, pageUtil);
		Long count = expendVoucherAppService.findExpendTaskCount(expendVoucherApp);
		OutputJson2(httpServletResponse,new GridModel(appTasKList, count));
		return null;	
	}
	
	/**领取任务
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/getUserTravelTask",method=RequestMethod.POST)
	public String getUserTravelTask(HttpServletResponse httpServletResponse,String taskId){
		try{
			expendVoucherAppService.getUserTravelTask(taskId);
			OutputJson(httpServletResponse,new DataModel("提示", "任务签收成功！", true));
		} catch (ActivitiTaskAlreadyClaimedException e) {
			OutputJson(httpServletResponse,new DataModel("提示", "此任务已被他人签收！", false));
		}
		return null;	
	}
	
	/**
	 * 任务办理
	 * @Title: setUserTravelTask 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月23日 上午11:09:13
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/setUserTravelTask",method=RequestMethod.POST)
	public String setUserTravelTask(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult){
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr =expendVoucherAppService.saveSubmitTask(taskModel);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/**
	 * 查询流程图
	 * @Title: showProcessImg 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月23日 上午11:15:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
	public String showProcessImg(HttpServletResponse httpServletResponse,String taskId){
		String imgName = Constants.EXPENDVOUCHERIMAGE;
		try {
			workFlowService.getDiagramResourceByTaskId(httpServletResponse, imgName, taskId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}


