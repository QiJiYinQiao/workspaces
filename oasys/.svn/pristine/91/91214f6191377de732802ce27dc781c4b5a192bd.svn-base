package com.oasys.model;

// Generated 2015-9-25 9:55:05 by Hibernate Tools 4.0.0

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

/**
 * PpeScrapAppAttach entity. 
 * @author lida
 * @Package com.oasys.model
 * @Description: 固定资产报废附加表实体对象
 * @date 2015/9/21
 * @version 1.0
 */
@Entity
@Table(name = "t_oa_ad_ppe_scrap_app_attach")
public class PpeScrapAppAttach implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2639265452383738227L;
	
	private Integer psaId;
	private String appNo;
	private Integer ppeNo;
	private String ppeName;
	private String ppeModel;
	private Date buyDate;
	private String buyDateStr;
	private BigDecimal usedYear;
	private BigDecimal ppeGross;
	private BigDecimal ppeNet;
	private BigDecimal depreciation;
	private BigDecimal ppeSalvageVal;
	private String scrapReson;
	private String remark;
	private PPEScrapApp ppeApp;

	
	public PpeScrapAppAttach() {
	}


	public PpeScrapAppAttach(Integer psaId, String appNo, Integer ppeNo,
			String ppeName, String ppeModel, Date buyDate, String buyDateStr,
			BigDecimal usedYear, BigDecimal ppeGross, BigDecimal ppeNet,
			BigDecimal depreciation, BigDecimal ppeSalvageVal,
			String scrapReson, String remark, PPEScrapApp ppeApp) {
		super();
		this.psaId = psaId;
		this.appNo = appNo;
		this.ppeNo = ppeNo;
		this.ppeName = ppeName;
		this.ppeModel = ppeModel;
		this.buyDate = buyDate;
		this.buyDateStr = buyDateStr;
		this.usedYear = usedYear;
		this.ppeGross = ppeGross;
		this.ppeNet = ppeNet;
		this.depreciation = depreciation;
		this.ppeSalvageVal = ppeSalvageVal;
		this.scrapReson = scrapReson;
		this.remark = remark;
		this.ppeApp = ppeApp;
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PSA_ID", unique = true, nullable = false)
	public Integer getPsaId() {
		return this.psaId;
	}

	public void setPsaId(Integer psaId) {
		this.psaId = psaId;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "PPE_NO")
	public Integer getPpeNo() {
		return this.ppeNo;
	}

	public void setPpeNo(Integer ppeNo) {
		this.ppeNo = ppeNo;
	}

	@Column(name = "PPE_NAME", length = 50)
	public String getPpeName() {
		return this.ppeName;
	}

	public void setPpeName(String ppeName) {
		this.ppeName = ppeName;
	}

	@Column(name = "PPE_MODEL", length = 50)
	public String getPpeModel() {
		return this.ppeModel;
	}

	public void setPpeModel(String ppeModel) {
		this.ppeModel = ppeModel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUY_DATE", length = 10)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "USED_YEAR", precision = 4, scale = 1)
	public BigDecimal getUsedYear() {
		return this.usedYear;
	}

	public void setUsedYear(BigDecimal usedYear) {
		this.usedYear = usedYear;
	}

	@Column(name = "PPE_GROSS", precision = 10)
	public BigDecimal getPpeGross() {
		return this.ppeGross;
	}

	public void setPpeGross(BigDecimal ppeGross) {
		this.ppeGross = ppeGross;
	}

	@Column(name = "PPE_NET", precision = 10)
	public BigDecimal getPpeNet() {
		return this.ppeNet;
	}

	public void setPpeNet(BigDecimal ppeNet) {
		this.ppeNet = ppeNet;
	}

	@Column(name = "DEPRECIATION", precision = 10)
	public BigDecimal getDepreciation() {
		return this.depreciation;
	}

	public void setDepreciation(BigDecimal depreciation) {
		this.depreciation = depreciation;
	}

	@Column(name = "PPE_SALVAGE_VAL", precision = 10)
	public BigDecimal getPpeSalvageVal() {
		return this.ppeSalvageVal;
	}

	public void setPpeSalvageVal(BigDecimal ppeSalvageVal) {
		this.ppeSalvageVal = ppeSalvageVal;
	}

	@Column(name = "SCRAP_RESON", length = 200)
	public String getScrapReson() {
		return this.scrapReson;
	}

	public void setScrapReson(String scrapReson) {
		this.scrapReson = scrapReson;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public String getBuyDateStr() {
		return buyDateStr;
	}

	public void setBuyDateStr(String buyDateStr) {
		this.buyDateStr = buyDateStr;
	}
	
	@Transient
	public PPEScrapApp getPpeApp() {
		return ppeApp;
	}

	public void setPpeApp(PPEScrapApp ppeApp) {
		this.ppeApp = ppeApp;
	}


}
