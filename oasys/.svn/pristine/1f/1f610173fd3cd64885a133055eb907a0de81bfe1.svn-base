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
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.AuditProcHis;
import com.oasys.model.EmpDimissionApp;
import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.PpeStockInfo;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.service.PpeTurnoverAppAttachService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.PpeTurnoverModel;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 *author:yuanzhongqiu
 *Time:2015年9月18日  下午4:17:09
 */
@Controller
@RequestMapping("/ppeTurnoverAppController")
public class PpeTurnoverAppController extends BaseController{
	@Autowired
	private PpeTurnoverAppService ppeTurnoverAppService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowTaskService workTaskService;
	@Autowired
	private PpeTurnoverAppAttachService ppeTurnoverAppAttachService;
	
	@RequestMapping(value="/toAddWindow")
	public ModelAndView toAddWindow(HttpServletRequest httpServletRequest){
		String account =  Constants.getCurrendUser().getAccount();
    	httpServletRequest.setAttribute("account", account);
    	ModelAndView mv = new ModelAndView("ad/ppeTurnover/ppeTurnoverAppForm");
		return mv;
	}
	@ResponseBody
	@RequestMapping(value="/findAllPpeTurnover",method=RequestMethod.POST)
	public String findAllPpeTurnover(HttpServletResponse response,Integer page,Integer rows,PpeTurnoverModel ppeTurnoverModel){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
		gridModel.setRows(ppeTurnoverAppService.findAllPpeTurnover(pageUtil,ppeTurnoverModel));
		gridModel.setTotal(ppeTurnoverAppService.findAllPpeTurnoverCount());
		OutputJson2(response, gridModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllPpeTurnover2",method=RequestMethod.POST)
	public String findAllPpeTurnover2(HttpServletResponse response,Integer page,Integer rows,PpeTurnoverApp ppeTurnoverApp){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
		gridModel.setRows(ppeTurnoverAppService.findAllPpeTurnover2(pageUtil,ppeTurnoverApp));
		gridModel.setTotal(ppeTurnoverAppService.findAllPpeTurnoverCount());
		OutputJson2(response, gridModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllPpeTurnoverApp",method=RequestMethod.POST)
	public String findAllPpeTurnoverApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, Integer page,Integer rows,PpeTurnoverApp ppeTurnoverApp,  BindingResult bindingResult){
    	PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<PpeTurnoverAttach> attachList = ppeTurnoverAppService.findPpeList(pageUtil,ppeTurnoverApp);//获取固定资产报废申请List
    	Long rowsCount = ppeTurnoverAppService.findPpeListCount(ppeTurnoverApp);//获取总记录数
		gridModel.setRows(attachList);
		gridModel.setTotal(rowsCount);
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	
	@ResponseBody
    @RequestMapping(value="/delPpeTurnover",method=RequestMethod.POST)
    public String delPpeTurnover(HttpServletResponse httpServletResponse,String appNo){
		boolean isSuccess = true;
		try {
			ppeTurnoverAppService.delPpeTurnover(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;

    }
	@ResponseBody
	@RequestMapping(value="/addPpeTurnoverApp",method=RequestMethod.POST)
	public String savePpeTurnoverApp(HttpServletResponse response,HttpServletRequest request,PpeTurnoverApp ppeTurnoverApp){
		boolean bl = ppeTurnoverAppService.savePpeTurnoverApp(ppeTurnoverApp);
    	if(bl){
			OutputJson2(response,new Json("提示","保存成功!",bl,ppeTurnoverApp.getAppNo()));
		}else{
			OutputJson2(response,new Json("提示","出错了,保存失败!",bl));
		}
    	return null;
	}
	/**
	 * 提交申请启动流程
	 * @param httpServletResponse
	 * @param ppeTurnoverApp
	 * @return
	 */
	/*@ResponseBody
    @RequestMapping(value="/startWorkflow",method=RequestMethod.POST)
    public String startWorkflow(HttpServletResponse httpServletResponse,PpeTurnoverApp ppeTurnoverApp){
    	boolean bl = ppeTurnoverAppService.startWorkflow(ppeTurnoverApp);
    	if(bl){
    		OutputJson(httpServletResponse,new Json("提示","提交申请成功!",bl));
    	}else{
    		OutputJson(httpServletResponse,new Json("提示","出错了,提交申请失败!",bl));
    	}
    	return null;
    }*/
	
	@ResponseBody
	@RequestMapping(value="/saveStartProcess",method=RequestMethod.POST)
	public String saveStartProcess(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, PpeTurnoverApp ppeTurnoverApp){
			boolean isSuccess = true;
			String resultStr = "";
			try {
				resultStr = ppeTurnoverAppService.saveStartProcess(ppeTurnoverApp);
			} catch (Exception e) {
				System.out.println(e.toString());
				isSuccess = false;
			}
			Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
			OutputJson(httpServletResponse,json);
			return null;
	}
	
	/**
	 * 查询待办任务
	 * @param httpServletResponse
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/findAllPpeTurnoverAppTaskList",method=RequestMethod.POST)
    public String findAllPpeTurnoverAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows, PpeTurnoverApp ppeTurnoverApp){
        GridModel gridModel = new GridModel();
        gridModel.setRows(ppeTurnoverAppService.findAllPpeTurnoverAppTaskList(page,rows,ppeTurnoverApp));
        gridModel.setTotal(ppeTurnoverAppService.countAllPpeTurnoverAppTaskList());
        OutputJson2(httpServletResponse, gridModel);
    	return null;
    }
	/**
	 * 签收任务
	 * @param httpServletResponse
	 * @param taskId
	 * @return
	 */
/*	@ResponseBody
    @RequestMapping(value="/holdWorkTask",method=RequestMethod.POST)
    public String holdWorkTask(HttpServletResponse httpServletResponse,String taskID){
    	boolean bl = ppeTurnoverAppService.saveHoldWorkTask(taskID);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","签收任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,签收任务失败!",bl));
    	}
    	return null;
    }
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
	 * 办理任务
	 * @param httpServletResponse
	 * @param ppeTurnoverApp
	 * @param result
	 * @param auditProcHis
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveTask",method=RequestMethod.POST)
	public String saveTask(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeTurnoverAppService.saveTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveTaskPpeTurnoverAppBatch",method=RequestMethod.POST)
	public String saveTaskPpeTurnoverAppBatch(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeTurnoverAppService.saveSubmitTaskBatch(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
 	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer ptaId){
    	ppeTurnoverAppService.getDiagramResourceByPaId(httpServletResponse,ptaId);
    	return null;
    }
	 
	 @ResponseBody
	 @RequestMapping(value="/findPpeStock",method=RequestMethod.POST)
	 public String findPpeStock(HttpServletResponse response,PpeStockInfo ppeStockInfo,String appNo){
		 PpeTurnoverAttach attach = ppeTurnoverAppAttachService.findPpeAttachList(appNo,ppeStockInfo.getPpeCode());
		 boolean flag=true;
		 if (attach==null) {
			 PpeStockInfo ppeStock = ppeTurnoverAppService.findPpeStock(ppeStockInfo);
			 if (ppeStock!=null) {
				OutputJson2(response,new Json("提示","保存成功!",flag,ppeStock));
			 }else{
				flag=false;
				OutputJson2(response,new Json("提示","编号错误,请重新输入!",flag));
			 }
		 } else {
			 flag=false;
			 OutputJson2(response,new Json("提示","编号重复,请重新输入!",flag));
		 }
		 return null;
	 }
}


