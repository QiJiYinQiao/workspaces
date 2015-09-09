package com.bpms.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 理财产品数据模型
 * 
 * @author 张健 2015/07/14
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/07/14 张健 创建.
 */
@Entity
@Table(name = "t_bp_invest_products")
public class InvestProduct implements java.io.Serializable {

	private static final long serialVersionUID = 3479325522242842129L;
	
	private String prodId;          //理财产品ID
	private String prodName;      	//产品名称
	private Integer lendingCycle;	//出借周期
	private BigDecimal ars;         //年化收益率
	private BigDecimal ytm;         //到期收益率
	private BigDecimal lowLendEdu;  //最低出借金额
	private BigDecimal higLendEdu;  //最高出借金额
	private String repaymentMode;  //还款方式
	private String prodDesc;         //产品描述
//	private Character prodStatus;    //产品状态
	private String prodStatus;    //产品状态
	private BigDecimal msf;        //年化折标系数，是固定的数值，具体如下所示
									//45T->0.2
									//90T->0.25 1/(360/90)
									//180T->0.5 1/(360/180)
									//365T->1 1(/360/360)
	
//	private Set TBpInvestorderAndProductses = new HashSet(0);

	@Column(name = "PROD_STATUS", length = 1)
	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public InvestProduct() {
	}

	public InvestProduct(String prodId) {
		this.prodId = prodId;
	}

	public InvestProduct(String prodId, Integer lendingCycle,
			BigDecimal ars, BigDecimal ytm, BigDecimal lowLendEdu,
			BigDecimal higLendEdu, String repaymentMode, String prodDesc,
			String prodStatus, BigDecimal msf) {
		this.prodId = prodId;
		this.lendingCycle = lendingCycle;
		this.ars = ars;
		this.ytm = ytm;
		this.lowLendEdu = lowLendEdu;
		this.higLendEdu = higLendEdu;
		this.repaymentMode = repaymentMode;
		this.prodDesc = prodDesc;
		this.prodStatus = prodStatus;
		this.msf = msf;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "PROD_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	@Column(name = "PROD_NAME")
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Column(name = "LENDING_CYCLE")
	public Integer getLendingCycle() {
		return this.lendingCycle;
	}

	public void setLendingCycle(Integer lendingCycle) {
		this.lendingCycle = lendingCycle;
	}

	@Column(name = "ARS", precision = 5)
	public BigDecimal getArs() {
		return this.ars;
	}

	public void setArs(BigDecimal ars) {
		this.ars = ars;
	}

	@Column(name = "YTM", precision = 5)
	public BigDecimal getYtm() {
		return this.ytm;
	}

	public void setYtm(BigDecimal ytm) {
		this.ytm = ytm;
	}

	@Column(name = "LOW_LEND_EDU", precision = 15, scale = 5)
	public BigDecimal getLowLendEdu() {
		return this.lowLendEdu;
	}

	public void setLowLendEdu(BigDecimal lowLendEdu) {
		this.lowLendEdu = lowLendEdu;
	}

	@Column(name = "HIG_LEND_EDU", precision = 15, scale = 5)
	public BigDecimal getHigLendEdu() {
		return this.higLendEdu;
	}

	public void setHigLendEdu(BigDecimal higLendEdu) {
		this.higLendEdu = higLendEdu;
	}

	@Column(name = "REPAYMENT_MODE", length = 1)
	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	@Column(name = "PROD_DESC", length = 100)
	public String getProdDesc() {
		return this.prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

//	@Column(name = "PROD_STATUS", length = 1)
//	public Character getProdStatus() {
//		return this.prodStatus;
//	}
//
//	public void setProdStatus(Character prodStatus) {
//		this.prodStatus = prodStatus;
//	}

	public BigDecimal getMsf() {
		return msf;
	}

	public void setMsf(BigDecimal msf) {
		this.msf = msf;
	}

	@Override
	public String toString() {
		return "InvestProduct [prodId=" + prodId + ", prodName=" + prodName
				+ ", lendingCycle=" + lendingCycle + ", ars=" + ars + ", ytm="
				+ ytm + ", lowLendEdu=" + lowLendEdu + ", higLendEdu="
				+ higLendEdu + ", repaymentMode=" + repaymentMode
				+ ", prodDesc=" + prodDesc + ", prodStatus=" + prodStatus
				+ ", msf=" + msf + "]";
	}	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TBpInvestProducts")
//	public Set getTBpInvestorderAndProductses() {
//		return this.TBpInvestorderAndProductses;
//	}
//
//	public void setTBpInvestorderAndProductses(Set TBpInvestorderAndProductses) {
//		this.TBpInvestorderAndProductses = TBpInvestorderAndProductses;
//	}

	
}
