package com.bpms.service;

import com.bpms.model.OutboundVerify;

/**
 * 
 * @author liuhh 外访问题核实对应的service接口
 *
 */
public interface OutboundVerifyService {
	/**
	 * 保存或修改外访核实
	 * 
	 * @param outsurveyAnswer
	 *            外访问题核实
	 * @return 保存/修改是否成功
	 */
	public boolean saveOutboundVerify(OutboundVerify outboundVerify);

	/**
	 * 根据ID查询外访问题核实
	 * 
	 * @param id
	 *            外访核实问题的ID
	 * @return 外访核实问题
	 */
	public OutboundVerify findById(String id);
}
