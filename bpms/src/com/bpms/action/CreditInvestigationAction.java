package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditInvestigation;
import com.bpms.service.CreditInvestigationService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 征信信息详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/creditInvestigation")
@Action(value = "creditInvestigationAction")
public class CreditInvestigationAction extends BaseAction implements
		ModelDriven<CreditInvestigation> {

	private static final long serialVersionUID = 1L;

	private CreditInvestigation creditInvestigation = new CreditInvestigation();

	@Autowired
	private CreditInvestigationService creditInvestigationService;

	public void saveCreditInvestigation() {
		// 修改/保存征信信息详情的信息
		boolean result = creditInvestigationService
				.persistenceCreditInvestigation(creditInvestigation);
		// 组织提示消息
		String message = result ? "征信信息保存成功!" : "征信信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("征信信息详情", message, result, creditInvestigation));
	}

	@Override
	public CreditInvestigation getModel() {
		return creditInvestigation;
	}

}
