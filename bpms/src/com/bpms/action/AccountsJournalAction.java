package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.AccountsJournal;
import com.bpms.service.AccountsJournalService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 财务详情Action
 * 
 * @author liuhh 2015/06/23
 */
@Namespace("/accountsJournal")
@Action(value = "accountsJournalAction")
public class AccountsJournalAction extends BaseAction implements
		ModelDriven<AccountsJournal> {

	private static final long serialVersionUID = 1L;

	private AccountsJournal accountsJournal = new AccountsJournal();

	@Autowired
	private AccountsJournalService acountJournalService;

	public void saveAccountsJournal() {
		// 修改/保存财务详情的信息
		boolean result = acountJournalService
				.persistenceAccountsJournal(accountsJournal);
		// 组织提示消息
		String message = result ? "财务信息保存成功!" : "财务信息保存失败!";
		// 返回前台
		OutputJson(new DataModel("财务详情", message, result, accountsJournal));
	}

	public void deleteAccountJournal() {
		// 删除财务详情的信息
		boolean result = acountJournalService
				.deleteAccountsJournal(accountsJournal.getBankFlowId());
		// 组织提示消息
		String message = result ? "财务信息删除成功!" : "财务信息删除失败!";
		// 返回前台
		OutputJson(new DataModel("财务详情", message, result));

	}

	@Override
	public AccountsJournal getModel() {
		return accountsJournal;
	}

}
