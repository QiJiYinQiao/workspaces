package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.BusinessTripAttach;
import com.oasys.model.SystemCode;
import com.oasys.model.TravelExpensesApp;
import com.oasys.model.TravelExpensesOther;
import com.oasys.service.SystemCodeService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
/**
 * 差率报销申请
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/TravelExpensesApp")
public class TravelExpensesAppController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private SystemCodeService SystemCodeService;
	@Autowired
	private TravelExpensesAppService travelExpensesAppService;
	
	/**
	 * 查询差率报销列表
	 * @Title: findLeaveAppList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param leaveApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月13日 下午3:02:14
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findTravelExpensesAppList",method=RequestMethod.POST)
	public String findTravelExpensesAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("travelExpensesApp") TravelExpensesApp travelExpensesApp,  BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<TravelExpensesApp> travelAppList = travelExpensesAppService.findTravelAppList(travelExpensesApp, pageUtil);
		Long count = travelExpensesAppService.findtravelAppListCount(travelExpensesApp);
		OutputJson2(httpServletResponse,new GridModel(travelAppList, count));
		return null;
	}
	
	/**
	 * 计算出差补助
	 * @Title: setTravelSubsidyAmt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param travelExpensesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月19日 上午10:21:02
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/setTravelSubsidyAmt",method=RequestMethod.POST)
	public String setTravelSubsidyAmt(HttpServletResponse httpServletResponse,Double btDays){
		double subsidyAmt = travelExpensesAppService.setTravelSubsidyAmt(btDays);
		OutputJson(httpServletResponse, subsidyAmt);
		return null;
	}
	
	/**
	 * 查询申请人所有借款
	 * @Title: findUserLoan 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param btDays
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月25日 下午2:01:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUserLoanList",method=RequestMethod.POST)
	public String findUserLoanList(HttpServletResponse httpServletResponse){
		List<ComboBoxModel> list = travelExpensesAppService.findLoanByUserId();
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/**
	 * 保存差率报销申请主表信息
	 * @Title: saveTravelExpensesApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param travelExpensesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月16日 上午10:56:35
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTravelExpensesApp",method=RequestMethod.POST)
	public String saveTravelExpensesApp(HttpServletResponse httpServletResponse,@ModelAttribute("travelExpensesApp") TravelExpensesApp travelExpensesApp,  BindingResult bindingResult){
		TravelExpensesApp expensesApp = travelExpensesAppService.saveTravelExpensesApp(travelExpensesApp);
		OutputJson2(httpServletResponse,expensesApp);
		return null;
	}
	
	/**
	 * 查询申请人已选的借款申请
	 * @Title: findSelectUserLoan 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月25日 下午4:37:19
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findSelectUserLoan",method=RequestMethod.POST)
	public String findSelectUserLoan(HttpServletResponse httpServletResponse,String appNo){
		List<ComboBoxModel> list = travelExpensesAppService.findUserLoan(appNo);
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/***
	 * 查询费用总项目
	 * @Title: findSysDictList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月13日 下午5:36:47
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findSysDictList",method=RequestMethod.POST)
	public String findSysDictList(HttpServletResponse httpServletResponse){
		List<SystemCode> list = SystemCodeService.findSystemCodeByType(Constants.TRAVELEXPENSES_TYPE);
		OutputJson2(httpServletResponse,list);
		return null;
	}
	
	/**
	 * 删除差旅报销申请信息
	 * @Title: deleteTravelExpensesApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 上午9:23:35
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deleteTravelExpensesApp",method=RequestMethod.POST)
	public String deleteTravelExpensesApp(HttpServletResponse httpServletResponse,String appNo){
		boolean flag = travelExpensesAppService.deleteTravelExpensesApp(appNo);
		if(flag){
			OutputJson(httpServletResponse,new Json("提示", "差旅报销申请删除成功！", flag));
		}else{
			OutputJson(httpServletResponse,new Json("提示", "差旅报销申请删除失败！", flag));
		}
		return null;
	}
	
	
	/**
	 * 保存费用申请项目信息
	 * @Title: saveTravelOther 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param travelExpensesOther
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月13日 下午5:42:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTravelOther",method=RequestMethod.POST)
	public String saveTravelOther(HttpServletResponse httpServletResponse,@ModelAttribute("travelExpensesOther") TravelExpensesOther travelExpensesOther,  BindingResult bindingResult){
		String appNo = travelExpensesAppService.savetravelExpensesOther(travelExpensesOther);
		OutputJson(httpServletResponse, appNo);
		return null;
	}
	
	/**
	 * 批量删除费用项目
	 * @Title: deleteTravelOther 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月16日 上午10:39:32
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deleteTravelOther",method=RequestMethod.POST)
	public String deleteTravelOther(HttpServletResponse httpServletResponse,String ids,String appNo){
		boolean flag = travelExpensesAppService.deleteTravelOther(ids,appNo);
		if(flag){
			OutputJson(httpServletResponse,new Json("提示", "费用项目删除成功！", flag));
		}else{
			OutputJson(httpServletResponse,new Json("提示", "费用项目删除失败！", flag));
		}
		return null;
	}
	
	/**
	 * 查询申请下的附加表信息列表
	 * @Title: findTravelOtherList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月16日 上午11:55:42
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findTravelOtherList",method=RequestMethod.POST)
	public String findTravelOtherList(HttpServletResponse httpServletResponse,String appNo,Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<TravelExpensesOther> otherList = travelExpensesAppService.findTravelOtherList(appNo, pageUtil);
		Long count = travelExpensesAppService.findTravelOtherCount(appNo);
		OutputJson2(httpServletResponse,new GridModel(otherList, count));
		return null;
	}
	
	
	/**
	 * 查询流程图
	 * @Title: showBadgeProcess 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param teaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午4:09:15
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showBadgeProcess",method=RequestMethod.GET)
	public String showBadgeProcess(HttpServletResponse httpServletResponse,Integer teaId){
		travelExpensesAppService.getDiagramResourceByPaId(httpServletResponse, teaId);
		return null;
	}
	
	/**
	 * 交通费用信息列表
	 * @Title: findBusinessAttList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月18日 下午5:49:18
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findBusinessAttList",method=RequestMethod.POST)
	public String findBusinessAttList(HttpServletResponse httpServletResponse,String appNo){
		List<BusinessTripAttach> businessAtt = travelExpensesAppService.findBusinessAtt(appNo);
		Long count = travelExpensesAppService.findBusinessAttCount(appNo);
		OutputJson2(httpServletResponse,new GridModel(businessAtt, count));
		return null;
	}
	
	/**
	 * 批量删除交通费用项目信息
	 * @Title: deleteBusinessTripAtt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月18日 下午5:59:45
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBusinessTripAtt",method=RequestMethod.POST)
	public String deleteBusinessTripAtt(HttpServletResponse httpServletResponse,String ids,String appNo){
		boolean flag = travelExpensesAppService.deleteBusinessTripAtt(ids, appNo);
		if(flag){
			OutputJson(httpServletResponse,new Json("提示", "交通费项目删除成功！", flag));
		}else{
			OutputJson(httpServletResponse,new Json("提示", "交通费项目删除失败！", flag));
		}
		return null;
	}
	
	/**
	 * 保存交通费用
	 * @Title: saveBusinessTripAtt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param businessTripAttach
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月19日 上午9:25:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveBusinessTripAtt",method=RequestMethod.POST)
	public String saveBusinessTripAtt(HttpServletResponse httpServletResponse,@ModelAttribute("businessTripAttach") BusinessTripAttach businessTripAttach,BindingResult bindingResult){
		String appNo = travelExpensesAppService.savebusinessTripAtt(businessTripAttach);
		OutputJson(httpServletResponse, appNo);
		return null;
	}
	
	/**
	 * 查询交通工具列表
	 * @Title: findBusinVehicleLIst 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月19日 上午9:56:25
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findBusinVehicleLIst",method=RequestMethod.POST)
	public String findBusinVehicleLIst(HttpServletResponse httpServletResponse){
		List<SystemCode> list = SystemCodeService.findSystemCodeByType(Constants.BUSINESSTRIPATTACHE);
		
		OutputJson(httpServletResponse, list);
		return null;
	}
}


