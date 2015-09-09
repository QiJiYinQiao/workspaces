package com.bpms.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * @ClassName: ObligationMatch 
 * @Description: TODO 债权匹配实体类
 * @author PANCHUANHE
 * @date 2015年8月20日 下午3:30:46
 */
@Entity
@Table(name = "t_bp_obligation_match")
public class ObligationMatch implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
    private String omId;
    /**
	 * 贷款合同编号
	 */
    private String lcontractNo;
    /**
	 * 投资合同编号
	 */
    private String icontractNo;
    /**
	 * 匹配额度
	 */
    private BigDecimal matchEdu;
    /**
	 * 可用额度
	 */
    private BigDecimal usableEdu;
    /**
	 * 账户总额
	 */
    private BigDecimal investEdu;
    /**
	 * 到期日期
	 */
    private Date dueDate;
    
    @Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "OM_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getOmId() {
		return omId;
	}
	
	public void setOmId(String omId) {
		this.omId = omId;
	}
	
	@Column(name = "LCONTRACT_NO", length = 50)
	public String getLcontractNo() {
		return lcontractNo;
	}
	
	public void setLcontractNo(String lcontractNo) {
		this.lcontractNo = lcontractNo;
	}
	
	@Column(name = "ICONTRACT_NO", length = 50)
	public String getIcontractNo() {
		return icontractNo;
	}
	
	public void setIcontractNo(String icontractNo) {
		this.icontractNo = icontractNo;
	}
	
	@Column(name = "MATCH_EDU", precision = 15, scale = 5)
	public BigDecimal getMatchEdu() {
		return matchEdu;
	}
	public void setMatchEdu(BigDecimal matchEdu) {
		this.matchEdu = matchEdu;
	}
	
	@Column(name = "USABLE_EDU", precision = 15, scale = 5)
	public BigDecimal getUsableEdu() {
		return usableEdu;
	}
	
	public void setUsableEdu(BigDecimal usableEdu) {
		this.usableEdu = usableEdu;
	}
	
	@Column(name = "INVEST_EDU", precision = 15, scale = 5)
	public BigDecimal getInvestEdu() {
		return investEdu;
	}
	
	public void setInvestEdu(BigDecimal investEdu) {
		this.investEdu = investEdu;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE")
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
