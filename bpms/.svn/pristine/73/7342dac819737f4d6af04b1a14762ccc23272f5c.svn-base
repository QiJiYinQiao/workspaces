package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditAuditReport;
import com.bpms.model.LoansDetails;
import com.bpms.service.CreditAuditReportService;
import com.bpms.service.LoansDetailsService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 贷款详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/loansDetails")
@Action(value = "loansDetailsAction")
public class LoansDetailsAction extends BaseAction implements
		ModelDriven<LoansDetails> {

	private static final long serialVersionUID = 1L;

	private LoansDetails loansDetails = new LoansDetails();

	// 信审报告的ID
	private String carId;

	@Autowired
	private LoansDetailsService loansDetailsService;

	@Autowired
	private CreditAuditReportService auditReportService;

	public void saveLoanDetails() {
		// 修改/保存贷款详情的信息
		boolean result = loansDetailsService
				.persistenceLoansDetails(loansDetails);
		// 设置关联关系
		CreditAuditReport auditReport = auditReportService
				.findCreditAuditReoprtById(carId);
		auditReport.setLoansDetails(loansDetails);
		auditReportService.persistenceCreditAuditReport(auditReport);
		// 组织提示消息
		String message = result ? "贷款信息保存成功!" : "贷款信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("贷款详情", message, result, loansDetails));
	}

	@Override
	public LoansDetails getModel() {
		return loansDetails;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
