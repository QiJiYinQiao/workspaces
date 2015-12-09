package com.oasys.viewModel;


public class PurchaseAppAttachModel implements java.io.Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer psaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 物品
	 */
	private String articleName;
	/**
	 * 型号规格
	 */
	private String model;
	/**
	 * 单价
	 */
	private String price;
	/**
	 * 数量
	 */
	private Integer qty;
	/**
	 * 合计价格
	 */
	private String totalAmt;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 使用人
	 */
	private Integer user;
	/**
	 * 保管人
	 */
	private Integer depositary;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 使用人姓名
	 */
	private String userName;
	/**
	 * 保管人姓名
	 */
	private String depositaryName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 其他单位
	 */
	private String  unitOther;
	/**
	 * 物品名称
	 */
	private String  articleNameText;

	public PurchaseAppAttachModel() {
	}

	public PurchaseAppAttachModel(Integer psaId) {
		this.psaId = psaId;
	}

	public PurchaseAppAttachModel(Integer psaId, String appNo, String articleName,
			String model, String price, Integer qty, String totalAmt,
			String purpose, Integer user, Integer depositary, String remark) {
		this.psaId = psaId;
		this.appNo = appNo;
		this.articleName = articleName;
		this.model = model;
		this.price = price;
		this.qty = qty;
		this.totalAmt = totalAmt;
		this.purpose = purpose;
		this.user = user;
		this.depositary = depositary;
		this.remark = remark;
	}

	public Integer getPsaId() {
		return psaId;
	}

	public void setPsaId(Integer psaId) {
		this.psaId = psaId;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getDepositary() {
		return depositary;
	}

	public void setDepositary(Integer depositary) {
		this.depositary = depositary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepositaryName() {
		return depositaryName;
	}

	public void setDepositaryName(String depositaryName) {
		this.depositaryName = depositaryName;
	}
	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitOther() {
		return unitOther;
	}

	public void setUnitOther(String unitOther) {
		this.unitOther = unitOther;
	}
    
	public String getArticleNameText() {
		return articleNameText;
	}

	public void setArticleNameText(String articleNameText) {
		this.articleNameText = articleNameText;
	}

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
