package com.bpms.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.LoanContract;
import com.bpms.model.vo.LoanContractInfoModel;
import com.bpms.service.LoanContractService;
import com.bpms.util.PageUtil;
import com.bpms.util.WordGeneratorUtil;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 合同 控制器
 */
@Namespace("/loanContract")
@Action(value = "loanContractAction")
public class LoanContractAction extends BaseAction implements
		ModelDriven<LoanContract> {
	private static final long serialVersionUID = 6361458777773218094L;

	@Autowired
	private LoanContractService loanContractService;

	private LoanContract loanContract = new LoanContract();

	@Override
	public LoanContract getModel() {
		return loanContract;
	}

	private String organizationId;
	private String loanName;
	private String loanBgDateS;
	private String loanBgDateE;
	private String loanCity;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanBgDateS() {
		return loanBgDateS;
	}

	public void setLoanBgDateS(String loanBgDateS) {
		this.loanBgDateS = loanBgDateS;
	}

	public String getLoanBgDateE() {
		return loanBgDateE;
	}

	public void setLoanBgDateE(String loanBgDateE) {
		this.loanBgDateE = loanBgDateE;
	}

	/**
	 * 保存合同信息
	 */
	public void saveLoanContract() {
		boolean b = loanContractService.saveLoanConract(loanContract);
		String msg = b ? "保存成功" : "保存失败";
		OutputJson(new DataModel("", msg, b, loanContract), "text/html");
	}

	/**
	 * 根据合同的id查询合同信息
	 */
	public void findLoanContract() {
		OutputJson(loanContractService.findLoanContract(loanContract.getLcId()));
	}

	/**
	 * 导出合同信息
	 */
	public void downloadContract() {
		try {
			LoanContract contract = loanContractService
					.findLoanContractByLoanOrderId(loanContract
							.getLoanOrderId());
			if (contract != null) {
				contract.exportDocDataHanding();
				File file = WordGeneratorUtil.createDoc(contract,
						File.separator + "temp" + File.separator + "loan"
								+ File.separator + "借款协议模板.ftl");
				// 设置头文件
				ServletActionContext.getResponse().setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(contract.getContractNo()
										+ ".doc", "utf-8"));
				// 写入流中
				IOUtils.write(FileUtils.readFileToByteArray(file),
						ServletActionContext.getResponse().getOutputStream());
				// 删除文件
				if (file.exists())
					file.delete();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证合同是否存在
	 */
	public void checkIsContractExist() {
		OutputJson(loanContractService
				.findLoanContractByLoanOrderId(loanContract.getLoanOrderId()));
	}

	/**
	 * 完善和合同信息
	 */
	public void completeContract() {
		boolean b = loanContractService.saveCompleteContract(loanContract);
		String msg = b ? "保存成功" : "保存失败";
		OutputJson(new DataModel("", msg, b), "text/html");
	}

	/**
	 * 查询合同信息列表
	 */
	public void findLoanContractInfo() {
		GridModel gridModel = new GridModel();
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("organizationId", organizationId);
		map.put("loanName", loanName);
		map.put("loanBgDateS", loanBgDateS);
		map.put("loanBgDateE", loanBgDateE);
		map.put("contractNo", loanContract.getContractNo());
		map.put("loanCity", loanCity);
		List<LoanContractInfoModel> contractInfoModes = loanContractService
				.findLoanConractInfoModelList(map, new PageUtil(page, rows));
		gridModel.setRows(contractInfoModes);
		gridModel.setTotal(loanContractService.getCountOfConractInfoModel(map));
		OutputJson2(gridModel);
	}

	/**
	 * 导出合同信息列表
	 */
	public void exprotLoanConractInfoModel() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// map.put("organizationId", organizationId);
			map.put("loanName", loanName);
			map.put("loanBgDateS", loanBgDateS);
			map.put("loanBgDateE", loanBgDateE);
			map.put("contractNo", loanContract.getContractNo());
			map.put("loanCity", loanCity);
			this.loanContractService.exprotLoanConractInfoModel(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLoanCity() {
		return loanCity;
	}

	public void setLoanCity(String loanCity) {
		this.loanCity = loanCity;
	}
}
