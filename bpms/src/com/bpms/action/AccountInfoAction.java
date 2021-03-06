package com.bpms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.AccountInfo;
import com.bpms.model.LoanorderAndAccountinfo;
import com.bpms.service.AccountInfoService;
import com.bpms.service.LoanorderAndAccountinfoService;
import com.bpms.util.Collections;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 开户行Action
 * 
 * @author panchuanhe 2015/6/30
 */
@Namespace("/accountInfo")
@Action(value = "accountInfoAction")
public class AccountInfoAction extends BaseAction implements
		ModelDriven<AccountInfo> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private LoanorderAndAccountinfoService loanorderAndAccountinfoServiceImpl;
	// 开户信息
	private AccountInfo accountInfo;
	// 贷款人客户id
	private String loanerId;
	// 订单id
	private String loanOrderId;
	// ids
	private String accountIds;
	
	private String bankNames;
	private String accountNames;
	private String accountNums;
	// 区分编辑开户行信息,还是查看开户行信息的字段
	private String operate;

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	/**
	 * 保存/更新
	 * 
	 * @return
	 */
	public String saveAccountInfo() {
		accountInfo.setCusType("A");// 客户类型
		boolean bl = accountInfoService.saveAccountInfo(accountInfo);
		DataModel json = null;
		if (bl) {
			json = new DataModel("提示", "恭喜你,保存成功!", true);
		} else {
			json = new DataModel("提示", "出错了,保存失败!", false);
		}
		OutputJson(json);
		return null;
	}

	/**
	 * 编辑时，回显开户行列表,已与本订单绑定的开户行，设置为默认选中
	 * 
	 * @return
	 */
	public String findAllListChacked() {
		GridModel gridModel = new GridModel();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanOrderId",loanOrderId);
			param.put("cusId", loanerId);
			param.put("operate", operate);
			gridModel.setRows(accountInfoService.findAccountInfoList(param));
//			gridModel.setTotal(accountInfoService.findAccountInfoListCount(param));
			OutputJson(gridModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String doEditAccountInfo() {
		// 如果有id则查询关联表中是否已被其他订单所用，如果是，则不能修改
		if (StringUtils.isNotBlank(accountInfo.getAccountId())) {
			// 开户行id去关联表中查
			List<LoanorderAndAccountinfo> laaList = loanorderAndAccountinfoServiceImpl.findByAccountId(accountInfo.getAccountId());
			if(Collections.listIsNotEmpty(laaList)){
				for (LoanorderAndAccountinfo loanorderAndAccountinfo : laaList) {
					// 如果该紧急联系人被其订单绑定，则该紧急联系人不能被删除
					if (!loanOrderId.equals(loanorderAndAccountinfo.getLoanOrderId())) {
						OutputJson(new DataModel("提示", "该开户行信息已被其他订单绑定不能修改,修改失败!", false));
						return null;
					}
				}
			}
			OutputJson(new DataModel("提示","该开户行信息未被其他订单绑定!",true));
			return null;
		}
		OutputJson(new DataModel("提示","没有选择要编辑开户行信息!",false));
		return null;
	}

	/**
	 * 根据id删除
	 * 
	 * @return
	 */
	public String doDeleteById() {
		if (StringUtils.isNotBlank(accountIds)) {
			String[] ids = accountIds.split(",");
			for (String accountId : ids) {
				// 开户行id去关联表中查
				List<LoanorderAndAccountinfo> laaList = loanorderAndAccountinfoServiceImpl
						.findByAccountId(accountId);
				for (LoanorderAndAccountinfo loanorderAndAccountinfo : laaList) {
					// 如果该紧急联系人被其订单绑定，则该紧急联系人不能被删除
					if (!loanOrderId.equals(loanorderAndAccountinfo.getLoanOrderId())) {
						OutputJson(new DataModel("提示", "该开户行信息已被其他订单绑定不能删除,删除失败!", false));
						return null;
					} else {
						// 解除本订单与该开户行的关系
						loanorderAndAccountinfoServiceImpl.deleteByAccountIdAndLoanOrderId(
										loanorderAndAccountinfo.getAccountId(),
										loanorderAndAccountinfo.getLoanOrderId());
					}
				}
				accountInfoService.deleteById(accountId);
				OutputJson(new DataModel("提示", "删除成功.", true));
				return null;
			}
		}
		OutputJson(new DataModel("提示", "没有选择要删除的开户行信息!", false));
		return null;
	}

	/**
	 * 保存开户行与订单的关系
	 * 
	 * @return
	 */
	public String saveAccountInfoActionLoanOrder() {
		try {
			// 在新建关系
			if (StringUtils.isNotBlank(accountIds)) {
				String[] ids = accountIds.split(",");
				loanorderAndAccountinfoServiceImpl.saveloanorderAndAccountinfo(loanOrderId, ids);
				OutputJson(new DataModel("提示", "保存成功.",true));
				return null;
			}else{
				OutputJson(new DataModel("提示","没有选择要保存的开户行信息!",false));
			}
		} catch (Exception e) {
			OutputJson(new DataModel("提示","保存失败",false));
		}
		return null;
	}
	
	/**
	 * 修改银行账户信息
	 */
	public void updateAccountInfo() {
		boolean b = false;
		String msg = "修改失败";
		if (StringUtils.isNotBlank(accountIds)) {
			String[] accountId = accountIds.split(",");
			String[] bankName = bankNames.split(",");
			String[] accountName = accountNames.split(",");
			String[] accountNum = accountNums.split(",");
			for (int i = 0 ; i < accountId.length; i++) {
				if (StringUtils.isNotBlank(accountId[i])) {
					AccountInfo ai = accountInfoService.findById(accountId[i].trim());
					ai.setBankName(bankName[i].trim());
					ai.setAccountName(accountName[i].trim());
					ai.setAccountNum(accountNum[i].trim());
					b = accountInfoService.saveAccountInfo(ai);
				}
			}
		}
		if (b) {
			msg = "修改成功";
		}
		OutputJson(new DataModel("提示", msg, b));
	}
	
	public void hasICBCAccount() {
		OutputJson(accountInfoService.hasICBCAccount(loanerId,loanOrderId));
	}

	@Override
	public AccountInfo getModel() {
		if (null == accountInfo) {
			accountInfo = new AccountInfo();
		}
		return accountInfo;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
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

	public String getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(String accountIds) {
		this.accountIds = accountIds;
	}

	public String getBankNames() {
		return bankNames;
	}

	public void setBankNames(String bankNames) {
		this.bankNames = bankNames;
	}

	public String getAccountNames() {
		return accountNames;
	}

	public void setAccountNames(String accountNames) {
		this.accountNames = accountNames;
	}

	public String getAccountNums() {
		return accountNums;
	}

	public void setAccountNums(String accountNums) {
		this.accountNums = accountNums;
	}

}
