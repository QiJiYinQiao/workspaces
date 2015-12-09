package com.oasys.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.Organization;
import com.oasys.model.OrganizationRole;
import com.oasys.model.Role;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.util.Collections;
import com.oasys.viewModel.ComboBoxModel;
@Service("roleService")
@SuppressWarnings("unchecked")
public class RoleServiceImpl implements RoleService
{

	@Autowired
	private PublicDao<Role> roleDao;
	
	@Autowired
	private OrganizationService orgService;
	
	@Override
	public List<ComboBoxModel> findRoleList() {
		String hql="from Role";
		 List<Role> roleList = roleDao.find(hql);
		 List<ComboBoxModel> list=new ArrayList<ComboBoxModel>();
		 for (Role role : roleList) {
			//key编码
			 String code=role.getRoleCode();
			 //value
			 String text=role.getName();
			
			ComboBoxModel boxModel=new ComboBoxModel(code, text);
			 list.add(boxModel);
		}
		 return list;
	}

	@Override
	public Role findRoleByCode(String roleCode) {
		String hql = "select r from Role r where r.roleCode='"
				+ roleCode + "'";
		List<Role> list = roleDao.find(hql);
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Role> findRoleListsByRoleCode(Integer roleID){
		String sql = "SELECT * FROM qqms.t_role WHERE find_in_set(role_id, get_role_child("+roleID+"))";
		List<Object> list = roleDao.findBySQL(sql);
		List<Role> roleList = new ArrayList<Role>();
		Object[] obj = null;
		Role role;
		for (int j = 0; j < list.size(); j++) {
			role = new Role();
			obj = (Object[]) list.get(j);
			role.setRoleId(obj[0] == null ? 0 : (Integer) obj[0]);
			role.setName(obj[1] == null ? "" : String.valueOf(obj[1]));
			role.setPid(obj[2] == null ? 0 : (Integer) obj[2]);
			role.setRoleType(obj[3] == null ? "" : String.valueOf(obj[3]));
			role.setStatus(obj[5] == null ? "" : String.valueOf(obj[5]));
			role.setRoleCode(obj[11] == null ? "" : String.valueOf(obj[11]));
			roleList.add(role);
		}
		return roleList;
	}

	@Override
	public Role findRoleByRoleId(Integer roleId) {
		return roleDao.get(Role.class, roleId);
	}

	@Override
	public List<ComboBoxModel> findRoleListByOrgID(Integer orgID) {
		Organization org =  orgService.findOrganizationByOrganizationId(orgID);
		Set<OrganizationRole> orgRoleList = org.getOrganizationRoles();
		List<ComboBoxModel> comBoxList = new ArrayList<ComboBoxModel>();
		ComboBoxModel combox;
		for (OrganizationRole orgRole : orgRoleList) {
			combox = new ComboBoxModel(orgRole.getRole().getRoleId().toString(), orgRole.getRole().getName());
			comBoxList.add(combox);
		}
		return comBoxList;
	}

	@Override
	public String getRoleStrByIDs(String roleIds, String splitStr) {
		String ids[] = roleIds.split(",");
		String returnStr = "";
		for (String string : ids) {
			returnStr += findRoleByRoleId(Integer.valueOf(string)).getName()+splitStr;
		}
		returnStr = returnStr.length()>0 ? returnStr.substring(0, returnStr.length()-1) : returnStr;
		return returnStr;
	}
	
}
