package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.OutsurveyAnswer;
import com.bpms.service.OutsurveyAnswerService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 外访问题回答Action
 * 
 * @author liuhh 2015/06/23
 */
@SuppressWarnings("serial")
@Namespace("/outsurveyAnswer")
@Action(value = "outsurveyAnswerAction")
public class OutsurveyAnswerAction extends BaseAction implements
		ModelDriven<OutsurveyAnswer> {

	// 驱动模型
	private OutsurveyAnswer outsurveyAnswer = new OutsurveyAnswer();

	// 注入service
	@Autowired
	private OutsurveyAnswerService service;

	@Override
	public OutsurveyAnswer getModel() {
		return outsurveyAnswer;
	}

	/**
	 * 保存或修改外访问题回答
	 */
	public String saveOuboundVerify() {
		boolean result = service.saveOutsurveyAnswer(outsurveyAnswer);
		OutputJson(getMessage(result));
		return null;

	}

	/**
	 * 根据ID获取对应的问题回答
	 */
	public String findById() {
		OutputJson(service.findById(outsurveyAnswer.getAnswer()));
		return null;
	}
}
