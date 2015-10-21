package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.FirstauditQuestioncollect;
import com.bpms.model.vo.OutSurveyQuestionAndAnswer;
import com.bpms.service.FirstauditQuestioncollectService;

/**
 * 问题服务的实现类
 * 
 * @author liuhh
 *
 */
@Service
public class FirstauditQuestioncollectServiceImpl implements
		FirstauditQuestioncollectService {

	@Autowired
	private BaseDAO<FirstauditQuestioncollect> dao;

	@Override
	public boolean saveFirstauditQuestioncollect(
			FirstauditQuestioncollect questioncollect) {
		if (StringUtils.isNotBlank(questioncollect.getQuestionId())) {
			dao.update(questioncollect);
		} else {
			dao.save(questioncollect);
		}
		return true;
	}

	@Override
	public FirstauditQuestioncollect findById(String id) {
		return dao.get(FirstauditQuestioncollect.class, id);
	}

	@Override
	public List<FirstauditQuestioncollect> findByOrderId(String loanOrderId) {
		String hql = " FROM FirstauditQuestioncollect fqc WHERE fqc.loanOrderId = '"+loanOrderId+"'";
		return dao.find(hql);
	}

	@Override
	public List<OutSurveyQuestionAndAnswer> findQAByOrderId(String loanOrderId) {
		List<OutSurveyQuestionAndAnswer> qaaList = new ArrayList<OutSurveyQuestionAndAnswer>();
		String sql = "SELECT tbfq.QUESTION_ID,tboa.ANSWER_ID,tbfq.LOAN_ORDER_ID,tbfq.QUESTION_DESC,tboa.ANSWER,tbfq.CREATER,tbfq.CREATE_TIME,tboa.OUTSURVEY_REPORT_SUMMARY_ID FROM t_bp_firstaudit_questioncollect tbfq LEFT JOIN t_bp_outsurvey_answer tboa ON tbfq.QUESTION_ID = tboa.QUESTION_ID WHERE tbfq.LOAN_ORDER_ID = '"+loanOrderId+"'";
		List<Object> list = dao.findBySQL(sql);
		for(Object obj : list){
			Object[] osqa = (Object[]) obj;
			OutSurveyQuestionAndAnswer oqaa = new OutSurveyQuestionAndAnswer();
			oqaa.setQuestionId((String) osqa[0]);
			oqaa.setLoanOrderId((String) osqa[2]);
			oqaa.setQuestionDesc((String) osqa[3]);
			oqaa.setCreater((String) osqa[5]);
			oqaa.setCreateTime((Date) osqa[6]);
			if(null!= osqa[1] && !StringUtils.isEmpty((String) osqa[1])){
				oqaa.setAnswerId((String) osqa[1]);
				oqaa.setAnswer((String) osqa[4]);
				oqaa.setOutsurveyReportSummaryId((String) osqa[7]);
			}
			qaaList.add(oqaa);
		}
		return qaaList;
	}

}
