package com.oasys.model;

// Generated 2015-11-13 9:43:53 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 差旅费申请主表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_oa_fd_travel_expenses_app", catalog = "oasys", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class TravelExpensesApp implements java.io.Serializable,Cloneable {

	private Integer teaId;
	private String appNo;
	private String jkAppNo;//借款编号
	private Integer applicantNo;
	private Integer deptNo;
	private Date appDate;
	private String appStatus;
	private BigDecimal btDays;
	private BigDecimal subsidyAmt;
	/**出差事由*/
	private String btReason;
	/**报销总额*/
	private BigDecimal expAmt;
	/**预借旅费*/
	private BigDecimal grantExp;
	/**补领金额*/
	private BigDecimal supplyAmt;
	/**退换金额*/
	private BigDecimal givebackAmt;
	private String procStatus;
	private String remark;
	
	/**申请人名字*/
	private String applicantName;
	/**申请部门名字*/
	private String fullName;
	/**
	 * //开始申请时间
	 */
	private String appDateS;
	/**
	 * //结束时间
	 */
	private String appDateE;
	/**角色编码*/
	private String roleCode;
	/**0：总部，1：分部*/
	private String deptLevel;
	
	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskID;//任务taskID
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String ideaRemark;//审批意见
	private String definitionKey;//流程表示key

	
	
	@Transient
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Transient
	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	@Transient
	public String getApplicantName() {
		return applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	@Transient
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public TravelExpensesApp() {
	}

	public TravelExpensesApp(String appNo) {
		this.appNo = appNo;
	}

	


	public TravelExpensesApp(Integer teaId, String appNo, Integer applicantNo,String jkAppNo,
			Integer deptNo, Date appDate, String appStatus, String btReason,BigDecimal btDays, BigDecimal subsidyAmt,
			BigDecimal expAmt, BigDecimal grantExp, BigDecimal supplyAmt,
			BigDecimal givebackAmt, String procStatus, String remark,
			String applicantName, String fullName, String appDateS,
			String appDateE, String roleCode, String deptLevel,
			WorkFlowTasksModel taskModel, String result, String taskID,
			String formKey, String assistant, String isSuccess,
			String ideaRemark, String definitionKey) {
		super();
		this.teaId = teaId;
		this.appNo = appNo;
		this.jkAppNo=jkAppNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.btReason = btReason;
		this.btDays = btDays;
		this.subsidyAmt = subsidyAmt;
		this.expAmt = expAmt;
		this.grantExp = grantExp;
		this.supplyAmt = supplyAmt;
		this.givebackAmt = givebackAmt;
		this.procStatus = procStatus;
		this.remark = remark;
		this.applicantName = applicantName;
		this.fullName = fullName;
		this.appDateS = appDateS;
		this.appDateE = appDateE;
		this.roleCode = roleCode;
		this.deptLevel = deptLevel;
		this.taskModel = taskModel;
		this.result = result;
		this.taskID = taskID;
		this.formKey = formKey;
		this.assistant = assistant;
		this.isSuccess = isSuccess;
		this.ideaRemark = ideaRemark;
		this.definitionKey = definitionKey;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TEA_ID", unique = true, nullable = false)
	public Integer getTeaId() {
		return this.teaId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	@Column(name = "APP_NO", unique = true, nullable = false, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "JK_APP_NO", length = 256)
	public String getJkAppNo() {
		return this.jkAppNo;
	}

	public void setJkAppNo(String jkAppNo) {
		this.jkAppNo = jkAppNo;
	}
	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Column(name = "DEPT_NO")
	public Integer getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	
	@Column(name = "BT_DAYS", precision = 4, scale = 1)
	public BigDecimal getBtDays() {
		return this.btDays;
	}

	public void setBtDays(BigDecimal btDays) {
		this.btDays = btDays;
	}

	@Column(name = "SUBSIDY_AMT", precision = 10)
	public BigDecimal getSubsidyAmt() {
		return this.subsidyAmt;
	}

	public void setSubsidyAmt(BigDecimal subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
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

	@Column(name = "BT_REASON", length = 200)
	public String getBtReason() {
		return this.btReason;
	}

	public void setBtReason(String btReason) {
		this.btReason = btReason;
	}

	@Column(name = "EXP_AMT", precision = 10)
	public BigDecimal getExpAmt() {
		return this.expAmt;
	}

	public void setExpAmt(BigDecimal expAmt) {
		this.expAmt = expAmt;
	}

	@Column(name = "GRANT_EXP", precision = 10)
	public BigDecimal getGrantExp() {
		return this.grantExp;
	}

	public void setGrantExp(BigDecimal grantExp) {
		this.grantExp = grantExp;
	}

	@Column(name = "SUPPLY_AMT", precision = 10)
	public BigDecimal getSupplyAmt() {
		return this.supplyAmt;
	}

	public void setSupplyAmt(BigDecimal supplyAmt) {
		this.supplyAmt = supplyAmt;
	}

	@Column(name = "GIVEBACK_AMT", precision = 10)
	public BigDecimal getGivebackAmt() {
		return this.givebackAmt;
	}

	public void setGivebackAmt(BigDecimal givebackAmt) {
		this.givebackAmt = givebackAmt;
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
