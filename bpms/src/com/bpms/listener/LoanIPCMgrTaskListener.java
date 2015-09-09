package com.bpms.listener;

import com.bpms.util.Constants;

/**
 * @Creater chenfl
 * @File_Name IPCAuditTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 借款业务流程IPC审核节点任务监听器
 */
@SuppressWarnings("serial")
public class LoanIPCMgrTaskListener extends LoanInitialAuditTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCMGR;
	}

}
