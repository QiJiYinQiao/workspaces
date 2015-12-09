package com.oasys.controller;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.LoanApp;
import com.oasys.service.LoanAppService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author wangxincheng
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *借款申请
 */

@Controller
@RequestMapping("/LoanApp")
public class LoanAppController extends BaseController{
	
	@Autowired
	private  LoanAppService loanAppService;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private SystemCodeService SystemCodeService;
	
	
	/**
	 * 查询你借款列表
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
	 * @date 2015年11月26日 下午3:14:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findLoanAppList",method=RequestMethod.POST)
	public String findLoanAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("loanApp") LoanApp loanApp,  BindingResult bindingResult){
		PageUtil pageUtil = new PageUtil(page,rows);
		List<LoanApp> loanAppList = loanAppService.findLoanAppList(loanApp, pageUtil);
		Long count = loanAppService.findLoanCount(loanApp);
		OutputJson2(httpServletResponse, new GridModel(loanAppList,count));
		return null;
	}
	
	/**
	 * 查询借款人所在部门下的所有人
	 * @Title: findOrgUserList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param loanApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月26日 下午5:06:19
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findOrgUserList",method=RequestMethod.POST)
	public String findOrgUserList(HttpServletResponse httpServletResponse){
		List<ComboBoxModel> list = loanAppService.findUserListByOrgId();
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/**
	 * 保存借款申请
	 * @Title: saveLoanApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param loanApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月26日 下午5:47:07
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveLoanApp",method=RequestMethod.POST)
	public String saveLoanApp(HttpServletResponse httpServletResponse,@ModelAttribute("loanApp") LoanApp loanApp,  BindingResult bindingResult){
		// 判断填写过多少申请
		boolean flag = loanAppService.getLoanPurposeCount(loanApp);
		
		if (flag) {
			OutputJson2(httpServletResponse, new DataModel("提示", "同一个人最多申请两个同中类型借款用途！", false, null));
		}else{
			BigDecimal loanAMT = loanAppService.getUserLoanAMT(loanApp);
			if(loanAMT!=null){
				OutputJson2(httpServletResponse, new DataModel("提示", "该付款人最多申请借款金额为"+loanAMT.doubleValue()+"元！", false, null));
			}else{
				LoanApp app = loanAppService.saveOrUpdLoanApp(loanApp);
				OutputJson2(httpServletResponse, new DataModel("提示", "借款申请保存成功", true, app));
			}
		}
		return null;
	}
	
	/**
	 * 根据申请编号删除借款申请
	 * @Title: deleteLoanApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月27日 上午9:52:50
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deleteLoanApp",method=RequestMethod.POST)
	public String deleteLoanApp(HttpServletResponse httpServletResponse,String appNo){
		boolean flag = loanAppService.deleteLoanApp(appNo);
		if(flag){
			OutputJson(httpServletResponse,new DataModel("提示", "借款申请删除成功！", true));
		}else{
			OutputJson(httpServletResponse,new DataModel("提示", "借款申请删除失败！", false));
		}
		return null;
	}
	
	/**
	 * 查询流程图
	 * @Title: showBadgeProcess 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param btaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月27日 上午11:33:52
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showBadgeProcess",method=RequestMethod.GET)
	public String showBadgeProcess(HttpServletResponse httpServletResponse,Integer btaId){
		loanAppService.getDiagramResourceByPaId(httpServletResponse, btaId);
		return null;
	}
	
	
	
}


