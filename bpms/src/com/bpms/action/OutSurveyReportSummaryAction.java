package com.bpms.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.OutsurveyAnswer;
import com.bpms.model.OutsurveyReportSummary;
import com.bpms.model.vo.OutSurveyQuestionAndAnswer;
import com.bpms.service.FirstauditQuestioncollectService;
import com.bpms.service.OutSurveyReportSummaryService;
import com.bpms.service.OutsurveyAnswerService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/outSurveyReportSummary")
@Action(value = "outSurveyReportSummaryAction")
public class OutSurveyReportSummaryAction extends BaseAction implements ModelDriven<OutsurveyReportSummary> {
	
	private static final long serialVersionUID = 1L;
	private OutsurveyReportSummary iosrs = new OutsurveyReportSummary();
	
	@Autowired
	private OutSurveyReportSummaryService ipcOutSurveyReportSummaryService;
	@Autowired
	private OutsurveyAnswerService outsurveyAnswerService;
	@Autowired
	private FirstauditQuestioncollectService service;
	
	@Override
	public OutsurveyReportSummary getModel() {
		return iosrs;
	}
	
	private String answerDesc;//回答
	private String quesIDs;//问题ID
	private String answersIdList;//回答ID
	
	public void saveIpcOutSurveyReportSummary(){
		Boolean b = ipcOutSurveyReportSummaryService.persistenceOutSurveySummaryReport(iosrs);
		String msg = "保存失败";
		if(b && StringUtils.isNotBlank(quesIDs)){
			//保存初审问题答案
			String[] as = answerDesc.split("@@@");
			String[] qs = quesIDs.split("@@@");
			String[] ai = null;
			if(null!=answersIdList && StringUtils.isNotEmpty(answersIdList)){
				 ai = answersIdList.split("@@@");
			}
			for(int i = 0 ; i < as.length; i++){
				OutsurveyAnswer osa = new OutsurveyAnswer();
				if(null!=ai){
					osa.setAnswerId(ai[i]);
				}
				osa.setAnswer(as[i]);
				osa.setQuestionId(qs[i]);
				osa.setOutsurveyReportSummaryId(iosrs.getOutsurveyReportSummaryId());
				outsurveyAnswerService.saveOutsurveyAnswer(osa);
			}
			msg = "保存成功";
		} else if(b) {
			msg = "保存成功";
		}
		List<OutSurveyQuestionAndAnswer> list = service.findQAByOrderId(iosrs.getLoanOrderId());
		for(OutSurveyQuestionAndAnswer osqa : list){
			osqa.setOutsurveyReportSummaryId(iosrs.getOutsurveyReportSummaryId());
		}
		OutputJson(new DataModel("提示",msg,b,list));
	}
	
	public void findIpcOutSurveyReportSummary(){
		OutsurveyReportSummary outSummary = ipcOutSurveyReportSummaryService.findOutSurveyReportSummaryById(iosrs.getLoanOrderId());
		OutputJson(outSummary);
	}

	public String getQuesIDs() {
		return quesIDs;
	}

	public void setQuesIDs(String quesIDs) {
		this.quesIDs = quesIDs;
	}

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	public String getAnswersIdList() {
		return answersIdList;
	}

	public void setAnswersIdList(String answersIdList) {
		this.answersIdList = answersIdList;
	}

}
