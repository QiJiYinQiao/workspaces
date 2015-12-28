package com.oasys.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.model.UseStampApp;
import com.oasys.model.UseStampAppAttach;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.PpeUsedTaskService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UseStampAppService;
import com.oasys.service.UseStampTaskService;
import com.oasys.service.UserService;
import com.oasys.service.workFlow.WorkFlowService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 用章申请任务
 * @Title: UseStampTaskController.java 
 * @Package com.oasys.controller 
 * @Description: TODO
 * @author WANGXINCHENG  
 * @date 2015年12月15日 下午3:17:05 
 * @version V1.0
 */
@Controller
@RequestMapping("/UseStampTask")
public class UseStampTaskController extends BaseController{
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;
	/** 注入service. */
	@Autowired
	private WorkFlowService workflowService;
	@Autowired
	private UseStampAppService useStampAppService;
	@Autowired
	private UseStampTaskService useStampTaskService;
	
	/**
	 * 提交申请
	 * @Title: startPpeUsedConfirm 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param usaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 下午3:19:28
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/startUseStampApp",method=RequestMethod.POST)
	public String startUseStampApp(HttpServletResponse httpServletResponse,@RequestParam(value="usaId",required=true) Integer usaId){
		String resultStr = "";
		Json json=null;
		resultStr = useStampTaskService.addStartProcessInstance(usaId);
		if(StringUtils.isBlank(resultStr)){
			json=getMessage(false);
		}else{
			json=new Json("提示", resultStr, true);
		}
		OutputJson(httpServletResponse,json);
		return null;
	}
	
	/**
	 * 查询待办任务
	 * @Title: findPpeUsedTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param useStampApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 下午4:18:11
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUseStampTaskList",method=RequestMethod.POST)
	public String findUseStampTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("useStampApp") UseStampApp useStampApp, BindingResult bindingResult){
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		List<UseStampApp> taskList = useStampTaskService.findNotClimTask(useStampApp, pageUtil);
		Long total = useStampTaskService.findNotClimTaakCount(useStampApp);
		OutputJson(httpServletResponse, new GridModel(taskList, total));
		
		return null;
	}
	
	/**
	 * 个人领取任务
	 * @Title: taskUserClaim 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskID
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 下午4:24:06
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/taskUserClaim",method=RequestMethod.POST)
	public String taskUserClaim(HttpServletResponse httpServletResponse,String taskID){
		try {
			useStampTaskService.getTaskUserClaim(taskID);
			OutputJson(httpServletResponse,new DataModel("提示", "任务签收成功！", true));
		} catch (ActivitiTaskAlreadyClaimedException e) {
			OutputJson(httpServletResponse,new DataModel("提示", "此任务已被他人签收！", false));
		}
		return null;
	}
	
	/**
	 * 任务办理
	 * @Title: saveTaskMgrResult 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param turnoveDtimer判断是否移交
	 * @param @param receiverDtime 接受视
	 * @param @param emsNo	快递单号
	 * @param @param postAddr 邮寄地址
	 * @param @param contactWay 联系方式
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 下午5:23:29
	 * @throws
	 * String turnoverDtime,String receiverDtime,String emsNo,String postAddr,String contactWay,Integer saaId,
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskMgrResult",method=RequestMethod.POST)
	public String saveTaskMgrResult(HttpServletResponse httpServletResponse,String turnoverDtime,@ModelAttribute("useStampAppAttach") UseStampAppAttach useStampAppAttach,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult){
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = useStampTaskService.addUserTask(taskModel, turnoverDtime, useStampAppAttach);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/**
	 * 批量办理任务
	 * @Title: saveTaskConsumablesAppBatch 
	 * @Description: TODO
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月16日 下午3:05:38
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addMangeTaskList",method=RequestMethod.POST)
	public String addMangeTaskList(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr =useStampTaskService.addMangeTaksList(taskModel);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
}


