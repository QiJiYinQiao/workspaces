package com.bpms.service.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.OutsurveyAnswer;
import com.bpms.service.OutsurveyAnswerService;

/**
 * 问题回答的实现类
 * 
 * @author liuhh
 *
 */
@Service
public class OutsurveyAnswerServiceImpl implements OutsurveyAnswerService {

	@Autowired
	private BaseDAO<OutsurveyAnswer> dao;

	@Override
	public boolean saveOutsurveyAnswer(OutsurveyAnswer outsurveyAnswer) {
		if (StringUtils.isNotBlank(outsurveyAnswer.getAnswerId())) {
			dao.update(outsurveyAnswer);
		} else {
			dao.save(outsurveyAnswer);
		}
		return true;
	}

	@Override
	public OutsurveyAnswer findById(String id) {
		return dao.get(OutsurveyAnswer.class, id);
	}

}
