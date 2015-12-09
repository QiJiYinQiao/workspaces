package com.oasys.model.VO;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 管理中心月度工作计划表vo
 * @ClassName: MonthlyWorkPlanModel 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月15日 下午2:59:52
 */
public class MonthlyWorkPlanModel implements java.io.Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer mwpId;
	/**
	 * 填表人
	 */
	private Integer preparer;
	/**
	 * 填表人职位
	 */
	private String position;
	/**
	 * 所属部门
	 */
	private Integer appDept;
	/**
	 * 填表日期
	 */
	private Date fillingDate;
	/**
	 * 本月未完成工作及原因
	 */
	private String ufwReson;
	/**
	 * 备注信息
	 */
	private String remark;

	
	/**
     * 主键ID
     */
	private Integer mwpaId;
	/**
	 * 本月工作内容总结
	 */
	private String contSumup;
	/**
	 * 本月第一周完成进度
	 */
	private BigDecimal completed1;
	/**
	 * 本月第二周完成进度
	 */
	private BigDecimal completed2;
	/**
	 * 本月第三周完成进度
	 */
	private BigDecimal completed3;
	/**
	 * 本月第四周完成进度
	 */
	private BigDecimal completed4;
	/**
	 * 下月工作内容计划
	 */
	private String contPlan;
	/**
	 * 下月第一周计划完成进度
	 */
	private BigDecimal planComp1;
	/**
	 * 本月第二周计划完成进度
	 */
	private BigDecimal planComp2;
	/**
	 * 本月第三周计划完成进度
	 */
	private BigDecimal planComp3;
	/**
	 * 本月第四周计划完成进度
	 */
	private BigDecimal planComp4;
	/**
	 * 备注信息
	 */
	private String sonRemark;
	
	
	/**
	 * 填表人名称
	 */
	private String preparerName;
	/**
	 * 所属部门名称
	 */
	private String appDeptName;
	
	private String appDateMini;
	
	private String appDateMax;
	
	
	public Integer getMwpId() {
		return mwpId;
	}
	public void setMwpId(Integer mwpId) {
		this.mwpId = mwpId;
	}
	public Integer getPreparer() {
		return preparer;
	}
	public void setPreparer(Integer preparer) {
		this.preparer = preparer;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getAppDept() {
		return appDept;
	}
	public void setAppDept(Integer appDept) {
		this.appDept = appDept;
	}
	public Date getFillingDate() {
		return fillingDate;
	}
	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}
	public String getUfwReson() {
		return ufwReson;
	}
	public void setUfwReson(String ufwReson) {
		this.ufwReson = ufwReson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getMwpaId() {
		return mwpaId;
	}
	public void setMwpaId(Integer mwpaId) {
		this.mwpaId = mwpaId;
	}
	public String getContSumup() {
		return contSumup;
	}
	public void setContSumup(String contSumup) {
		this.contSumup = contSumup;
	}
	public BigDecimal getCompleted1() {
		return completed1;
	}
	public void setCompleted1(BigDecimal completed1) {
		this.completed1 = completed1;
	}
	public BigDecimal getCompleted2() {
		return completed2;
	}
	public void setCompleted2(BigDecimal completed2) {
		this.completed2 = completed2;
	}
	public BigDecimal getCompleted3() {
		return completed3;
	}
	public void setCompleted3(BigDecimal completed3) {
		this.completed3 = completed3;
	}
	public BigDecimal getCompleted4() {
		return completed4;
	}
	public void setCompleted4(BigDecimal completed4) {
		this.completed4 = completed4;
	}
	public String getContPlan() {
		return contPlan;
	}
	public void setContPlan(String contPlan) {
		this.contPlan = contPlan;
	}
	public BigDecimal getPlanComp1() {
		return planComp1;
	}
	public void setPlanComp1(BigDecimal planComp1) {
		this.planComp1 = planComp1;
	}
	public BigDecimal getPlanComp2() {
		return planComp2;
	}
	public void setPlanComp2(BigDecimal planComp2) {
		this.planComp2 = planComp2;
	}
	public BigDecimal getPlanComp3() {
		return planComp3;
	}
	public void setPlanComp3(BigDecimal planComp3) {
		this.planComp3 = planComp3;
	}
	public BigDecimal getPlanComp4() {
		return planComp4;
	}
	public void setPlanComp4(BigDecimal planComp4) {
		this.planComp4 = planComp4;
	}
	public String getSonRemark() {
		return sonRemark;
	}
	public void setSonRemark(String sonRemark) {
		this.sonRemark = sonRemark;
	}
	
	public String getPreparerName() {
		return preparerName;
	}
	public void setPreparerName(String preparerName) {
		this.preparerName = preparerName;
	}
	public String getAppDeptName() {
		return appDeptName;
	}
	public void setAppDeptName(String appDeptName) {
		this.appDeptName = appDeptName;
	}
	public String getAppDateMini() {
		return appDateMini;
	}
	public void setAppDateMini(String appDateMini) {
		this.appDateMini = appDateMini;
	}
	public String getAppDateMax() {
		return appDateMax;
	}
	public void setAppDateMax(String appDateMax) {
		this.appDateMax = appDateMax;
	}
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
