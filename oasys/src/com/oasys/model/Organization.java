package com.oasys.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * Organization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_organization",catalog="QQMS")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Organization implements java.io.Serializable,Cloneable {
	private static final long serialVersionUID = -3560252981789957584L;
	private Integer id;
	private Integer organizationId;
	private Integer companyId;
	private String myid;
	private Integer pid;
	private String fullName;
	private String ename;
	private Integer manager;
	private String iconCls;
	private Integer assistantManager;
	private Integer empQty;
	private String status;
	private Date created;
	private Date lastmod;
	private String shortName;
	private String tel;
	private String fax;
	private String description;
	private Integer creater;
	private Integer modifyer;
	private String state = "closed";
	private String regionType;
	private String deptLevel;
	private String treeLevel;
	
	
	
	@Column(name="ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TREE_LEVEL", length = 2)
	public String getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}

	private Set<OrganizationRole> organizationRoles = new HashSet<OrganizationRole>(
			0);

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** full constructor */
	public Organization(Integer companyId, String myid, Integer pid,
			String fullName, String ename, Integer manager, String iconCls,
			Integer assistantManager, Integer empQty, String status,
			Date created, Date lastmod, String shortName, String tel,
			String fax, String description, Integer creater, Integer modifyer,
			String state, String regionType, String deptLevel) {
		this.companyId = companyId;
		this.myid = myid;
		this.pid = pid;
		this.fullName = fullName;
		this.ename = ename;
		this.manager = manager;
		this.iconCls = iconCls;
		this.assistantManager = assistantManager;
		this.empQty = empQty;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.shortName = shortName;
		this.tel = tel;
		this.fax = fax;
		this.description = description;
		this.creater = creater;
		this.modifyer = modifyer;
		this.state = state;
		this.regionType = regionType;
		this.deptLevel = deptLevel;
	}

	
	

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ORGANIZATION_ID", unique = true, nullable = false)
	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "COMPANY_ID")
	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "MYID", length = 25)
	public String getMyid() {
		return this.myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "FULL_NAME")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "ENAME", length = 100)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "MANAGER")
	public Integer getManager() {
		return this.manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "ASSISTANT_MANAGER")
	public Integer getAssistantManager() {
		return this.assistantManager;
	}

	public void setAssistantManager(Integer assistantManager) {
		this.assistantManager = assistantManager;
	}

	@Column(name = "EMP_QTY")
	public Integer getEmpQty() {
		return this.empQty;
	}

	public void setEmpQty(Integer empQty) {
		this.empQty = empQty;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return this.lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "SHORT_NAME", length = 50)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFYER")
	public Integer getModifyer() {
		return this.modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	@Column(name = "STATE", length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "REGION_TYPE", length = 1)
	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	@Column(name = "DEPT_LEVEL", length = 1)
	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<OrganizationRole> getOrganizationRoles() {
		// TODO Auto-generated method stub
		return organizationRoles;
	}

	public void setOrganizationRoles(Set<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}
	
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}