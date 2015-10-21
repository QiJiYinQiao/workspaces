package com.bpms.listener;

import com.bpms.util.Constants;


/**
 * @Creater chenfl
 * @File_Name FinancingSalesCustDeptTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 财富业务流程"销客主管节点"任务监听器
 */
@SuppressWarnings("serial")
public class InvestSalesCustMgrTaskListener extends BaseTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_SALESCUSTMANAGER;
	}

}