package com.oasys.webService.model;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.oasys.webService.util.RoleCodeConvertUtil;

public class Role {
	
	private Integer roleId;
	private String roleName;
	private String roleCode;
	private Integer roleType;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	
	public static List<Role> parse(List<User> userList){
		List<Role> roleList = new ArrayList<Role>();
		List<String> roleStrList = new ArrayList<String>();
		Role role;
		String roleName = "";
		for (User user : userList) {
//			if(StringUtils.isBlank(user.getOffice())){
//				System.out.println(user.getUserid());
//			}
			role = new Role();
			roleName = RoleCodeConvertUtil.getRoleNameByEmOffice().get(user.getOffice());
			role.setRoleCode(StringUtils.isBlank(roleName) ? user.getOffice() : roleName);
			role.setRoleId(1);
			role.setRoleName(StringUtils.isBlank(roleName) ? user.getOffice() : roleName);
			role.setRoleType(0);
			if(roleStrList.contains(role.getRoleName()))
				continue;
			roleList.add(role);
			roleStrList.add(role.getRoleName());
		}
		return roleList;
	}
	
}