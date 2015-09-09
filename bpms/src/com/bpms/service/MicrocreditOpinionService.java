package com.bpms.service;

import com.bpms.model.MicrocreditOpinion;

/**
 * 微贷业务呈报意见表SERVICE
 * @author Sun
 *
 */
public interface MicrocreditOpinionService {
	/**
	 * 保存微贷业务呈报意见表
	 * @param mo
	 * @return
	 */
	public boolean saveMicrocreditOpinion(MicrocreditOpinion mo);
	/**
	 * 根据订单ID查询微带业务呈报意见
	 * @param loanOrderId
	 * @return
	 */
	public MicrocreditOpinion findMicrocreditOpinionByOid(String loanOrderId);
}
