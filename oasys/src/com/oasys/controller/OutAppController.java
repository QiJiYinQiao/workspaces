package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.font.EAttribute;

import com.oasys.model.AuditProcHis;
import com.oasys.model.OutApp;
import com.oasys.model.PPEScrapApp;
import com.oasys.model.VO.OutAppModel;
import com.oasys.service.OutAppService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请Controller
 * @ClassName: OutAppController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:18:55
 */
@Controller
@RequestMapping("/outAppController")
public class OutAppController extends BaseController{

	@Autowired
	private OutAppService outAppService;
	/**
	 * 申请人下拉列表框
	 * @Title: createCombogrid 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月26日 下午3:10:56
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/createCombogrid",method=RequestMethod.POST)
	public String createCombogrid(HttpServletResponse httpServletResponse,String q){
		GridModel gridModel = new GridModel();
		gridModel.setRows(outAppService.findCreateCombogrid(q));
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 新增
	 * @Title: saveOutApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param outApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午1:24:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveOutApp",method=RequestMethod.POST)
	public String saveOutApp(HttpServletResponse httpServletResponse,@ModelAttribute("outApp") OutApp outApp, BindingResult bindingResult){
		OutputJson(httpServletResponse,outAppService.saveOutApp(outApp));
		return null;
	}
	/**
	 * 查询列表
	 * @Title: findOutAppList 
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
	@RequestMapping(value="/findOutAppList",method=RequestMethod.POST)
	public String findOutAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,OutAppModel outAppModel){
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(outAppService.findOutAppList(pageUtil,outAppModel));
		gridModel.setTotal(outAppService.countFindOutAppList(outAppModel));
		OutputJson(httpServletResponse,gridModel);
		return null;
	}
	/**
	 * 删除
	 * @Title: delOutAppbyBtaId 
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
	@RequestMapping(value="/delOutAppByOutId",method=RequestMethod.POST)
	public String delOutAppByOutId(HttpServletResponse httpServletResponse,Integer outId){
		boolean bl = outAppService.delOutAppByOutId(outId);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","删除成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,删除失败!",bl));
		}
		return null;
	}
	/**
	 * 提交申请（开启流程）
	 * @Title: sumitApply 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param outApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 上午10:53:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/sumitApply",method=RequestMethod.POST)
	public String sumitApply(HttpServletResponse httpServletResponse,OutApp outApp){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = outAppService.sumitApply(outApp);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse,json);
		return null;
	}
	/**
	 * 查询所有任务list
	 * @Title: findAllOutAppTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 下午1:21:00
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findAllOutAppTaskList",method=RequestMethod.POST)
	public String findAllOutAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
		GridModel gridModel = new GridModel();
		gridModel.setRows(outAppService.findAllOutAppTaskList(new PageUtil(page, rows),workFlowTasksModel));
		gridModel.setTotal(outAppService.countFindAllOutAppTaskList());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
     * 签收任务
     * @Title: holdWorkTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param taskId
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午1:21:00
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/holdWorkTask",method=RequestMethod.POST)
    public String holdWorkTask(HttpServletResponse httpServletResponse,String taskId){
    	boolean bl = outAppService.saveHoldWorkTask(taskId);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","签收任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,签收任务失败!",bl));
    	}
    	return null;
    }
    /**
     * 办理任务（分部）
     * @Title: submitTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param purchaseAppModel
     * @param @param result
     * @param @param auditProcHis
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午2:32:36
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTask",method=RequestMethod.POST)
    public String submitTask(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,BindingResult bindingResult){
    	boolean saveSuccess = true;
    	String resultStr = "";
    	try {
    		resultStr = outAppService.saveSubmitTaskBo(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
    	Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
    	return null;
    }
    /**
     * 办理任务（总部）
     * @Title: submitTask2 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param outAppModel
     * @param @param result
     * @param @param auditProcHis
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午8:02:12
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTask2",method=RequestMethod.POST)
    public String submitTask2(HttpServletResponse httpServletResponse,OutAppModel outAppModel,String result,AuditProcHis auditProcHis){
    	boolean bl = outAppService.saveSubmitTaskHo(outAppModel, result, auditProcHis);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","办理任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,办理任务失败!",bl));
    	}
    	return null;
    }
    /**
     * 查看流程图
     * @Title: showProcessImg 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param paId
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月9日 下午3:35:52
     * @throws
     */
    @RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer efaId){
    	outAppService.getDiagramResourceByPaId(httpServletResponse,efaId);
    	return null;
    }
}
