package com.oasys.model;

// Generated 2015-10-14 10:29:36 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理中心月度工作计划附加表实体
 * @ClassName: MonthlyWorkPlanAttach 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月15日 下午2:53:30
 */
@Entity
@Table(name = "t_oa_ad_monthly_work_plan_attach")
@SuppressWarnings("serial")
public class MonthlyWorkPlanAttach implements java.io.Serializable,Cloneable{
    /**
     * 主键ID
     */
	private Integer mwpaId;
	/**
	 * 月度工作计划登记编号(外键)
	 */
	private Integer mwpId;
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
	private String remark;

	public MonthlyWorkPlanAttach() {
	}

	public MonthlyWorkPlanAttach(
			MonthlyWorkPlan monthlyWorkPlan, String contSumup,
			BigDecimal completed1, BigDecimal completed2,
			BigDecimal completed3, BigDecimal completed4, String contPlan,
			BigDecimal planComp1, BigDecimal planComp2, BigDecimal planComp3,
			BigDecimal planComp4, String remark) {
		this.contSumup = contSumup;
		this.completed1 = completed1;
		this.completed2 = completed2;
		this.completed3 = completed3;
		this.completed4 = completed4;
		this.contPlan = contPlan;
		this.planComp1 = planComp1;
		this.planComp2 = planComp2;
		this.planComp3 = planComp3;
		this.planComp4 = planComp4;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MWPA_ID", unique = true, nullable = false)
	public Integer getMwpaId() {
		return this.mwpaId;
	}

	public void setMwpaId(Integer mwpaId) {
		this.mwpaId = mwpaId;
	}
	
	@Column(name = "MWP_ID")
	public Integer getMwpId() {
		return mwpId;
	}

	public void setMwpId(Integer mwpId) {
		this.mwpId = mwpId;
	}

	@Column(name = "CONT_SUMUP", length = 500)
	public String getContSumup() {
		return this.contSumup;
	}

	public void setContSumup(String contSumup) {
		this.contSumup = contSumup;
	}

	@Column(name = "COMPLETED1", precision = 5)
	public BigDecimal getCompleted1() {
		return this.completed1;
	}

	public void setCompleted1(BigDecimal completed1) {
		this.completed1 = completed1;
	}

	@Column(name = "COMPLETED2", precision = 5)
	public BigDecimal getCompleted2() {
		return this.completed2;
	}

	public void setCompleted2(BigDecimal completed2) {
		this.completed2 = completed2;
	}

	@Column(name = "COMPLETED3", precision = 5)
	public BigDecimal getCompleted3() {
		return this.completed3;
	}

	public void setCompleted3(BigDecimal completed3) {
		this.completed3 = completed3;
	}

	@Column(name = "COMPLETED4", precision = 5)
	public BigDecimal getCompleted4() {
		return this.completed4;
	}

	public void setCompleted4(BigDecimal completed4) {
		this.completed4 = completed4;
	}

	@Column(name = "CONT_PLAN", length = 500)
	public String getContPlan() {
		return this.contPlan;
	}

	public void setContPlan(String contPlan) {
		this.contPlan = contPlan;
	}

	@Column(name = "PLAN_COMP1", precision = 5)
	public BigDecimal getPlanComp1() {
		return this.planComp1;
	}

	public void setPlanComp1(BigDecimal planComp1) {
		this.planComp1 = planComp1;
	}

	@Column(name = "PLAN_COMP2", precision = 5)
	public BigDecimal getPlanComp2() {
		return this.planComp2;
	}

	public void setPlanComp2(BigDecimal planComp2) {
		this.planComp2 = planComp2;
	}

	@Column(name = "PLAN_COMP3", precision = 5)
	public BigDecimal getPlanComp3() {
		return this.planComp3;
	}

	public void setPlanComp3(BigDecimal planComp3) {
		this.planComp3 = planComp3;
	}

	@Column(name = "PLAN_COMP4", precision = 5)
	public BigDecimal getPlanComp4() {
		return this.planComp4;
	}

	public void setPlanComp4(BigDecimal planComp4) {
		this.planComp4 = planComp4;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
