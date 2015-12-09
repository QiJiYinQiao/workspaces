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

import com.oasys.model.LeaveApp;
import com.oasys.model.SystemCode;
import com.oasys.model.TravelExpensesApp;
import com.oasys.model.TravelExpensesOther;
import com.oasys.service.SystemCodeService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.TravelTaskService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
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
@RequestMapping("/TravelTaskApp")
public class TravelTaskAppController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private SystemCodeService SystemCodeService;
	@Autowired
	private TravelTaskService  travelTaskService;
	
	
	/**
	 * 提交申请
	 * @Title: showBadgeProcess 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param teaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:35:04
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/startTravelExpensesApp",method=RequestMethod.POST)
	public String startTravelExpensesApp(HttpServletResponse httpServletResponse,Integer teaId){
		String resultStr = "";
		Json json=null;
		resultStr = travelTaskService.addTravelTask(teaId);
		if(StringUtils.isBlank(resultStr)){
			json=getMessage(false);
		}else{
			json=new Json("提示", resultStr, true);
		}
		OutputJson(httpServletResponse,json);
		return null;
	}
	
	/**
	 * 代办任务列表
	 * @Title: findTravelTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param travelExpensesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:44:03
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findTravelTaskList",method=RequestMethod.POST)
	public String findTravelTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("travelExpensesApp") TravelExpensesApp travelExpensesApp, BindingResult bindingResult){
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		List<TravelExpensesApp> travelTaskList = travelTaskService.findTravelTaskList(travelExpensesApp, pageUtil);
		Long count = travelTaskService.findTravelTaskCount(travelExpensesApp);
		OutputJson2(httpServletResponse, new GridModel(travelTaskList, count));
		return null;
	}
	
	/**
	 * 个人领取任务
	 * @Title: getUserTravelTask 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:47:21
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/getUserTravelTask",method=RequestMethod.POST)
	public String getUserTravelTask(HttpServletResponse httpServletResponse,String taskId){
		try {
			travelTaskService.getUserTravelTask(taskId);
			OutputJson(httpServletResponse,new DataModel("提示", "任务签收成功！", true));
		} catch (ActivitiTaskAlreadyClaimedException e) {
			OutputJson(httpServletResponse,new DataModel("提示", "此任务已被他人签收！", false));
		}
		return null;
	}
	
	/**
	 * 个人任务办理，公共的方法
	 * @Title: setUserTravelTask 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:52:33
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/setUserTravelTask",method=RequestMethod.POST)
	public String setUserTravelTask(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult){
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = travelTaskService.saveSubmitTask(taskModel);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/**
	 * 查询申请流程图
	 * @Title: showProcessImg 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @param leaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月27日 上午11:12:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
	public String showProcessImg(HttpServletResponse httpServletResponse,String taskId){
		String imgName = Constants.TRAVELEXPENSESIMAGE;
		try {
			workFlowService.getDiagramResourceByTaskId(httpServletResponse, imgName, taskId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}


