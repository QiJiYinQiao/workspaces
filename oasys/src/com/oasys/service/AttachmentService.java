package com.oasys.service;

import java.util.List;

import com.oasys.model.Attachment;
import com.oasys.model.VO.AttachmentModel;

public interface AttachmentService {
	/**
	 * 持久化附件
	 */
	boolean persistenceAttachment(Attachment attachment);
	
	/**
	 * 根据用户的id和申请编号查询附件列表
	 * 
	 * @param userId
	 *            用户的id
	 * @param orderId
	 *            订单的id
	 * @return
	 */
	List<AttachmentModel> findAttachmentList(String userId, String appNo,String detailId);
	
	/**
	 * 
	 * @time:2015年10月12日 下午3:54:19
	 * @Title:findAttachmentByULA
	 * @Description:TODO（这里描述这个方法的作用）根据登陆用户，申请编号查询附件信息,订单ID
	 * @param userId
	 * @param loanOrderId
	 * @param isDetail
	 * @return
	 * @throws:
	 */
	List<AttachmentModel> findAttachmentByULA(String userId,String appNo, String isDetail,String detailId);
	/**
	 * 通过附件查询是否有相同申请
	 * 
	 * @param attachment
	 * @return
	 */
	Attachment findSameByNT(Attachment attachment);
	
	/**
	 * 根据附件的id获取附件信息
	 * 
	 * @param id
	 * @return
	 */
	Attachment findAttachmentById(String id);
	
	/**
	 * 根据ID删除附件
	 */
	boolean delAttachment(String id);
}
