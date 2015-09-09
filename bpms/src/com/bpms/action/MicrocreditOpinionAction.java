package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.MicrocreditOpinion;
import com.bpms.model.Users;
import com.bpms.model.vo.MicrocreditOpinionModel;
import com.bpms.service.CommonService;
import com.bpms.service.MicrocreditOpinionService;
import com.bpms.service.UserService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 微贷业务呈报意见表控制器
 * 
 * @author Sun
 *
 */
@Namespace("/microcreditOpinion")
@Action(value = "microcreditOpinionAction")
public class MicrocreditOpinionAction extends BaseAction implements
		ModelDriven<MicrocreditOpinionModel> {

	private static final long serialVersionUID = 8870688358417428885L;

	private MicrocreditOpinionModel microcreditOpinionModel = new MicrocreditOpinionModel();

	@Autowired
	private MicrocreditOpinionService microcreditOpinionService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;

	@Override
	public MicrocreditOpinionModel getModel() {
		return microcreditOpinionModel;
	}

	private String loanOrderId;

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public void saveMicrocreditOpinion() {
		MicrocreditOpinion microcreditOpinion = new MicrocreditOpinion();
		BeanUtils.copyProperties(microcreditOpinionModel, microcreditOpinion);
		String msg = "保存失败";
		boolean b = microcreditOpinionService
				.saveMicrocreditOpinion(microcreditOpinion);
		if (b) {
			msg = "保存成功";
		}
		OutputJson(
				new DataModel("", msg, true, microcreditOpinion.getMcbrId()),
				"text/html");
	}

	public void findMicrocreditOpinionByOid() {
		MicrocreditOpinion microcreditOpinion = microcreditOpinionService
				.findMicrocreditOpinionByOid(microcreditOpinionModel
						.getLoanOrderId());
		if (null != microcreditOpinion) {
			Integer operatorA = Integer.parseInt(microcreditOpinion
					.getOperatorA());
			Users usera = userService.findUserById(operatorA);

			Integer operatorB = Integer.parseInt(microcreditOpinion
					.getOperatorB());
			Users userb = userService.findUserById(operatorB);

			// 查询期限
			String dictCode = microcreditOpinion.getAdviceLoanPeriod();
			String loanPeriod = commonService.findDictName("loan_period_type",
					dictCode);

			microcreditOpinion.setOperatorA(usera.getName());
			microcreditOpinion.setOperatorB(userb.getName());
			microcreditOpinion.setAdviceLoanPeriod(loanPeriod);
		}

		OutputJson(microcreditOpinion);
	}

	public void saveFinalMicrocreditOpinion() {
		MicrocreditOpinion microcreditOpinion = microcreditOpinionService
				.findMicrocreditOpinionByOid(microcreditOpinionModel
						.getLoanOrderId());
		if(null!=microcreditOpinion){
			microcreditOpinion.setFinalLoanAmt(microcreditOpinionModel
					.getFinalLoanAmt());
			microcreditOpinion.setFinalLoanPeriod(microcreditOpinionModel
					.getFinalLoanPeriod());
			microcreditOpinion.setFinalRepayMthd(microcreditOpinionModel
					.getFinalRepayMthd());
		}
		this.microcreditOpinionService
				.saveMicrocreditOpinion(microcreditOpinion);
		OutputJson(getMessage(true));
	}

}
