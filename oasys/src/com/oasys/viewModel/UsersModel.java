package com.oasys.viewModel;

import java.util.Date;

public class UsersModel implements java.io.Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String myid;
	private String account;
	private String name;
	private Integer organizeId;
	private Integer dutyId;
	private Integer titleId;
	private String password;
	private String email;
	private String lang;
	private String theme;
	private Date firstVisit;
	private Date previousVisit;
	private Date lastVisits;
	private Integer loginCount;
	private Integer isemployee;
	private String status;
	private String ip;
	private String description;
	private Integer questionId;
	private String answer;
	private Integer isonline;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;
	private String tel;
	private String fullName;//所在机构名称
	private String mobile;
	//英文名字
	private String ename;
	
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
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
	public Integer getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	public Integer getDutyId() {
		return dutyId;
	}
	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
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
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Date getFirstVisit() {
		return firstVisit;
	}
	public void setFirstVisit(Date firstVisit) {
		this.firstVisit = firstVisit;
	}
	public Date getPreviousVisit() {
		return previousVisit;
	}
	public void setPreviousVisit(Date previousVisit) {
		this.previousVisit = previousVisit;
	}
	public Date getLastVisits() {
		return lastVisits;
	}
	public void setLastVisits(Date lastVisits) {
		this.lastVisits = lastVisits;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getIsemployee() {
		return isemployee;
	}
	public void setIsemployee(Integer isemployee) {
		this.isemployee = isemployee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getIsonline() {
		return isonline;
	}
	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public Object clone(){
		// TODO Auto-generated method stub
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
