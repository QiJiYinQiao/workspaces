package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Assets;
import com.bpms.model.CreditAuditReport;
import com.bpms.service.AssetsService;
import com.bpms.service.CreditAuditReportService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 资产详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/assets")
@Action(value = "assetsAction")
public class AssetsAction extends BaseAction implements ModelDriven<Assets> {

	private static final long serialVersionUID = 1L;

	private Assets assets = new Assets();

	// 信审报告的ID
	private String carId;

	@Autowired
	private AssetsService assetsService;

	@Autowired
	private CreditAuditReportService auditReportService;

	public void saveAssets() {
		// 修改/保存资产详情的信息
		boolean result = assetsService.persistenceAssets(assets);
		// 设置关联关系
		CreditAuditReport auditReport = auditReportService
				.findCreditAuditReoprtById(carId);
		auditReport.setAssets(assets);
		auditReportService.persistenceCreditAuditReport(auditReport);
		// 组织提示消息
		String message = result ? "资产信息保存成功!" : "资产信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("资产详情", message, result, assets));
	}

	@Override
	public Assets getModel() {
		return assets;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
