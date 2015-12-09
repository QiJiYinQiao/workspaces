package com.oasys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.AuditProcHis;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.ProcessNameModel;
import com.oasys.viewModel.WorkFlowTasksModel;


/**
 * 
 * @author lida
 * @time 2015/9/22
 * @remark 任务管理Controller(待办任务、受理任务)
 * 
 * */
@Controller
@RequestMapping("/workFlowTaskAction")
public class WorkFlowTaskController extends BaseController{
	
	/** 注入service. */
	@Autowired
	private WorkFlowTaskService workflowService;
	
	
	/**
	 * 查询下拉列表框
	 * */
	@ResponseBody
	@RequestMapping(value="/getProcNameList",method=RequestMethod.POST)
	public void getProcNameList(HttpServletRequest request,HttpServletResponse httpServletResponse){
		List<ProcessNameModel> procList = workflowService.processNameList();
		if(null == procList || procList.size() == 0){
			procList = new ArrayList<ProcessNameModel>();
			procList.add(0, new ProcessNameModel("", "无待办任务",0,""));
		}else{
			procList.add(0, new ProcessNameModel("", "请选择待办任务种类：",0,""));
		}
		OutputJson(httpServletResponse,procList);
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月30日 下午4:00:47
	 * @Title:getAppType 
	 * @Description:TODO（这里描述这个方法的作用）查询已办理任务申请类型下拉框
	 * @param response
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/getAppTypeList",method=RequestMethod.POST)
	public void getAppTypeList(HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();
		map.put("GP", "工牌申请");
		map.put("MP", "名片申请");
		map.put("CW", "物料采购申请");
		map.put("CG", "固定资产采购申请");
		map.put("BF", "固定资产报废申请");
		map.put("YJ", "固定资产移交申请");
		map.put("YH", "低值易耗品申请");
		map.put("YZ", "用章申请");
		map.put("YY", "印章移交申请");
		map.put("FY", "费用申请");
		List<ProcessNameModel> procList =new ArrayList<ProcessNameModel>();
		for(@SuppressWarnings("rawtypes") Map.Entry entry:map.entrySet()){
			ProcessNameModel model =new ProcessNameModel();
			model.setProcessKey(entry.getKey().toString());
			model.setProcessName(entry.getValue().toString());
			procList.add(model);
		}
		OutputJson(response, procList);
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月2日 下午1:13:09
	 * @Title:getAppTypeListByPre
	 * @Description:TODO（这里描述这个方法的作用）根据申请编号前缀查询已办理任务
	 * @param response
	 * @param preAppNo
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/getAppTypeListByPre",method=RequestMethod.POST)
	public void getAppTypeListByPre(HttpServletResponse response,String preAppNo,Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page, rows);
		Long count = Long.parseLong(workflowService.getHisList(preAppNo, pageUtil)[1].toString());
		GridModel gridModel = new GridModel();
    	gridModel.setRows((List<AuditProcHis>)workflowService.getHisList(preAppNo, pageUtil)[0]);
    	gridModel.setTotal(count);
    	OutputJson(response, gridModel);
	}
	
	/**
	 * 
	 * @author:lida
	 * @time:2015年12月3日 09:33:45
	 * @Title:getAppTypeListByPre
	 * @Description:TODO（这里描述这个方法的作用）根据申请编号前缀查询已办理任务
	 * @param response
	 * @param preAppNo
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/saveSubmitTaskBatch",method=RequestMethod.POST)
	public void saveSubmitTaskBatch(HttpServletResponse response,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult){
		boolean isSuccess = true;
		try {
			workflowService.saveSubmitTaskBatch(taskModel);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(response,getMessage(isSuccess));
	}
	
}
