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
}
