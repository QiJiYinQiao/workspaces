package com.bpms.service;

import com.bpms.model.Role;
import com.bpms.model.RoleAndPermission;


/**
 * 角色绑定权限service
 * 
 * @author liuhh
 *
 */
public interface RoleAndPermissionService {

	/**
	 * 角色绑定权限
	 */
	void saveRoleAndPermissions(RoleAndPermission roleAndPermission);
	
	/**
	 * 角色绑定权限是否已经存在
	 */
	RoleAndPermission isExistRoleAndPermissions(RoleAndPermission roleAndPermission);
	
	/**
	 * 根据RoleCode，删除关系表中相应的数据
	 */
	public boolean delRoleAndPermissionByRoleCode(Role role);

	/**
	 * 根据roleCode，删除当前角色中，与投资业务相关的权限
	 * @Title: delRoleAndPermissionOfInvestBusinessByRoleCode 
	 * @Description: TODO
	 * @param @param roleCode
	 * @param @return
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2016年1月26日 上午10:40:22
	 * @throws
	 */
	public void delRoleAndPermissionOfInvestBusinessByRoleCode(String roleCode);
}
