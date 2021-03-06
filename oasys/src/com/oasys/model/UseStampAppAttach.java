package com.oasys.model;

// Generated 2015-12-11 13:36:55 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * TOaAdUseStampAppAttach generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_use_stamp_app_attach", catalog = "oasys")
public class UseStampAppAttach implements java.io.Serializable {

	private Integer saaId;
	private String appNo;
	/**印章名字id*/
	private String stampName;
	/**印章类型*/
	private String stampType;
	/**其它印证*/
	private String stampTypeOther;
	/**用章事由*/
	private String useReason;
	/**是否归还*/
	private String isGiveback;
	/**接受时间，手动填写*/
	private Date receiverDtime;
	/**归还时间*/
	private Date givebackDtime;
	/**快递单号*/
	private String emsNo;
	/**邮寄地址*/
	private String postAddr;
	/**联系方式*/
	private String contactWay;
	private String remark;
	
	/**印章名字*/
	private String stampText;
	/**印章类型名称*/
	private String stampTypeName;
	/**使用性质*/
	private String useNature;

	public UseStampAppAttach() {
	}

	public UseStampAppAttach(String appNo, String stampName,
			String stampType, String stampTypeOther, String useReason,
			String isGiveback, Date receiverDtime, Date givebackDtime,
			String emsNo, String postAddr, String contactWay, String remark,String stampText,String stampTypeName,String useNature) {
		this.appNo = appNo;
		this.stampName = stampName;
		this.stampType = stampType;
		this.stampTypeOther = stampTypeOther;
		this.useReason = useReason;
		this.isGiveback = isGiveback;
		this.receiverDtime = receiverDtime;
		this.givebackDtime = givebackDtime;
		this.emsNo = emsNo;
		this.postAddr = postAddr;
		this.contactWay = contactWay;
		this.remark = remark;
		this.stampText=stampText;
		this.stampTypeName=stampTypeName;
		this.useNature=useNature;
	}
	@Transient
	public String getUseNature() {
		return useNature;
	}

	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}

	@Transient
	public String getStampTypeName() {
		return stampTypeName;
	}

	public void setStampTypeName(String stampTypeName) {
		this.stampTypeName = stampTypeName;
	}

	@Transient
	public String getStampText() {
		return stampText;
	}

	public void setStampText(String stampText) {
		this.stampText = stampText;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SAA_ID", unique = true, nullable = false)
	public Integer getSaaId() {
		return this.saaId;
	}

	public void setSaaId(Integer saaId) {
		this.saaId = saaId;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "STAMP_NAME", length = 80)
	public String getStampName() {
		return this.stampName;
	}

	public void setStampName(String stampName) {
		this.stampName = stampName;
	}

	@Column(name = "STAMP_TYPE", length = 1)
	public String getStampType() {
		return this.stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	@Column(name = "STAMP_TYPE_OTHER", length = 30)
	public String getStampTypeOther() {
		return this.stampTypeOther;
	}

	public void setStampTypeOther(String stampTypeOther) {
		this.stampTypeOther = stampTypeOther;
	}

	@Column(name = "USE_REASON", length = 256)
	public String getUseReason() {
		return this.useReason;
	}

	public void setUseReason(String useReason) {
		this.useReason = useReason;
	}

	@Column(name = "IS_GIVEBACK", length = 1)
	public String getIsGiveback() {
		return this.isGiveback;
	}

	public void setIsGiveback(String isGiveback) {
		this.isGiveback = isGiveback;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECEIVER_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getReceiverDtime() {
		return this.receiverDtime;
	}

	public void setReceiverDtime(Date receiverDtime) {
		this.receiverDtime = receiverDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GIVEBACK_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getGivebackDtime() {
		return this.givebackDtime;
	}

	public void setGivebackDtime(Date givebackDtime) {
		this.givebackDtime = givebackDtime;
	}

	@Column(name = "EMS_NO", length = 50)
	public String getEmsNo() {
		return this.emsNo;
	}

	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	@Column(name = "POST_ADDR", length = 256)
	public String getPostAddr() {
		return this.postAddr;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	@Column(name = "CONTACT_WAY", length = 25)
	public String getContactWay() {
		return this.contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
