package com.oasys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oasys.model.CredentialsApp;
import com.oasys.model.CredentialsAppAttach;
import com.oasys.service.CredentialsAppAttachService;
import com.oasys.service.CredentialsAppService;
import com.oasys.service.OrganizationService;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;

@Controller
public class CredentialsAppController extends BaseController{
	@Autowired
	private CredentialsAppService credentialsAppService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CredentialsAppAttachService credentialsAppAttachService;
	
	/**
	 * 展示数据
	 * @Title: index 
	 * @Description: 展示数据
	 * @param @param response
	 * @param @param request
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月20日 下午5:54:49
	 * @throws
	 */
	@RequestMapping("/credentials/index")
	public String index(HttpServletResponse response,HttpServletRequest request,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		map.put("procStatus", request.getParameter("procStatus"));
		map.put("appDateBefore", request.getParameter("queryAppDateBegin"));
		map.put("appDateAffter", request.getParameter("queryAppDateEnd"));
		map.put("useDateBefore", request.getParameter("queryUseDateBegin"));
		map.put("useDateAffer", request.getParameter("queryUseDateEnd"));
		gridModel.setRows(credentialsAppService.getList(map,pageUtil));
		gridModel.setTotal(credentialsAppService.getCount(map));
		OutputJson2(response,gridModel);
		return null;
	}
	
	/**
	 * 增加数据
	 * @Title: addCredentialsApp 
	 * @Description: 增加数据
	 * @param @param response
	 * @param @param request
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月20日 下午5:55:03
	 * @throws
	 */
	@RequestMapping("/credentials/addCredetialsApp")
	public String addCredentialsApp(HttpServletResponse response,HttpServletRequest request){
		CredentialsApp credentialsApp = new CredentialsApp();
		if(!"".equals(request.getParameter("uqaId")) && request.getParameter("uqaId")!=null){
			credentialsApp.setUqaId(Integer.parseInt(request.getParameter("uqaId")));
		}
		credentialsApp.setAppDate(new Date());//申请时间
		credentialsApp.setApplicantNo(Constants.getCurrendUser().getUserId());//申请人
		if(!"".equals(request.getParameter("appNo")) && request.getParameter("appNo")!=null){
			credentialsApp.setAppNo(request.getParameter("appNo"));//申请编号
		}else{
			credentialsApp.setAppNo(UniqueIdUtil.generate("ZZ"));//申请编号
		}
		credentialsApp.setAppStatus(null);
		credentialsApp.setProcStatus(Constants.PROC_INIT);//流程状态
		credentialsApp.setDeptNo(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId());//部门编号
		credentialsApp.setRemark(request.getParameter("remark"));//备注
		boolean flag=false;
		String[] str = request.getParameter("selectedCheckbox").split(",");
		List<String> list = new ArrayList<String>();  
	    for(int i = 0; i < str.length; i++) {
	        if(!list.contains(str[i])) {  
	            list.add(str[i]);  
	        }
	    }
	    for (int i = 0; i < list.size(); i++) {
			CredentialsAppAttach credentialsAppAttach = new CredentialsAppAttach();
			if(!"".equals(request.getParameter("auqaId"))&&request.getParameter("auqaId")!=null){
				credentialsAppAttach.setUqaId(Integer.parseInt(request.getParameter("auqaId")));
			}
			credentialsAppAttach.setAppNo(credentialsApp.getAppNo());//申请编号
			if(request.getParameter((list.get(i)+"Name"))!=null){
				credentialsAppAttach.setCsDesc(request.getParameter((list.get(i)+"Name")));//证章描述
			}
			if("CaiWuZhang".equals(list.get(i))){
				credentialsAppAttach.setCsType("1");
			}else if("FaRenZhang".equals(list.get(i))){
				credentialsAppAttach.setCsType("2");
			}else if("FaRenShenFenZheng".equals(list.get(i))){
				credentialsAppAttach.setCsType("3");
			}else if("HeTong".equals(list.get(i))){
				credentialsAppAttach.setCsType("5");
			}else if("ZiZhi".equals(list.get(i))){
				credentialsAppAttach.setCsType("4");
			}else if("QiTa".equals(list.get(i))){
				credentialsAppAttach.setCsType("6");
			}
			if(request.getParameter(("useDesc"+list.get(i)))!=null){
				credentialsAppAttach.setCsPurPose(request.getParameter(("useDesc"+list.get(i))));//证章用途
			}
			if(request.getParameter(("isLetOut"+list.get(i)))!=null){
				credentialsAppAttach.setIsLetOut(request.getParameter(("isLetOut"+list.get(i))));//财务章是否外借
			}
			if(request.getParameter(("isOriginal"+list.get(i)))!=null){
				credentialsAppAttach.setIsOriginal(request.getParameter(("isOriginal"+list.get(i))));//合同是否原件
			}else {
				credentialsAppAttach.setIsOriginal("0");//合同是否原件
			}
			if(request.getParameter("planUseDate")!=null && StringUtils.isNotBlank(request.getParameter("planUseDate"))){
				credentialsAppAttach.setPlanUseDate(DateUtils.parse(request.getParameter("planUseDate"),DateUtils.DATE_SMALL_STR));//计划使用日期
			}
			if(request.getParameter("planRestDate")!=null && StringUtils.isNotBlank(request.getParameter("planRestDate"))){
				credentialsAppAttach.setPlanRestDate(DateUtils.parse(request.getParameter("planRestDate"),DateUtils.DATE_SMALL_STR));//计划归还日期
			}
			credentialsAppAttach.setIsRestored("0");//是否归还
			flag=credentialsAppAttachService.saveCredentialsAppAttach(credentialsAppAttach);//保存附件表
	    }
	    if(flag){
	    	flag=credentialsAppService.saveCredentialsApp(credentialsApp);//保存主表
	    }
		OutputJson(response,getMessage(flag));
		return null;
	}
	
	/**
	 * 附件表展示信息，根据对应编号查询对应的申请信息
	 * @Title: getAttachList 
	 * @Description: 附件表展示信息，根据对应编号查询对应的申请信息
	 * @param @param response
	 * @param @param appNo
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月23日 下午5:53:27
	 * @throws
	 */
	@RequestMapping("/credentials/getAttachList")
	public String getAttachList(HttpServletResponse response,String appNo,Integer page,Integer rows){
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		gridModel.setRows(credentialsAppAttachService.getList(appNo,pageUtil));
		gridModel.setTotal(credentialsAppAttachService.getCount(appNo));
		OutputJson2(response,gridModel);
		return null;
	}
	
	/**
	 * 删除附件表信息
	 * @Title: delCredentialsAttach 
	 * @Description: 删除附件表信息
	 * @param @param response
	 * @param @param id
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月23日 下午5:53:58
	 * @throws
	 */
	@RequestMapping("/credentials/delCredentialsAttach")
	public String delCredentialsAttach(HttpServletResponse response,String id){
		boolean flag = credentialsAppAttachService.delCredentialsAppAttach(id);
		if(flag){
			 OutputJson2(response,new Json("提示","删除数据成功!",flag));
		}else {
			 OutputJson2(response,new Json("提示","删除数据失败!",flag));
		}
		return null;
	}
	
	/**
	 * 删除主表申请
	 * @Title: delCredentials 
	 * @Description: 删除主表申请
	 * @param @param response
	 * @param @param id
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月23日 下午5:54:10
	 * @throws
	 */
	@RequestMapping("/credentials/delCredentials")
	public String delCredentials(HttpServletResponse response,Integer id){
		boolean flag=credentialsAppAttachService.delCredentialsAppAttachByAppNo(id);
		if(flag){
			 credentialsAppService.delCredentialsApp(id);
			 OutputJson2(response,new Json("提示","删除申请成功!",flag));
		}else{
			 OutputJson2(response,new Json("提示","删除申请失败!",flag));
		}
		return null;
	}
}
