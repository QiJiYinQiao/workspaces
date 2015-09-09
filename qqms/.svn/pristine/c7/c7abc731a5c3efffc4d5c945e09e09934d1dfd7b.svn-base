package com.qqms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_bp_contacts")
public class Emccontact implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer contactId;
	/**
	 * 财富客户ID，外键
	 */
	private Integer cusId;
	/**
	 * 客户类型
	 */
	private String cusType;
	/**
	 * 紧急联系人的状态
	 */
	private String contactStatus;
	/**
	 * 紧急联系人姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String genderType;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 移动电话
	 */
	private String tel;
	/**
	 * 固定电话
	 */
	private String fixedTel;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 与您的关系
	 */
	private String relationship;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 邮编
	 */
	private String zip;
	/**
	 * 工作单位
	 */
	private String workplace;
	/**
	 * 创建日期
	 */
	private Date created;
	/**
	 * 创建人
	 */
	private Integer creator;
	/**
	 * 修改日期
	 */
	private Date lastmod;
	/**
	 * 修改人
	 */
	private Integer modifiyer;
	
	private String blank01;
	private String blank02;

	public Emccontact() {
	}

	public Emccontact(Integer cusId, String cusType, String contactStatus,
			String name, String genderType, Date birthday, String idType,
			String idNo, String tel, String fixedTel, String email,
			String relationship, String province, String city, String area,
			String street, String zip, String workplace, Date created,
			Integer creator, Date lastmod, Integer modifiyer, String blank01,
			String blank02) {
		this.cusId = cusId;
		this.cusType = cusType;
		this.contactStatus = contactStatus;
		this.name = name;
		this.genderType = genderType;
		this.birthday = birthday;
		this.idType = idType;
		this.idNo = idNo;
		this.tel = tel;
		this.fixedTel = fixedTel;
		this.email = email;
		this.relationship = relationship;
		this.province = province;
		this.city = city;
		this.area = area;
		this.street = street;
		this.zip = zip;
		this.workplace = workplace;
		this.created = created;
		this.creator = creator;
		this.lastmod = lastmod;
		this.modifiyer = modifiyer;
		this.blank01 = blank01;
		this.blank02 = blank02;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID", unique = true, nullable = false)
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@Column(name = "CUS_ID")
	public Integer getCusId() {
		return this.cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_TYPE", length = 30)
	public String getCusType() {
		return this.cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	@Column(name = "CONTACT_STATUS", length = 1)
	public String getContactStatus() {
		return this.contactStatus;
	}

	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}

	@Column(name = "NAME", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER_TYPE", length = 1)
	public String getGenderType() {
		return this.genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ID_TYPE", length = 32)
	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	@Column(name = "ID_NO", length = 32)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FIXED_TEL", length = 20)
	public String getFixedTel() {
		return this.fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "RELATIONSHIP", length = 100)
	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Column(name = "PROVINCE", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "AREA", length = 50)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "STREET", length = 1000)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "ZIP", length = 20)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "WORKPLACE", length = 100)
	public String getWorkplace() {
		return this.workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED", length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "CREATOR")
	public Integer getCreator() {
		return this.creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return this.lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "MODIFIYER")
	public Integer getModifiyer() {
		return this.modifiyer;
	}

	public void setModifiyer(Integer modifiyer) {
		this.modifiyer = modifiyer;
	}

	@Column(name = "BLANK01", length = 100)
	public String getBlank01() {
		return this.blank01;
	}

	public void setBlank01(String blank01) {
		this.blank01 = blank01;
	}

	@Column(name = "BLANK02", length = 100)
	public String getBlank02() {
		return this.blank02;
	}

	public void setBlank02(String blank02) {
		this.blank02 = blank02;
	}
}
