package com.bpms.service;

import java.util.List;

import com.bpms.model.FirstauditQuestioncollect;
import com.bpms.model.vo.OutSurveyQuestionAndAnswer;

/**
 * 
 * @author liuhh 外访问题对应的service接口
 *
 */
public interface FirstauditQuestioncollectService {
	/**
	 * 保存或修改对应的外访核实问题
	 * 
	 * @param questioncollect
	 *            外访核实问题
	 * @return 保存是否成功
	 */
	public boolean saveFirstauditQuestioncollect(
			FirstauditQuestioncollect questioncollect);

	/**
	 * 根据ID查询外访核实问题
	 * 
	 * @param id
	 *            外访核实问题的ID
	 * @return 外访核实问题
	 */
	public FirstauditQuestioncollect findById(String id);
	
	/**
	 * 根据订单ID查询外访核实问题
	 * 
	 * @param id
	 *           订单的ID
	 * @return 外访核实问题
	 */
	public List<FirstauditQuestioncollect> findByOrderId(String id);
	
	/**
	 * 根据订单ID查询问题及答案
	 * 
	 */
	public List<OutSurveyQuestionAndAnswer> findQAByOrderId(String loanOrderId);
}
