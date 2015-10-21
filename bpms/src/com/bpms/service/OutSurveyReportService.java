package com.bpms.service;

import com.bpms.model.OutsurveyReport;

public interface OutSurveyReportService {
	/**
	 * 持久化外访调查报告
	 * 
	 * @return
	 */
	public boolean persistenceOutSurveyReport(
			OutsurveyReport OutSurveyReport);
	
	/**
	 * 根据订单的id获取外访调查报告
	 * 
	 * @param loanOrderid
	 *            外访调查报告的id
	 * @return 外访调查报告
	 */
	public OutsurveyReport findOutSurveyReportById(
			String loanOrderid);
	
	
}
