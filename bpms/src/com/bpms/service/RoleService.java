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
}
