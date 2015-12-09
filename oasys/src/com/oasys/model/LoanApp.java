package com.oasys.model;

// Generated 2015-11-13 9:43:53 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 借款申请
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_oa_fd_loan_app", catalog = "oasys", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class LoanApp implements java.io.Serializable,Cloneable {

	private Integer btaId;//ID
	private String appNo;//编号
	private Integer applicantNo;//申请人id
	private Date appDate;//申请时间
	private String appStatus;//申请状态
	private String isBalance;//是否销账
	private String capitalNature;//资金性质
	private String loanReson;//借款理由
	private BigDecimal loanAmt;//借款金额
	private Integer payerNo;//付款人id
	private Date payDate;//付款日期
	private String payMode;//付款方式
	private String loanPurpose;//借款用途
	private String procStatus;//流程状态
	private String remark;//备注
	
	/**
	 * //开始申请时间
	 */
	private String appDateS;
	/**
	 * //结束时间
	 */
	private String appDateE;
	/**
	 * 申请人姓名
	 */
	private String applicantName;
	/**
	 * 资金性质名字
	 */
	private String capitalNatureName;
	/**
	 * 付款人姓名
	 */
	private String payerName;
	/**
	 * 角色
	 */
	private String roleCode;
	/**
	 * 判断总部、分部
	 */
	private String deptLeave;
	
	
	
	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskID;//任务taskID
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String ideaRemark;//审批意见
	private String definitionKey;//流程表示key
	

	public LoanApp() {
	}

	

	


	public LoanApp(Integer btaId, String appNo, Integer applicantNo,
			Date appDate, String appStatus, String isBalance,
			String capitalNature, String loanReson, BigDecimal loanAmt,
			Integer payerNo, Date payDate, String payMode,String loanPurpose,
			String procStatus, String remark, String appDateS,
			String appDateE, String applicantName, String capitalNatureName,
			String payerName, String roleCode, String deptLeave,
			WorkFlowTasksModel taskModel, String result, String taskID,
			String formKey, String assistant, String isSuccess,
			String ideaRemark, String definitionKey) {
		super();
		this.btaId = btaId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.isBalance = isBalance;
		this.capitalNature = capitalNature;
		this.loanReson = loanReson;
		this.loanAmt = loanAmt;
		this.payerNo = payerNo;
		this.payDate = payDate;
		this.payMode = payMode;
		this.procStatus = procStatus;
		this.loanPurpose=loanPurpose;
		this.remark = remark;
		this.appDateS = appDateS;
		this.appDateE = appDateE;
		this.applicantName = applicantName;
		this.capitalNatureName = capitalNatureName;
		this.payerName = payerName;
		this.roleCode = roleCode;
		this.deptLeave = deptLeave;
		this.taskModel = taskModel;
		this.result = result;
		this.taskID = taskID;
		this.formKey = formKey;
		this.assistant = assistant;
		this.isSuccess = isSuccess;
		this.ideaRemark = ideaRemark;
		this.definitionKey = definitionKey;
	}



	@Transient
	public String getRoleCode() {
		return roleCode;
	}



	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	@Transient
	public String getDeptLeave() {
		return deptLeave;
	}

	public void setDeptLeave(String deptLeave) {
		this.deptLeave = deptLeave;
	}



	@Transient
	public String getAppDateS() {
		return appDateS;
	}



	public void setAppDateS(String appDateS) {
		this.appDateS = appDateS;
	}


	@Transient
	public String getAppDateE() {
		return appDateE;
	}



	public void setAppDateE(String appDateE) {
		this.appDateE = appDateE;
	}


	@Transient
	public String getApplicantName() {
		return applicantName;
	}



	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	@Transient
	public String getCapitalNatureName() {
		return capitalNatureName;
	}



	public void setCapitalNatureName(String capitalNatureName) {
		this.capitalNatureName = capitalNatureName;
	}


	@Transient
	public String getPayerName() {
		return payerName;
	}



	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}


	@Transient
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}



	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}


	@Transient
	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}


	@Transient
	public String getTaskID() {
		return taskID;
	}



	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}


	@Transient
	public String getFormKey() {
		return formKey;
	}



	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}


	@Transient
	public String getAssistant() {
		return assistant;
	}



	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}


	@Transient
	public String getIsSuccess() {
		return isSuccess;
	}



	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}


	@Transient
	public String getIdeaRemark() {
		return ideaRemark;
	}



	public void setIdeaRemark(String ideaRemark) {
		this.ideaRemark = ideaRemark;
	}


	@Transient
	public String getDefinitionKey() {
		return definitionKey;
	}



	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BTA_ID", unique = true, nullable = false)
	public Integer getBtaId() {
		return this.btaId;
	}

	public void setBtaId(Integer btaId) {
		this.btaId = btaId;
	}

	@Column(name = "LOAN_PURPOSE", length = 1)
	public String getLoanPurpose() {
		return this.loanPurpose;
	}
	
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "IS_BALANCE", length = 1)
	public String getIsBalance() {
		return this.isBalance;
	}

	public void setIsBalance(String isBalance) {
		this.isBalance = isBalance;
	}

	@Column(name = "CAPITAL_NATURE", length = 80)
	public String getCapitalNature() {
		return this.capitalNature;
	}

	public void setCapitalNature(String capitalNature) {
		this.capitalNature = capitalNature;
	}

	@Column(name = "LOAN_RESON", length = 200)
	public String getLoanReson() {
		return this.loanReson;
	}

	public void setLoanReson(String loanReson) {
		this.loanReson = loanReson;
	}

	@Column(name = "LOAN_AMT", precision = 10)
	public BigDecimal getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	@Column(name = "PAYER_NO")
	public Integer getPayerNo() {
		return this.payerNo;
	}

	public void setPayerNo(Integer payerNo) {
		this.payerNo = payerNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name = "PAY_MODE", length = 1)
	public String getPayMode() {
		return this.payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public String getProcStatus() {
		return this.procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
