package com.bpms.listener;

import com.bpms.util.Constants;

/**
 * @Creater chenfl
 * @File_Name ApprovalMgrTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 监听数据岗节点
 */
@SuppressWarnings("serial")
public class LoanDataPostTaskListener extends LoanIPCAuditChooseTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_DATAPOST;
	}

}
