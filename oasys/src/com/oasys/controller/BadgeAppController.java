package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.BadgeApp;
import com.oasys.model.BadgeAppAttach;
import com.oasys.model.Users;
import com.oasys.service.BadgeAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author wangxincheng
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *工牌申请表
 */

@Controller
@RequestMapping("/BadgeApp")
public class BadgeAppController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private BadgeAppService badgeAppService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	
	
	//保存申请人
	@ResponseBody
	@RequestMapping(value="/saveBadgeAttache",method=RequestMethod.POST)
	public String saveBadgeAttache(HttpServletResponse httpServletResponse,@ModelAttribute("badgeAppAttach") BadgeAppAttach badgeAppAttach){
		BadgeApp badgeApp = badgeAppService.saveBadgeAttach(badgeAppAttach);
		OutputJson(httpServletResponse,badgeApp);
		return null;
	}
	
	//查询申请下的申请人
	@ResponseBody
	@RequestMapping(value="/findBadgeAttList",method=RequestMethod.POST)
	public String findBadgeAttList(HttpServletResponse httpServletResponse,String appNo,String deptNo,Integer page,Integer rows){
		//申请人条数
		Long atttotal = badgeAppService.findbadgeAtttotal(appNo, deptNo);
		//申请人信息
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		List<BadgeAppAttach> attList = badgeAppService.findBadgeAttList(appNo,deptNo,pageUtil);
		if(attList!=null && attList.size()>0){
			for (BadgeAppAttach badgeAppAttach : attList) {
				Users user = userService.findUserById(badgeAppAttach.getApplicantNo());
				//申请人名字
				badgeAppAttach.setName(user.getName());
				//部门名字
				String fullName=organizationService.getOrgNameByID(badgeAppAttach.getDeptNo());
				badgeAppAttach.setFullName(fullName);
				//职位名字
				String code=badgeAppAttach.getPosition();
				badgeAppAttach.setPositionName(roleService.findRoleByCode(code).getName());
			}
			
		}
		OutputJson(httpServletResponse, new GridModel(attList, atttotal) );
		return null;
	}
	
	//删除申请人
	@ResponseBody
	@RequestMapping(value="/deleteBadgeAttList",method=RequestMethod.POST)
	public String deleteBadgeAttList(HttpServletResponse httpServletResponse,String ids){
		boolean flag = badgeAppService.deleteBadgeAttList(ids);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	//查询主表信息
	@ResponseBody
	@RequestMapping(value="/findBadgeAppList",method=RequestMethod.POST)
	public String findBadgeAppList(HttpServletResponse httpServletResponse,String appNo){
		BadgeApp badgeApp = badgeAppService.findBadgeAppNo(appNo);
		Users users = userService.findUserById(badgeApp.getRegistrantNo());
		badgeApp.setRegName(users.getName());
		OutputJson(httpServletResponse, badgeApp);
		return null;
	}
	
	//查询工牌申请列表
	@ResponseBody
	@RequestMapping(value="/findBadgeAppAttList",method=RequestMethod.POST)
	public String findBadgeAppAttList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("badgeApp") BadgeApp badgeApp,  BindingResult bindingResult){
		//申请总条数
		Long total = badgeAppService.findtotal(badgeApp);
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
		List<BadgeAppModel> appList = badgeAppService.getBadgeApp(pageUtil,badgeApp);
		OutputJson(httpServletResponse, new GridModel(appList,total));
		return null;
	}
	
	
	//根据部门id查询用户列
	@ResponseBody
	@RequestMapping(value="/findOrgUserList",method=RequestMethod.POST)
	public String findOrgUserList(HttpServletResponse httpServletResponse,String organizeId){
		List<ComboBoxModel> userList = userService.findOrgUserList(organizeId);
		OutputJson(httpServletResponse, userList);
		return null;
	}

	//删除申请人
	@ResponseBody
	@RequestMapping(value="/deleteBadgeApp",method=RequestMethod.POST)
	public String deleteBadgeApp(HttpServletResponse httpServletResponse,String appNo){
		boolean flag=badgeAppService.deleteBadgeApp(appNo);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	
	//删除工牌申请空数据
	@ResponseBody
	@RequestMapping(value="/delBadgeNotDate",method=RequestMethod.POST)
	public String delBadgeNotDate(HttpServletResponse httpServletResponse,String pnrIds){
		boolean flag=badgeAppService.deletebadgeNotDate(pnrIds);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	/**
	 * 根据部门加载申请人，排除已添加的申请人
	 * @Title: findUserListByDept 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param organizeId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月22日 下午1:44:18
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUserListByDept",method=RequestMethod.POST)
	public String findUserListByDept(HttpServletResponse httpServletResponse,String appNo,String organizeId){
		String deptList = badgeAppService.findDeptList(appNo, organizeId);
		List<ComboBoxModel> userList = badgeAppService.findDeptNoUserList(deptList, organizeId);
		OutputJson(httpServletResponse, userList);
		return null;
	}
	
	/**
	 * 获取当前登录人的总部、分部
	 * @Title: findUserDeptLeave 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月1日 下午4:38:35
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUserDeptLeave",method=RequestMethod.POST)
	public String findUserDeptLeave(HttpServletResponse httpServletResponse){
		Integer deptLeave =badgeAppService.findRegUserDeptNo();
		OutputJson(httpServletResponse, deptLeave);
		return null;
	}
	
	
}


