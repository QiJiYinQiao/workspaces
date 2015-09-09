package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.vo.LoanerJointModel;
import com.bpms.service.LoanerJointService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 共同贷款人的Action
 * 
 * @author liuhh
 *
 */
@Namespace("/loanerJoint")
@Action(value = "loanerJointAction")
public class LoanerJointAction extends BaseAction implements
		ModelDriven<LoanerJointModel> {

	private static final long serialVersionUID = 3658084064057123814L;

	private LoanerJointModel loanerJoint = new LoanerJointModel();

	@Autowired
	private LoanerJointService loanerJointService;

	public void persistenceLoanerJoint() {
		loanerJointService.persistenceLoanerJoint(loanerJoint);
		OutputJson(new  DataModel("提示", "保存成功！！", true, loanerJoint.getLjId()));
	}

	public void findLoanerJointByOrderId() {
		OutputJson(loanerJointService.findLoanerJointByOrderId(loanerJoint
				.getLoanOrderId()));
	}

	@Override
	public LoanerJointModel getModel() {
		return loanerJoint;
	}

}
