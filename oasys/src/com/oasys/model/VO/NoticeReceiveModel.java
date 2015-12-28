package com.oasys.model.VO;

import java.io.Serializable;
import java.util.Date;

public class NoticeReceiveModel implements Serializable {
	private Integer noiId;
	private String noticeNo;
	private Integer sender;
	private String sendName;
	private Date sendDtime;
	private String topic;
	private String noticeGrade;
	private String noticeContent;
	private String sentStatus;
	private String isNeedReceipt;
	private String isAllReceipted;
	private String isHaveAtt;
	private String remark;

	private Integer niaId;
	private Integer receiver;
	private String isReceipted;
	private String isDel;

	public Integer getNoiId() {
		return noiId;
	}

	public void setNoiId(Integer noiId) {
		this.noiId = noiId;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public Date getSendDtime() {
		return sendDtime;
	}

	public void setSendDtime(Date sendDtime) {
		this.sendDtime = sendDtime;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getNoticeGrade() {
		return noticeGrade;
	}

	public void setNoticeGrade(String noticeGrade) {
		this.noticeGrade = noticeGrade;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}

	public String getIsNeedReceipt() {
		return isNeedReceipt;
	}

	public void setIsNeedReceipt(String isNeedReceipt) {
		this.isNeedReceipt = isNeedReceipt;
	}

	public String getIsAllReceipted() {
		return isAllReceipted;
	}

	public void setIsAllReceipted(String isAllReceipted) {
		this.isAllReceipted = isAllReceipted;
	}

	public String getIsHaveAtt() {
		return isHaveAtt;
	}

	public void setIsHaveAtt(String isHaveAtt) {
		this.isHaveAtt = isHaveAtt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getNiaId() {
		return niaId;
	}

	public void setNiaId(Integer niaId) {
		this.niaId = niaId;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public String getIsReceipted() {
		return isReceipted;
	}

	public void setIsReceipted(String isReceipted) {
		this.isReceipted = isReceipted;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
