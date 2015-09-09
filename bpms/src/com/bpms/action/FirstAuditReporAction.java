package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditAuditReport;
import com.bpms.model.FirstAuditReport;
import com.bpms.service.CreditAuditReportService;
import com.bpms.service.FirstAuditReportService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 初审资质分析详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/firstAuditReport")
@Action(value = "firstAuditReportAction")
public class FirstAuditReporAction extends BaseAction implements
		ModelDriven<FirstAuditReport> {

	private static final long serialVersionUID = 1L;

	private FirstAuditReport firstAuditReport = new FirstAuditReport();

	// 信审报告的ID
	private String carId;

	@Autowired
	private FirstAuditReportService firstAuditReportService;

	@Autowired
	private CreditAuditReportService auditReportService;

	public void saveFirstAuditReport() {
		// 修改/保存初审资质分析详情的信息
		boolean result = firstAuditReportService
				.persistenceFirstAuditReport(firstAuditReport);
		// 设置关联关系
		CreditAuditReport auditReport = auditReportService
				.findCreditAuditReoprtById(carId);
		auditReport.setFirstAuditReport(firstAuditReport);
		auditReportService.persistenceCreditAuditReport(auditReport);
		// 组织提示消息
		String message = result ? "初审资质分析信息保存成功!" : "初审资质分析信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("初审资质分析详情", message, result, firstAuditReport));
	}

	@Override
	public FirstAuditReport getModel() {
		return firstAuditReport;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
