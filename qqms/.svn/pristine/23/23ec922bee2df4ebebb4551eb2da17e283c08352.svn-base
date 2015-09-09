package com.qqms.service;

import java.util.List;
import java.util.Map;

import com.qqms.model.Permission;
import com.qqms.model.Role;
import com.qqms.viewModel.TreeGrid;



public interface PermissionAssignmentService
{

	List<TreeGrid> findAllFunctionsList(Integer pid );

	Long getCount(Map<String, Object> param );

	boolean savePermission(Integer roleId, String checkedIds );

	List<Permission> getRolePermission(Integer roleId );

	boolean persistenceRole(Map<String, List<Role>> map );

	List<Role> findAllRoleList(Map<String, Object> param, Integer page, Integer rows, boolean isPage );

	boolean persistenceRole(Role r );

	boolean persistenceRole(Integer roleId );
	
	public boolean isExistsCode(Role role);

}
