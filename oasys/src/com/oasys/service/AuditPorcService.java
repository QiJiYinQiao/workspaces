package com.oasys.service;

import java.util.List;

import com.oasys.model.AuditProcHis;


public interface AuditPorcService {
	//查看审批意见
	List<AuditProcHis> getAuditPorcList(String appNo);
	//获取审批意见
	boolean getApplyStatus(String appNo);
}
