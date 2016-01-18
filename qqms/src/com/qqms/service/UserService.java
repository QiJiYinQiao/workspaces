package com.qqms.service;

import java.util.List;
import java.util.Map;

import com.qqms.model.Users;
import com.qqms.util.PageUtil;
import com.qqms.viewModel.Json;
import com.qqms.viewModel.UserRoleModel;

public interface UserService
{

	boolean persistenceUsers(Map<String, List<Users>> map );

	List<Users> findAllUserList(Map<String, Object> map, PageUtil pageUtil);

	Long getCount(Map<String, Object> map , PageUtil pageUtil);

	List<UserRoleModel> findUsersRolesList(Integer userId );

	boolean saveUserRoles(Integer userId, String isCheckedIds );

	Json persistenceUsers(Users u );

	boolean delUsers(String ids);
	
	boolean isExistsAccount(Users u);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月20日 下午5:44:46
	 * @Title:getJointOrganId
	 * @Description:TODO（这里描述这个方法的作用）获取用户对接地区
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws:
	 */
	public String getJointOrganCity(String userId, String roleId);
}
