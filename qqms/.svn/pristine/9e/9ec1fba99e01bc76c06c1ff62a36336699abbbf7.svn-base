package com.qqms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.qqms.model.Organization;
import com.qqms.service.OrganizationService;
import com.qqms.util.Constants;
import com.qqms.viewModel.Json;

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
	 * 获取组织机构的信息列表
	 * 
	 * @return 组织机构信息里列表的TreeGrid
	 * @throws Exception
	 */
	public String findOrganizationListTreeGrid() throws Exception {
		OutputJson(organizationService.findOrganizationList(id));
		return null;
	}

	/**
	 * 持久化组织
	 * 
	 * @return 持久化信息json
	 * @throws Exception
	 */
	public String persistenceOrganization() throws Exception {
		OutputJson(
				getMessage(organizationService
						.persistenceOrganization(getModel())),
				Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 删除实体组织机构的信息
	 * 
	 * @return 删除组织机构信息是否成功
	 * @throws Exception
	 */
	public String delOrganization() throws Exception {
		Json json = new Json();
		if (organizationService.delOrganization(id)) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		OutputJson(json);
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月21日 上午10:57:54
	 * @Title:getRegionTypeById
	 * @Description:TODO 根据id取到区域类型（这里描述这个方法的作用）
	 * @return
	 * @throws Exception
	 * @throws:
	 */
	public String getRegionTypeById() throws Exception{
		OutputJson(organizationService.getRegionTypeById(id));
		return null;
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月9日 下午5:31:54
	 * @Title:getOrganizationCode
	 * @Description:TODO（这里描述这个方法的作用）得到组织编码是属于财富、借款还是其它
	 * @return
	 * @throws:
	 */
	public String getOrganizationCode(){
		OutputJson(organizationService.getOrganizationCode(id));
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
