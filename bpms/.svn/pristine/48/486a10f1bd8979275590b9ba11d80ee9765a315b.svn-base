package com.bpms.service;

import java.util.List;

import com.bpms.model.AuditInfoRecord;

/**
 * 稽核信息记录service
 * @author PANCHUANHE
 * @date 2016/6/16
 */
public interface AuditInfoRecordService {
    /**
     * 新增
     * @param a
     * @return
     */
	public boolean saveAuditInfoRecord(AuditInfoRecord a);
	
	/**
	 * 根据订单ID查询稽核记录
	 * @param id
	 * @return  AuditInfoRecord
	 */
	public List<AuditInfoRecord> findAIRByOid(String oid);
	
	/**
	 * 根据名称，订单ID，核查对象查询公司稽核信息
	 * @param name 公司名
	 * @param loanOrderId 订单ID
	 * @return
	 */
	public AuditInfoRecord findAuditByName(String name,String loanOrderId,String auditItem);
	
	/**
	 * 删除稽核信息
	 * @param air
	 * @return
	 */
	public boolean deleteAudit(AuditInfoRecord air);
	
}
