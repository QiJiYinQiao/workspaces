package com.oasys.viewModel;

import java.math.BigDecimal;
import java.util.Date;

import com.oasys.util.UniqueIdUtil;

/**
 *author:yuanzhongqiu
 *Time:2015年9月22日  下午12:00:44
 */
public class PpeTurnoverModel  implements java.io.Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	//主键ID   
		private Integer ptaId;
		//申请编号
		private String appNo;
		//申请人ID
		private Integer applicantNo;
		//申请人
		private String name;
		//申请部门
		private Integer appDept;
		
		private String appDeptName;
		//申请日期
		private Date appDate;
		
		private String appDateBefore;
		private String appDateAfter;
		//申请状态
		private String appStatus;
		//交接日期
		private Date concedeDate;
		//交接事由
		private String concedeReson;
		//交接地点
		private String concedeAddr;
		//接收部门
		private Integer takeoverDept;
		
		private String takeoverDeptName;
		//接收后使用人
		private Integer takeoverUser;
		
		private String takeoverUserName;
		//资产数量
		private Integer ppeQty;
		//资产金额合计
		private BigDecimal ppeTotalAmt;
		//流程状态
		private Character procStatus;
		//备注信息
		private String remark;
		//资产编号
		private String ppeNo;
		//资产名称
		private String ppeName;
		//规格型号
		private String model;
		//单位
		private String unit;
		//数量
		private Integer qty;
		//资产金额
		private BigDecimal ppeAmt;
		public Integer getPtaId() {
			return ptaId;
		}
		public void setPtaId(Integer ptaId) {
			this.ptaId = ptaId;
		}
		public String getAppNo() {
			return appNo;
		}
		public void setAppNo(String appNo) {
			this.appNo = appNo;
		}
		public Integer getApplicantNo() {
			return applicantNo;
		}
		public void setApplicantNo(Integer applicantNo) {
			this.applicantNo = applicantNo;
		}
		public Integer getAppDept() {
			return appDept;
		}
		public void setAppDept(Integer appDept) {
			this.appDept = appDept;
		}
		public Date getAppDate() {
			return appDate;
		}
		public void setAppDate(Date appDate) {
			this.appDate = appDate;
		}
		public String getAppStatus() {
			return appStatus;
		}
		public void setAppStatus(String appStatus) {
			this.appStatus = appStatus;
		}
		public Date getConcedeDate() {
			return concedeDate;
		}
		public void setConcedeDate(Date concedeDate) {
			this.concedeDate = concedeDate;
		}
		public String getConcedeReson() {
			return concedeReson;
		}
		public void setConcedeReson(String concedeReson) {
			this.concedeReson = concedeReson;
		}
		public String getConcedeAddr() {
			return concedeAddr;
		}
		public void setConcedeAddr(String concedeAddr) {
			this.concedeAddr = concedeAddr;
		}
		public Integer getTakeoverDept() {
			return takeoverDept;
		}
		public void setTakeoverDept(Integer takeoverDept) {
			this.takeoverDept = takeoverDept;
		}
		public Integer getTakeoverUser() {
			return takeoverUser;
		}
		public void setTakeoverUser(Integer takeoverUser) {
			this.takeoverUser = takeoverUser;
		}
		public Integer getPpeQty() {
			return ppeQty;
		}
		public void setPpeQty(Integer ppeQty) {
			this.ppeQty = ppeQty;
		}
		public BigDecimal getPpeTotalAmt() {
			return ppeTotalAmt;
		}
		public void setPpeTotalAmt(BigDecimal ppeTotalAmt) {
			this.ppeTotalAmt = ppeTotalAmt;
		}
		public Character getProcStatus() {
			return procStatus;
		}
		public void setProcStatus(Character procStatus) {
			this.procStatus = procStatus;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getPpeNo() {
			return ppeNo;
		}
		public void setPpeNo(String ppeNo) {
			this.ppeNo = ppeNo;
		}
		public String getPpeName() {
			return ppeName;
		}
		public void setPpeName(String ppeName) {
			this.ppeName = ppeName;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public Integer getQty() {
			return qty;
		}
		public void setQty(Integer qty) {
			this.qty = qty;
		}
		public BigDecimal getPpeAmt() {
			return ppeAmt;
		}
		public void setPpeAmt(BigDecimal ppeAmt) {
			this.ppeAmt = ppeAmt;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTakeoverUserName() {
			return takeoverUserName;
		}
		public void setTakeoverUserName(String takeoverUserName) {
			this.takeoverUserName = takeoverUserName;
		}
		public String getAppDeptName() {
			return appDeptName;
		}
		public void setAppDeptName(String appDeptName) {
			this.appDeptName = appDeptName;
		}
		public String getTakeoverDeptName() {
			return takeoverDeptName;
		}
		public void setTakeoverDeptName(String takeoverDeptName) {
			this.takeoverDeptName = takeoverDeptName;
		}
		public String getAppDateBefore() {
			return appDateBefore;
		}
		public void setAppDateBefore(String appDateBefore) {
			this.appDateBefore = appDateBefore;
		}
		public String getAppDateAfter() {
			return appDateAfter;
		}
		public void setAppDateAfter(String appDateAfter) {
			this.appDateAfter = appDateAfter;
		}
		
		
}


