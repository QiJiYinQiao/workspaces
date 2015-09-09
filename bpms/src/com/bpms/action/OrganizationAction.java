package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Organization;
import com.bpms.service.OrganizationService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 组织机构管理Action
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Namespace("/orgz")
@Action(value = "organizationAction")
public class OrganizationAction extends BaseAction implements
		ModelDriven<Organization> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -4604242185439314975L;

	/** 自动注入组织机构管理的Service */
	@Autowired
	private OrganizationService organizationService;

	/** 主键. */
	private Integer id;

	/** 组织机构实体类. */
	private Organization organization;

	/**
	 * 获取组织机构信息列表
	 * 
	 * @return 组织机构信息列表的json
	 * @throws Exception
	 */
	public String findOrganizationList() throws Exception {
		OutputJson(organizationService.findOrganizationList());
		return null;
	}

	/**
	 * 创建model实例
	 */
	public Organization getModel() {
		if (null == organization) {
			organization = new Organization();
		}
		return organization;
	}

	/**
	 * 所属地区
	 */
	public void findOrganizationCombo(){
		OutputJson(organizationService.findOrganizationCombo());
	}
	
	/************************** 属性的Getter和Setter方法.************************ */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
