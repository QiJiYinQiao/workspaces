package com.oasys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oasys.model.PenaltyNoticeReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.PenaltyNoticeRegService;
import com.oasys.service.UserService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.TreeModel;
import com.sun.star.lib.uno.protocols.urp.urp;

@Controller
public class PenaltyNoticeRegController extends BaseController{
	@Autowired
	private PenaltyNoticeRegService penaltyNoticeReg;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	
	/**
	 * 展示数据
	 * @Title: index 
	 * @Description: 展示数据
	 * @param @param request
	 * @param @param response
	 * @param @param rows
	 * @param @param page
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月14日 下午2:26:23
	 * @throws
	 */
	@RequestMapping(value="penaltyNoticeReg/index")
	public String index(HttpServletRequest request,HttpServletResponse response,Integer rows,Integer page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", request.getParameter("beginTime"));
		map.put("endTime", request.getParameter("endTime"));
		map.put("fineInfo", request.getParameter("fineInfo"));
		map.put("fineType", request.getParameter("fineType"));
		map.put("fineUserDept", request.getParameter("fineUserDept"));
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		gridModel.setRows(penaltyNoticeReg.getList(map, pageUtil));
		gridModel.setTotal(penaltyNoticeReg.getCount(map));
		OutputJson(response,gridModel);
		return null;
	}
	
	/**
	 * 删除罚款记录
	 * @Title: delPenaltyNoticeReg 
	 * @Description: 删除罚款记录
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月14日 下午3:36:15
	 * @throws
	 */
	@RequestMapping(value="penaltyNoticeReg/delPenaltyNoticeReg")
	public String delPenaltyNoticeReg(HttpServletRequest request,HttpServletResponse response){
		boolean flag=penaltyNoticeReg.delFineInfo(request.getParameter("id"));
		if(flag){
	 		 OutputJson2(response,new Json("提示","删除申请成功!",flag));
	 	}else {
	 		 OutputJson2(response,new Json("提示","删除申请失败!",flag));
		}
		return null;
	}
	/**
	 * 加载部门
	 * @Title: loadDept 
	 * @Description: 加载部门
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月14日 下午4:37:02
	 * @throws
	 */
	@RequestMapping(value="penaltyNoticeReg/loadDept")
	public String loadDept(HttpServletResponse response){
		GridModel gridModel = new GridModel();
		gridModel.setRows(organizationService.findOrganizationList());
		OutputJson(response, gridModel);
		return null;
	}
	
	/**
	 * 根据受罚类型查询受罚对象
	 * @Title: loadFineInfoByType 
	 * @Description: 根据受罚类型查询受罚对象
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月16日 下午5:10:18
	 * @throws
	 */
	@RequestMapping(value="penaltyNoticeReg/loadFineInfo")
	public String loadFineInfoByType(HttpServletRequest request,HttpServletResponse response,String id){
		GridModel gridModel = new GridModel();
		if(Integer.parseInt(id)==1){//受罚部门
			gridModel.setRows(organizationService.findOrganizationList());
		}else{//受罚人
			gridModel.setRows(userService.findAllUserList(null, new PageUtil(1, 10000)));
		}
		OutputJson(response, gridModel);
		return null;
	}
	
	/**
	 * 添加罚款记录
	 * @Title: addFineInfo 
	 * @Description: 添加罚款记录
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月21日 下午4:22:31
	 * @throws
	 */
	@RequestMapping(value="penaltyNoticeReg/addFineInfo")
	public String addFineInfo(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getParameter("pt")+"pt----"+request.getParameter("pnrId")+"pnrId----"+request.getParameter("penaltyReson")+"penaltyReson---"+request.getParameter("signDept")+"signDept---"+request.getParameter("ptType")+"ptType");
		ShiroUser shiroUser = Constants.getCurrendUser();
		PenaltyNoticeReg pn = new PenaltyNoticeReg();
		pn.setPnrId((request.getParameter("pnrId")==null||"".equals(request.getParameter("pnrId")))?null:Integer.parseInt(request.getParameter("pnrId")));
		pn.setPtNo(Integer.parseInt(request.getParameter("pt")));//受罚部门编号
		pn.setRegDateTime(DateUtils.parse(DateUtils.getNowTime()));//登记时间
		pn.setPenaltyAMT(Double.parseDouble(request.getParameter("penaltyAMT")));//罚款金额
		pn.setPenaltyDate(DateUtils.parse(request.getParameter("penaltyDate"),DateUtils.DATE_SMALL_STR));//罚款时间
		pn.setPenaltyReson(request.getParameter("penaltyReson"));//罚款原因
		pn.setSignDeptNo(Integer.parseInt(request.getParameter("signDept")));//罚款单签发部门
		if(request.getParameter("penaltyResonOther")!=null || !"".equals(request.getParameter("penaltyResonOther"))){
			pn.setPenaltyResonOther(request.getParameter("penaltyResonOther"));//其他受罚原因
		}
		pn.setPtType(request.getParameter("ptType"));//受罚类型
		pn.setRegistrantNo(shiroUser.getUserId());//登记人
		pn.setRemark(request.getParameter("remark"));//备注
		boolean flag=penaltyNoticeReg.saveFine(pn);
		if(flag){
	 		 OutputJson2(response,new Json("提示","提交申请成功!",flag));
	 	}else {
	 		 OutputJson2(response,new Json("提示","提交申请失败!",flag));
		}
		return null;
	}
}
