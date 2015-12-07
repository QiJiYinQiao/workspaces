package com.bpms.listener.loan.settlement;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.ipc.LoanIPCDataTaskListener;
import com.bpms.listener.loan.letter.LoanDataPostTaskListener;
import com.bpms.util.Constants;

/**
 * 贷款结算子流程中数据专员对应的活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanSettlementShuJuZhuanYuanTaskListener implements TaskListener {

	// IPC数据岗
	private TaskListener loanIPCShuJuZhuanYuan = new LoanIPCDataTaskListener();

	// 信审的数据岗
	private TaskListener loanLetterShuJuZhuanYuan = new LoanDataPostTaskListener();

	@Override
	public void notify(DelegateTask task) {
		// 判断是什么业务调用的贷款结算子流程
		// 如果是IPC业务调用的贷款子流程,则使用IPC数据专员的数据岗节点.
		// 如果是信审业务调用的贷款结算子流程,则使用信审业务数据专员的数据岗节点.
		Object object = task.getVariables().get(Constants.PROCESS_KEY);
		if (null != object) {
			if (object.equals("LoanOrderIPC")) {
				loanIPCShuJuZhuanYuan.notify(task);
			} else if (object.equals("LoanOrderLetter")) {
				loanLetterShuJuZhuanYuan.notify(task);
			}
		}
	}
}
