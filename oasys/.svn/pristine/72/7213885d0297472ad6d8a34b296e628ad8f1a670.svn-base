package com.oasys.model;

// Generated 2015-10-14 10:29:36 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 管理中心月度工作计划表实体
 * @ClassName: MonthlyWorkPlan 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月15日 下午2:52:06
 */
@Entity
@Table(name = "t_oa_ad_monthly_work_plan")
@SuppressWarnings("serial")
public class MonthlyWorkPlan implements java.io.Serializable,Cloneable{

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

	public MonthlyWorkPlan() {
	}

	public MonthlyWorkPlan(Integer preparer, String position,
			Integer appDept, Date fillingDate, String ufwReson, String remark,
			Set<MonthlyWorkPlanAttach> monthlyWorkPlanAttaches) {
		this.preparer = preparer;
		this.position = position;
		this.appDept = appDept;
		this.fillingDate = fillingDate;
		this.ufwReson = ufwReson;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MWP_ID", unique = true, nullable = false)
	public Integer getMwpId() {
		return this.mwpId;
	}

	public void setMwpId(Integer mwpId) {
		this.mwpId = mwpId;
	}

	@Column(name = "PREPARER")
	public Integer getPreparer() {
		return this.preparer;
	}

	public void setPreparer(Integer preparer) {
		this.preparer = preparer;
	}

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "APP_DEPT")
	public Integer getAppDept() {
		return this.appDept;
	}

	public void setAppDept(Integer appDept) {
		this.appDept = appDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FILLING_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getFillingDate() {
		return this.fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

	@Column(name = "UFW_RESON", length = 500)
	public String getUfwReson() {
		return this.ufwReson;
	}

	public void setUfwReson(String ufwReson) {
		this.ufwReson = ufwReson;
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
