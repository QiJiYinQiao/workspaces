package com.oasys.model.VO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.oasys.model.UserRole;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 3091722681204768199L;
	private Integer userId;// 用户id
	private String myid;// 用户编码
	private String account;// 登录账户
	private String name;// 用户名称
	private String ename;// 用户英文名称
	private Integer organizeId;// 部门编号
	private String password;// 登录密码
	private String email;// 电子邮箱
	private String mobile;// 移动电话
	private Integer age;// 年龄
	private String gender;// 性别
	private String origo;// 籍贯
	private String nation;// 民族
	private Date birthday;// 出生日期
	private Integer height;// 身高
	private BigDecimal weight;// 体重
	private String healthStatus;// 健康状况
	private String domiType;// 户籍性质
	private String idCard;// 身份证号码
	private String idCardAddr;// 身份证地址
	private String maritalStatus;// 婚姻状况
	private String education;// 最高学历
	private String graduateSchool;// 毕业院校
	private String major;// 专业
	private Date graduateDate;// 毕业时间
	private Date takejobDate;// 参加工作时间
	private String domiAddr;// 户口所在地
	private String postalAddr;// 通讯地址
	private String status;// 有效标识
	private String description;// 备注信息
	private Date created;// 创建时间
	private Date lastmod;// 修改时间
	private Integer creater;// 创建人
	private Integer modifyer;// 修改人
	private String tel;// 固定电话
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOrigo() {
		return origo;
	}
	public void setOrigo(String origo) {
		this.origo = origo;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getDomiType() {
		return domiType;
	}
	public void setDomiType(String domiType) {
		this.domiType = domiType;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdCardAddr() {
		return idCardAddr;
	}
	public void setIdCardAddr(String idCardAddr) {
		this.idCardAddr = idCardAddr;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Date getGraduateDate() {
		return graduateDate;
	}
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	public Date getTakejobDate() {
		return takejobDate;
	}
	public void setTakejobDate(Date takejobDate) {
		this.takejobDate = takejobDate;
	}
	public String getDomiAddr() {
		return domiAddr;
	}
	public void setDomiAddr(String domiAddr) {
		this.domiAddr = domiAddr;
	}
	public String getPostalAddr() {
		return postalAddr;
	}
	public void setPostalAddr(String postalAddr) {
		this.postalAddr = postalAddr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastmod() {
		return lastmod;
	}
	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Integer getModifyer() {
		return modifyer;
	}
	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
