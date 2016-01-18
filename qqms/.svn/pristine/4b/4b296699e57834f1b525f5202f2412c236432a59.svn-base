package com.qqms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.qqms.model.Organization;
import com.qqms.model.Users;
import com.qqms.service.OrganizationService;
import com.qqms.service.UserService;
import com.qqms.util.Constants;
import com.qqms.util.PageUtil;
import com.qqms.viewModel.GridModel;
import com.qqms.viewModel.Json;

/**
 * 用户管理Action
 * 
 * @author panchuanhe
 * @date 2015-05-07
 */
@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<Users> {
	private static final long serialVersionUID = -8188592660918385632L;
	//service层注入
	private UserService userService;
	@Autowired
	private OrganizationService orgService;
	private String isCheckedIds;
	//实体对象
	private Users users;
	/**
	 * 查询用户列表
	 * @author panchuanhe
	 * @date 2015-05-07
	 */
	public String findAllUserList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue
					+ Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds,
				searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUserList(map, pageUtil));
		gridModel.setTotal(userService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月12日 下午2:22:53
	 * @Title:showOrganizationName
	 * @Description:根据组织id得到组织名字（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String showOrganizationName(){
		List<Organization> orgList = orgService.findOrganizationByIdList();
		OutputJson(orgList);
		return null;
	}
	

	/**
	 * 函数功能说明 TODO:持久化用户 Administrator修改者名字 2013-5-29修改日期 修改内容
	 * 
	 * @Title: persistenceUsers
	 * @Description:
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String persistenceUsers() throws Exception {
		Map<String, List<Users>> map = new HashMap<String, List<Users>>();
		map.put("addList", JSON.parseArray(inserted, Users.class));
		map.put("updList", JSON.parseArray(updated, Users.class));
		map.put("delList", JSON.parseArray(deleted, Users.class));
		Json json = new Json();
		if (userService.persistenceUsers(map)) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		} else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}

	/**
	 * 新增和修改
	 */
	public String persistenceUsersDig() throws Exception {
		
		boolean flag = userService.isExistsAccount(getModel());
		Json json = new Json();
		if (flag) {
			json.setMessage("该账号已存在，请更换!");
		} else{
			json = userService.persistenceUsers(getModel());
		}
		OutputJson(json,Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 用户删除
	 * @author panchuanhe
	 * @date 2015/5/8
	 */
	public String delUsers() throws Exception {
		boolean bl = userService.delUsers(IDS);
		Json json = null;
		if(bl){
			 json = new Json("提示","数据删除成功!",bl);
		}else{
			 json = new Json("提示","数据删除失败!",bl);
		}
		OutputJson(json);
		return null;
	}

	/**
	 * 函数功能说明 TODO:查询用户拥有角色 Administrator修改者名字 2013-5-29修改日期 修改内容
	 * 
	 * @Title: findUsersRolesList
	 * @Description:
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String findUsersRolesList() throws Exception {
		OutputJson(userService.findUsersRolesList(getModel().getUserId()));
		return null;
	}

	/**
	 * 函数功能说明 TODO:保存用户分配的角色 Administrator修改者名字 2013-5-29修改日期 修改内容
	 * 
	 * @Title: saveUserRoles
	 * @Description:
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String saveUserRoles() throws Exception {
		Json json = new Json();
		if (userService.saveUserRoles(getModel().getUserId(), isCheckedIds)) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		} else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}

	public Users getModel() {
		if (null == users) {
			users = new Users();
		}
		return users;
	}
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getIsCheckedIds() {
		return isCheckedIds;
	}

	public void setIsCheckedIds(String isCheckedIds) {
		this.isCheckedIds = isCheckedIds;
	}
	
}
