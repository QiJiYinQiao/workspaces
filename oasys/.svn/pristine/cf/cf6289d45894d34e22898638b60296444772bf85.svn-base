package com.oasys.service.webService;

import java.io.File;
import java.util.List;

import com.oasys.model.Department;
import com.oasys.model.User;

public interface EmWebService {
	/***
	 * 保存263用户
	 * @param user
	 */
	void saveEmUser(User user);
	/***
	 * 保存263组织机构 
	 * @param dept
	 */
	void saveEmDepartment(Department dept);
	/***
	 * 更新组织机构字段
	 */
	void saveOrgField();
	/***
	 * 配置角色权限
	 */
	void saveRolePermission();
	/***
	 * 同步角色
	 * @return
	 */
	int syncEmRole();
	/***
	 * 同步组织机构
	 * @return
	 */
	int syncDepartment();
	/***
	 * 同步用户
	 * @return
	 */
	int syncEmUsers();
	
	/***
	 * 配置角色上下级关系
	 */
	int saveRoleParent();
	
	/***
	 * 配置角色roleType
	 */
	void saveRoleType();
	
	/**
	 * 
	 * @author:lida
	 * @time:2015年12月28日 14:27:58
	 * @Title:importExcelToDB
	 * @Description:TODO（这里描述这个方法的作用）把excel里边的数据写入数据库固定资产库存表里
	 * @param file 上传的excel文件
	 * @throws:
	 */
	public List<String> startImportExcelToDB(File file);
	
	public void saveOrgRegionType();
}
