package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.OutsurveyReport;
import com.bpms.service.OutSurveyReportService;

@Service("ipcOutSurveyReportService")
public class OutSurveyReportServiceImpl implements OutSurveyReportService {

	@Autowired
	private BaseDAO<OutsurveyReport> baseDAO;
	
	@Override
	public boolean persistenceOutSurveyReport(
			OutsurveyReport outSurveyReport) {
		if (StringUtils.isBlank(outSurveyReport.getOutsurveyReportId())) {
			baseDAO.save(outSurveyReport);
		} else {
			baseDAO.update(outSurveyReport);
		}
		return true;
	}

	@Override
	public OutsurveyReport findOutSurveyReportById(String loanOrderid) {
		String hql = " FROM OutsurveyReport isr WHERE isr.loanOrderId = '"+loanOrderid+"'";
		List<OutsurveyReport> list = baseDAO.find(hql);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
