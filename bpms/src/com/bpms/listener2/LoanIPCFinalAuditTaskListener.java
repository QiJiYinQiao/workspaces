package com.bpms.listener2;

import com.bpms.listener.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * IPC终审活动的监听
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCFinalAuditTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCZHONGSHEN;
	}

}
