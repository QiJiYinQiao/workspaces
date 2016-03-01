package com.bpms.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Users;
import com.bpms.service.UserAndOrganizationService;
import com.bpms.view.model.GridModel;

/**
 * 用户控制器
 * @author Sun
 *
 */
@Namespace("/userOrg")
@Action(value = "userAndOrganization")
public class UserAndOrganizationAction extends BaseAction {

	private static final long serialVersionUID = 7009161158346977305L;
	
	@Autowired
	private UserAndOrganizationService userService;
	
	/**
	 * 用户的ID
	 */
	private String userIds;
	
	/**
	 * 组织机构的ID
	 */
	private String organizationId;

	/**
	 * 根据组织机构的ID获取已经绑定的用户的信息
	 */
	public void findBindingUsersByOrganizationId(){
		List<Users> list = userService.findBindingUsersByOrganizationId(getOrganizationId());
		OutputJson(new GridModel(list, (long)list.size()));
	}
	
	/**
	 * 绑定外援用户和组织机构的信息
	 */
	public void saveBindingUserAndOrganization(){
		try{
			userService.saveBindingUserAndOrganization(getOrganizationId(), getUserIds());
			OutputJson(getMessage(true));
		}catch(Exception e){
			OutputJson(getMessage(false));
		}
	}
	
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
