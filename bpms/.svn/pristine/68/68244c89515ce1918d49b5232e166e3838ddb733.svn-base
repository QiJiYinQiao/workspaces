package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.OutboundVerify;
import com.bpms.service.OutboundVerifyService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 外访问题核实Action
 * 
 * @author liuhh 2015/06/23
 */
@SuppressWarnings("serial")
@Namespace("/outboundVerify")
@Action(value = "outboundVerifyAction")
public class OutboundVerifyAction extends BaseAction implements
		ModelDriven<OutboundVerify> {

	// 驱动模型
	private OutboundVerify outboundVerify = new OutboundVerify();

	// 注入service
	@Autowired
	private OutboundVerifyService service;

	@Override
	public OutboundVerify getModel() {
		return outboundVerify;
	}

	/**
	 * 保存或修改外访问题核实
	 */
	public String saveOuboundVerify() {
		boolean result = service.saveOutboundVerify(outboundVerify);
		OutputJson(getMessage(result));
		return null;

	}

	/**
	 * 根据ID获取对应的问题核实信息
	 */
	public String findById() {
		OutputJson(service.findById(outboundVerify.getVerifyId()));
		return null;
	}
}
