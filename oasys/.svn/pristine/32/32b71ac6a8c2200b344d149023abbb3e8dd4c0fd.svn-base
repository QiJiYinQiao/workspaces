package com.oasys.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.AuditProcHis;
import com.oasys.model.CardApp;
import com.oasys.model.CardAppAttach;
import com.oasys.model.Role;
import com.oasys.model.Users;
import com.oasys.service.AuditPorcService;
import com.oasys.service.CardAppAttachService;
import com.oasys.service.CardApplyService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.UserService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.CardAppAttachModel;
import com.oasys.viewModel.CardAppVO;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.UsersModel;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 
 * @author Guo
 *
 */
@Controller 
public class CardAppController extends BaseController{
	private static Logger logger = Logger.getLogger(CardAppController.class);
	@Autowired
	private CardApplyService cardApplyService;
	@Autowired
	private AuditPorcService auditPorcService;
	@Autowired
	private UserService userService;
	@Autowired
	private CardAppAttachService cardAppAttachService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;

	/**
	 * 展示名片
	 * @Title: index 
	 * @Description: 展示名片
	 * @param @param response
	 * @param @param request
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午10:59:50
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/callingCard/index")
	public String index(HttpServletResponse response,HttpServletRequest request,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		map.put("status", request.getParameter("procStatus"));
		map.put("dateBegin", request.getParameter("appDateBefore"));
		map.put("dateEnd", request.getParameter("appDateAfter"));
		map.put("daetApplyBegin", request.getParameter("appApplyDateBefore"));
		map.put("daetApplyEnd", request.getParameter("appApplyDateAfter"));
		gridModel.setRows(cardApplyService.getList(map,pageUtil,1));//0查询所有人的申请,1查询登录人申请
		gridModel.setTotal(cardApplyService.getCount(map,1));
		OutputJson(response,gridModel);
		return null;
	}
	
	/**
	 * 展示名片附件
	 * @Title: indexCardAppAttach 
	 * @Description: 展示名片附件
	 * @param @param response
	 * @param @param request
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:08
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/callingCard/indexCardAppAttach")
	public String indexCardAppAttach(HttpServletResponse response,HttpServletRequest request,String caID){
		List<CardAppAttachModel> list = cardAppAttachService.getList(caID);
		OutputJson(response,list);
		return null;
	}
	
	/**
	 * 删除名片
	 * @Title: removeCard 
	 * @Description: 删除名片
	 * @param @param id
	 * @param @param map
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月29日 下午2:07:40
	 * @throws
	 */
	@RequestMapping(value="/callingCard/removeCard")
	public String removeCard(@RequestParam("id")String id,ModelMap map,HttpServletResponse response){
		boolean flag=cardApplyService.delCard(Integer.parseInt(id+""));
		if(flag){
	 		 OutputJson2(response,new Json("提示","删除申请成功!",flag));
	 	}else {
	 		 OutputJson2(response,new Json("提示","删除申请失败!",flag));
		}
		return null;
	}
	
	/**
	 * 删除名片附件为未保存的
	 * @Title: removeCardAccess 
	 * @Description: 删除名片附件
	 * @param @param id
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月13日 上午10:45:06
	 * @throws
	 */
	@RequestMapping(value="/callingCard/removeCardAccess")
	public String removeCardAccess(@RequestParam("id")String id,HttpServletResponse response){
		boolean flag=cardApplyService.removeCardAccessory();
		OutputJson(response,getMessage(flag));
		return null;
	}
	

	/**
	 * 删除名片附件
	 * @Title: removeCardAttach 
	 * @Description: 删除名片附件
	 * @param @param id
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月29日 下午2:07:29
	 * @throws
	 */
	@RequestMapping(value="/callingCard/removeCardAttach")
	public String removeCardAttach(@RequestParam("id")String id,HttpServletResponse response,HttpServletRequest request){
		boolean flag=cardAppAttachService.delCardAttach(request.getParameter("id"));
		if(flag){
	 		 OutputJson2(response,new Json("提示","删除申请成功!",flag));
	 	}else {
	 		 OutputJson2(response,new Json("提示","删除申请失败!",flag));
		}
		OutputJson(response,getMessage(flag));
		return null;
	}
	
	/**
	 * 添加名片附件申请
	 * @Title: addCard 
	 * @Description: 添加名片附件申请
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:20
	 * @throws
	 */
	@RequestMapping(value="/callingCard/addCard",method=RequestMethod.POST )
	public String addCard(HttpServletRequest request,HttpServletResponse response){
		//名片附件表
		CardAppAttach e = new CardAppAttach();
		if(org.apache.commons.lang3.StringUtils.isNotBlank(request.getParameter("caId"))){
			e.setCaId(Integer.parseInt(request.getParameter("caId")));
		}
		e.setAppNo(request.getParameter("appNo"));
		/*if(request.getParameter("appNo")==null || "".equals(request.getParameter("appNo"))){
			e.setAppNo(null);//申请编号
		}else{
			e.setAppNo(request.getParameter("appNo"));//申请编号
		}*/
		if("0".equals(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getDeptLevel())){//总部
			e.setApplicantNo(Constants.getCurrendUser().getUserId());//申请人
			e.setDeptNo(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId());//部门
			Role role = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId()).get(0);
			e.setPosition(role.getName());//申请人职位
			Users users = role.getUserRoles().iterator().next().getUsers();
			e.setPersonalTel(users.getMobile());
			e.setOfficeTel(users.getTel());
			e.setEmail(users.getEmail());
		}else{
			e.setApplicantNo(Integer.parseInt(request.getParameter("applicantNo")));//申请人
			e.setDeptNo(Integer.parseInt(request.getParameter("deptNo")+""));//部门
			Role role = userService.findRoleListByUserId(Integer.parseInt(request.getParameter("applicantNo"))).get(0);
			e.setPosition(role.getName());//申请人职位
			Users users = role.getUserRoles().iterator().next().getUsers();
			e.setPersonalTel(users.getMobile());
			e.setOfficeTel(users.getTel());
			e.setEmail(users.getEmail());
		}
		e.setBranchAddr(request.getParameter("branchAddr"));//分公司地址
		e.setComUrl(request.getParameter("comUrl"));//网址
		e.setAppQty(Integer.parseInt(request.getParameter("appQty")));//申请数量
		e.setPrice(new BigDecimal(request.getParameter("price")));//单价
		e.setUnit("盒");//申请单位
		e.setRemark(request.getParameter("remark"));//备注
		boolean flag=cardAppAttachService.addCardAppAttach(e);
		/*if(e.getAppNo()!=null && !"null".equals(e.getAppNo())){
			cardApplyService.updateCountByAppQty(e.getAppNo());
		}*/
		OutputJson(response,getMessage(flag));
		return null;
	}
	
	/**
	 * 保存名片申请
	 * @Title: saveCardApp 
	 * @Description: 保存名片申请
	 * @param @param httpServletResponse
	 * @param @param cardAppAttach
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:51:52
	 * @throws
	 */
	@RequestMapping("/callingCard/saveCardApp.do")
	public String saveCardApp(HttpServletResponse httpServletResponse,CardAppAttach cardAppAttach,String price){
		if(cardAppAttach.getName()==null || "".equals(cardAppAttach.getName())){
			cardAppAttach.setApplicantNo(Constants.getCurrendUser().getUserId());//获取当前登录人提交人 总部
		}
		if(cardAppAttach.getDeptNo()==null){
			cardAppAttach.setDeptNo(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId());//获取当前登录人的部门 总部
		}
		CardApp cardApp = cardApplyService.saveCardAttach(cardAppAttach,price);//保存附件表和主表
		OutputJson(httpServletResponse,cardApp);
		return null;
	}
	
	/**
	 * 查询添加的申请
	 * @Title: findCardAttList 
	 * @Description: 查询添加的申请
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @param deptNo
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:52:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="callingCard/findCardAttList",method=RequestMethod.POST)
	public String findCardAttList(HttpServletResponse httpServletResponse,String appNo,String deptNo,Integer page,Integer rows){
		//增加
		if(deptNo==null || "".equals(deptNo)){
			deptNo=organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId()+"";
		}
		//申请人条数
		Long atttotal = cardApplyService.findCardAtttotal(appNo, deptNo);
		//申请人信息
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		List<CardAppAttach> attList = cardApplyService.findCardAttList(appNo,deptNo,pageUtil);
		/*if(attList!=null && attList.size()>0){
			for (CardAppAttach cardAppAttach : attList) {
				Users user = userService.findUserById(cardAppAttach.getApplicantNo());
				//申请人名字
				cardAppAttach.setName(user.getName());
				//部门名字
				String fullName=organizationService.getOrgNameByID(cardAppAttach.getDeptNo());
				cardAppAttach.setFullName(fullName);
				//职位名字
				String code=cardAppAttach.getPosition();
				cardAppAttach.setPositionName(code);
			}
			
		}*/
		OutputJson(httpServletResponse, new GridModel(attList, atttotal) );
		return null;
	}
	
	/**
	 * 删除名片申请
	 * @Title: deleteCardAttList 
	 * @Description: 删除名片申请
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:53:22
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/callingCard/delCardApp",method=RequestMethod.POST)
	public String deleteCardAttList(HttpServletResponse httpServletResponse,String ids){
		boolean flag = cardApplyService.delCardApply(ids);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	/**
	 * 添加名片
	 * @Title: addCardTable 
	 * @Description: 添加名片
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:41
	 * @throws
	 */
	@RequestMapping(value="/callingCard/addCardTable")
	public String addCardTable(HttpServletRequest request,HttpServletResponse response){
		ShiroUser shiroUser = Constants.getCurrendUser();
		CardApp  cardApp = new CardApp();
		cardApp.setAppNo(UniqueIdUtil.generate("MP"));//唯一编号
		cardApp.setRegistrantNO(shiroUser.getUserId());//登记人Id
		cardApp.setRegDeteTime(DateUtils.parse(DateUtils.getNowTime()));//登记日期
		cardApp.setAppDate(DateUtils.parse(DateUtils.getNowTime(),DateUtils.DATE_SMALL_STR));//申请日期
		cardApp.setAppStatus(null);//申请状态
		cardApp.setAppQty(cardAppAttachService.getCountByCardApp(null).intValue());//申请数量
		cardApp.setProcStatus(1);//流程状态
		cardApp.setUnit("盒");//单位
		cardApp.setRemark(cardAppAttachService.getCountByCardApp(null)+"");//备注
		boolean flag=cardApplyService.addCard(cardApp);
		if(flag){
			cardAppAttachService.modifyCardAttachCaId(cardApp.getAppNo());
		}
		OutputJson(response,getMessage(flag));
		return null;
	}
	
	/**
	 * 查看审批意见
	 * @Title: getAuditPorc 
	 * @Description: 查看审批意见
	 * @param @param appNo
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:51
	 * @throws
	 */
	@RequestMapping(value="/callingCard/getAuditPorc")
	public String getAuditPorc(@RequestParam("appNo")String appNo,HttpServletResponse response){
		List<AuditProcHis> list = auditPorcService.getAuditPorcList(appNo);
		OutputJson(response, list);
		return null;
	}
	
	/**
	 * 加载姓名
	 * @Title: getUserInfo 
	 * @Description: 加载姓名
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:58
	 * @throws
	 */
	@RequestMapping(value="/callingCard/getUserInfo")
	public String getUserInfo(@RequestParam("q")String q,HttpServletResponse response){
		/*GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findOrgUserList(q));
		OutputJson(response, gridModel);*/
		List<ComboBoxModel> userList = userService.findOrgUserList(q);
		OutputJson(response, userList);
		return null;
	}
	
	/**
	 * 根据姓名加载信息
	 * @Title: loadUserInfo 
	 * @Description: 根据姓名加载信息
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:01:14
	 * @throws
	 */
	@RequestMapping(value="/callingCard/loadUserInfo")
	public String loadUserInfo(@RequestParam("q")String q,HttpServletResponse response){
		List<UsersModel> list=null;
		try {
			list = userService.loadUserInfo(Integer.parseInt(q));
			OutputJson(response,list.get(0));
		} catch (NumberFormatException e) {
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * 加载部门
	 * @Title: getDeptInfo 
	 * @Description: 加载部门
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月20日 下午7:48:43
	 * @throws
	 */
/*	@RequestMapping(value="/callingCard/getDeptInfo")
	public String getDeptInfo(HttpServletResponse response){
		GridModel gridModel = new GridModel();
		gridModel.setRows(organizationService.findOrganizationByIdList());
		OutputJson(response, gridModel);
		return null;
	}*/

	
	/**
	 * 提交申请
	 * @Title: saveCardApply 
	 * @Description: 提交申请
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:02:13
	 * @throws
	 */
	@RequestMapping(value="/callingCard/saveCardApply")
	public String saveCardApply(@RequestParam("id")String q,HttpServletResponse response){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr=cardApplyService.submitCardApply(Integer.parseInt(q));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(response,json);
		return null;
	}
	
	/**
	 * 查看待办任务
	 * @Title: findAllPurchaseAppTaskList 
	 * @Description: 查看待办任务
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月24日 上午9:35:24
	 * @throws
	 */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/QueryCardTask",method=RequestMethod.POST)
	 public String findAllPurchaseAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
	    	Integer firstResult = (page-1)*rows;
	        Integer maxResults = rows;
	        GridModel gridModel = new GridModel();
	        gridModel.setRows(cardApplyService.getTaskByGroup(firstResult,maxResults,workFlowTasksModel));
	        OutputJson(httpServletResponse, gridModel);
	    	return null;
	  }
	 
	 /**
	  * 签收任务
	  * @Title: signTask 
	  * @Description: 签收任务
	  * @param @param taskId
	  * @param @param httpServletResponse
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月9日 下午2:24:31
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/SignTask",method=RequestMethod.POST)
	 public String signTask(@RequestParam("taskId")String taskId,HttpServletResponse httpServletResponse){
		 	boolean flag=cardApplyService.signTask(taskId);
		 	if(flag){
		 		 OutputJson2(httpServletResponse,new Json("提示","提交申请成功!",flag));
		 	}else {
		 		 OutputJson2(httpServletResponse,new Json("提示","提交申请失败!",flag));
			}
	    	return null;
	  }
	 
	 
	 /**
	  * 办理任务
	  * @Title: handleTast 
	  * @Description: 办理任务
	  * @param @param status
	  * @param @param taskId
	  * @param @param isSuccess
	  * @param @param httpServletResponse
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月9日 下午2:24:43
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/handleTask",method=RequestMethod.POST)
	public String saveTaskEmpSalPositionChgApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = cardApplyService.saveSubmitTask(taskModel);//执行受理申请
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	 
	 /**
	  * 获取办理任务
	  * @Title: getAcceptTask 
	  * @Description: 获取办理任务
	  * @param @param httpServletResponse
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月9日 下午2:24:59
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/getAcceptTask",method=RequestMethod.POST)
	 public String getAcceptTask(HttpServletResponse httpServletResponse){
		 	List<CardAppVO> list = cardApplyService.getAcceptTask();
	        OutputJson2(httpServletResponse, list);
	    	return null;
	  }
	 
	 /**
	  * 查看流程图
	  * @Title: showProcessImg 
	  * @Description: 查看流程图
	  * @param @param httpServletResponse
	  * @param @param paId
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月14日 下午6:50:10
	  * @throws
	  */
	 @RequestMapping(value="/callingCard/showProcessImg",method=RequestMethod.GET)
     public String showProcessImg(HttpServletResponse response,Integer caId){
	 	cardApplyService.getDiagramResourceByCaId(response, caId);
    	return null;
     }
	 
	 /**
	  * 查询申请人部门内所有满足入职满1个月的用户(分部)
	  * @Title: getUserByDept 
	  * @Description: 查询申请人部门内所有满足入职满1个月的用户(分部)
	  * @param @param response
	  * @param @param organizeId
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年12月2日 上午11:11:02
	  * @throws
	  */
	 @RequestMapping(value="/callingCard/getUserByDept")
	 public String getUserByDept(HttpServletResponse response,Integer organizeId){
		 List<ComboBoxModel> list = cardApplyService.getUserByDeptNo(organizeId);
		 OutputJson(response, list);
		 return null;
	 }
	 
	 @RequestMapping(value="/callingCard/isHeadOrBranch")
	 public String getTypeByIsHeadOrBranch(HttpServletResponse response){
		 boolean flag = false;//分部
		 if("0".equals(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getDeptLevel())){
			flag = true;
		 }
		 OutputJson(response, getMessage(flag));
		 return null;
	 }
	 
}
