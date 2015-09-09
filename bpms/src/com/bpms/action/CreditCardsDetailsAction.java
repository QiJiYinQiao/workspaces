package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditAuditReport;
import com.bpms.model.CreditCardsDetails;
import com.bpms.service.CreditAuditReportService;
import com.bpms.service.CreditCardsDetailsService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 信用卡使用详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/creditCardsDetails")
@Action(value = "creditCardsDetailsAction")
public class CreditCardsDetailsAction extends BaseAction implements
		ModelDriven<CreditCardsDetails> {

	private static final long serialVersionUID = 1L;

	private CreditCardsDetails creditCardsDetails = new CreditCardsDetails();

	// 信审报告的ID
	private String carId;

	@Autowired
	private CreditCardsDetailsService creditCardsDetailsService;

	@Autowired
	private CreditAuditReportService auditReportService;

	public void saveCreditCardsDetails() {
		// 修改/保存信用卡详情的信息
		boolean result = creditCardsDetailsService
				.persistenceCreditCardsDetails(creditCardsDetails);
		// 设置关联关系
		CreditAuditReport auditReport = auditReportService
				.findCreditAuditReoprtById(carId);
		auditReport.setCreditCardsDetails(creditCardsDetails);
		auditReportService.persistenceCreditAuditReport(auditReport);
		// 组织提示消息
		String message = result ? "信用卡信息保存成功!" : "信用卡信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("信用卡详情", message, result, creditCardsDetails));
	}

	@Override
	public CreditCardsDetails getModel() {
		return creditCardsDetails;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
