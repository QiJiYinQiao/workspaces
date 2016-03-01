package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Role;
import com.bpms.model.RoleAndPermission;
import com.bpms.service.RoleAndPermissionService;
import com.bpms.util.Collections;

@Service("roleAndPermissionService")
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {

	@Autowired
	private BaseDAO<RoleAndPermission> roleAndPermissionDao;
	
	@Override
	public void saveRoleAndPermissions(RoleAndPermission roleAndPermission) {
		roleAndPermissionDao.save(roleAndPermission);
	}

	@Override
	public RoleAndPermission isExistRoleAndPermissions(RoleAndPermission roleAndPermission) {
		String hql = "from RoleAndPermission rp where rp.permissionId='"+roleAndPermission.getPermissionId()+"' and rp.roleId='"+roleAndPermission.getRoleId()+"'";
		List<RoleAndPermission> roleAndPermissions = roleAndPermissionDao.find(hql);
		if(Collections.listIsNotEmpty(roleAndPermissions)){
			return roleAndPermissions.get(0);
		}
		return null;
	}

	@Override
	public boolean delRoleAndPermissionByRoleCode(Role role) {
		String sql = "delete from t_role_and_permission where ROLE_ID = '"+role.getRoleId()+"'";
		roleAndPermissionDao.executeBySql(sql);				
		return true;
	}
	
	
	public void delRoleAndPermissionOfInvestBusinessByRoleCode(String roleCode){
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM t_role_and_permission ");
		sql.append("WHERE ");
		sql.append("permission_id IN ('110', '104', '147', '148', '149', '150', '151', '152', '238', '235', '236', '237', '153') AND role_id <> 1 ");
		sql.append("AND ");
		sql.append("role_id IN (select t2.role_id from t_role t2 where t2.role_code = '"+roleCode+"') ");

		roleAndPermissionDao.executeBySql(sql.toString());
	
	}
		
}
