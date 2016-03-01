package com.bpms.service;

import com.bpms.model.Role;
import com.bpms.model.UserAndRole;


/**
 * 用户角色绑定的service
 * 
 * @author liuhh
 *
 */
public interface UserAndRoleService {

	/**
	 * 保存用户角色的关系
	 */
	void saveUserAndRoles(UserAndRole userAndRole);
	
	/**
	 * 查看用户的角色绑定关系是否已经存在,如果存在则不再进行编写
	 * @param userAndRole
	 */
	UserAndRole isExistUserAndRoles(UserAndRole userAndRole);
	
	/**
	 * 删除用户之前绑定用户的角色关系
	 * @param userId 删除用户的之前的角色绑定关系
	 */
	void deleteUserAndRoles(Integer userId);
	
	/**
	 * 根据Role对象，删除用户与角色之间的关系。
	 * @Title: deleteUserAndRoleByRole 
	 * @Description: TODO
	 * @param @param role
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月26日 下午12:58:30
	 * @throws
	 */
	void deleteUserAndRoleByRole(Role role);
}
