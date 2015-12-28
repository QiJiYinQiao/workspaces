package com.oasys.controller;

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

import com.oasys.model.ConsumablesApp;
import com.oasys.model.ConsumablesAppAttach;
import com.oasys.model.EmpDimissionApp;
import com.oasys.model.EmpDimissionTakeoverInfo;
import com.oasys.model.Organization;
import com.oasys.model.PenaltyNoticeSubmitApp;
import com.oasys.model.PenaltyNoticeSubmitAppAttach;
import com.oasys.service.EmpDimissionAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.PenaltyNoticeSubmitAppService;
import com.oasys.service.UserService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 
 * @Title: EmpDimissionAppController.java 
 * @Package com.oasys.controller 
 * @Description: TODO
 * @author yuanzhongqiu  
 * @date 2015年10月26日 上午11:17:18 
 * @version V1.0
 */
@Controller
@RequestMapping("/penaltyNoticeSubmitAppController")
public class PenaltyNoticeSubmitAppController extends BaseController{
	@Autowired
	private PenaltyNoticeSubmitAppService penaltyNoticeSubmitAppService;
	@Autowired
	private WorkFlowTaskService workTaskService;
	@Autowired
	private OrganizationService orgService;
	/**
	 * 
	 * @Title: findEmpDimissionAppList 
	 * @Description: 查询离职申请列表
	 * @param @param httpServletResponse
	 * @param @param empDimissionApp
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月26日 下午2:53:47
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPenaltyNoticeSubmitAppList",method=RequestMethod.POST)
	public String findPenaltyNoticeSubmitAppList(HttpServletResponse httpServletResponse,PenaltyNoticeSubmitApp penaltyNoticeSubmitApp, Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<PenaltyNoticeSubmitAppAttach> attachList = penaltyNoticeSubmitAppService.findPenaltyNoticeSubmitAppList(pageUtil,penaltyNoticeSubmitApp);
//    	Long rowsCount = penaltyNoticeSubmitAppService.findPenaltyNoticeSubmitAppCount(penaltyNoticeSubmitApp);//获取总记录数
		gridModel.setRows(attachList);
		gridModel.setTotal((long)attachList.size());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: saveEmpDimissionApp 
	 * @Description: 保存(新增/编辑)
	 * @param @param response
	 * @param @param request
	 * @param @param empDimissionApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月26日 下午4:29:38
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/savePenaltyNoticeSubmitApp",method=RequestMethod.POST)
	public String savePenaltyNoticeSubmitApp(HttpServletResponse response,HttpServletRequest request,PenaltyNoticeSubmitApp penaltyNoticeSubmitApp){
		boolean bl = penaltyNoticeSubmitAppService.savePenaltyNoticeSubmitApp(penaltyNoticeSubmitApp);
    	if(bl){
			OutputJson2(response,new Json("提示","保存成功!",bl,penaltyNoticeSubmitApp.getAppNo()));
		}else{
			OutputJson2(response,new Json("提示","出错了,保存失败!",bl));
		}
    	return null;
	}
	/**
	 * 
	 * @Title: delEmpDimissionApp 
	 * @Description: 删除申请
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月26日 下午4:31:08
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delPenaltyNoticeSubmitApp",method=RequestMethod.POST)
	public String delPenaltyNoticeSubmitApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="appNo",required = true) String appNo){
		boolean isSuccess = true;
		try {
			penaltyNoticeSubmitAppService.delPenaltyNoticeSubmitApp(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	/**
	 * 
	 * @Title: startProcessEmpDimissionApp 
	 * @Description: 提交申请，启动流程
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param empDimissionApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月28日 下午7:05:50
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveStartProcess",method=RequestMethod.POST)
	public String saveStartProcess(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, PenaltyNoticeSubmitApp penaltyNoticeSubmitApp){
			boolean isSuccess = true;
			String resultStr = "";
			try {
				resultStr = penaltyNoticeSubmitAppService.saveStartProcess(penaltyNoticeSubmitApp);
			} catch (Exception e) {
				System.out.println(e.toString());
				isSuccess = false;
			}
			Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
			OutputJson(httpServletResponse,json);
			return null;
	}
	
	/**
	 * 
	 * @Title: showProcessImg 
	 * @Description: 查看流程图
	 * @param @param httpServletResponse
	 * @param @param edaId
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月29日 上午10:26:24
	 * @throws
	 */
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer pnrId){
		penaltyNoticeSubmitAppService.getDiagramResourceByPaId(httpServletResponse,pnrId);
    	return null;
    }
	
	/**
	 * 
	 * @Title: findEmpDimissionAppTask 
	 * @Description: 查询待办任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param empDimissionApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月29日 上午10:57:39
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPenaltyNoticeSubmitAppTask",method=RequestMethod.POST)
	public String findPenaltyNoticeSubmitAppTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,PenaltyNoticeSubmitApp penaltyNoticeSubmitApp,  BindingResult bindingResult){
    	PageUtil pageUtil = new PageUtil(page,rows);
    	List<PenaltyNoticeSubmitAppAttach> list = penaltyNoticeSubmitAppService.findPenaltyNoticeSubmitAppTask(pageUtil,penaltyNoticeSubmitApp);//获取申请任务列表
//    	Long listCount = penaltyNoticeSubmitAppService.findPenaltyNoticeSubmitAppTaskCount(penaltyNoticeSubmitApp);//获取总记录数
    	GridModel gridModel = new GridModel();
		gridModel.setRows(list);
		gridModel.setTotal((long)list.size());
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: signForTask 
	 * @Description: 签收任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskID
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月29日 上午11:14:24
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/signForTask",method=RequestMethod.POST)
	public String signForTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="taskID",required = true) String taskID){
		boolean isSuccess = true;
		try{
			workTaskService.signForTask(taskID);//执行任务签收
		}catch (ActivitiTaskAlreadyClaimedException e) {
			// TODO: handle exception
			isSuccess = false;
			System.out.println("该任务已经被接收");
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	/**
	 * 
	 * @Title: saveTaskEmpDimissionApp 
	 * @Description: 办理任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param empDimissionApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月29日 下午1:02:05
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTask",method=RequestMethod.POST)
	public String saveTask(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = penaltyNoticeSubmitAppService.saveTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	/**
	 * 
	 * @Title: saveTaskPenaltyNoticeSubmitAppBatch 
	 * @Description: 批量受理
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年12月16日 下午4:51:48
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskPenaltyNoticeSubmitAppBatch",method=RequestMethod.POST)
	public String saveTaskPenaltyNoticeSubmitAppBatch(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = penaltyNoticeSubmitAppService.saveSubmitTaskBatch(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
}
