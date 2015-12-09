package com.oasys.service;


import java.util.List;

import com.oasys.model.Role;
import com.oasys.viewModel.ComboBoxModel;

public interface RoleService 
{

	/**
	 * 查询所有工牌申请列表
	 */
	List<ComboBoxModel> findRoleList();
	/**
	 * 根据角色编码查询角色
	 * @Title: findRoleByCode 
	 * @Description: TODO
	 * @param @param roleCode
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Role
	 * @date 2015年9月23日 下午2:43:43
	 * @throws
	 */
	Role findRoleByCode(String roleCode);
	/**
	 * 根据roleId获取role
	 * @Title: findRoleByRoleId 
	 * @Description: TODO
	 * @param @param roleId
	 * @param @return
	 * @author PANCHUANHE
	 * @return Role
	 * @date 2015年9月23日 上午11:31:17
	 * @throws
	 */
	Role findRoleByRoleId(Integer roleId);
	
	/** 根据roleCode递归寻找子集List */
	public List<Role> findRoleListsByRoleCode(Integer roleID);
	
	/** 根据部门获取部门下对应的角色列表 */
	public List<ComboBoxModel> findRoleListByOrgID(Integer orgID);
	
	/** 根据角色ID拼接角色名称字符串 */
	public String getRoleStrByIDs(String roleIds,String splitStr);
	
}
