package com.oasys.service;

import java.util.List;

import com.oasys.model.AuditProcHis;
import com.oasys.model.StatusNameRef;
import com.oasys.model.VO.AuditProcHisModel;

/**
 * 审批流程履历表Service
 * @ClassName: AuditProcHisServiceImpl 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月24日 下午1:17:16
 */
public interface AuditProcHisService {
	
	/**
	 * 保存
	 * @Title: saveAuditProcHis 
	 * @Description: TODO
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年9月24日 下午1:18:39
	 * @throws
	 */
	boolean saveAuditProcHis(AuditProcHis auditProcHis);
	/**
	 * @author xujianwei
	 * @time:2015年9月29日 下午4:42:06
	 * @Title:findAllAuditProcHisList
	 * @Description:TODO（这里描述这个方法的作用）查看历史审批意见
	 * @param appNo 申请编号
	 * @return
	 * @throws:
	 */
	public List<AuditProcHisModel> findAllAuditProcHisList(String appNo); 
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月2日 下午3:40:15
	 * @Title:getModelByID
	 * @Description:TODO（这里描述这个方法的作用）根据id查询历史
	 * @param id
	 * @return
	 * @throws:
	 */
	public StatusNameRef getModelByID(int id);

}
