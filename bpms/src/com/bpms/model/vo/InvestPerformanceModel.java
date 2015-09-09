package com.bpms.model.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: InvestPerformanceModel
 * @Description: TODO 理财业绩VO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年8月5日 下午2:39:15
 *
 */
public class InvestPerformanceModel implements Serializable, Cloneable {
	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	private String orgName;// 机构名称
	private String investMoneyDay;// 当日理财金额（元）
	private String countDay;// 当日单数（笔）
	private String investEduMonth;// 当月理财金额（元）
	private String investEduMonthOfYear;// 当月年化金额（元）

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInvestMoneyDay() {
		return investMoneyDay;
	}

	public void setInvestMoneyDay(String investMoneyDay) {
		this.investMoneyDay = investMoneyDay;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}

	public String getInvestEduMonth() {
		return investEduMonth;
	}

	public void setInvestEduMonth(String investEduMonth) {
		this.investEduMonth = investEduMonth;
	}

	public String getInvestEduMonthOfYear() {
		return investEduMonthOfYear;
	}

	public void setInvestEduMonthOfYear(String investEduMonthOfYear) {
		this.investEduMonthOfYear = investEduMonthOfYear;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
