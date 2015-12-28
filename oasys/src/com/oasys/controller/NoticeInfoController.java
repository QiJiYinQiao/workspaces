package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.NoticeInfo;
import com.oasys.model.NoticeInfoAttach;
import com.oasys.model.Organization;
import com.oasys.model.Users;
import com.oasys.model.VO.NoticeReceiveModel;
import com.oasys.service.NoticeInfoService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;

@Controller
@RequestMapping("/noticeInfoController")
public class NoticeInfoController extends BaseController{
	
	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:47:31
	 * @Title:findNoticeSendList
	 * @Description:TODO（这里描述这个方法的作用）发件箱列表
	 * @param httpServletResponse
	 * @param page
	 * @param rows
	 * @param noticeInfo
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findNoticeSendList",method=RequestMethod.POST)
	public String findNoticeSendList(HttpServletResponse httpServletResponse,Integer page,Integer rows,NoticeInfo noticeInfo){
		PageUtil pageUtil=new PageUtil(page,rows);
		GridModel gridModel=new GridModel();
		gridModel.setRows(noticeInfoService.findNoticeInfoSendList(noticeInfo, pageUtil));
		gridModel.setTotal(noticeInfoService.getCountNoticeInfoSendList(noticeInfo));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:47:47
	 * @Title:findNoticeReceiveList
	 * @Description:TODO（这里描述这个方法的作用）收件箱列表
	 * @param httpServletResponse
	 * @param page
	 * @param rows
	 * @param noticeInfo
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findNoticeReceiveList",method=RequestMethod.POST)
	public String findNoticeReceiveList(HttpServletResponse httpServletResponse,Integer page,Integer rows){
		PageUtil pageUtil=new PageUtil(page,rows);
		GridModel gridModel=new GridModel();
		gridModel.setRows(noticeInfoService.findNoticeInfoReceiveList(Constants.getCurrendUser().getUserId(), pageUtil));
		gridModel.setTotal(noticeInfoService.getCountNoticeInfoReceiveList(Constants.getCurrendUser().getUserId()));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 上午11:36:38
	 * @Title:persistenceNoticeInfo
	 * @Description:TODO（这里描述这个方法的作用）
	 * @param httpServletResponse
	 * @param noticeInfo
	 * @param userIds 收件人id拼接字符串
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/persistenceNoticeInfo",method=RequestMethod.POST)
	public String persistenceNoticeInfo(HttpServletResponse httpServletResponse,NoticeInfo noticeInfo,String userIds){
		OutputJson(httpServletResponse, getMessage(noticeInfoService.persistenceNoticeInfo(noticeInfo, userIds)));
		return null;
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午4:37:19
	 * @Title:delNoticeInfo
	 * @Description:TODO（这里描述这个方法的作用）删除发件箱级联删除收件箱
	 * @param httpServletResponse
	 * @param noticeInfo
	 * @param ids
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/delNoticeInfo",method=RequestMethod.POST)
	public String delNoticeInfo(HttpServletResponse httpServletResponse,String ids){
		OutputJson(httpServletResponse, getMessage(noticeInfoService.delNoticeInfo(ids)));
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午4:37:35
	 * @Title:delNoticeInfo
	 * @Description:TODO（这里描述这个方法的作用）删除收件箱
	 * @param httpServletResponse
	 * @param noticeInfo
	 * @param ids
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/delNoticeInfoAttach",method=RequestMethod.POST)
	public String delNoticeInfoAttach(HttpServletResponse httpServletResponse,String ids){
		OutputJson(httpServletResponse, getMessage(noticeInfoService.delNoticeInfoAttach(ids)));
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:48:07
	 * @Title:findUserList
	 * @Description:TODO（这里描述这个方法的作用）查询用户列表
	 * @param httpServletResponse
	 * @param page
	 * @param rows
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findUserList",method=RequestMethod.POST)
	public String findUserList(HttpServletResponse httpServletResponse,Integer page,Integer rows){
		List<ComboBoxModel> userList = userService.findOrgUserList(null);
		OutputJson(httpServletResponse, userList);
		return null;
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:48:21
	 * @Title:findNoticeInfoAttachByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号和当前登录用户查询主表
	 * @param httpServletResponse
	 * @param noticeNo
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findNoticeInfoAttachByNoticeNo",method=RequestMethod.POST)
	public String findNoticeInfoAttachByNoticeNo(HttpServletResponse httpServletResponse,String noticeNo){
		OutputJson(httpServletResponse,noticeInfoService.findNoticeInfoAttachByNoticeNo(noticeNo));
		return null;
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:49:00
	 * @Title:receiptByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）回执通知信息
	 * @param httpServletResponse
	 * @param noticeNo
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/receiptByNoticeNo",method=RequestMethod.POST)
	public String receiptByNoticeNo(HttpServletResponse httpServletResponse,String noticeNo){
		OutputJson(httpServletResponse,getMessage(noticeInfoService.receiptByNoticeNo(noticeNo)));
		return null;
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月11日 下午5:04:05
	 * @Title:getModelByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号获取通知对象model
	 * @param httpServletResponse
	 * @param noticeNo
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/getModelByNoticeNo",method=RequestMethod.POST)
	public String getModelByNoticeNo(HttpServletResponse httpServletResponse,String noticeNo){
		OutputJson(httpServletResponse,noticeInfoService.getModelByNoticeNo(noticeNo));
		return null;
	}
	
	@RequestMapping(value="/toEditNotice")
	public ModelAndView toEditNotice(String noiId,String noticeNo){
		if(StringUtils.isBlank(noiId)){
			noticeNo = UniqueIdUtil.generate("TZ");
		}
		ModelAndView mv =new ModelAndView("pd/notice/noticeEditDlg");
		mv.addObject("noticeNo", noticeNo);
		return mv;
	}
	
	/**
	 * 查询用户列表
	 * @author panchuanhe
	 * @date 2015-05-07
	 */
	@ResponseBody
	@RequestMapping(value="/findAllUserList")
	public String findAllUserList(HttpServletResponse httpServletResponse,Users u) throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUserList(u));
		gridModel.setTotal(userService.getCount(u));
		OutputJson(httpServletResponse,gridModel);
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月17日 下午5:23:26
	 * @Title:findNoticeInfoAttByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号查询收件人
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findReceiversByNoticeNo")
	public String findReceiversByNoticeNo(HttpServletResponse httpServletResponse,String noticeNo){
		List<NoticeInfoAttach> attList = noticeInfoService.findReceiversByNoticeNo(noticeNo);
		OutputJson(httpServletResponse, attList);
		return null;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/showOrganizationName")
	public String showOrganizationName(HttpServletResponse httpServletResponse){
		List<Organization> orgList = organizationService.findOrganizationByIdList();
		OutputJson(httpServletResponse,orgList);
		return null;
	}
	
}


