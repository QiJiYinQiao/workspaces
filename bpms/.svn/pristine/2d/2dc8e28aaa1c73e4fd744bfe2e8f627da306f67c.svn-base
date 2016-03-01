package com.bpms.service;

import java.util.List;

import com.bpms.model.UserAndOrganization;
import com.bpms.model.Users;

/**
 * 用户绑定外援和组织机构service
 * 
 * @author liuhh
 *
 */
public interface UserAndOrganizationService {

	/**
	 * 保存用户用户和组织机构的关系
	 * 
	 * @param userAndOrganization
	 */
	void saveUserAndOrganization(UserAndOrganization userAndOrganization);

	/**
	 * 判断用户和组织机构的关系是否存在
	 * 
	 * @param userAndOrganization
	 * @return
	 */
	UserAndOrganization isExistUserAndOrganization(UserAndOrganization userAndOrganization);

	/**
	 * 删除组织机构下的用户绑定信息，从新进行绑定操作
	 * 
	 * @param organizationId
	 */
	void deleteUserAndOrganization(Integer organizationId);
	
	/**
	 * 绑定用户的信息
	 * @param organizationId
	 * @param userIds
	 * @throws Exception 
	 */
	void  saveBindingUserAndOrganization(String organizationId,String userIds) throws Exception;
	
	/**
	 * 根据组织机构的ID获取已经绑定的用户信息
	 * @param organizationId 组织机构的ID
	 * @return
	 */
	List<Users>  findBindingUsersByOrganizationId(String organizationId);
}
