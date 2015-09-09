package com.bpms.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.AuditInfoRecord;
import com.bpms.model.Company;
import com.bpms.model.LoanOrder;
import com.bpms.model.LoanorderAndCompany;
import com.bpms.service.AttachmentService;
import com.bpms.service.AuditInfoRecordService;
import com.bpms.service.CompanyService;
import com.bpms.service.LoanorderAndCompanyService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 稽核信息记录 控制器
 */
@Namespace("/auditInfoRecord")
@Action(value = "auditInfoRecordAction")
public class AuditInfoRecordAction extends BaseAction implements ModelDriven<AuditInfoRecord>{

	private static final long serialVersionUID = 1L;

	/** 自动注入service. */
	@Autowired
	private AuditInfoRecordService auditInfoRecordService;
	
	@Autowired
	private LoanorderAndCompanyService loanorderAndCompanyService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	/** modelDriven. */
	private AuditInfoRecord air = new AuditInfoRecord();
	private String loanOrderId;
	private String loanerId;
	private String attidList;
	private String comId;
	private String regId;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getAttidList() {
		return attidList;
	}

	public void setAttidList(String attidList) {
		this.attidList = attidList;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Override
	public AuditInfoRecord getModel() {
		return air;
	}
	
	public String findAIRByOid(){
		OutputJson(auditInfoRecordService.findAIRByOid(loanOrderId));
		return null;
	}
	
	public String saveAuditInfoRecord(){
		boolean b ;
		String msg = "保存失败";
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		//如果是公司类型，判断是否新增公司
		if("audit_company".equals(air.getAuditItem()) && StringUtils.isBlank(air.getAuditId())){
			Company company = new Company();
			Company comexist = companyService.findComByON(loanOrderId, air.getName());
			if(null==comexist){
				//如果不存在，保存公司信息
				company.setName(air.getName());
				company.setLoanerId(loanerId);
				company.setRegId(regId);
				b = companyService.saveCompany(company);
				//保存公司订单关系
				LoanorderAndCompany lac = new LoanorderAndCompany();
				lac.setComId(company.getComId());
				lac.setLoanOrderId(loanOrderId);
				b = loanorderAndCompanyService.saveLoanorderAndCompany(lac);
			}
			air.setLoanOrder(new LoanOrder(loanOrderId));
			b = auditInfoRecordService.saveAuditInfoRecord(air);
			map.put("company", company);
		}
		else{
			air.setLoanOrder(new LoanOrder(loanOrderId));
			b = auditInfoRecordService.saveAuditInfoRecord(air);
		}
		if(b){
			msg = "保存成功!";
		}
		map.put("air", air);
		OutputJson(new DataModel("提示",msg,b,map),"text/html");
		return null;
	}
	
	public void findCompanyAuditByName(){
		AuditInfoRecord auditInfo = auditInfoRecordService.findAuditByName(air.getName(), loanOrderId,air.getAuditItem());
		OutputJson(auditInfo);
	}
	
	public void delCompanyAuditAndAtt(){
		String attids[] = attidList.split(",");
		for(int i = 0 ; i < attids.length ; i++){
			if(StringUtils.isBlank(attids[i])){
				
			}else{
				attachmentService.delAttachAuditRL(attids[i]);
				attachmentService.delAttachment(attids[i]);
			}
		}
		auditInfoRecordService.deleteAudit(air);
		loanorderAndCompanyService.deleteByComIdAndLoanOrderId(comId, loanOrderId);
	}
	
}
