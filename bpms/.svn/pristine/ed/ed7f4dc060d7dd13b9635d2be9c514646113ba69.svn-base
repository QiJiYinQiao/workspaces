package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditAuditReport;
import com.bpms.model.FinalAuditReport;
import com.bpms.model.vo.FinalAuditReportModel;
import com.bpms.service.CreditAuditReportService;
import com.bpms.service.FinalAuditReportService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 终审资质分析详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/finalAuditReportAction")
@Action(value = "finalAuditReportAction")
public class FinalAuditReportAction extends BaseAction implements
		ModelDriven<FinalAuditReportModel> {

	private static final long serialVersionUID = 1L;

	private FinalAuditReportModel finalAuditReportModel = new FinalAuditReportModel();

	@Autowired
	private FinalAuditReportService finalAuditReportService;

	@Autowired
	private CreditAuditReportService auditReportService;

	public void saveFinalAuditReport() {
		try {
			FinalAuditReport finalAuditReport = new FinalAuditReport();
			BeanUtils.copyProperties(finalAuditReportModel, finalAuditReport);
			// 修改/保存终审资质分析详情的信息
			boolean result = finalAuditReportService
					.persistenceFinalAuditReport(finalAuditReport);
			// 设置关联关系
			CreditAuditReport auditReport = auditReportService
					.findCreditAuditReportByLoanOrderId(finalAuditReportModel
							.getLoanOrderId());
			auditReport.setFinalAuditReport(finalAuditReport);
			auditReportService.persistenceCreditAuditReport(auditReport);
			// 组织提示消息
			String message = result ? "终审资质分析信息保存成功!" : "终审资质分析信息保存失败!";
			// 返回前台
			OutputJson(new DataModel("终审资质分析详情", message, result,
					finalAuditReportModel));
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

	public void findFinalAuditReportByLoanOrderId() {
		OutputJson(new DataModel(
				"提示",
				"获取终审资质分析信息成功.",
				true,
				finalAuditReportService
						.findFinalAuditReportByLoanOrderId(finalAuditReportModel
								.getLoanOrderId())));
	}

	@Override
	public FinalAuditReportModel getModel() {
		return finalAuditReportModel;
	}

}
