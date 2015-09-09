package com.qqms.model.VO;
/**
 * 
 * @ClassName: AccountJournal
 * @Description: TODO 账户流水VO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年7月3日 下午4:00:51
 *
 */
public class AccountJournalVO implements Cloneable{
	private String accountNo;//账号后四位
	private String accountType;//账号类型
	private String month01;//第一个月份
	private String income01;//入账金额
	private String month02;//第二个月份
	private String income02;//入账金额
	private String month03;//第三个月份
	private String income03;//入账金额
	private String month04;//第四个月份
	private String income04;//入账金额
	private String month05;//第五个月份
	private String income05;//入账金额
	private String month06;//第六个月份
	private String income06;//入账金额
	private String avemonIncome;//月均
	private String analysis;//流水分析
	

	public String getAccountNo() {
		return accountNo;
	}




	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}




	public String getAccountType() {
		return accountType;
	}




	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}




	public String getMonth01() {
		return month01;
	}




	public void setMonth01(String month01) {
		this.month01 = month01;
	}




	public String getIncome01() {
		return income01;
	}




	public void setIncome01(String income01) {
		this.income01 = income01;
	}




	public String getMonth02() {
		return month02;
	}




	public void setMonth02(String month02) {
		this.month02 = month02;
	}




	public String getIncome02() {
		return income02;
	}




	public void setIncome02(String income02) {
		this.income02 = income02;
	}




	public String getMonth03() {
		return month03;
	}




	public void setMonth03(String month03) {
		this.month03 = month03;
	}




	public String getIncome03() {
		return income03;
	}




	public void setIncome03(String income03) {
		this.income03 = income03;
	}




	public String getMonth04() {
		return month04;
	}




	public void setMonth04(String month04) {
		this.month04 = month04;
	}




	public String getIncome04() {
		return income04;
	}




	public void setIncome04(String income04) {
		this.income04 = income04;
	}




	public String getMonth05() {
		return month05;
	}




	public void setMonth05(String month05) {
		this.month05 = month05;
	}




	public String getIncome05() {
		return income05;
	}




	public void setIncome05(String income05) {
		this.income05 = income05;
	}




	public String getMonth06() {
		return month06;
	}




	public void setMonth06(String month06) {
		this.month06 = month06;
	}




	public String getIncome06() {
		return income06;
	}




	public void setIncome06(String income06) {
		this.income06 = income06;
	}




	public String getAvemonIncome() {
		return avemonIncome;
	}




	public void setAvemonIncome(String avemonIncome) {
		this.avemonIncome = avemonIncome;
	}




	public String getAnalysis() {
		return analysis;
	}




	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}




	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
