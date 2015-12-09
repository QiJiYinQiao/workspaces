package com.oasys.service;

import java.util.List;
import java.util.Map;

import com.oasys.model.Organization;
import com.oasys.model.Role;
import com.oasys.model.Users;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.UserRoleModel;
import com.oasys.viewModel.UsersModel;

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
	
	Users getUserByID(Integer id);
	
	Users findUserById(Integer userid);
	
	List<UsersModel> findUserList(String q);
	
	List<Users> findUsersByOrgId(Integer orgId);
	/**
	 * 根据用户id查询用户的部门及用户信息
	 * @Title: findUserOrgById 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return UsersModel
	 * @date 2015年9月29日 下午5:01:31
	 * @throws
	 */
	UsersModel findUserOrgById(Integer userId);
	List<UsersModel> loadUserInfo(Integer id);
	
	/**
	 * 根据用户Id获取role的集合
	 * @Title: findRoleListByUserId 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<Role>
	 * @date 2015年9月23日 上午11:20:09
	 * @throws
	 */
	List<Role> findRoleListByUserId(Integer userId);
	/**
	 * 根据部门id查询用户
	 * @Title: findOrgUserList 
	 * @Description: TODO
	 * @param @param organizeId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<Users>
	 * @date 2015年9月29日 下午2:40:36
	 * @throws
	 */
	List<ComboBoxModel> findOrgUserList(String organizeId);
	/**
	 * 根据userid获取部门对象
	 * @Title: findOrgByuserId 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author PANCHUANHE
	 * @return Organization
	 * @date 2015年10月9日 下午8:42:55
	 * @throws
	 */
	Organization findOrgByuserId(Integer userId);
	//获取所有用户名和部门
	List<UsersModel> getUserInfo();
	
	//根据组织机构和角色 获取该组织机构下的角色是否配置用户
	Long getUserByOrgAndRole(String orgID,String roleCode);
	
	//根据组织机构和角色对应关系 获取该组织机构下的角色是否配置的用户
	List<String> getUserIdsByOrgAndRoleOrg(String orgID,String roleCode);
}
