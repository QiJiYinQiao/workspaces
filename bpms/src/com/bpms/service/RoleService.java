package com.bpms.service;

import com.bpms.model.Role;

/**
 * 角色查询的service
 * 
 * @author liuhh
 *
 */
public interface RoleService {

	/**
	 * 根据角色的code获取角色信息
	 * 
	 * @param roleCode
	 * @return
	 */
	public Role findRoleByCode(String roleCode);
	
	/**
	 * 根据橘色的名称获取角色信息
	 * @param roleName 角色的名称
	 * @return 角色的信息
	 */
	public Role findRoleByName(String roleName);
	
	/**
	 * 保存角色的信息
	 * @param role
	 */
	public void saveRole(Role role);
	
	/**
	 * 删除角色
	 * @Title: delRole 
	 * @Description: TODO
	 * @param @param role
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月13日 下午5:24:13
	 * @throws
	 */
	public void delRole(Role role);
}
