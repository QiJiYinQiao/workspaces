package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.OutsurveyReport;
import com.bpms.service.OutSurveyReportService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/outSurveyReport")
@Action(value = "outSurveyReportAction")
public class OutSurveyReportAction extends BaseAction implements ModelDriven<OutsurveyReport> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OutSurveyReportService ipcOutSurveyReportService;
	
	private OutsurveyReport outSurveyReport = new OutsurveyReport();
	
	@Override
	public OutsurveyReport getModel() {
		return outSurveyReport;
	}

	public void saveIpcOutSurveyReport(){
		Boolean b = ipcOutSurveyReportService.persistenceOutSurveyReport(outSurveyReport);
		String msg = "保存失败";
		if(b){
			msg = "保存成功";
		}
		OutputJson(new DataModel("提示", msg, b, outSurveyReport));
	}
	
	public void findIpcOutSurveyReport(){
		OutsurveyReport iosr = ipcOutSurveyReportService.findOutSurveyReportById(outSurveyReport.getLoanOrderId());
		OutputJson(iosr);
	}
	
}
